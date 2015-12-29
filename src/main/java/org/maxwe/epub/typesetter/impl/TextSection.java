package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.parser.impl.Text;
import org.maxwe.epub.typesetter.constant.Configer;
import org.maxwe.epub.typesetter.core.AChapter;
import org.maxwe.epub.typesetter.core.APage;
import org.maxwe.epub.typesetter.core.ASection;
import org.maxwe.epub.typesetter.core.IMeta;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-12-26 19:19.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class TextSection extends ASection {
    private LinkedHashMap<Integer, LinkedHashMap<Integer, String>> words = new LinkedHashMap<Integer, LinkedHashMap<Integer, String>>();
    private LinkedList<IMeta> metas = new LinkedList<IMeta>();

    protected TextSection(AChapter chapter, APage page) {
        super(chapter, page);
    }

    @Override
    public LinkedList<IMeta> getMetas() {
        return this.metas;
    }

    @Override
    protected ASection typeset() {
        int paragraphOffset = this.getChapter().getCurrentParagraphIndexInChapter();
        int sectionOffset = this.getChapter().getCurrentSectionIndexInParagraph();
        int metaOffset = this.getChapter().getCurrentMetaIndexInSection();



        Text textSection = (Text) this.getChapter().getParsedChapter().getParagraph(paragraphOffset).getSection(sectionOffset);
        int originLength = textSection.getLength();
        int currentOffset = metaOffset;

        System.out.println("===开始=== ：屏幕cursorX=" + this.getPage().getCursorX() + ", cursorY=" + this.getPage().getCursorY() + ", 段落=" + this.getChapter().getCurrentParagraphIndexInChapter() + ", 片段=" + this.getChapter().getCurrentSectionIndexInParagraph() + ", 元素=" + metaOffset + ", 总长=" + originLength + ", 当前=" + currentOffset);
        /**
         * 行排版
         * 终止条件
         * 1：超出屏幕排版区域范围
         * 2：超出内容排版区域范围
         */
        while (this.getCursorY() + Configer.CONFIGER_WORD_SIZE <= this.getEndY() && currentOffset < originLength) {
            System.out.println("      ===行循环-开始=== ：屏幕cursorX=" + this.getPage().getCursorX() + ", cursorY=" + this.getPage().getCursorY() + ", 段落=" + this.getChapter().getCurrentParagraphIndexInChapter() + ", 片段=" + this.getChapter().getCurrentSectionIndexInParagraph() + ", 元素=" + metaOffset + ", 总长=" + originLength + ", 当前=" + currentOffset);

            /**
             * 行存储
             */
            LinkedHashMap<Integer, String> lineWords = new LinkedHashMap<Integer, String>();
            /**
             * 列排版
             * 即行间排版
             * 终止条件
             * 1：超出排版区域范围
             * 2：超出排版内容范围
             */
            while (this.getCursorX() + Configer.CONFIGER_WORD_SIZE <= this.getEndX() && currentOffset < originLength) {
                System.out.println("            ===列循环-开始=== ：屏幕cursorX=" + this.getPage().getCursorX() + ", cursorY=" + this.getPage().getCursorY() + ", 段落=" + this.getChapter().getCurrentParagraphIndexInChapter() + ", 片段=" + this.getChapter().getCurrentSectionIndexInParagraph() + ", 元素=" + metaOffset + ", 总长=" + originLength + ", 当前=" + currentOffset);
                String word = textSection.getWord(currentOffset);
                lineWords.put(this.getCursorX(), word);
                this.metas.add(new Word(this.getCursorX(),this.getCursorY(),0,0,currentOffset,word));
                /**
                 * 修改片段级别的偏移量
                 */
                this.getChapter().setCurrentMetaIndexInSection(currentOffset++);
                /**
                 * 检测内容边界
                 */
                if (currentOffset >= originLength) {
                    /**
                     * 片段中的文字已经排版完毕
                     * 重置片段级别内部的游标
                     * 并且使片段级别的游标向下游动一个单位
                     */
                    this.getChapter().setCurrentMetaIndexInSection(0);
                    this.getChapter().setCurrentSectionIndexInParagraph(this.getChapter().getCurrentSectionIndexInParagraph() + 1);
                }
                /**
                 * X轴坐标的移动：当前字的位置+单个文字所占用大小+字间距=下一个字的位置
                 */
                this.setCursorX(this.getCursorX() + Configer.CONFIGER_WORD_SIZE + Configer.CONFIGER_WORD_SPACING);
                System.out.println("            ===列循环-结束=== ：屏幕cursorX=" + this.getPage().getCursorX() + ", cursorY=" + this.getPage().getCursorY() + ", 段落=" + this.getChapter().getCurrentParagraphIndexInChapter() + ", 片段=" + this.getChapter().getCurrentSectionIndexInParagraph() + ", 元素=" + metaOffset + ", 总长=" + originLength + ", 当前=" + currentOffset + ", 文字=" + word);
            }

            /**
             * 一个行循环完毕后
             * 重置相关的游标
             * 开始下一个行循环
             */
            this.setCursorX(this.getStartX());
            this.words.put(this.getCursorY(), lineWords);

            /**
             * Y轴坐标的一定：当前行的位置+单个文字所占用的大小+行间距=下一行的位置
             */
            this.setCursorY(this.getCursorY() + Configer.CONFIGER_WORD_SIZE + Configer.CONFIGER_LINE_SPACING);
            this.getPage().setCursorY(this.getCursorY());

            /**
             * 上层的排版依据的是在页面范围之内的排版才能添加到排版集合中
             * 此处消除当前Y轴的文字大小以及行间距的提前量
             */
            if (this.getCursorY() > this.getPage().getEndY()) {
                this.getPage().setCursorY(this.getPage().getEndY());
            }
            System.out.println("      ===行循环-结束=== ：屏幕cursorX=" + this.getPage().getCursorX() + ", cursorY=" + this.getPage().getCursorY() + ", 段落=" + this.getChapter().getCurrentParagraphIndexInChapter() + ", 片段=" + this.getChapter().getCurrentSectionIndexInParagraph() + ", 元素=" + metaOffset + ", 总长=" + originLength + ", 当前=" + currentOffset);
        }

        /**
         *  避免初始值过大造成的死循环
         */
        if (this.getPage().getCursorY() + Configer.CONFIGER_WORD_SIZE > this.getPage().getEndY()) {
            this.getPage().setCursorY(this.getPage().getEndY());
        }

        /**
         * 由于解析的架构设计是单个的替换对象直接看做一个section
         * 所以受限于整体架构的设计
         * 终止条件比较特殊
         * 需要文字排版完成
         */
        if (currentOffset >= originLength) {
            this.getChapter().setCurrentMetaIndexInSection(0);
            this.getChapter().setCurrentSectionIndexInParagraph(this.getChapter().getCurrentSectionIndexInParagraph() + 1);
        }
//
//        if (this.getChapter().getCurrentSectionIndexInParagraph() >= this.getChapter().getParsedChapter().getParagraph(this.getChapter().getCurrentParagraphIndexInChapter()).getSectionLength()){
//            this.getChapter().setCurrentParagraphIndexInChapter(this.getChapter().getCurrentParagraphIndexInChapter() + 1);
//        }

        System.out.println("===结束=== ：屏幕cursorX=" + this.getPage().getCursorX() + ", cursorY=" + this.getPage().getCursorY() + ", 段落=" + this.getChapter().getCurrentParagraphIndexInChapter() + ", 片段=" + this.getChapter().getCurrentSectionIndexInParagraph() + ", 元素=" + metaOffset + ", 总长=" + originLength + ", 当前=" + currentOffset);

        return this;
    }
}
