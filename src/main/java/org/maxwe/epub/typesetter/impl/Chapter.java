package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.typesetter.core.AChapter;

/**
 * Created by Pengwei Ding on 2015-12-26 16:30.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description:
 */
public class Chapter extends AChapter {

    public Chapter(String chapterFilePath, int screenWidth, int screenHeight) throws Exception {
        super(new org.maxwe.epub.parser.impl.Chapter(chapterFilePath), screenWidth, screenHeight);
    }

    @Override
    protected AChapter typeset() {
        while (this.getCurrentTypesetterOffsetOnParagraph() < this.getChapter().getParagraphLength()) {
            Page page = new Page(this);
            page.typeset();
            this.getPages().add(page);
        }
        return this;
    }
}
