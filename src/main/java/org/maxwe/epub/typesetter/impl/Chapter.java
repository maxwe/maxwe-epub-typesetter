package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.parser.core.IChapter;
import org.maxwe.epub.typesetter.core.AChapter;
import org.maxwe.epub.typesetter.core.IPage;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-12-26 16:30.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 章节排版规则：只要章节中有段落没有排完就可以继续排下去成为页面
 */
public class Chapter extends AChapter {

    private LinkedList<IPage> pages = new LinkedList<IPage>();

    public Chapter(IChapter chapter, int startX, int startY, int endX, int endY) {
        super(chapter, startX, startY, endX, endY);
    }

    @Override
    public LinkedList<IPage> getPages() {
        return this.pages;
    }

    @Override
    public AChapter typeset() {
        while (this.getCurrentParagraphIndexInChapter() < this.getParsedChapter().getParagraphLength()) {
            Page page = new Page(this);
            page.typeset();
            this.pages.add(page);
        }
        return this;
    }
}