package org.maxwe.epub.typesetter.impl.dev;

import org.maxwe.epub.parser.EPubParser;
import org.maxwe.epub.parser.core.INavigation;
import org.maxwe.epub.parser.impl.Content;
import org.maxwe.epub.typesetter.core.IChapter;
import org.maxwe.epub.typesetter.core.IConfigure;
import org.maxwe.epub.typesetter.core.IPage;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pengwei Ding on 2016-03-07 15:07.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 异步加载页码
 * TODO
 * 添加清理多余缓存页面-已解决
 * 添加进度记录与重现
 * 添加异步累计与通知方式
 * 调整缓冲页面数量
 * 添加重拍版
 * 可配置屏幕信息
 */
public class TypesetterManager {
    private ExecutorService previousExecutorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(1), new ThreadPoolExecutor.DiscardOldestPolicy());
    private ExecutorService nextExecutorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(1), new ThreadPoolExecutor.DiscardOldestPolicy());

    private final int STOCK_CLIQUE = 20;
    private LinkedList<IPage> previousPageQueue = new LinkedList();
    private IPage currentPage;
    private LinkedList<IPage> nextPageQueue = new LinkedList();


    private Content content;
    private IConfigure configure;
    private LinkedHashMap<Integer, String> indexToPath = new LinkedHashMap<Integer, String>();

    public TypesetterManager(String bookDir, IConfigure configure) {
        try {
            EPubParser ePubParser = new EPubParser(bookDir);
            this.content = ePubParser.getContent();
            this.configure = configure;
            LinkedList<INavigation> navigation = this.content.getNavigation();
            for (int index = 0; index < navigation.size(); index++) {
                indexToPath.put(index, navigation.get(index).getHref());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.initPages();
    }

    public IPage getCurrentPage() {
        return currentPage;
    }

    public LinkedList<IPage> getPage(PageScrolledStatus flag) {
        LinkedList<IPage> pages = new LinkedList();
        if (flag == PageScrolledStatus.current) {
            pages.add(0, this.previousPageQueue.size() < 1 ? null : this.previousPageQueue.getLast());
            pages.add(1, this.currentPage);
            pages.add(2, this.nextPageQueue.size() < 1 ? null : this.nextPageQueue.getFirst());
        } else if (flag == PageScrolledStatus.previous) {
            this.nextPageQueue.addFirst(this.currentPage);
            this.currentPage = this.previousPageQueue.size() < 1 ? null : this.previousPageQueue.pollLast();
            pages.add(this.previousPageQueue.size() < 1 ? null : this.previousPageQueue.getLast());
        } else if (flag == PageScrolledStatus.next) {
            this.previousPageQueue.addLast(this.currentPage);
            this.currentPage = this.nextPageQueue.size() < 1 ? null : this.nextPageQueue.pollFirst();
            pages.add(this.nextPageQueue.size() < 1 ? null : this.nextPageQueue.getFirst());
        }

        this.buildPagesTask();
        return pages;
    }

    private void buildPagesTask() {
        this.previousExecutorService.execute(new Runnable() {
            public void run() {
                if (previousPageQueue.size() <= STOCK_CLIQUE) {
                    /**
                     * 如果previousPageQueue的长度小于或等于@{STOCK_CLIQUE}就在队列最前端取一页
                     * 根据取出的页码获取上一个章节的信息
                     * 排版上一个章节出来的结果倒序插入到对列
                     */
                    IPage first = previousPageQueue.size() < 1 ? null : previousPageQueue.getFirst();
                    if (first != null) {
                        int chapterIndex = first.getChapterIndex() - 1;
                        String path = indexToPath.get(chapterIndex);
                        if (path != null) {
                            LinkedList<IPage> iPages = buildPages(path, chapterIndex);
                            for (int index = iPages.size() - 1; index >= 0; index--) {
                                previousPageQueue.addFirst(iPages.get(index));
                            }
                        }
                    }
                } else {
                    /**
                     * 删除多余的前页
                     * 从后向前遍历队列
                     * 如果@{IPage.chapterIndex}在@{STOCK_CLIQUE}之外发生变化则删除
                     */
                    LinkedList<IPage> iPageRemove = new LinkedList<IPage>();
                    int size = previousPageQueue.size();
                    boolean isMarked = false;
                    IPage iPageRemoveMark = null;
                    for (int iPageRemoveIndex = size - 1; iPageRemoveIndex >= 0; iPageRemoveIndex--) {
                        IPage iPage = previousPageQueue.get(iPageRemoveIndex);
                        if (isMarked) {
                            iPageRemove.add(iPage);
                        } else {
                            if (iPageRemoveMark != null && iPage.getChapterIndex() != iPageRemoveMark.getChapterIndex() && (size - iPageRemoveIndex) > STOCK_CLIQUE) {
                                iPageRemove.add(iPage);
                                isMarked = true;
                            } else {
                                iPageRemoveMark = iPage;
                            }
                        }
                    }
                    /**
                     * TODO 线程问题
                     * 监测在翻页过程中iPageRemoveIndex是否是会整体变化
                     */
                    for (IPage iPage : iPageRemove) {
                        previousPageQueue.remove(iPage);
                    }
                }
            }
        });

        this.nextExecutorService.execute(new Runnable() {
            public void run() {
                if (nextPageQueue.size() <= STOCK_CLIQUE) {
                    IPage last = nextPageQueue.size() < 1 ? null : nextPageQueue.getLast();
                    if (last == null) {
                        last = currentPage;
                    }
                    if (last != null) {
                        int chapterIndex = last.getChapterIndex() + 1;
                        String path = indexToPath.get(chapterIndex);
                        if (path != null) {
                            nextPageQueue.addAll(buildPages(path, chapterIndex));
                        }
                    }
                } else {
                    /**
                     * 删除多余的
                     */
                    LinkedList<IPage> iPageRemove = new LinkedList<IPage>();
                    IPage iPageRemoveMark = null;
                    boolean isMarked = false;
                    for (int iPageRemoveIndex = 0; iPageRemoveIndex < nextPageQueue.size(); iPageRemoveIndex++) {
                        /**
                         * TODO 线程同步问题
                         * 下标问题会随着翻页发生
                         */
                        IPage iPage = nextPageQueue.get(iPageRemoveIndex);
                        if (isMarked) {
                            iPageRemove.add(nextPageQueue.get(iPageRemoveIndex));
                        } else {
                            if (iPageRemoveMark != null && iPage.getChapterIndex() != iPageRemoveMark.getChapterIndex() && iPageRemoveIndex > STOCK_CLIQUE) {
                                iPageRemove.add(nextPageQueue.get(iPageRemoveIndex));
                                isMarked = true;
                            } else {
                                iPageRemoveMark = iPage;
                            }
                        }
                    }

                    for (IPage iPage : iPageRemove) {
                        nextPageQueue.remove(iPage);
                    }
                }
            }
        });
    }

    /**
     * 初始化
     * 填充缓冲区
     * 仅仅能被初始化子线程调用
     */
    private void initPages() {
        int chapterIndex = this.configure.getChapterIndex();
        /**
         * 偏移计数器
         */
        int counter = 0;
        do {
            /**
             * 由于configure设置的是当前章节的ID，向前偏移先减去1
             */
            counter --;
            String previousPath = this.indexToPath.get(chapterIndex + counter);
            if (previousPath != null) {
                this.previousPageQueue.addAll(0, buildPages(previousPath, chapterIndex + counter));
            }
        } while (this.previousPageQueue.size() < STOCK_CLIQUE && chapterIndex + counter >= 0);

        /**
         * 重置偏移计数器
         */
        counter = 0;

        do {
            String nextPath = this.indexToPath.get(chapterIndex + counter);
            if (nextPath != null) {
                this.nextPageQueue.addAll(buildPages(nextPath, chapterIndex + counter));
            }
            /**
             * 由于configure设置的是当前章节的ID，向后偏移后加1
             */
            counter++;
        } while (this.nextPageQueue.size() < STOCK_CLIQUE && chapterIndex + counter < this.indexToPath.size());

        /**
         * 缓冲区满
         * 设置前页，当前页，后页指向
         */
        for (IPage iPage : this.nextPageQueue) {
            if (iPage.getStartParagraphIndexInChapter() == this.configure.getParagraphIndex()
                    && iPage.getStartSectionIndexInParagraph() == this.configure.getSectionIndex()
                    && iPage.getStartMetaIndexInSection() == this.configure.getMetaIndex()) {
                this.currentPage = this.nextPageQueue.pollFirst();
                break;
            } else {
                this.previousPageQueue.addLast(this.nextPageQueue.pollFirst());
            }
        }
    }

    private LinkedList<IPage> buildPages(String path, int chapterIndex) {
        try {
            org.maxwe.epub.parser.core.IChapter parserChapter = new org.maxwe.epub.parser.impl.Chapter(path, chapterIndex);
            IChapter chapter = new Chapter(parserChapter, this.configure).typeset();
            LinkedList<IPage> pages = chapter.getPages();
            System.out.println("本次排版章节ID = " + chapterIndex + " ,章节名称 = " + pages.get(0).getChapterName() + " ,页码数量：" + pages.size());
            return pages;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public enum PageScrolledStatus {
        previous, current, next
    }
}
