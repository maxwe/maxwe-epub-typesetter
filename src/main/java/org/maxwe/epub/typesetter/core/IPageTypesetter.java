package org.maxwe.epub.typesetter.core;

import org.maxwe.epub.parser.core.INavigation;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-09-05 16:04.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public interface IPageTypesetter extends ITypesetter{

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
     * 提供当页面所属的章节
     * @return
     */
    INavigation getNavigation();

    /**
     * 所属段落在章节中的偏移量
     * @return
     */
    int getStartParagraphOffset();
    int getEndParagraphOffset();

    /**
     * 所属片段在段落中的偏移量
     * @return
     */
    int getStartSectionOffset();
    int getEndSectionOffset();

    /**
     * 所属文字在片段中的偏移量
     * @return
     */
    int getStartOffset();
    int getEndOffset();

    LinkedList<ISectionTypesetter> getSectionsTypesetter();

}
