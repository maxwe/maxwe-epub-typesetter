package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.typesetter.core.AChapter;
import org.maxwe.epub.typesetter.core.APage;
import org.maxwe.epub.typesetter.core.ASection;
import org.maxwe.epub.typesetter.core.IMeta;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-12-26 19:19.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class TextSection extends ASection {

    protected TextSection(AChapter chapter, APage page) {
        super(chapter, page);
    }

    @Override
    public LinkedList<IMeta> getMetas() {
        return null;
    }

    @Override
    protected ASection typeset() {
        return null;
    }
}
