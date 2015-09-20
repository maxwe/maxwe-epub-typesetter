package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.parser.core.ISection;
import org.maxwe.epub.parser.impl.Video;
import org.maxwe.epub.typesetter.core.IChapterTypesetter;

/**
 * Created by Pengwei Ding on 2015-09-13 09:16.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class VideoTypesetter extends ASectionTypesetter {

    private String imagePath;
    /**
     * 默认视频文件比例
     */
    private final int scaleWidth = 16;
    private final int scaleHeight = 9;

    public String getImagePath() {
        return this.imagePath;
    }

    @Override
    public void typeset(IChapterTypesetter chapterTypesetter) {
        int paragraphOffset = chapterTypesetter.getParagraphOffset();
        int sectionOffset = chapterTypesetter.getSectionOffset();
        int offset = chapterTypesetter.getOffset();

        ISection section = chapterTypesetter.getChapter().getParagraph(paragraphOffset).getSection(sectionOffset);

        Video video = (Video) section;
        this.imagePath = video.getMediaPath();

        int width = this.endX - this.startX;
        int height = width * this.scaleHeight / this.scaleWidth;

        this.currentX = this.currentX + width;
        this.currentY = this.currentY + height;

        if (this.currentY <= this.endY){
            chapterTypesetter.setOffset(0);
            chapterTypesetter.setSectionOffset(chapterTypesetter.getSectionOffset() + 1);
        }
    }

    @Override
    public void print() {
        for (int i = 0; i < 10; i++) {
            if (i==5){
                System.out.println("视频区域");
                System.out.print(this.imagePath + " {" + this.currentX + "," + this.currentY + "}");
            }else {
                for (int j=0;j<25;j++){
                    System.out.print("@");
                }
            }
            System.out.println();
        }
    }
}
