package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.parser.core.IChapter;
import org.maxwe.epub.parser.core.INavigation;
import org.maxwe.epub.parser.core.IParagraph;
import org.maxwe.epub.parser.impl.Book;
import org.maxwe.epub.typesetter.constant.LayoutStyle;
import org.maxwe.epub.typesetter.core.IPage;
import org.maxwe.epub.typesetter.core.ISection;
import org.maxwe.epub.typesetter.core.ITypesetter;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-09-05 16:04.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class DefaultTypesetter implements ITypesetter {
    private static DefaultTypesetter defaultTypesetter;
    private DefaultTypesetter(){}
    public static DefaultTypesetter getInstance(){
        if (defaultTypesetter == null){
            synchronized (DefaultTypesetter.class){
                if (defaultTypesetter == null){
                    defaultTypesetter = new DefaultTypesetter();
                }
            }
        }
        return defaultTypesetter;
    }


    /**
     * 向后排版的页面集
     */
    private LinkedList<IPage> nextPages = new LinkedList<IPage>();

    /**
     * 向前排版的页面集
     */
    private LinkedList<IPage> previousPages = new LinkedList<IPage>();

    /**
     * 向后排版的最大缓存数量
     */
    private static final int MAX_NEXT_PAGE_COUNTER = 5;

    /**
     * 向前排版的最大缓存数量
     */
    private static final int MAX_PREVIOUS_PAGE_COUNTER = 5;

    private String filePath;
    private int screenWidth = 1080;
    private int screenHeight = 1920;
    private Object typeface;
    private int fontSize;
    private LayoutStyle layoutStyle = LayoutStyle.Transverse_Left_Right;

    public void setFilePath(String filePath){
        this.filePath = filePath;
    }

    public void setScreen(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;
    }

    public void setTypeface(Object typeface) {

    }

    public void setFontSize(int size) {

    }

    public void setLayoutStyle(LayoutStyle layoutStyle) {

    }

    public void calculatorPageNumber(PageNumberCalculatorListener pageNumberCalculatorListener) {

    }

    public void setPageNumberCalculatorListener(PageNumberCalculatorListener pageNumberCalculatorListener) {

    }

    public void setTypesetterListener(TypesetterListener typesetterListener) {

    }

    public void scheduledTypesetter() {

    }

    public void typeset(TypesetterListener typesetterListener) {

    }

    public void typeset(INavigation navigation, TypesetterListener typesetterListener) {

    }

    public void typeset(INavigation navigation, IParagraph paragraph, TypesetterListener typesetterListener) {

    }

    public void typeset(INavigation navigation, IParagraph paragraph, ISection section, TypesetterListener typesetterListener) {

    }

    public void typeset(INavigation navigation, IParagraph paragraph, ISection section, int offset, TypesetterListener typesetterListener) {

    }

    public void typeset(INavigation navigation, IParagraph paragraph, ISection section, int offset, int textOffset, TypesetterListener typesetterListener) {

    }

    public IPage getCurrentPage() {
        return null;
    }

    public IPage getNextPage() {
        return null;
    }

    public IPage getPreviousPage() {
        return null;
    }

    public int getCurrentPageNumber() {
        return 0;
    }

    public int getTotalPageNumber() {
        return 0;
    }



    /**
     * 设置三条排版线程
     * 向前排版线程
     * 向后排版线程
     * 计算页码线程
     */
    private Thread nextPageThread = new Thread();
    private Thread previousPageThread = new Thread();
    private Thread pageNumberThread = new Thread();

    private class CalculatorPageNumberTask implements Runnable{
        private PageNumberCalculatorListener pageNumberCalculatorListener;
        public CalculatorPageNumberTask(PageNumberCalculatorListener pageNumberCalculatorListener){
            this.pageNumberCalculatorListener = pageNumberCalculatorListener;
        }

        public void run() {
            try {
                this.pageNumberCalculatorListener.onStart();
                Book book = new Book(filePath);
                LinkedList<INavigation> navigations = book.getNavigations();
                for (INavigation navigation:navigations){
                    IChapter iChapter = book.navigateTo(navigation);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
