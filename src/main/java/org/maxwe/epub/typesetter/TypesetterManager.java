package org.maxwe.epub.typesetter;

import org.maxwe.epub.parser.core.IChapter;
import org.maxwe.epub.typesetter.core.IPageTypesetter;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-09-07 13:31.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class TypesetterManager {
    private static TypesetterManager typesetterManager;
    private TypesetterManager(){}

    public static TypesetterManager getInstance(){
        if (typesetterManager == null){
            synchronized (TypesetterManager.class){
                if (typesetterManager == null){
                    typesetterManager = new TypesetterManager();
                }
            }
        }
        return typesetterManager;
    }

    /**
     * 向后排版的页面集
     */
    private LinkedList<IPageTypesetter> nextPages = new LinkedList<IPageTypesetter>();

    /**
     * 向前排版的页面集
     */
    private LinkedList<IPageTypesetter> previousPages = new LinkedList<IPageTypesetter>();

    /**
     * 向后排版的最大缓存数量
     */
    private static final int MAX_NEXT_PAGE_COUNTER = 5;

    /**
     * 向前排版的最大缓存数量
     */
    private static final int MAX_PREVIOUS_PAGE_COUNTER = 5;

    private int screenWidth = 1080;
    private int screenHeight = 1920;

    private Thread nextPageThread = new Thread();
    private Thread previousPageThread = new Thread();
    private Thread pageNumberThread = new Thread();

    public LinkedList<IPageTypesetter> chapterTypesetter(IChapter chapter){
        return null;
    }

    public void calculatorPageNumber(){

    }
}
