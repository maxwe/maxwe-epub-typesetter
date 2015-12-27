package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.typesetter.core.AChapter;
import org.maxwe.epub.typesetter.core.APage;
import org.maxwe.epub.typesetter.core.IParagraph;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-12-26 16:45.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 页面排版
 * 边界控制条件有两个
 * 1：内容边界 在章节段落已经排版完成无论是否触及显示边界都应该终止该页面的排版
 * 2：显示边界 在显示边界外无论章节是否已经排版完成了整个章节都应该终止该页面的排版
 * 继续排版的条件就是同时满足上述两个条件
 */
public class Page extends APage {

    public Page(AChapter chapter) {
        super(chapter);
    }

    @Override
    public LinkedList<IParagraph> getParagraphs() {
        return null;
    }

    @Override
    protected APage typeset() {
        int paragraphLength = this.getChapter().getChapter().getParagraphLength();
        while (this.getStartX() < this.getScreenHeight() && this.getChapter().getCurrentTypesetterOffsetOnParagraph() < paragraphLength) {
            Paragraph paragraph = new Paragraph(this.getChapter(),this);
            paragraph.typeset();
            this.getParagraphs().add(paragraph);
        }
        return this;
    }
}
