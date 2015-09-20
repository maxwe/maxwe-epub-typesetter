package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.parser.core.IParagraph;
import org.maxwe.epub.parser.core.ISection;
import org.maxwe.epub.parser.impl.Audio;
import org.maxwe.epub.parser.impl.Image;
import org.maxwe.epub.parser.impl.Text;
import org.maxwe.epub.parser.impl.Video;
import org.maxwe.epub.typesetter.core.AParagraphTypesetter;
import org.maxwe.epub.typesetter.core.IChapterTypesetter;
import org.maxwe.epub.typesetter.core.ISectionTypesetter;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-09-15 16:40.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 排版规则
 * 段落排版片段层的终止条件有两个
 * 1：超出页面范围
 *  1.1：超出当前页面范围的排版片段元素不被认可，也不会添加到集合中，同时相关的游标也不做任何的改变，该排版片段元素将会在下一页面重新排版
 * 2：当前段落已经排版完成
 *  2.1：移动段落片段的排版游标，结束当前段落排版片段的排版，使页面进入到下一个段落排版片段的排版
 */
public class ParagraphTypesetter extends AParagraphTypesetter {

    private LinkedList<ISectionTypesetter> sectionTypesetters = new LinkedList<ISectionTypesetter>();

    @Override
    public void typeset(IChapterTypesetter chapterTypesetter) {
        int paragraphOffset = chapterTypesetter.getParagraphOffset();

        IParagraph paragraph = chapterTypesetter.getChapter().getParagraph(paragraphOffset);
        int sectionLength = paragraph.getSectionLength();
        /**
         * 若片段偏移量在片段长度范围内表示该段落还有片段没有排版完成
         * 终止条件1：超出排版区域范围；2：超出排版内容范围
         */
        while (this.currentY < this.endY && chapterTypesetter.getSectionOffset() < sectionLength) {
            ISection section = paragraph.getSection(chapterTypesetter.getSectionOffset());
            ISectionTypesetter sectionTypesetter = null;
            if (section instanceof Text) {
                sectionTypesetter = new TextTypesetter();
            } else if (section instanceof Image) {
                sectionTypesetter = new ImageTypesetter();
            } else if (section instanceof Audio) {
                sectionTypesetter = new AudioTypesetter();
            } else if (section instanceof Video) {
                sectionTypesetter = new VideoTypesetter();
            }

            /**
             * 每一个排版片段的起始点总是以当前XY轴的坐标开始的
             */
            sectionTypesetter.setStartPoint(this.currentX, this.currentY);
            /**
             * 每一个排版片段的结束点总是以当前页面规定的XY轴的最大坐标
             */
            sectionTypesetter.setEndPoint(this.endX, this.endY);

            sectionTypesetter.typeset(chapterTypesetter);
            /**
             * 排版完成后更新当前XY轴的位置，这个当前位置如果没有超出页面范围，将作为下个排版元素的起始位置
             */
            this.currentX = sectionTypesetter.getEndPoint()[0];
            this.currentY = sectionTypesetter.getEndPoint()[1];

            /**
             * 识别子排版元素的排版是否是合格的，如果是合格的则添加
             * 这个合格指的是图片、音视频的排版是否超出页面范围
             * 如果超出范围则该元素将进入到下一页面的排版
             */
            if (this.currentY <= this.endY){
                this.sectionTypesetters.add(sectionTypesetter);
            }

            /**
             * 这个设定是根据当前的横向排版策略进行的
             * 每个排版片段排班结束后重置X轴位置的起始点
             */
            this.currentX = this.startX;
        }

        /**
         * 重置排版游标
         */
        if (chapterTypesetter.getSectionOffset() >= sectionLength){
            chapterTypesetter.setSectionOffset(0);
            chapterTypesetter.setParagraphOffset(chapterTypesetter.getParagraphOffset() + 1);
        }
    }

    public LinkedList<ISectionTypesetter> getSectionTypesetters() {
        return sectionTypesetters;
    }

    @Override
    public void print() {
        System.out.println("------------------分段线-----------------");
        for (ISectionTypesetter sectionTypesetter:this.sectionTypesetters){
            sectionTypesetter.print();
        }
    }
}
