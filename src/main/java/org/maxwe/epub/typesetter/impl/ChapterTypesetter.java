package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.parser.core.IChapter;
import org.maxwe.epub.parser.core.INavigation;
import org.maxwe.epub.parser.impl.Chapter;
import org.maxwe.epub.typesetter.core.IChapterTypesetter;
import org.maxwe.epub.typesetter.core.APageTypesetter;
import org.maxwe.epub.typesetter.core.ITypesetterListener;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pengwei Ding on 2015-09-11 15:18.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class ChapterTypesetter implements IChapterTypesetter {
    private IChapter chapter;
    private String title;
    private int index;
    private int paragraphOffset;
    private int sectionOffset;
    private int offset;
    /**
     * 初始化当前章节的在读页面是负1，标示在读的页面还在上一个章节
     */
    private int currentPageIndex = -1;

    private LinkedList<APageTypesetter> pageTypesetters = new LinkedList<APageTypesetter>();

    public ChapterTypesetter(IChapter chapter) {
        this.chapter = chapter;
        this.title = this.chapter.getTitle();
    }

    public ChapterTypesetter(INavigation navigation) throws Exception {
        this.chapter = new Chapter(navigation.getHref());
        this.title = this.chapter.getTitle();
        this.index = navigation.getIndex();
    }

    public String getChapterId() {
        return this.chapter.getHref();
    }

    public String getTitle() {
        return this.title;
    }

    public int getIndex() {
        return this.index;
    }

    public INavigation getNavigation() {
        return null;
    }

    public IChapter getChapter() {
        return this.chapter;
    }

    public int getParagraphOffset() {
        return this.paragraphOffset;
    }

    public int getSectionOffset() {
        return this.sectionOffset;
    }

    public int getOffset() {
        return offset;
    }

    public void setParagraphOffset(int paragraphOffset) {
        this.paragraphOffset = paragraphOffset;
    }

    public void setSectionOffset(int sectionOffset) {
        this.sectionOffset = sectionOffset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<APageTypesetter> getPageTypesetters() {
        return this.pageTypesetters;
    }

    public int getPageTypesetterSize() {
        return this.pageTypesetters.size();
    }

    public IChapterTypesetter typeset(int screenWidth, int screenHeight) {
        while (this.getParagraphOffset() < this.getChapter().getParagraphLength()) {
            APageTypesetter pageTypesetter = new PageTypesetter(0, 0, screenWidth, screenHeight);
            pageTypesetter.setIndex(this.pageTypesetters.size());
            pageTypesetter.typeset(this);
            this.pageTypesetters.add(pageTypesetter);
        }
        return this;
    }

    public void setTypesetterListener(ITypesetterListener typesetterListener) {

    }

    public void print() {
        System.out.println("==============章节线，章节标题题：" + this.getTitle() + ",总页码："+ this.getPageTypesetterSize()+"=================");
        for (APageTypesetter pageTypesetter:this.pageTypesetters){
            pageTypesetter.print();
        }
    }
}
