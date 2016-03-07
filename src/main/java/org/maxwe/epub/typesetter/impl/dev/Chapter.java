package org.maxwe.epub.typesetter.impl.dev;

import org.maxwe.epub.parser.core.IChapter;
import org.maxwe.epub.typesetter.core.AChapter;
import org.maxwe.epub.typesetter.core.IConfigure;
import org.maxwe.epub.typesetter.core.IPage;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-12-26 16:30.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 章节排版规则：只要章节中有段落没有排完就可以继续排下去成为页面
 */
public class Chapter extends AChapter {

    private ChapterTypesetListener chapterTypesetListener = new ChapterTypesetListener() {
        public void onChapterTypesetStart(org.maxwe.epub.typesetter.core.IChapter chapter) {

        }

        public void onPageTypesetOver(org.maxwe.epub.typesetter.core.IChapter chapter, int indexInChapter) {

        }

        public void onChapterTypesetEnd(org.maxwe.epub.typesetter.core.IChapter chapter) {

        }
    };

    private LinkedList<IPage> pages = new LinkedList<IPage>();

    public Chapter(IChapter chapter,IConfigure configure, int startX, int startY, int endX, int endY) {
        super(chapter, configure,startX, startY, endX, endY);
    }



    @Override
    public LinkedList<IPage> getPages() {
        return this.pages;
    }

    @Override
    public AChapter typeset() {
        this.chapterTypesetListener.onChapterTypesetStart(this);
        while (this.getCurrentParagraphIndexInChapter() < this.getParsedChapter().getParagraphLength()) {
            Page page = new Page(this,this.getConfigure());
            page.typeset();
            this.pages.add(page);
            this.chapterTypesetListener.onPageTypesetOver(this, this.pages.size());
        }
        this.chapterTypesetListener.onChapterTypesetEnd(this);
        return this;
    }

    public Chapter setChapterTypesetListener(ChapterTypesetListener chapterTypesetListener) {
        if (chapterTypesetListener != null){
            this.chapterTypesetListener = chapterTypesetListener;
        }
        return this;
    }

    public interface ChapterTypesetListener{
        void onChapterTypesetStart(org.maxwe.epub.typesetter.core.IChapter chapter);
        void onPageTypesetOver(org.maxwe.epub.typesetter.core.IChapter chapter,int indexInChapter);
        void onChapterTypesetEnd(org.maxwe.epub.typesetter.core.IChapter chapter);
    }


}
