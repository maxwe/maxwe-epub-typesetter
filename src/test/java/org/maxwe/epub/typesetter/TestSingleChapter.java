package org.maxwe.epub.typesetter;

import junit.framework.TestCase;
import org.maxwe.epub.typesetter.core.IChapter;
import org.maxwe.epub.typesetter.core.IPage;
import org.maxwe.epub.typesetter.impl.Chapter;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-12-29 16:40.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 纯文本排版
 */
public class TestSingleChapter extends TestCase {
    private final String path = TestSingleChapter.class.getResource("/").getPath() + "sample/OEBPS/Text/ds00216103_test.xhtml";

    private final int startX = 0;
    private final int startY = 0;
    private final int endX = 320;
    private final int endY = 480;

    public void test() throws Exception{
        org.maxwe.epub.parser.core.IChapter parserChapter = new org.maxwe.epub.parser.impl.Chapter(path);
        org.maxwe.epub.typesetter.core.IChapter chapter = new Chapter(parserChapter, startX, startY, endX, endY).setChapterTypesetListener(new Chapter.ChapterTypesetListener() {
            public void onChapterTypesetStart(IChapter chapter) {
                System.out.println("章节：《" + chapter.getChapterName() + "》排版开始");
            }

            public void onPageTypesetOver(IChapter chapter, int indexInChapter) {
                System.out.println("章节：《" + chapter.getChapterName() + "》排版到第" + indexInChapter + "页");
            }

            public void onChapterTypesetEnd(IChapter chapter) {
                System.out.println("章节：《" + chapter.getChapterName() + "》排版结束，共有" + chapter.getPages().size() + "页");
            }
        }).typeset();
        LinkedList<IPage> pages = chapter.getPages();
        assertFalse(pages == null);

        for (IPage page:pages){
            System.out.println("===================="+ page.getChapterName()+"====================");
            page.print();
        }
    }
}
