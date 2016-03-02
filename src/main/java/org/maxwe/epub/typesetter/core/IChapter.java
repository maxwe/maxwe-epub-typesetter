package org.maxwe.epub.typesetter.core;

import org.maxwe.epub.typesetter.Configure;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-12-26 16:04.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @章节对象
 */
public interface IChapter {
    /**
     * 屏幕操作属性
     * 可以绘制的X轴起始点
     * @return
     */
    int getStartX();
    /**
     * 屏幕操作Y轴属性
     * 可以绘制的起始点
     * @return
     */
    int getStartY();
    /**
     * 屏幕操作属性
     * 可以绘制的X轴结束点
     * @return
     */
    int getEndX();
    /**
     * 屏幕操作属性
     * 可以绘制的Y轴结束点
     * @return
     */
    int getEndY();

    /**
     * 屏幕操作属性
     * 可绘制的屏幕宽度
     * @return
     */
    int getScreenWidth();

    /**
     * 屏幕操作属性
     * 可绘制的屏幕高度
     * @return
     */
    int getScreenHeight();


    /**
     * 章节ID
     * @return
     */
    String getChapterId();


    /**
     * 章节名称
     * @return
     */
    String getChapterName();


    /**
     * 章节顺序
     * @return
     */
    int getChapterIndex();

    LinkedList<IPage> getPages();

    Configure getConfigure();

}
