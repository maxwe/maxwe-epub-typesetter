package org.maxwe.epub.typesetter;

import junit.framework.TestCase;
import org.maxwe.epub.typesetter.core.IPage;
import org.maxwe.epub.typesetter.impl.Chapter;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-12-29 16:40.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 纯文本排版
 */
public class TestPlainText extends TestCase {
    private final String path = SingleChapterTypesetter.class.getResource("/").getPath() + "sample/OEBPS/Text/ds00216103_test.xhtml";

    private final int startX = 0;
    private final int startY = 0;
    private final int endX = 320;
    private final int endY = 480;

    public void testPlainText() throws Exception{
        org.maxwe.epub.parser.core.IChapter parserChapter = new org.maxwe.epub.parser.impl.Chapter(path);
        org.maxwe.epub.typesetter.core.IChapter chapter = new Chapter(parserChapter, startX, startY, endX, endY).typeset();
        LinkedList<IPage> pages = chapter.getPages();
        assertFalse(pages == null);

        for (IPage page:pages){
            page.print();
        }
    }
}
