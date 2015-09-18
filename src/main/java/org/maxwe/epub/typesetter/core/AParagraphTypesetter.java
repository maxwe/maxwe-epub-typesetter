package org.maxwe.epub.typesetter.core;

import org.maxwe.epub.typesetter.impl.ASectionTypesetter;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-09-15 16:34.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public abstract class AParagraphTypesetter extends ASectionTypesetter {

    public abstract LinkedList<ISectionTypesetter> getSectionTypesetters();
}
