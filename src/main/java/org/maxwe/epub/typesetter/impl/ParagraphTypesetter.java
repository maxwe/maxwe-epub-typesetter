package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.parser.core.IChapter;
import org.maxwe.epub.parser.core.IParagraph;
import org.maxwe.epub.parser.core.ISection;
import org.maxwe.epub.parser.impl.Audio;
import org.maxwe.epub.parser.impl.Image;
import org.maxwe.epub.parser.impl.Text;
import org.maxwe.epub.parser.impl.Video;
import org.maxwe.epub.typesetter.core.AParagraphTypesetter;
import org.maxwe.epub.typesetter.core.IChapterTypesetter;
import org.maxwe.epub.typesetter.core.ISectionTypesetter;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-09-15 16:40.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class ParagraphTypesetter extends AParagraphTypesetter {

    private LinkedList<ISectionTypesetter> sectionTypesetters = new LinkedList<ISectionTypesetter>();

    @Override
    public void typeset(IChapterTypesetter chapterTypesetter) {
        IChapter chapter = chapterTypesetter.getChapter();
        int paragraphOffset = chapterTypesetter.getParagraphOffset();

        IParagraph paragraph = chapter.getParagraph(paragraphOffset);
        int sectionLength = paragraph.getSectionLength();
        /**
         * 若片段偏移量在片段长度范围内表示该段落还有片段没有排版完成
         * 终止条件1：超出排版区域范围；2：超出排版内容范围
         */
        while (this.currentY <= this.endY && chapterTypesetter.getSectionOffset() < sectionLength) {
            ISection section = paragraph.getSection(chapterTypesetter.getSectionOffset());
            ISectionTypesetter sectionTypesetter = null;
            if (section instanceof Text) {
                sectionTypesetter = new TextTypesetter();
            } else if (section instanceof Image) {
                sectionTypesetter = new ImageTypesetter();
            } else if (section instanceof Audio) {
                sectionTypesetter = new AudioTypesetter();
            } else if (section instanceof Video) {
                sectionTypesetter = new VideoTypesetter();
            }
            sectionTypesetter.setStartPoint(this.currentX,this.currentY);
            sectionTypesetter.setEndPoint(this.endX,this.endY);

            sectionTypesetter.typeset(chapterTypesetter);
            this.currentX = sectionTypesetter.getEndPoint()[0];
            this.currentY = sectionTypesetter.getEndPoint()[1];
            this.sectionTypesetters.add(sectionTypesetter);
        }

        if (chapterTypesetter.getSectionOffset() >= sectionLength){
            chapterTypesetter.setSectionOffset(0);
            chapterTypesetter.setParagraphOffset(chapterTypesetter.getParagraphOffset() + 1);
        }
    }

    public LinkedList<ISectionTypesetter> getSectionTypesetters() {
        return sectionTypesetters;
    }

    @Override
    public void print() {

    }
}
