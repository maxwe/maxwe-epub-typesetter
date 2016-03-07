package org.maxwe.epub.typesetter.core;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015private int12private int26 15:42.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 建议的页面对象的默认实现
 */
public abstract class APage implements IPage {

    private int startX;
    private int startY;
    private int endX;
    private int endY;

    private String chapterId;
    private String chapterName;
    private int chapterIndex;
    private int startParagraphIndexInChapter;
    private int endParagraphIndexInChapter;
    private int startSectionIndexInParagraph;
    private int endSectionInParagraph;
    private int startMetaIndexInSection;
    private int endMetaIndexInSection;

    private int indexInChapter;
    private int indexInBook;

    private int cursorX;
    private int cursorY;

    private AChapter chapter;
    private IConfigure configure;
    protected APage(AChapter chapter,IConfigure configure){
        this.chapter = chapter;
        this.configure = configure;
        this.cursorX = this.startX = this.chapter.getStartX();
        this.cursorY = this.startY = this.chapter.getStartY();
        this.endX = this.chapter.getEndX();
        this.endY = this.chapter.getEndY();
        this.chapterId = this.chapter.getChapterId();
        this.chapterName = this.chapter.getChapterName();
        this.chapterIndex = this.chapter.getChapterIndex();
        this.startParagraphIndexInChapter = this.chapter.getCurrentParagraphIndexInChapter();
        this.startSectionIndexInParagraph = this.chapter.getCurrentSectionIndexInParagraph();
        this.startMetaIndexInSection = this.chapter.getCurrentMetaIndexInSection();
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public int getScreenWidth() {
        return this.getEndX() - this.getStartX();
    }

    public int getScreenHeight() {
        return this.getEndY() - this.getStartY();
    }

    public String getChapterId() {
        return chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public int getChapterIndex() {
        return chapterIndex;
    }

    public int getStartParagraphIndexInChapter() {
        return startParagraphIndexInChapter;
    }

    public void setStartParagraphIndexInChapter(int startParagraphIndexInChapter) {
        this.startParagraphIndexInChapter = startParagraphIndexInChapter;
    }

    public int getEndParagraphIndexInChapter() {
        return endParagraphIndexInChapter;
    }

    public void setEndParagraphIndexInChapter(int endParagraphIndexInChapter) {
        this.endParagraphIndexInChapter = endParagraphIndexInChapter;
    }

    public int getStartSectionIndexInParagraph() {
        return startSectionIndexInParagraph;
    }

    public void setStartSectionIndexInParagraph(int startSectionIndexInParagraph) {
        this.startSectionIndexInParagraph = startSectionIndexInParagraph;
    }

    public int getEndSectionInParagraph() {
        return endSectionInParagraph;
    }

    public void setEndSectionInParagraph(int endSectionInParagraph) {
        this.endSectionInParagraph = endSectionInParagraph;
    }

    public int getStartMetaIndexInSection() {
        return startMetaIndexInSection;
    }

    public void setStartMetaIndexInSection(int startMetaIndexInSection) {
        this.startMetaIndexInSection = startMetaIndexInSection;
    }

    public int getEndMetaIndexInSection() {
        return endMetaIndexInSection;
    }

    public void setEndMetaIndexInSection(int endMetaIndexInSection) {
        this.endMetaIndexInSection = endMetaIndexInSection;
    }

    public int getIndexInChapter() {
        return indexInChapter;
    }

    public void setIndexInChapter(int indexInChapter) {
        this.indexInChapter = indexInChapter;
    }

    public int getIndexInBook() {
        return indexInBook;
    }

    public void setIndexInBook(int indexInBook) {
        this.indexInBook = indexInBook;
    }

    public int getCursorX() {
        return cursorX;
    }

    public void setCursorX(int cursorX) {
        this.cursorX = cursorX;
    }

    public int getCursorY() {
        return cursorY;
    }

    public void setCursorY(int cursorY) {
        this.cursorY = cursorY;
    }

    public AChapter getChapter() {
        return chapter;
    }

    public abstract LinkedList<IParagraph> getParagraphs();

    public IConfigure getConfigure() {
        return configure;
    }

    protected abstract APage typeset();

    public void print() {
//        System.out.println("本页信息：章节名称= " + this.getChapterName()
//                + " ,偏移起始位置={" + this.getChapterIndex() + " ," + this.getStartParagraphIndexInChapter() + " ," + this.getStartSectionIndexInParagraph() +" ," + this.getStartMetaIndexInSection()
//                + "} ,偏移结束位置={" + this.getChapterIndex() + " ," + this.getEndParagraphIndexInChapter() + " ," + this.getEndSectionInParagraph() + " ," + this.getEndMetaIndexInSection()
//                + "},坐标起始点= {" + this.getStartX() + " ," + this.getStartY() + "} ,坐标结束点= {" + this.getEndX() + " ," + this.getEndY() + "}");
    }
}
