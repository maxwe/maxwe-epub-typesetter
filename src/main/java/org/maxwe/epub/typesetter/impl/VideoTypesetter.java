package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.typesetter.constant.LayoutStyle;
import org.maxwe.epub.typesetter.core.IChapterTypesetter;
import org.maxwe.epub.typesetter.core.ISectionTypesetter;
import org.maxwe.epub.typesetter.core.ITypesetter;

/**
 * Created by Pengwei Ding on 2015-09-13 09:16.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class VideoTypesetter implements ISectionTypesetter {
    public <E extends ITypesetter> E setStartPoint(int x, int y) {
        return null;
    }

    public <E extends ITypesetter> E setEndPoint(int x, int y) {
        return null;
    }

    public int[] getEndPoint() {
        return new int[0];
    }

    public <E extends ITypesetter> E setTypeface(Object typeface) {
        return null;
    }

    public <E extends ITypesetter> E setFontSize(int size) {
        return null;
    }

    public <E extends ITypesetter> E setLayoutStyle(LayoutStyle layoutStyle) {
        return null;
    }

    public void typeset(IChapterTypesetter chapterTypesetter) {

    }
}
