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
    private int screenWidth;
    private int screenHeight;

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

    private AChapter chapter;

    protected APage(AChapter chapter){
        this.chapter = chapter;
        this.chapterId = chapter.getChapterId();
        this.chapterName = chapter.getChapterName();
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

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public int getChapterIndex() {
        return chapterIndex;
    }

    public void setChapterIndex(int chapterIndex) {
        this.chapterIndex = chapterIndex;
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

    public AChapter getChapter() {
        return chapter;
    }

    public abstract LinkedList<IParagraph> getParagraphs();

    protected abstract APage typeset();
}
