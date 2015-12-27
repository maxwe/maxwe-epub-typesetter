package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.parser.core.IParagraph;
import org.maxwe.epub.parser.core.ISection;
import org.maxwe.epub.parser.impl.*;
import org.maxwe.epub.parser.impl.Audio;
import org.maxwe.epub.parser.impl.Video;
import org.maxwe.epub.typesetter.core.AChapter;
import org.maxwe.epub.typesetter.core.APage;
import org.maxwe.epub.typesetter.core.AParagraph;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-12-26 18:50.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class Paragraph extends AParagraph {

    public Paragraph(AChapter chapter, APage page) {
        super(chapter,page);
    }

    @Override
    public LinkedList<org.maxwe.epub.typesetter.core.ISection> getSections() {
        return null;
    }

    @Override
    protected AParagraph typeset() {
        int paragraphOffset = this.getChapter().getCurrentTypesetterOffsetOnParagraph();
        IParagraph paragraph = this.getChapter().getChapter().getParagraph(paragraphOffset);
        int sectionLength = paragraph.getSectionLength();
        while (this.getStartY() < this.getPage().getScreenHeight() && this.getChapter().getCurrentTypesetterOffsetOnSection() < sectionLength) {
            ISection section = paragraph.getSection(this.getChapter().getCurrentTypesetterOffsetOnSection());
            if (section instanceof Text) {

            } else if (section instanceof org.maxwe.epub.parser.impl.Image) {
            } else if (section instanceof Audio) {
            } else if (section instanceof Video) {
            }
        }
        return this;
    }
}
