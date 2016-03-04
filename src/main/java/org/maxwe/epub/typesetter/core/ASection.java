package org.maxwe.epub.typesetter.core;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015private int12private int26 17:21.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public abstract class ASection implements ISection {

    private int startX;
    private int startY;
    private int endX;
    private int endY;

    private int startOffsetInParagraph;
    private int endOffsetInParagraph;
    
    private AChapter chapter;
    private APage page;

    protected ASection(AChapter chapter,APage page) {
        this.chapter = chapter;
        this.page = page;
        this.startX = this.page.getCursorX();
        this.startY = this.page.getCursorY();
        this.endX = this.page.getEndX();
        this.endY = this.page.getEndY();
        this.startOffsetInParagraph = this.chapter.getCurrentSectionIndexInParagraph();
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

    public int getStartOffsetInParagraph() {
        return startOffsetInParagraph;
    }

    public void setStartOffsetInParagraph(int startOffsetInParagraph) {
        this.startOffsetInParagraph = startOffsetInParagraph;
    }

    public int getEndOffsetInParagraph() {
        return endOffsetInParagraph;
    }

    public void setEndOffsetInParagraph(int endOffsetInParagraph) {
        this.endOffsetInParagraph = endOffsetInParagraph;
    }

    public AChapter getChapter() {
        return chapter;
    }

    public APage getPage() {
        return page;
    }

    public abstract LinkedList<IMeta> getMetas();

    protected abstract ASection typeset();

    public void print() {
//        System.out.println("本片段信息：偏移起始位置= {" + this.getStartOffsetInParagraph() + "} ,偏移结束位置= {" + this.getEndOffsetInParagraph()
//                + "} ,本片段信息：坐标起始点= {" + this.getStartX() + " ," + this.getStartY() + "} ,页面坐标结束点= {" + this.getEndX() + " ," + this.getEndY() + "}");
    }
}
