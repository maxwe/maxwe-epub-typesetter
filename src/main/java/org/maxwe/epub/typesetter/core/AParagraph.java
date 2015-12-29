package org.maxwe.epub.typesetter.core;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015private int12private int26 17:19.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public abstract class AParagraph implements IParagraph{

    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int cursorX;
    private int cursorY;

    private int startOffsetInChapter;
    private int endOffsetInChapter;


    private AChapter chapter;
    private APage page;

    public AParagraph(AChapter chapter,APage page){
        this.chapter = chapter;
        this.page = page;
        this.cursorX = this.startX = this.chapter.getStartX();
        this.cursorY = this.startY = this.chapter.getStartY();
        this.endX = this.chapter.getEndX();
        this.endY = this.chapter.getEndY();
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
        return this.getEndX() - this.getStartX();
    }

    public int getScreenHeight() {
        return this.getEndY() - this.getStartY();
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

    public int getStartOffsetInChapter() {
        return startOffsetInChapter;
    }

    public void setStartOffsetInChapter(int startOffsetInChapter) {
        this.startOffsetInChapter = startOffsetInChapter;
    }

    public int getEndOffsetInChapter() {
        return endOffsetInChapter;
    }

    public void setEndOffsetInChapter(int endOffsetInChapter) {
        this.endOffsetInChapter = endOffsetInChapter;
    }

    public AChapter getChapter() {
        return chapter;
    }

    public APage getPage() {
        return page;
    }

    public abstract LinkedList<ISection> getSections();

    protected abstract AParagraph typeset();


}
