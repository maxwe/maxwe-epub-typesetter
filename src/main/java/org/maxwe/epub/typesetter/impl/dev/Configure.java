package org.maxwe.epub.typesetter.impl.dev;

import org.maxwe.epub.typesetter.core.IConfigure;

/**
 * Created by Pengwei Ding on 2016-03-02 10:01.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 排版配置文件
 */
public class Configure implements IConfigure {
    protected int startX = 0;
    protected int startY = 0;
    protected int endX = 320;
    protected int endY = 480;

    /**
     * 字体
     */
    protected String fontStyle;
    /**
     * 字号
     */
    protected int fontSize = 25;
    /**
     * 字间距
     */
    protected int fontSpace = 2;
    /**
     * 行间距
     */
    protected int lineSpace = 25;
    /**
     * 页边距
     */
    protected int pageMargin  = 25;
    /**
     * 已经阅读到的章节位置
     */
    protected int chapterIndex;
    /**
     * 已经阅读到的段落位置
     */
    protected int paragraphIndex;
    /**
     * 已经阅读到的片段位置
     */
    protected int sectionIndex;
    /**
     * 已经阅读到的元素位置
     */
    protected int metaIndex;


    public Configure() {
        super();
    }

    public Configure(int chapterIndex, int paragraphIndex, int sectionIndex, int metaIndex) {
        this.chapterIndex = chapterIndex;
        this.paragraphIndex = paragraphIndex;
        this.sectionIndex = sectionIndex;
        this.metaIndex = metaIndex;
    }

    public Configure(String fontStyle, int fontSize, int fontSpace, int lineSpace, int pageMargin, int chapterIndex, int paragraphIndex, int sectionIndex, int mateIndex) {
        this(chapterIndex,paragraphIndex,sectionIndex,mateIndex);
        this.fontStyle = fontStyle;
        this.fontSize = fontSize;
        this.fontSpace = fontSpace;
        this.lineSpace = lineSpace;
        this.pageMargin = pageMargin;
    }


    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getFontSpace() {
        return fontSpace;
    }

    public void setFontSpace(int fontSpace) {
        this.fontSpace = fontSpace;
    }

    public int getLineSpace() {
        return lineSpace;
    }

    public void setLineSpace(int lineSpace) {
        this.lineSpace = lineSpace;
    }

    public int getPageMargin() {
        return pageMargin;
    }

    public void setPageMargin(int pageMargin) {
        this.pageMargin = pageMargin;
    }

    public int getChapterIndex() {
        return chapterIndex;
    }

    public void setChapterIndex(int chapterIndex) {
        this.chapterIndex = chapterIndex;
    }

    public int getParagraphIndex() {
        return paragraphIndex;
    }

    public void setParagraphIndex(int paragraphIndex) {
        this.paragraphIndex = paragraphIndex;
    }

    public int getSectionIndex() {
        return sectionIndex;
    }

    public void setSectionIndex(int sectionIndex) {
        this.sectionIndex = sectionIndex;
    }

    public int getMetaIndex() {
        return metaIndex;
    }

    public void setMateIndex(int metaIndex) {
        this.metaIndex = metaIndex;
    }
}
