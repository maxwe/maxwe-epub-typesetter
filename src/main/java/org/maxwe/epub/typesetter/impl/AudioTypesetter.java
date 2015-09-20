package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.parser.core.ISection;
import org.maxwe.epub.parser.impl.Audio;
import org.maxwe.epub.typesetter.core.IChapterTypesetter;

/**
 * Created by Pengwei Ding on 2015-09-13 09:15.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class AudioTypesetter extends ASectionTypesetter {

    private String imagePath;

    /**
     * 默认音频文件的宽高
     */
    private final int width = 100;
    private final int height = 100;

    public String getImagePath() {
        return this.imagePath;
    }
    
    @Override
    public void typeset(IChapterTypesetter chapterTypesetter) {
        int paragraphOffset = chapterTypesetter.getParagraphOffset();
        int sectionOffset = chapterTypesetter.getSectionOffset();
        int offset = chapterTypesetter.getOffset();

        ISection section = chapterTypesetter.getChapter().getParagraph(paragraphOffset).getSection(sectionOffset);

        Audio audio = (Audio) section;

        this.imagePath = audio.getMediaPath();

        int width = this.width;
        int height = this.height;

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
                System.out.println("音频区域");
                System.out.print(this.imagePath + " {" + this.currentX + "," + this.currentY + "}");
            }else {
                for (int j=0;j<25;j++){
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }
}
