package org.maxwe.epub.typesetter.core;

import org.maxwe.epub.parser.core.INavigation;
import org.maxwe.epub.typesetter.impl.ASectionTypesetter;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-09-05 16:04.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 页面排版
 * 在排版的设计中，页面排版是章节排版的子级元素
 * 页面要能够定位自己在章节中的位置、起始偏移量、结束偏移量等信息
 */
public abstract class APageTypesetter extends ASectionTypesetter{

    /**
     * 提供标题
     * @return
     */
    public abstract String getTitle();

    /**
     * 提供当前页面在章节中的页码
     * @return
     */
    public abstract int getIndex();
    public abstract void setIndex(int index);

    /**
     * 提供当页面所属的章节
     * @return
     */
    public abstract INavigation getNavigation();

    /**
     * 所属段落在章节中的起始和结束偏移量
     * @return
     */
    public abstract int getStartParagraphOffset();
    public abstract int getEndParagraphOffset();

    /**
     * 所属片段在段落中的起始和结束偏移量
     * @return
     */
    public abstract int getStartSectionOffset();
    public abstract int getEndSectionOffset();

    /**
     * 所属文字在片段中的起始和结束偏移量
     * @return
     */
    public abstract int getStartOffset();
    public abstract int getEndOffset();

    public abstract LinkedList<AParagraphTypesetter> getParagraphTypesetters();

}
