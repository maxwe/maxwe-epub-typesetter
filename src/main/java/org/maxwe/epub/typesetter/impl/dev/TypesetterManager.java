package org.maxwe.epub.typesetter.impl.dev;

import org.maxwe.epub.parser.EPubParser;
import org.maxwe.epub.parser.core.INavigation;
import org.maxwe.epub.parser.impl.Content;
import org.maxwe.epub.typesetter.core.IChapter;
import org.maxwe.epub.typesetter.core.IConfigure;
import org.maxwe.epub.typesetter.core.IPage;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2016-03-07 15:07.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 异步加载页码
 */
public class TypesetterManager {

    private final int STOCK_CLIQUE = 25;
    private PageScrolledStatus previousStatus = PageScrolledStatus.next;
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
        this.buildPages();
    }

    public IPage getCurrentPage() {
        return currentPage;
    }

    public LinkedList<IPage> getPage(PageScrolledStatus flag) {
        LinkedList<IPage> pages = new LinkedList();
        if (flag == PageScrolledStatus.current) {
            pages.add(0,this.previousPageQueue.size() < 1 ? null:this.previousPageQueue.getLast());
            pages.add(1,this.currentPage);
            pages.add(2,this.nextPageQueue.size() <1 ? null:this.nextPageQueue.getFirst());
        } else if (flag == PageScrolledStatus.previous) {
            this.nextPageQueue.addFirst(this.currentPage);
            this.currentPage = this.previousPageQueue.pollLast();
            pages.add(this.previousPageQueue.getLast());
//            if (previousStatus == PageScrolledStatus.next) {
//
//            } else if (previousStatus == PageScrolledStatus.previous) {
//                this.nextPageQueue.addFirst(this.currentPage);
//                this.currentPage = this.previousPageQueue.pollLast();
//                pages.add(this.previousPageQueue.size() < 1 ? null:this.previousPageQueue.getLast());
//            }
            this.previousStatus = flag;
        } else if (flag == PageScrolledStatus.next) {
            this.previousPageQueue.addLast(this.currentPage);
            this.currentPage = this.nextPageQueue.pollFirst();
            pages.add(this.nextPageQueue.size() < 1 ? null:this.nextPageQueue.getFirst());
//            if (previousStatus == PageScrolledStatus.next) {
//            } else if (previousStatus == PageScrolledStatus.previous) {
//                this.nextPageQueue.addFirst(this.currentPage);
//                this.currentPage = this.previousPageQueue.pollLast();
//                pages.add(this.previousPageQueue.getLast());
//            }
            this.previousStatus = flag;
        }

        this.buildPages();
        return pages;
    }

    private void buildPages() {
        new Thread(new Runnable() {
            public void run() {
                synchronized (previousPageQueue) {
                    if (previousPageQueue.size() <= STOCK_CLIQUE) {
                        IPage first = previousPageQueue.size() < 1 ? null : previousPageQueue.getFirst();
                        if (first != null){
                            int chapterIndex = first.getChapterIndex() - 1;
                            String path = indexToPath.get(chapterIndex);
                            if (path != null) {
                                LinkedList<IPage> iPages = buildPages(path, chapterIndex);
                                for (IPage page : iPages) {
                                    previousPageQueue.addFirst(page);
                                }
                            }
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                synchronized (nextPageQueue) {
                    if (nextPageQueue.size() <= STOCK_CLIQUE) {
                        IPage last = nextPageQueue.size() < 1 ? null : nextPageQueue.getLast();
                        if (last == null){
                            last = currentPage;
                        }
                        if (last != null){
                            int chapterIndex = last.getChapterIndex() + 1;
                            String path = indexToPath.get(chapterIndex);
                            if (path != null) {
                                nextPageQueue.addAll(buildPages(path, chapterIndex));
                            }
                        }
                    }
                }
            }
        }).start();
    }

    private void initPages() {
        int chapterIndex = this.configure.getChapterIndex();
        String previousPath = this.indexToPath.get(chapterIndex - 1);
        String nextPath = this.indexToPath.get(chapterIndex);
        if (previousPath != null) {
            this.previousPageQueue.addAll(buildPages(previousPath, chapterIndex - 1));
        }

        if (nextPath != null) {
            this.nextPageQueue.addAll(buildPages(nextPath, chapterIndex));
        }

        for (IPage iPage : this.nextPageQueue) {
            if (iPage.getStartParagraphIndexInChapter() == this.configure.getParagraphIndex()
                    && iPage.getStartSectionIndexInParagraph() == this.configure.getSectionIndex()
                    && iPage.getStartMetaIndexInSection() == this.configure.getMetaIndex()) {
                this.currentPage = this.nextPageQueue.pollFirst();
            } else {
                this.previousPageQueue.addLast(this.nextPageQueue.pollFirst());
            }
        }
        this.buildPages();
    }

    private LinkedList<IPage> buildPages(String path, int chapterIndex) {
        try {
            org.maxwe.epub.parser.core.IChapter parserChapter = new org.maxwe.epub.parser.impl.Chapter(path, chapterIndex);
            Configure configure = new Configure(0, 0, 0, 0);
            IChapter chapter = new Chapter(parserChapter, configure, 100, 100, 1440 - 100, 2304 - 100).typeset();
            return chapter.getPages();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.buildPages();
        return null;
    }


    public enum PageScrolledStatus {
        previous, current, next
    }
}
