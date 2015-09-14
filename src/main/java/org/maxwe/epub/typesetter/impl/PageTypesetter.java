package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.parser.core.IChapter;
import org.maxwe.epub.parser.core.INavigation;
import org.maxwe.epub.parser.core.IParagraph;
import org.maxwe.epub.typesetter.constant.Configer;
import org.maxwe.epub.typesetter.core.IChapterTypesetter;
import org.maxwe.epub.typesetter.core.IPageTypesetter;
import org.maxwe.epub.typesetter.core.ISectionTypesetter;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-09-12 12:52.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class PageTypesetter implements IPageTypesetter {

    private String title;
    private String index;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int currentX;
    private int currentY;
    private INavigation navigation;

    /**
     * 页面的起始偏移量
     */
    private int startParagraphOffset;
    private int startSectionOffset;
    private int startOffset;

    /**
     * 页面的结束偏移量
     * 也就是当前偏移量
     */
    private int endParagraphOffset;
    private int endSectionOffset;
    private int endOffset;

    private LinkedList<ISectionTypesetter> sectionTypesetters = new LinkedList<ISectionTypesetter>();

    public PageTypesetter() {
    }

    public PageTypesetter(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }


    public String getTitle() {
        return this.title;
    }

    public String getIndex() {
        return this.index;
    }

    public PageTypesetter setStartPoint(int x, int y) {
        this.startX = x;
        this.startY = y;
        return this;
    }

    public PageTypesetter setEndPoint(int x, int y) {
        this.endX = x;
        this.endY = y;
        return this;
    }

    public int[] getEndPoint() {
        return new int[0];
    }

    public INavigation getNavigation() {
        return this.navigation;
    }

    public int getStartParagraphOffset() {
        return this.startParagraphOffset;
    }

    public int getEndParagraphOffset() {
        return this.endParagraphOffset;
    }

    public int getStartSectionOffset() {
        return this.startSectionOffset;
    }

    public int getEndSectionOffset() {
        return this.endSectionOffset;
    }

    public int getStartOffset() {
        return this.startOffset;
    }

    public int getEndOffset() {
        return this.endOffset;
    }

    public LinkedList<ISectionTypesetter> getSectionsTypesetter() {
        return this.sectionTypesetters;
    }

    public void typeset(IChapterTypesetter chapterTypesetter) {
        IChapter chapter = chapterTypesetter.getChapter();

        /**
         * 初始化偏移量
         */
        this.startParagraphOffset = this.endParagraphOffset = chapterTypesetter.getParagraphOffset();
        this.startSectionOffset = this.endSectionOffset = chapterTypesetter.getSectionOffset();
        this.startOffset = this.endOffset = chapterTypesetter.getOffset();

        /**
         * 行排版
         */
        while (this.currentY + Configer.CONFIGER_WORD_SIZE <= this.endY){
            /**
             * 列排版
             * 即行间排版
             */
            while (this.currentX + Configer.CONFIGER_WORD_SIZE <= this.endX){

                this.currentX = this.currentX + Configer.CONFIGER_WORD_SIZE;
            }
            this.currentY = this.currentY + Configer.CONFIGER_LINE_SPACING;
        }



        LinkedList<IParagraph> paragraphs = chapterTypesetter.getChapter().getParagraphs();
        int sizeOfParagraphs = paragraphs.size();

    }
}
