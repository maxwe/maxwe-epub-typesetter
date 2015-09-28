package org.maxwe.epub.typesetter.core;

import org.maxwe.epub.parser.core.INavigation;
import org.maxwe.epub.typesetter.impl.ASectionTypesetter;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-09-05 16:04.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public abstract class APageTypesetter extends ASectionTypesetter{

    /**
     * 提供标题
     * @return
     */
    public abstract String getTitle();

    /**
     * 提供当前的页码
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
     * 所属段落在章节中的偏移量
     * @return
     */
    public abstract int getStartParagraphOffset();
    public abstract int getEndParagraphOffset();

    /**
     * 所属片段在段落中的偏移量
     * @return
     */
    public abstract int getStartSectionOffset();
    public abstract int getEndSectionOffset();

    /**
     * 所属文字在片段中的偏移量
     * @return
     */
    public abstract int getStartOffset();
    public abstract int getEndOffset();

    public abstract LinkedList<AParagraphTypesetter> getParagraphTypesetters();

}
