package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.parser.core.IChapter;
import org.maxwe.epub.parser.core.INavigation;
import org.maxwe.epub.typesetter.core.IChapterTypesetter;
import org.maxwe.epub.typesetter.core.IPageTypesetter;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-09-11 15:18.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class ChapterTypesetter implements IChapterTypesetter {
    private IChapter chapter;
    private String title;
    private int paragraphOffset;
    private int sectionOffset;
    private int offset;
    private LinkedList<IPageTypesetter> pagesOfChapter = new LinkedList<IPageTypesetter>();



    public ChapterTypesetter(IChapter chapter) {
        this.chapter = chapter;
        this.title = this.chapter.getTitle();
    }

    public String getTitle() {
        return null;
    }

    public INavigation getNavigation() {
        return null;
    }

    public IChapter getChapter() {
        return null;
    }

    public int getParagraphOffset() {
        return 0;
    }

    public int getSectionOffset() {
        return 0;
    }

    public int getOffset() {
        return 0;
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
}
