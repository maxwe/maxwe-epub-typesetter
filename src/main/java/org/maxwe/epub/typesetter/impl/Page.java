package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.parser.core.IParagraph;
import org.maxwe.epub.parser.impl.Chapter;
import org.maxwe.epub.typesetter.constant.LayoutStyle;
import org.maxwe.epub.typesetter.core.IChapter;
import org.maxwe.epub.typesetter.core.IPage;
import org.maxwe.epub.typesetter.core.ISection;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-09-07 14:48.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class Page implements IPage {

    private LinkedList<ISection> sections = new LinkedList<ISection>();

    private IChapter chapterTypesetter;

    public Page(IChapter chapterTypesetter){
        this.chapterTypesetter = chapterTypesetter;
    }

    public String getTitle() {
        return this.chapterTypesetter.getTitle();
    }

    public String getIndex() {
        return null;
    }

    public LinkedList<ISection> getSections() {
        return this.sections;
    }

    public Page setStartX(int x) {
        return this;
    }

    public Page setStartY(int y) {
        return null;
    }

    public Page setStartPoint(int x, int y) {
        return null;
    }

    public Page setTypesetterAreaWidth(int width) {
        return null;
    }

    public Page setTypesetterAreaHeight(int height) {
        return null;
    }

    public Page setTypesetterArea(int width, int height) {
        return null;
    }

    public Page setTypeface(Object typeface) {
        return null;
    }

    public Page setFontSize(int size) {
        return null;
    }

    public Page setLayoutStyle(LayoutStyle layoutStyle) {
        return null;
    }

    public Page typeset() {
        int paragraphOffset = this.chapterTypesetter.getParagraphOffset();
        int sectionOffset = this.chapterTypesetter.getSectionOffset();
        int offset = this.chapterTypesetter.getOffset();
        Chapter chapter = this.chapterTypesetter.getChapter();
        LinkedList<IParagraph> paragraphs = chapter.getParagraphs();
        for (int i=paragraphOffset;i<paragraphs.size();i++){
            IParagraph paragraph = paragraphs.get(i);
            LinkedList<org.maxwe.epub.parser.core.ISection> sections = paragraph.getSections();
            for (int j=sectionOffset;j<sections.size();j++){
            }
        }

        return null;
    }
}
