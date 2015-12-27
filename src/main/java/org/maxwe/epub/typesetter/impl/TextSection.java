package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.typesetter.core.AChapter;
import org.maxwe.epub.typesetter.core.AParagraph;
import org.maxwe.epub.typesetter.core.ASection;

/**
 * Created by Pengwei Ding on 2015-12-26 19:19.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class TextSection extends ASection {

    protected TextSection(AChapter chapter, AParagraph paragraph) {
        super(chapter, paragraph);
    }

    @Override
    protected ASection typeset() {
        return null;
    }
}
