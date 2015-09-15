package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.typesetter.constant.LayoutStyle;
import org.maxwe.epub.typesetter.core.IChapterTypesetter;
import org.maxwe.epub.typesetter.core.ISectionTypesetter;

/**
 * Created by Pengwei Ding on 2015-09-15 16:53.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public abstract class ASectionTypesetter implements ISectionTypesetter{
    protected int startX;
    protected int startY;
    protected int endX;
    protected int endY;
    protected int currentX;
    protected int currentY;

    public ASectionTypesetter setStartPoint(int x, int y) {
        this.startX = x;
        this.startY = y;
        this.currentX = this.startX;
        this.currentY = this.startY;
        this.endX = this.startX;
        this.endY = this.startY;
        return this;
    }

    public ASectionTypesetter setEndPoint(int x, int y) {
        this.endX = x;
        this.endY = y;
        return this;
    }

    public int[] getEndPoint() {
        return new int[]{currentX,currentY};
    }

    public ASectionTypesetter setTypeface(Object typeface) {
        return this;
    }

    public ASectionTypesetter setFontSize(int size) {
        return this;
    }

    public ASectionTypesetter setLayoutStyle(LayoutStyle layoutStyle) {
        return this;
    }

    public abstract void typeset(IChapterTypesetter chapterTypesetter);
}
