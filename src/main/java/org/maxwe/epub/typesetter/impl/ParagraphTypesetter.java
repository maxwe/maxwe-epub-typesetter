package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.parser.core.IChapter;
import org.maxwe.epub.typesetter.core.IChapterTypesetter;

/**
 * Created by Pengwei Ding on 2015-09-15 16:40.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class ParagraphTypesetter extends ASectionTypesetter  {

    @Override
    public void typeset(IChapterTypesetter chapterTypesetter) {
        int paragraphOffset = chapterTypesetter.getParagraphOffset();
        IChapter chapter = chapterTypesetter.getChapter();
        int paragraphLength = chapter.getParagraphLength();
    }
}
