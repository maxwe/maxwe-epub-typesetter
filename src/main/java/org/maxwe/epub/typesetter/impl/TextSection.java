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
        /**
         *  避免初始cursorY值处于一个尴尬的范围：有空间但是无法写下一行字而造成死循环
         *  如 cursorY<endY && cursorY+字号>endY
         *  这个时候应该返回为null，上层调用处理值为null的情况
         *  一旦发生这个情况，应把页面的游标Y指向Y的最大值
         */
        if (this.getPage().getCursorY() + Configer.CONFIGER_WORD_SIZE > this.getPage().getEndY()) {
            this.getPage().setCursorY(this.getPage().getEndY());
            System.out.println("====预计这行代码根本不会执行====");
            return null;
        }

        int paragraphOffset = this.getChapter().getCurrentParagraphIndexInChapter();
        int sectionOffset = this.getChapter().getCurrentSectionIndexInParagraph();
        int metaOffset = this.getChapter().getCurrentMetaIndexInSection();

        Text textSection = (Text) this.getChapter().getParsedChapter().getParagraph(paragraphOffset).getSection(sectionOffset);
        int metaLength = textSection.getLength();
        /**
         * 行排版
         * 终止条件
         * 1：超出屏幕排版区域范围
         * 2：超出内容排版区域范围
         */
        while (this.getCursorY() + Configer.CONFIGER_WORD_SIZE <= this.getEndY() && metaOffset < metaLength) {

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
            while (this.getCursorX() + Configer.CONFIGER_WORD_SIZE <= this.getEndX() && metaOffset < metaLength) {
                String word = textSection.getWord(metaOffset);
                lineWords.put(this.getCursorX(), word);
                this.metas.add(new Word(this.getCursorX(),this.getCursorY(),0,0,metaOffset,word));
                /**
                 * 修改片段级别的偏移量
                 */
                this.getChapter().setCurrentMetaIndexInSection(metaOffset++);
                /**
                 * X轴坐标的移动：当前字的位置+单个文字所占用大小+字间距=下一个字的位置
                 */
                this.getPage().setCursorX(this.getPage().getCursorX() + Configer.CONFIGER_WORD_SIZE + Configer.CONFIGER_WORD_SPACING);
            }

            /**
             * 一个行循环完毕后
             * 重置相关的游标
             * 开始下一个行循环
             */
            this.getPage().setCursorX(this.getPage().getStartX());

            if (lineWords.size() > 0){
                this.words.put(this.getPage().getCursorY(), lineWords);
            }

            /**
             * Y轴坐标的一定：当前行的位置+单个文字所占用的大小+行间距=下一行的位置
             */
            this.getPage().setCursorY(this.getPage().getCursorY() + Configer.CONFIGER_WORD_SIZE + Configer.CONFIGER_LINE_SPACING);
        }

        if (this.getPage().getCursorY() + Configer.CONFIGER_WORD_SIZE > this.getPage().getEndY()) {
            this.getPage().setCursorY(this.getPage().getEndY());
        }

        if (metaOffset >= metaLength) {
            /**
             * 片段中的文字已经排版完毕
             * 重置片段级别内部的游标
             * 并且使片段级别的游标向下游动一个单位
             */
            this.getChapter().setCurrentMetaIndexInSection(0);
            this.getChapter().setCurrentSectionIndexInParagraph(this.getChapter().getCurrentSectionIndexInParagraph() + 1);
        }
        return this;
    }
}
