package org.maxwe.epub.typesetter.core;

import org.maxwe.epub.typesetter.constant.LayoutStyle;

import java.io.Serializable;

/**
 * Created by Pengwei Ding on 2015-09-05 16:04.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 排版接口
 * 整体架构的设计描述如下
 * 排版是按照整个章节进行的，产生章节排版对象，即章节排版对象产生了页面对象集合，为保证页面排版内容的连续性，章节排版对象应保持相关数据
 * TODO 保持相关排版数据有两种设计方式，1：排版游标（本次处理采用的方法，先排版，根据相关条件判断，继续排版或者回滚）；2：裁剪法(根据相关条件先判断，符合条件就排版，排版后就裁剪源信息)
 * 排版元素分为页排版对象、段落排版对象、片段排版对象，
 * 其中片段排版对象指的是段落内连续的一串文字作为一个文字片段排版对象，每个图片作为图片片段排版对象，每个音频作为音频片段排版对象、每个视频作为视频片段排版对象
 * @TODO 目前这个设计的缺陷在应该考虑图片以及音视频的连续性，不应该把一串文字和单个的图片以及音视频放在同一个逻辑水平线上，都应该看做一个集合处理，将会获得统一的处理方法
 *
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
