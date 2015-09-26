package org.maxwe.epub.typesetter;

import org.maxwe.epub.parser.core.IBook;
import org.maxwe.epub.typesetter.core.APageTypesetter;
import org.maxwe.epub.typesetter.core.IChapterTypesetter;
import org.maxwe.epub.typesetter.core.IPageNumberCalculatorListener;
import org.maxwe.epub.typesetter.core.ITypesetterListener;
import org.maxwe.epub.typesetter.impl.ChapterTypesetter;

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
     * 向后的章节集合
     * 该集合中如果章节的总页数超出阀值则不会有新的章节进入
     */
    private LinkedList<IChapterTypesetter> chapterTypesetters = new LinkedList<IChapterTypesetter>();

    /**
     * 搜索页面集
     */
    private LinkedList<APageTypesetter> searchpageTypesetters = new LinkedList<APageTypesetter>();


    private int screenWidth = 320;
    private int screenHeight = 480;

    private int indexOfReadChapter;

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
        IChapterTypesetter chapterTypesetter = this.chapterTypesetters.get(this.indexOfReadChapter);
        if (!chapterTypesetter.hasExcess()){
            this.indexOfReadChapter ++;
        }
        return this.chapterTypesetters.get(this.indexOfReadChapter).getNextPage();
    }

    /**
     * 向前翻页
     *
     * @return
     */
    public APageTypesetter getPreviousPage() {
        this.scheduler();
        IChapterTypesetter chapterTypesetter = this.chapterTypesetters.get(this.indexOfReadChapter);
        if (!chapterTypesetter.hasExcess()){
            this.indexOfReadChapter --;
        }
        return this.chapterTypesetters.get(this.indexOfReadChapter).getPreviousPage();
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
    private int indexOfTypesetChapter = 0;
    private int indexOfPrevious = 0;

    private Thread thread;

    private void scheduler() {
        /**
         * 向后排版任务
         */
        int pageOfNextChapterTypesetter = 0;
        for (int i=this.indexOfReadChapter;i<this.chapterTypesetters.size();i++){
            pageOfNextChapterTypesetter = pageOfNextChapterTypesetter + this.chapterTypesetters.get(i).getExcessPagesSize();
        }
        if (pageOfNextChapterTypesetter < this.LIMIT) {
            /**
             * 满足向后排版条件
             */
            if (this.nextPageTypesetTask == null) {
                /**
                 * 满足当前工作线程状态
                 */
                if (this.indexOfTypesetChapter < this.book.getNavigations().size()){
                    try {
                        this.nextPageTypesetTask = this.executorService.submit(new PageTypesetterTask(new ChapterTypesetter(this.book.getNavigation(this.indexOfTypesetChapter)),this.typesetterListener));
                        this.indexOfTypesetChapter ++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

//        /**
//         * 向前排版任务
//         */
//        if (this.previousPageTypesetters.isEmpty() || this.previousPageTypesetters.size() < this.LIMIT) {
//            /**
//             * 满足向前排版条件
//             */
//            if (this.previousPageTypesetTask == null) {
//                /**
//                 * 满足当前工作线程状态
//                 */
//                //this.previousPageTypesetTask = this.executorService.submit(new PageTypesetterTask());
//            }
//        }
    }

    private class PageTypesetterTask implements Runnable {
        private IChapterTypesetter chapterTypesetter;
        private ITypesetterListener typesetterListener;
        public PageTypesetterTask(IChapterTypesetter chapterTypesetter, ITypesetterListener typesetterListener){
            this.chapterTypesetter = chapterTypesetter;
            this.typesetterListener = typesetterListener;
        }

        public void run() {
            chapterTypesetters.add(chapterTypesetter.typeset(screenWidth,screenHeight));
            nextPageTypesetTask = null;
            scheduler();
        }
    }

}
