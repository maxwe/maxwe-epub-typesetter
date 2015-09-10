package org.maxwe.epub.typesetter.core;

import org.maxwe.epub.typesetter.constant.LayoutStyle;

import java.io.Serializable;

/**
 * Created by Pengwei Ding on 2015-09-05 16:04.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 排版接口
 */
public interface ITypesetter extends Serializable {

    <E extends ITypesetter> E setStartX(int x);
    <E extends ITypesetter> E setStartY(int y);
    <E extends ITypesetter> E setStartPoint(int x,int y);
    <E extends ITypesetter> E setTypesetterAreaWidth(int width);
    <E extends ITypesetter> E setTypesetterAreaHeight(int height);
    <E extends ITypesetter> E setTypesetterArea(int width,int height);

    <E extends ITypesetter> E setTypeface(Object typeface);
    <E extends ITypesetter> E setFontSize(int size);
    <E extends ITypesetter> E setLayoutStyle(LayoutStyle layoutStyle);

    <E extends org.maxwe.epub.parser.core.ISection> void typeset(E section);
}
