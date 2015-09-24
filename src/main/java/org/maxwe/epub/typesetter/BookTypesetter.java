package org.maxwe.epub.typesetter;

import org.maxwe.epub.parser.core.IBook;
import org.maxwe.epub.typesetter.core.APageTypesetter;
import org.maxwe.epub.typesetter.core.IChapterTypesetter;
import org.maxwe.epub.typesetter.core.IPageNumberCalculatorListener;
import org.maxwe.epub.typesetter.core.ITypesetterListener;
import org.maxwe.epub.typesetter.impl.ChapterTypesetter;
import org.maxwe.epub.typesetter.impl.PageTypesetter;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Pengwei Ding on 2015-09-07 13:31.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class BookTypesetter implements IBookTypesetter {
    private IBook book;

    public BookTypesetter(IBook book) {
        this.book = book;
        this.scheduler();
    }

    private final int LIMIT = 10;

    /**
     * 向后翻页的页面集合
     */
    private LinkedList<APageTypesetter> nextPageTypesetters = new LinkedList<APageTypesetter>();

    /**
     * 当前页面的引用
     */
    private APageTypesetter currentPageTypesetter;

    /**
     * 向前翻页的页面集合
     */
    private LinkedList<APageTypesetter> previousPageTypesetters = new LinkedList<APageTypesetter>();

    /**
     * 搜索页面集
     */
    private LinkedList<APageTypesetter> searchpageTypesetters = new LinkedList<APageTypesetter>();


    private int screenWidth = 320;
    private int screenHeight = 480;

    /**
     * 排版监听
     */
    private ITypesetterListener typesetterListener = new ITypesetterListener() {
        public void onStart() {

        }

        public void onProgress(APageTypesetter pageTypesetter) {

        }

        public void onFinish() {

        }

        public void onError(Exception exception) {

        }
    };

    /**
     * 页码计算监听
     */
    private IPageNumberCalculatorListener pageNumberCalculatorListener = new IPageNumberCalculatorListener() {
        public void onStart() {

        }

        public void onProgress(int current) {

        }

        public void onFinish(int total) {

        }

        public void onError(int current, Exception exception) {

        }
    };

    public void setTypesetterListener(ITypesetterListener typesetterListener) {
        if (typesetterListener != null) {
            this.typesetterListener = typesetterListener;
        }
    }

    public void setPageNumberCalculatorListener(IPageNumberCalculatorListener pageNumberCalculatorListener) {
        if (pageNumberCalculatorListener != null) {
            this.pageNumberCalculatorListener = pageNumberCalculatorListener;
        }
    }


    /**
     * 向后翻页
     *
     * @return
     */
    public APageTypesetter getNextPage() {
        this.scheduler();
        /**
         * 首先将当前页面加入到向前翻页的页面集合的结尾未知
         */
        if (this.currentPageTypesetter != null) {
            this.previousPageTypesetters.addLast(this.currentPageTypesetter);
        }

        /**
         * 然后在向后翻页的页面集合中取出第一页赋值给当前页面
         */
        this.currentPageTypesetter = this.nextPageTypesetters.pollFirst();
        return this.currentPageTypesetter;
    }

    /**
     * 向前翻页
     *
     * @return
     */
    public APageTypesetter getPreviousPage() {
        this.scheduler();
        /**
         * 首先将当前页面加入到向后翻页的页面集合中的首页位置
         */
        if (this.currentPageTypesetter != null) {
            this.nextPageTypesetters.addFirst(this.currentPageTypesetter);
        }

        /**
         * 然后在向后翻页的页面集合中取出最后一页赋值给当前页面
         */
        this.currentPageTypesetter = this.previousPageTypesetters.pollLast();
        return this.currentPageTypesetter;
    }

    public void setFont(Object font) {

    }

    public void setFontSize(int size) {

    }

    public void search(String query) {

    }


    private ExecutorService executorService = Executors.newFixedThreadPool(4);
    private Future<?> nextPageTypesetTask;
    private Future<?> previousPageTypesetTask;
    private Future<?> pageNumberTypesetTask;
    private Future<?> searchTypesetTask;
    private int indexOfNextChapter = 0;
    private int indexOfPrevious = 0;

    private Thread thread;

    private void scheduler() {
        /**
         * 向后排版任务
         */
        if (this.nextPageTypesetters.isEmpty() || this.nextPageTypesetters.size() < this.LIMIT) {
            /**
             * 满足向后排版条件
             */
            if (this.nextPageTypesetTask == null) {
                /**
                 * 满足当前工作线程状态
                 */
                if (this.indexOfNextChapter < this.book.getNavigations().size()){
                    try {
                        this.nextPageTypesetTask = this.executorService.submit(new PageTypesetterTask(true,new ChapterTypesetter(this.book.getNavigation(this.indexOfNextChapter)),this.typesetterListener));
                        this.indexOfNextChapter ++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /**
         * 向前排版任务
         */
        if (this.previousPageTypesetters.isEmpty() || this.previousPageTypesetters.size() < this.LIMIT) {
            /**
             * 满足向前排版条件
             */
            if (this.previousPageTypesetTask == null) {
                /**
                 * 满足当前工作线程状态
                 */
                //this.previousPageTypesetTask = this.executorService.submit(new PageTypesetterTask());
            }
        }
    }

    private class PageTypesetterTask implements Runnable {
        private boolean nextPageTypesetter;
        private IChapterTypesetter chapterTypesetter;
        private ITypesetterListener typesetterListener;
        public PageTypesetterTask(boolean nextPageTypesetter,IChapterTypesetter chapterTypesetter, ITypesetterListener typesetterListener){
            this.nextPageTypesetter = nextPageTypesetter;
            this.chapterTypesetter = chapterTypesetter;
            this.typesetterListener = typesetterListener;
        }

        public void run() {
            this.typesetterListener.onStart();
            try {
                /**
                 * 段落不止
                 * 排版不息
                 */
                int index = 0;
                while (chapterTypesetter.getParagraphOffset() < chapterTypesetter.getChapter().getParagraphLength()) {
                    APageTypesetter pageTypesetter = new PageTypesetter(0, 0, screenWidth, screenHeight);
                    pageTypesetter.typeset(chapterTypesetter);
                    if (this.nextPageTypesetter){
                        nextPageTypesetters.add(pageTypesetter);
                    }else{
                        previousPageTypesetters.add(index,pageTypesetter);
                        index ++;
                    }
                    this.typesetterListener.onProgress(pageTypesetter);
                }
                this.typesetterListener.onFinish();
                nextPageTypesetTask = null;
                scheduler();
            } catch (Exception e) {
                this.typesetterListener.onError(e);
            }
        }
    }

}
