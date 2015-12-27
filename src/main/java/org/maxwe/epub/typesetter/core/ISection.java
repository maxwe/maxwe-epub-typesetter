package org.maxwe.epub.typesetter.core;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-12-26 17:21.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 元数据片段对象操作方式
 * 文字片段、图片片段、音频片段、视频片段应该实现该接口
 */
public interface ISection {

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
     * 片段在解析属性中的段落属性中的起始位置
     * @return
     */
    int getStartOffsetInParagraph();
    /**
     * 解析属性操作
     * 片段在解析属性中的段落属性中的结束位置
     * @return
     */
    int getEndOffsetInParagraph();

    /**
     * 片段对象中的所包含的元数据
     * @return
     */
    LinkedList<IMeta> getMetas();
}
