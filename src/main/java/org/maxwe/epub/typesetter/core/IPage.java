package org.maxwe.epub.typesetter.core;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-12-26 15:40.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @排版后的页面对象
 */
public interface IPage {

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
     * 获取页面所在的章节ID
     * @return
     */
    String getChapterId();
    /**
     * 解析属性操作
     * 获取页面所在的章节名称
     * @return
     */
    String getChapterName();
    /**
     * 解析属性操作
     * 获取页面所在的章节顺序
     * @return
     */
    int getChapterIndex();

    /**
     * 该页面起始的段落在章节中的顺序
     * @return
     */
    int getStartParagraphIndexInChapter();

    /**
     * 该页面结束的段落在章节中的顺序
     * @return
     */
    int getEndParagraphIndexInChapter();

    /**
     * 该页面起始的片段在所属段落中的顺序
     * @return
     */
    int getStartSectionIndexInParagraph();

    /**
     * 该页面结束的片段在所属段落中的顺序
     * @return
     */
    int getEndSectionInParagraph();

    /**
     * 该页面起始的元数据在所属片段中的顺序
     * @return
     */
    int getStartMetaIndexInSection();
    /**
     * 该页面结束的元数据在所属片段中的顺序
     * @return
     */
    int getEndMetaIndexInSection();


    /**
     * 该页面在章节中的顺序
     * @return
     */
    int getIndexInChapter();

    /**
     * 该页面在全书中的顺序
     * @return
     */
    int getIndexInBook();

    /**
     * 页面中的段落数据
     * @return
     */
    LinkedList<IParagraph> getParagraphs();

    IConfigure getConfigure();

    void print();
}
