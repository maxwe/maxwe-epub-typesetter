package org.maxwe.epub.typesetter.core;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-12-26 16:14.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 建议的段落排版实现
 */
public abstract class AChapter implements IChapter{
    /**
     * 章节对应的文件路径
     */
    private String chapterFilePath;
    /**
     * 章节ID
     */
    private String chapterId;
    /**
     * 章节名称
     */
    private String chapterName;
    /**
     * 章节在图书中的顺序
     */
    private int indexInBook;
    /**
     * 章节排版后的页面
     */
    private LinkedList<IPage> pages = new LinkedList<IPage>();


    /**
     * 有效的排版区域宽度
     */
    private int screenWidth;

    /**
     * 有效的排版区域高度
     */
    private int screenHeight;

    /**
     * 当前排版到的段落偏移量
     */
    private int currentTypesetterOffsetOnParagraph;
    /**
     * 当前排版到的片段偏移量
     */
    private int currentTypesetterOffsetOnSection;
    /**
     * 当前排版到的在片段中的偏移量
     */
    private int currentTypesetterOffsetInSection;

    private org.maxwe.epub.parser.core.IChapter chapter;

    protected AChapter(org.maxwe.epub.parser.core.IChapter chapter,int screenWidth,int screenHeight){
        this.chapter = chapter;
        this.chapterFilePath = chapter.getHref();
        this.chapterId = chapter.getHref();
        this.chapterName = chapter.getTitle();
        /**
         * TODO 设置章节在图书中的顺序
         */
        //this.setIndexInBook(this.chapter.getIndex());
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public String getChapterFilePath() {
        return chapterFilePath;
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

    public int getIndexInBook() {
        return indexInBook;
    }

    public void setIndexInBook(int indexInBook) {
        this.indexInBook = indexInBook;
    }

    public LinkedList<IPage> getPages() {
        return pages;
    }

    public void setPages(LinkedList<IPage> pages) {
        this.pages = pages;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getCurrentTypesetterOffsetOnParagraph() {
        return currentTypesetterOffsetOnParagraph;
    }

    public void setCurrentTypesetterOffsetOnParagraph(int currentTypesetterOffsetOnParagraph) {
        this.currentTypesetterOffsetOnParagraph = currentTypesetterOffsetOnParagraph;
    }

    public int getCurrentTypesetterOffsetOnSection() {
        return currentTypesetterOffsetOnSection;
    }

    public void setCurrentTypesetterOffsetOnSection(int currentTypesetterOffsetOnSection) {
        this.currentTypesetterOffsetOnSection = currentTypesetterOffsetOnSection;
    }

    public int getCurrentTypesetterOffsetInSection() {
        return currentTypesetterOffsetInSection;
    }

    public void setCurrentTypesetterOffsetInSection(int currentTypesetterOffsetInSection) {
        this.currentTypesetterOffsetInSection = currentTypesetterOffsetInSection;
    }

    public org.maxwe.epub.parser.core.IChapter getChapter() {
        return chapter;
    }

    protected abstract AChapter typeset();
}
