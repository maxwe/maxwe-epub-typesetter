package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.parser.core.INavigation;
import org.maxwe.epub.parser.core.IParagraph;
import org.maxwe.epub.parser.core.ISection;
import org.maxwe.epub.parser.impl.Audio;
import org.maxwe.epub.parser.impl.Image;
import org.maxwe.epub.parser.impl.Text;
import org.maxwe.epub.parser.impl.Video;
import org.maxwe.epub.typesetter.core.IChapterTypesetter;
import org.maxwe.epub.typesetter.core.IPageTypesetter;
import org.maxwe.epub.typesetter.core.ISectionTypesetter;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-09-12 12:52.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class PageTypesetter implements IPageTypesetter {

    private String title;
    private String index;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int currentX;
    private int currentY;
    private INavigation navigation;
    private int paragraphOffset;
    private int sectionOffset;
    private int offset;
    private LinkedList<ISectionTypesetter> sectionTypesetters = new LinkedList<ISectionTypesetter>();

    public PageTypesetter() {
    }

    public PageTypesetter(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }


    public String getTitle() {
        return this.title;
    }

    public String getIndex() {
        return this.index;
    }

    public PageTypesetter setStartPoint(int x, int y) {
        this.startX = x;
        this.startY = y;
        return this;
    }

    public PageTypesetter setEndPoint(int x, int y) {
        this.endX = x;
        this.endY = y;
        return this;
    }

    public int[] getEndPoint() {
        return new int[0];
    }

    public INavigation getNavigation() {
        return this.navigation;
    }

    public int paragraphOffset() {
        return this.paragraphOffset;
    }

    public int sectionOffset() {
        return this.sectionOffset;
    }

    public int offset() {
        return offset;
    }

    public LinkedList<ISectionTypesetter> getSectionsTypesetter() {
        return this.sectionTypesetters;
    }

    public void typeset(IChapterTypesetter chapterTypesetter) {
        this.paragraphOffset = chapterTypesetter.getParagraphOffset();
        this.sectionOffset = chapterTypesetter.getSectionOffset();
        this.offset = chapterTypesetter.getOffset();

        LinkedList<IParagraph> paragraphs = chapterTypesetter.getChapter().getParagraphs();
        int sizeOfParagraphs = paragraphs.size();
        /**
         * 段落排版
         */
        for (int i = chapterTypesetter.getParagraphOffset(); i < sizeOfParagraphs; i++ ) {
            /**
             * 片段排版
             */
            LinkedList<ISection> sections = paragraphs.get(i).getSections();
            int sizeOfSection = sections.size();
            for (int j = chapterTypesetter.getOffset(); j < sizeOfSection; j++) {
                /**
                 * 元素排版
                 */
                ISection section = sections.get(j);
                if (section instanceof Text) {
                    TextTypesetter textTypesetter = new TextTypesetter();

                    this.sectionTypesetters.add(textTypesetter);
                    Text text = (Text) section;

                    String text1 = text.getText();
                    text1.length();
                } else if (section instanceof Image) {
                    ImageTypesetter imageTypesetter = new ImageTypesetter();
                    this.sectionTypesetters.add(imageTypesetter);
                    /**
                     * Image是单元素对象，排版完成后重置偏移量
                     */
                    chapterTypesetter.setOffset(0);
                } else if (section instanceof Audio) {
                    AudioTypesetter audioTypesetter = new AudioTypesetter();
                    this.sectionTypesetters.add(audioTypesetter);
                    /**
                     * Audio是单元素对象，排版完成后重置偏移量
                     */
                    chapterTypesetter.setOffset(0);
                } else if (section instanceof Video) {
                    VideoTypesetter videoTypesetter = new VideoTypesetter();
                    this.sectionTypesetters.add(videoTypesetter);
                    /**
                     * Video是单元素对象，排版完成后重置偏移量
                     */
                    chapterTypesetter.setOffset(0);
                }
                /**
                 * 一个片段排版完成后片段排版偏移量增加1
                 */
                chapterTypesetter.setSectionOffset(j + 1);
            }
            /**
             * 一个段落排版完成后段落排版偏移量增加1
             */
            chapterTypesetter.setParagraphOffset(i + 1);
        }
    }
}
