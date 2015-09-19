package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.parser.core.ISection;
import org.maxwe.epub.parser.impl.Image;
import org.maxwe.epub.typesetter.core.IChapterTypesetter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Pengwei Ding on 2015-09-13 09:15.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 图片排版 计算图片的方式受限于android平台的相关API
 */
public class ImageTypesetter extends ASectionTypesetter {

    private String imagePath;

    public String getImagePath() {
        return this.imagePath;
    }

    @Override
    public void typeset(IChapterTypesetter chapterTypesetter) {
        int paragraphOffset = chapterTypesetter.getParagraphOffset();
        int sectionOffset = chapterTypesetter.getSectionOffset();
        int offset = chapterTypesetter.getOffset();

        ISection section = chapterTypesetter.getChapter().getParagraph(paragraphOffset).getSection(sectionOffset);

        Image image = (Image) section;
        this.imagePath = image.getMediaPath();

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(this.imagePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        this.currentX = this.currentX + width;
        this.currentY = this.currentY + height;

        chapterTypesetter.setOffset(0);
        chapterTypesetter.setSectionOffset(chapterTypesetter.getSectionOffset() + 1);

    }

    /**
     * 使用占位符矩阵标示图像
     */
    @Override
    public void print() {
        for (int i = 0; i < 20; i++) {
            if (i==10){
                System.out.print(this.imagePath + " {" + this.currentX + "," + this.currentY + "}");
            }else {
                for (int j=0;j<50;j++){
                    System.out.print("#");
                }
            }
            System.out.println();
        }
    }
}
