package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.parser.core.ISection;
import org.maxwe.epub.parser.impl.Text;
import org.maxwe.epub.typesetter.constant.Configer;
import org.maxwe.epub.typesetter.core.IChapterTypesetter;

import java.util.LinkedHashMap;

/**
 * Created by Pengwei Ding on 2015-09-12 12:57.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class TextTypesetter extends ASectionTypesetter {

    private LinkedHashMap<Integer, LinkedHashMap<Integer, String>> words = new LinkedHashMap<Integer, LinkedHashMap<Integer, String>>();

    @Override
    public void typeset(IChapterTypesetter chapterTypesetter) {
        int paragraphOffset = chapterTypesetter.getParagraphOffset();
        int sectionOffset = chapterTypesetter.getSectionOffset();
        int offset = chapterTypesetter.getOffset();
        ISection section = chapterTypesetter.getChapter().getParagraph(paragraphOffset).getSection(sectionOffset);

        Text text = ((Text) section);
        int originLength = text.getLength();

        /**
         * 行排版
         * 终止条件1：超出排版区域范围；2：超出排版内容范围
         */
        while (this.currentY + Configer.CONFIGER_WORD_SIZE <= this.endY && offset < originLength) {
            LinkedHashMap<Integer, String> lineWords = new LinkedHashMap<Integer, String>();
            /**
             * 列排版
             * 即行间排版
             * 终止条件1：超出排版区域范围；2：超出排版内容范围
             */
            while (this.currentX + Configer.CONFIGER_WORD_SIZE <= this.endX && offset < originLength) {
                String word = text.getWord(offset);
                lineWords.put(this.currentX, word);
                /**
                 * 修改偏移量
                 */
                chapterTypesetter.setOffset(offset++);
                /**
                 * X轴坐标的移动：当前字的位置+单个文字所占用大小+字间距=下一个字的位置
                 */
                this.currentX = this.currentX + Configer.CONFIGER_WORD_SIZE + Configer.CONFIGER_WORD_SPACING;
            }
            this.words.put(this.currentX, lineWords);
            /**
             * Y轴坐标的一定：当前行的位置+单个文字所占用的大小+行间距=下一行的位置
             */
            this.currentY = this.currentY + Configer.CONFIGER_WORD_SIZE + Configer.CONFIGER_LINE_SPACING;
        }
    }

    public LinkedHashMap<Integer, LinkedHashMap<Integer, String>> getWords() {
        return words;
    }
}
