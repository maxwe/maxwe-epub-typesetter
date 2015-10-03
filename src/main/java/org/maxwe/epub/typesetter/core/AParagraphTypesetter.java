package org.maxwe.epub.typesetter.core;

import org.maxwe.epub.typesetter.impl.ASectionTypesetter;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-09-15 16:34.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 段落排版
 * 在排版设计中，段落排版作为页面排版的子级元素
 * 段落要能够定位自己所在的页面信息，以及所在的章节偏移
 */
public abstract class AParagraphTypesetter extends ASectionTypesetter {


    public abstract LinkedList<ISectionTypesetter> getSectionTypesetters();
}
