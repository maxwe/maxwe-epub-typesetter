package org.maxwe.epub.typesetter.core;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-09-07 15:42.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public interface IChapter {
    String getTitle();
    org.maxwe.epub.parser.core.IChapter getChapter();
    int getParagraphOffset();
    int getSectionOffset();
    int getOffset();
    LinkedList<IPage> getPages();
    void typeset();
}
