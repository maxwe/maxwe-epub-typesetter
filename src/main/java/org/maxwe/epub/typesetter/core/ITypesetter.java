package org.maxwe.epub.typesetter.core;

import org.maxwe.epub.typesetter.constant.LayoutStyle;

import java.io.Serializable;

/**
 * Created by Pengwei Ding on 2015-09-05 16:04.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 排版接口
 */
public interface ITypesetter extends Serializable {

    /**
     * 设置XY轴的起始排版点坐标
     * @param x X轴坐标
     * @param y Y轴坐标
     * @param <E> 子类
     * @return 子类实例
     */
    <E extends ITypesetter> E setStartPoint(int x,int y);

    /**
     * 设置最大可排版的结束点
     * @param x 可用的绘制区域的宽
     * @param y 可用的绘制区域的高
     * @param <E> 子类
     * @return 子类实例
     */
    <E extends ITypesetter> E setEndPoint(int x,int y);

    /**
     * 实际的排版结束点
     * @return X轴坐标 Y轴坐标
     */
    int[] getEndPoint();

    <E extends ITypesetter> E setTypeface(Object typeface);
    <E extends ITypesetter> E setFontSize(int size);
    <E extends ITypesetter> E setLayoutStyle(LayoutStyle layoutStyle);

    void typeset(IChapterTypesetter chapterTypesetter);
}
