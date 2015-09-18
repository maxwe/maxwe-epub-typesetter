package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.parser.core.IChapter;
import org.maxwe.epub.parser.core.INavigation;
import org.maxwe.epub.typesetter.constant.LayoutStyle;
import org.maxwe.epub.typesetter.core.APageTypesetter;
import org.maxwe.epub.typesetter.core.IChapterTypesetter;
import org.maxwe.epub.typesetter.core.AParagraphTypesetter;
import org.maxwe.epub.typesetter.core.ISectionTypesetter;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-09-12 12:52.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class PageTypesetter extends APageTypesetter {

    private String title;
    private String index;
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

    private LinkedList<AParagraphTypesetter> paragraphTypesetters = new LinkedList<AParagraphTypesetter>();

    public PageTypesetter(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.currentX = startX;
        this.currentY = startY;
        this.endX = endX;
        this.endY = endY;
    }


    public String getTitle() {
        return this.title;
    }

    public String getIndex() {
        return this.index;
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

    public LinkedList<AParagraphTypesetter> getParagraphTypesetters() {
        return this.paragraphTypesetters;
    }

    public PageTypesetter setFontSize(int size) {
        return this;
    }

    public PageTypesetter setTypeface(Object typeface) {
        return this;
    }

    public PageTypesetter setLayoutStyle(LayoutStyle layoutStyle) {
        return this;
    }

    public void typeset(IChapterTypesetter chapterTypesetter) {
        IChapter chapter = chapterTypesetter.getChapter();
        int paragraphLength = chapter.getParagraphLength();
        while (this.currentY <= this.endY && chapterTypesetter.getParagraphOffset() < paragraphLength) {
            ISectionTypesetter paragraphTypesetter = new ParagraphTypesetter();
            paragraphTypesetter.setStartPoint(this.currentX, this.currentY);
            paragraphTypesetter.setEndPoint(this.endX,this.endY);
            paragraphTypesetter.typeset(chapterTypesetter);
            this.currentX = paragraphTypesetter.getEndPoint()[0];
            this.currentY = paragraphTypesetter.getEndPoint()[1];
            this.paragraphTypesetters.add((AParagraphTypesetter)paragraphTypesetter);
        }
    }
}
