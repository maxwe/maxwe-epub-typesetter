package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.typesetter.Configure;
import org.maxwe.epub.typesetter.core.AChapter;
import org.maxwe.epub.typesetter.core.APage;
import org.maxwe.epub.typesetter.core.AParagraph;
import org.maxwe.epub.typesetter.core.IParagraph;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-12-26 16:45.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 页面排版规则：只要章节中有段落没有排完就可以继续排下去，如果当页面还能排得下继续在当前页面排版，否则换个新页面继续排版
 * 边界控制条件有两个
 * 1：内容边界 在章节段落已经排版完成无论是否触及显示边界都应该终止该页面的排版
 * 2：显示边界 在显示边界外无论章节是否已经排版完成了整个章节都应该终止该页面的排版
 * 继续排版的条件就是同时满足上述两个条件
 *
 */
public class Page extends APage {

    private LinkedList<IParagraph> paragraphs = new LinkedList<IParagraph>();
    protected Page(AChapter chapter,Configure configure) {
        super(chapter,configure);
    }

    @Override
    public LinkedList<IParagraph> getParagraphs() {
        return this.paragraphs;
    }

    @Override
    protected APage typeset() {
        int paragraphLength = this.getChapter().getParsedChapter().getParagraphLength();
        while (this.getCursorY() < this.getEndY() && this.getChapter().getCurrentParagraphIndexInChapter() < paragraphLength) {
            AParagraph paragraph = new Paragraph(this.getChapter(),this).typeset();
            this.getParagraphs().add(paragraph);
            if (this.getChapter().getCurrentParagraphIndexInChapter() >= paragraphLength){
                break;
            }
        }
        return this;
    }

    public void print() {
        System.out.println("本页信息：章节名称= " + this.getChapterName()
                + " ,四维坐标起始={" + this.getChapterIndex() + " ," + this.getStartParagraphIndexInChapter() + " ," + this.getStartSectionIndexInParagraph() +" ," + this.getStartMetaIndexInSection()
                + "} ,四维坐标结束={" + this.getChapterIndex() + " ," + this.getEndParagraphIndexInChapter() + " ," + this.getEndSectionInParagraph() + " ," + this.getEndMetaIndexInSection()
                + "},页面坐标起始点= {" + this.getStartX() + " ," + this.getStartY() + "} ,页面坐标结束点= {" + this.getEndX() + " ," + this.getEndY() + "}");
        for (IParagraph paragraph:paragraphs){
            paragraph.print();
        }
    }
}
