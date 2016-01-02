package org.maxwe.epub.typesetter.core;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-12-26 17:22.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 段落对象操作方式
 */
public interface IParagraph {

    /**
     * 屏幕属性操作
     * 片段在屏幕上X轴的起始位置
     * @return
     */
    int getStartX();
    /**
     * 屏幕属性操作
     * 片段在屏幕上Y轴的起始位置
     * @return
     */
    int getStartY();
    /**
     * 屏幕属性操作
     * 片段在屏幕上X轴的结束位置
     * @return
     */
    int getEndX();
    /**
     * 屏幕属性操作
     * 片段在屏幕上Y轴的结束位置
     * @return
     */
    int getEndY();
    /**
     * 屏幕属性操作
     * 片段在屏幕上X轴可占用的最大位置
     * @return
     */
    int getScreenWidth();
    /**
     * 屏幕属性操作
     * 片段在屏幕上Y轴可占用的最大位置
     * @return
     */
    int getScreenHeight();

    /**
     * 解析属性操作
     * 段落在解析属性中的章节属性中的起始位置
     * @return
     */
    int getStartOffsetInChapter();
    /**
     * 解析属性操作
     * 段落在解析属性中的章节属性中的结束位置
     * @return
     */
    int getEndOffsetInChapter();

    /**
     * 段落对象中的所包含的片段数据
     * @return
     */
    LinkedList<ISection> getSections();

    void print();
}
