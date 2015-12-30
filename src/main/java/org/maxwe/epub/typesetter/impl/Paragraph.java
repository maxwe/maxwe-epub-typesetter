package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.parser.core.IParagraph;
import org.maxwe.epub.parser.impl.Audio;
import org.maxwe.epub.parser.impl.Text;
import org.maxwe.epub.parser.impl.Video;
import org.maxwe.epub.typesetter.core.*;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-12-26 18:50.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 段落排版规则：
 */
public class Paragraph extends AParagraph {
    private LinkedList<ISection> sections = new LinkedList<ISection>();
    public Paragraph(AChapter chapter, APage page) {
        super(chapter,page);
    }

    @Override
    public LinkedList<ISection> getSections() {
        return this.sections;
    }

    @Override
    protected AParagraph typeset() {
        int paragraphOffset = this.getChapter().getCurrentParagraphIndexInChapter();
        IParagraph paragraph = this.getChapter().getParsedChapter().getParagraph(paragraphOffset);
        int sectionLength = paragraph.getSectionLength();
        while (this.getPage().getCursorY() < this.getPage().getEndY() && this.getChapter().getCurrentSectionIndexInParagraph() < sectionLength) {
            org.maxwe.epub.parser.core.ISection section = paragraph.getSection(this.getChapter().getCurrentSectionIndexInParagraph());
            ASection sectionTypesetter = null;
            if (section instanceof Text) {
                sectionTypesetter = new TextSection(this.getChapter(), this.getPage()).typeset();
            } else if (section instanceof org.maxwe.epub.parser.impl.Image) {
                System.out.println("==========Image==========");
            } else if (section instanceof Audio) {
                System.out.println("==========Audio==========");
            } else if (section instanceof Video) {
                System.out.println("==========Video==========");
            }

            if (sectionTypesetter != null){
                this.sections.add(sectionTypesetter);
                if (this.getChapter().getCurrentSectionIndexInParagraph() >= sectionLength){
                    /**
                     * 段落中的片段已经排版完毕
                     * 重置段落级别内部的游标
                     * 并且使段落级别的游标向下游动一个单位
                     */
                    this.getChapter().setCurrentSectionIndexInParagraph(0);
                    this.getChapter().setCurrentParagraphIndexInChapter(this.getChapter().getCurrentParagraphIndexInChapter() + 1);
                    break;
                }
            }
        }
        return this;
    }
}
