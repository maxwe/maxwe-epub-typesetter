package org.maxwe.epub.typesetter;

import org.maxwe.epub.parser.core.IBook;
import org.maxwe.epub.typesetter.core.APageTypesetter;
import org.maxwe.epub.typesetter.core.IChapterTypesetter;
import org.maxwe.epub.typesetter.core.IPageNumberCalculatorListener;
import org.maxwe.epub.typesetter.core.ITypesetterListener;
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
public class BookTypesetter implements IBookTypesetter{
    private IBook book;
    public BookTypesetter(IBook book){
        this.book = book;
    }

    private int LIMIT = 10;

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
        this.typesetterListener = typesetterListener;
    }

    public void setPageNumberCalculatorListener(IPageNumberCalculatorListener pageNumberCalculatorListener) {
        this.pageNumberCalculatorListener = pageNumberCalculatorListener;
    }



    /**
     * 向后翻页
     * @return
     */
    public APageTypesetter getNextPage(){
        /**
         * 首先将当前页面加入到向前翻页的页面集合的结尾未知
         */
        if (this.currentPageTypesetter != null){
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
     * @return
     */
    public APageTypesetter getPreviousPage() {
        /**
         * 首先将当前页面加入到向后翻页的页面集合中的首页位置
         */
        if (this.currentPageTypesetter != null){
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
    private void scheduler(){
        Future<?> submit = executorService.submit(new Runnable() {
            public void run() {

            }
        });



        if (this.nextPageTypesetters.isEmpty() || this.nextPageTypesetters.size() < LIMIT){

        }
    }

    /**
     * 按照章节排版
     * @param chapterTypesetter
     * @param typesetterListener
     */
    public void typeset(IChapterTypesetter chapterTypesetter,ITypesetterListener typesetterListener){
        if (typesetterListener != null){
            this.typesetterListener = typesetterListener;
        }
        this.typesetterListener.onStart();
        try {
            /**
             * 段落不止
             * 排版不息
             */
            while (chapterTypesetter.getParagraphOffset() < chapterTypesetter.getChapter().getParagraphLength()){
                APageTypesetter pageTypesetter = new PageTypesetter(0,0,this.screenWidth,this.screenHeight);
                pageTypesetter.typeset(chapterTypesetter);
                this.nextPageTypesetters.add(pageTypesetter);
                this.typesetterListener.onProgress(pageTypesetter);
            }
            this.typesetterListener.onFinish();
        }catch (Exception e){
            this.typesetterListener.onError(e);
        }
    }


    private class PageTypesetterTask implements Runnable{
        public void run() {

        }
    }

}
