package org.maxwe.epub.typesetter;

/**
 * Created by Pengwei Ding on 2016-03-02 10:01.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 排版配置文件
 */
public class Configure {
    /**
     * 字体
     */
    private String fontStyle;
    /**
     * 字号
     */
    private int fontSize = 25;
    /**
     * 字间距
     */
    private int fontSpace = 2;
    /**
     * 行间距
     */
    private int lineSpace = 25;
    /**
     * 页边距
     */
    private int pageMargin  = 25;
    /**
     * 已经阅读到的章节位置
     */
    private int chapterIndex;
    /**
     * 已经阅读到的段落位置
     */
    private int paragraphIndex;
    /**
     * 已经阅读到的片段位置
     */
    private int sectionIndex;
    /**
     * 已经阅读到的元素位置
     */
    private int mateIndex;


    public Configure() {
        super();
    }

    public Configure(int chapterIndex, int paragraphIndex, int sectionIndex, int mateIndex) {
        this.chapterIndex = chapterIndex;
        this.paragraphIndex = paragraphIndex;
        this.sectionIndex = sectionIndex;
        this.mateIndex = mateIndex;
    }

    public Configure(String fontStyle, int fontSize, int fontSpace, int lineSpace, int pageMargin, int chapterIndex, int paragraphIndex, int sectionIndex, int mateIndex) {
        this(chapterIndex,paragraphIndex,sectionIndex,mateIndex);
        this.fontStyle = fontStyle;
        this.fontSize = fontSize;
        this.fontSpace = fontSpace;
        this.lineSpace = lineSpace;
        this.pageMargin = pageMargin;
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

    public int getMateIndex() {
        return mateIndex;
    }

    public void setMateIndex(int mateIndex) {
        this.mateIndex = mateIndex;
    }
}
