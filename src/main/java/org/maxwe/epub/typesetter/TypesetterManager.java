package org.maxwe.epub.typesetter;

import org.maxwe.epub.typesetter.core.IChapterTypesetter;
import org.maxwe.epub.typesetter.core.APageTypesetter;
import org.maxwe.epub.typesetter.impl.PageTypesetter;

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
     * 页面集
     */
    private LinkedList<APageTypesetter> tempPages = new LinkedList<APageTypesetter>();


    private int screenWidth = 320;
    private int screenHeight = 480;

    public void typeset(IChapterTypesetter chapterTypesetter){
        /**
         * 段落不止
         * 排版不息
         */
        while (chapterTypesetter.getParagraphOffset() < chapterTypesetter.getChapter().getParagraphLength()){
            APageTypesetter pageTypesetter = new PageTypesetter(0,0,screenWidth,screenHeight);
            pageTypesetter.typeset(chapterTypesetter);
            this.tempPages.add(pageTypesetter);
        }
    }

    public LinkedList<APageTypesetter> getPages() {
        return tempPages;
    }
}
