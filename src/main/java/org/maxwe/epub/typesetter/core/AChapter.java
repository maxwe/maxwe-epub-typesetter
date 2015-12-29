package org.maxwe.epub.typesetter.core;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015private int12private int26 16:14.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 建议的段落排版实现
 */
public abstract class AChapter implements IChapter {

    private int startX;
    private int startY;
    private int endX;
    private int endY;

    private String chapterId;
    private String chapterName;
    private int chapterIndex;

    private int currentParagraphIndexInChapter;
    private int currentSectionIndexInParagraph;
    private int currentMetaIndexInSection;

    private org.maxwe.epub.parser.core.IChapter parsedChapter;

    public AChapter(org.maxwe.epub.parser.core.IChapter chapter, int startX, int startY, int endX, int endY) {
        this.parsedChapter = chapter;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.chapterId = this.parsedChapter.getHref();
        this.chapterName = this.parsedChapter.getTitle();
        /**
         * TODO 数据类型不一致
         */
        //this.chapterIndex = this.parsedChapter.getIndex();
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

    public int getCurrentParagraphIndexInChapter() {
        return currentParagraphIndexInChapter;
    }

    public void setCurrentParagraphIndexInChapter(int currentParagraphIndexInChapter) {
        this.currentParagraphIndexInChapter = currentParagraphIndexInChapter;
    }

    public int getCurrentSectionIndexInParagraph() {
        return currentSectionIndexInParagraph;
    }

    public void setCurrentSectionIndexInParagraph(int currentSectionIndexInParagraph) {
        this.currentSectionIndexInParagraph = currentSectionIndexInParagraph;
    }

    public int getCurrentMetaIndexInSection() {
        return currentMetaIndexInSection;
    }

    public void setCurrentMetaIndexInSection(int currentMetaIndexInSection) {
        this.currentMetaIndexInSection = currentMetaIndexInSection;
    }

    public org.maxwe.epub.parser.core.IChapter getParsedChapter() {
        return parsedChapter;
    }

    public abstract LinkedList<IPage> getPages();

    protected abstract AChapter typeset();
}
