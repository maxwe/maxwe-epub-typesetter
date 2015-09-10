package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.typesetter.constant.LayoutStyle;
import org.maxwe.epub.typesetter.core.IChapter;
import org.maxwe.epub.typesetter.core.ISection;
import org.maxwe.epub.typesetter.core.ITypesetter;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-09-07 14:48.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class Page {

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

    public <E extends ITypesetter> E typeset(org.maxwe.epub.parser.core.ISection section) {
        return null;
    }
}
