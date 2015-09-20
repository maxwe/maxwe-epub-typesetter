package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.parser.core.ISection;
import org.maxwe.epub.parser.impl.Text;
import org.maxwe.epub.typesetter.constant.Configer;
import org.maxwe.epub.typesetter.core.IChapterTypesetter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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
        int currentOffset = offset;

        /**
         * 行排版
         * 终止条件1：超出排版区域范围；2：超出排版内容范围
         */
        while (this.currentY + Configer.CONFIGER_WORD_SIZE <= this.endY && currentOffset < originLength) {
            LinkedHashMap<Integer, String> lineWords = new LinkedHashMap<Integer, String>();
            /**
             * 列排版
             * 即行间排版
             * 终止条件1：超出排版区域范围；2：超出排版内容范围
             */
            while (this.currentX + Configer.CONFIGER_WORD_SIZE <= this.endX && currentOffset < originLength) {
                String word = text.getWord(currentOffset);
                lineWords.put(this.currentX, word);
                /**
                 * 修改偏移量
                 */
                chapterTypesetter.setOffset(currentOffset++);
                if (currentOffset >= originLength){
                    chapterTypesetter.setOffset(0);
                    chapterTypesetter.setSectionOffset(chapterTypesetter.getSectionOffset() + 1);
                }
                /**
                 * X轴坐标的移动：当前字的位置+单个文字所占用大小+字间距=下一个字的位置
                 */
                this.currentX = this.currentX + Configer.CONFIGER_WORD_SIZE + Configer.CONFIGER_WORD_SPACING;
            }

            this.currentX = this.startX;
            this.words.put(this.currentY, lineWords);
            /**
             * Y轴坐标的一定：当前行的位置+单个文字所占用的大小+行间距=下一行的位置
             */
            this.currentY = this.currentY + Configer.CONFIGER_WORD_SIZE + Configer.CONFIGER_LINE_SPACING;

            /**
             * 上层的排版依据的是在页面范围之内的排版才能添加到排版集合中
             * 此处消除当前Y轴的文字大小以及行间距的提前量
             */
            if (this.currentY > this.endY){
                this.currentY = this.endY;
            }
        }

        /**
         * 受限于整体架构的设计
         * 终止条件比较特殊
         * 需要文字排版完成
         */
        if (currentOffset >= originLength){
            chapterTypesetter.setOffset(0);
            chapterTypesetter.setSectionOffset(chapterTypesetter.getSectionOffset() + 1);
        }

    }

    public LinkedHashMap<Integer, LinkedHashMap<Integer, String>> getWords() {
        return words;
    }

    @Override
    public void print() {
        Set<Map.Entry<Integer, LinkedHashMap<Integer, String>>> lineEntries = this.words.entrySet();
        for (Map.Entry<Integer, LinkedHashMap<Integer, String>> lineEntry : lineEntries) {
            Integer lineY = lineEntry.getKey();
            LinkedHashMap<Integer, String> lineValue = lineEntry.getValue();
            Set<Map.Entry<Integer, String>> entries = lineValue.entrySet();
            for (Map.Entry<Integer, String> entry : entries) {
                Integer key = entry.getKey();
                String value = entry.getValue();
                System.out.print(value + "{" + String.format("% 4d", key) + "," + String.format("% 4d", lineY) + "} ");
            }
            System.out.println("");
        }
    }
}
