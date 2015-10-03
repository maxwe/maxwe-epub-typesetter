package org.maxwe.epub.typesetter.core;

import org.maxwe.epub.parser.core.IChapter;
import org.maxwe.epub.parser.core.INavigation;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-09-07 15:42.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 章节排版
 * 整书排版以章节排版为单位
 * 章节排版提供所有排版入口
 */
public interface IChapterTypesetter {

    /**
     * 获取章节的标题
     * @return
     */
    String getTitle();

    int getIndex();

    /**
     * 获取导航信息
     * @return
     */
    INavigation getNavigation();

    /**
     * 获取该章节排版对应的章节
     * @return
     */
    IChapter getChapter();

    /**
     * 三个定位坐标确定了目前已经排版的位置
     * @return
     */
    int getParagraphOffset();
    int getSectionOffset();
    int getOffset();

    void setParagraphOffset(int paragraphOffset);
    void setSectionOffset(int sectionOffset);
    void setOffset(int offset);

    /**
     * 排版完成的页面集合
     * @return
     */
    LinkedList<APageTypesetter> getPageTypesetters();
    int getPageTypesetterSize();

    /**
     * 排版执行者
     * @param screenWidth
     * @param screenHeight
     * @return
     */
    IChapterTypesetter typeset(int screenWidth,int screenHeight);
    IChapterTypesetter typeset(int screenWidth,int screenHeight,ITypesetterListener typesetterListener);

    /**
     * 设置监听器
     * @param typesetterListener
     */
    void setTypesetterListener(ITypesetterListener typesetterListener);

    /**
     * 打印以检验排版页面的效果
     */
    void print();

}
