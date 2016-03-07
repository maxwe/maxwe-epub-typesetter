package org.maxwe.epub.typesetter.impl.dev;

import org.maxwe.epub.typesetter.core.AChapter;
import org.maxwe.epub.typesetter.core.APage;
import org.maxwe.epub.typesetter.core.ASection;
import org.maxwe.epub.typesetter.core.IMeta;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2016-01-03 09:10.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class AudioSection extends ASection {

    private LinkedList<IMeta> metas = new LinkedList<IMeta>();

    protected AudioSection(AChapter chapter, APage page) {
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
        org.maxwe.epub.parser.impl.Audio section = (org.maxwe.epub.parser.impl.Audio) this.getChapter().getParsedChapter().getParagraph(paragraphOffset).getSection(sectionOffset);

        int width = this.getScreenWidth();
        int height = 300;
        String mediaPath = section.getMediaPath();

        this.metas.add(new Image(this.getStartX(),this.getStartY(),width,this.getPage().getCursorY() + height,0,mediaPath));

        this.getChapter().setCurrentSectionIndexInParagraph(this.getChapter().getCurrentSectionIndexInParagraph() + 1);
        this.getPage().setCursorY(this.getPage().getCursorY() + height);
        this.setEndOffsetInParagraph(this.getChapter().getCurrentSectionIndexInParagraph());
        return this;
    }

    public void print() {
        super.print();
        for (int i = 0; i < 10; i++) {
            if (i==5){
                System.out.println("音频区域");
                System.out.print(this.getMetas().get(0).getValue());
            }else {
                for (int j=0;j<25;j++){
                    System.out.print("♬");
                }
            }
            System.out.println();
        }
    }
}
