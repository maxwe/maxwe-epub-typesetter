package org.maxwe.epub.typesetter.core;

import org.maxwe.epub.parser.core.INavigation;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-09-05 16:04.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public interface IPageTypesetter{

    /**
     * 提供标题
     * @return
     */
    String getTitle();

    /**
     * 提供当前的页码
     * @return
     */
    String getIndex();

    /**
     * 设置XY轴的起始排版点坐标
     * @param x X轴坐标
     * @param y Y轴坐标
     * @param <E> 子类
     * @return 子类实例
     */
    <E extends IPageTypesetter> E setStartPoint(int x,int y);

    /**
     * 设置最大可排版的结束点
     * @param x 可用的绘制区域的宽
     * @param y 可用的绘制区域的高
     * @param <E> 子类
     * @return 子类实例
     */
    <E extends IPageTypesetter> E setEndPoint(int x,int y);

    /**
     * 实际的排版结束点
     * @return X轴坐标 Y轴坐标
     */
    int[] getEndPoint();

    /**
     * 提供当页面所属的章节
     * @return
     */
    INavigation getNavigation();

    /**
     * 所属段落在章节中的偏移量
     * @return
     */
    int paragraphOffset();

    /**
     * 所属片段在段落中的偏移量
     * @return
     */
    int sectionOffset();

    /**
     * 所属文字在片段中的偏移量
     * @return
     */
    int offset();

    LinkedList<ISectionTypesetter> getSectionsTypesetter();

    void typeset(IChapterTypesetter chapterTypesetter);
}
