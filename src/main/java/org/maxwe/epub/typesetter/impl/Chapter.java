package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.parser.core.IParagraph;
import org.maxwe.epub.parser.core.ISection;
import org.maxwe.epub.typesetter.core.IChapter;
import org.maxwe.epub.typesetter.core.IPage;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-09-07 15:41.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class Chapter implements IChapter {
    private org.maxwe.epub.parser.core.IChapter chapter;
    private int paragraphOffset;
    private int sectionOffset;
    private int offset;

    private int currentPageIndex;

    private LinkedList<IPage> pages = new LinkedList<IPage>();


    public Chapter(org.maxwe.epub.parser.core.IChapter chapter){
        this.chapter = chapter;
    }

    public String getTitle() {
        return this.chapter.getTitle();
    }

    public org.maxwe.epub.parser.core.IChapter getChapter() {
        return chapter;
    }

    public void setChapter(org.maxwe.epub.parser.core.IChapter chapter) {
        this.chapter = chapter;
    }

    public int getParagraphOffset() {
        return paragraphOffset;
    }

    public void setParagraphOffset(int paragraphOffset) {
        this.paragraphOffset = paragraphOffset;
    }

    public int getSectionOffset() {
        return sectionOffset;
    }

    public void setSectionOffset(int sectionOffset) {
        this.sectionOffset = sectionOffset;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public LinkedList<IPage> getPages() {
        return this.pages;
    }

    public void typeset() {
        LinkedList<IParagraph> paragraphs = this.chapter.getParagraphs();
        for (int i=this.paragraphOffset;i<paragraphs.size();i++){

        }


        for (int i=this.paragraphOffset;i<paragraphs.size();i++){
            LinkedList<ISection> sections = paragraphs.get(i).getSections();
            for (int j=this.sectionOffset;j<sections.size();j++){
                ISection iSection = sections.get(j);
                if (iSection instanceof org.maxwe.epub.parser.impl.Text){

                }else if (iSection instanceof org.maxwe.epub.parser.impl.Image){

                }else if (iSection instanceof org.maxwe.epub.parser.impl.Audio){

                }else if (iSection instanceof org.maxwe.epub.parser.impl.Video){

                }
            }
        }
    }
}
