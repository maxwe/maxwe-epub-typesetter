package org.maxwe.epub.typesetter.core;

import org.maxwe.epub.parser.core.IChapter;
import org.maxwe.epub.parser.core.INavigation;

/**
 * Created by Pengwei Ding on 2015-09-07 15:42.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public interface IChapterTypesetter {
    String getTitle();
    INavigation getNavigation();
    IChapter getChapter();
    int getParagraphOffset();
    int getSectionOffset();
    int getOffset();
    void setParagraphOffset(int paragraphOffset);
    void setSectionOffset(int sectionOffset);
    void setOffset(int offset);
}
