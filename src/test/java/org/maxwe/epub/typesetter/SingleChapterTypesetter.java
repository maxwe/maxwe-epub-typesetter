package org.maxwe.epub.typesetter;

import junit.framework.TestCase;
import org.junit.Test;
import org.maxwe.epub.parser.EPubParser;
import org.maxwe.epub.typesetter.core.APageTypesetter;
import org.maxwe.epub.typesetter.core.IChapterTypesetter;
import org.maxwe.epub.typesetter.core.ITypesetterListener;
import org.maxwe.epub.typesetter.impl.ChapterTypesetter;

import java.io.File;
import java.text.DecimalFormat;
import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-09-09 19:17.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class SingleChapterTypesetter extends TestCase {

    private String path = SingleChapterTypesetter.class.getResource("/").getPath() + "sample";

    private int screenWidth = 1400;
    private int screenHeight = 2560;

    private ITypesetterListener typesetterListener = new ITypesetterListener() {
        public void onStart(IChapterTypesetter chapterTypesetter) {
            System.out.println("=====排版《" + chapterTypesetter.getTitle() + "》开始=====");
        }

        public void onProgress(APageTypesetter pageTypesetter) {
            System.out.println("=====" + pageTypesetter.getIndexOfChapter() + "=====");
            pageTypesetter.print();
        }

        public void onFinish(IChapterTypesetter chapterTypesetter) {
            System.out.println("=====排版《" + chapterTypesetter.getTitle() + "》结束=====");
        }

        public void onError(Exception exception) {

        }
    };

    /*
    纯文本排版测试
    取值第三章
    章节文件名称ds00216101.xhtml
    章节共有纯文本段落14个
    文字约1000
    320x480   10W   900
    720x1080  10W   700
     */
    private final int INDEX_OF_PLAIN_TEXT = 2;


    @Test
    public void testPlainTextTypesetter() throws Exception {
        if (new File(this.path).exists()) {
            EPubParser ePubParser = new EPubParser(this.path);
            System.out.println("############ 章节名：" + ePubParser.getContent().getNavigation(INDEX_OF_PLAIN_TEXT).getTitle() + "############");
            System.out.println("############ 章节位置：" + ePubParser.getContent().getNavigation(INDEX_OF_PLAIN_TEXT).getHref() + "############");
            long start = System.nanoTime();
            IChapterTypesetter chapterTypesetter = new ChapterTypesetter(ePubParser.getContent().getNavigation(INDEX_OF_PLAIN_TEXT));
            LinkedList<APageTypesetter> pageTypesetters = chapterTypesetter.typeset(screenWidth,screenHeight,typesetterListener).getPageTypesetters();
            long duration = System.nanoTime() - start;
            DecimalFormat decimalFormat = new DecimalFormat("$,###");
            System.out.println("排版耗时：" + decimalFormat.format(duration).replace("$", "") + "毫微妙");
            System.out.println("共有页数：" + pageTypesetters.size());

            for (APageTypesetter pageTypesetter : pageTypesetters) {
                //pageTypesetter.print();
            }
        } else {
            assertFalse("测试文件不存在", true);
        }
    }

    /*
    纯图片排版测试
    取值第一章
    章节文件名称Cover.xhtml
    章节共有纯图片段落1个
    图片信息240x304
     */
    private final int INDEX_OF_PLAIN_IMAGE = 0;

    @Test
    public void testPlainImageTypesetter() throws Exception{
        if (new File(this.path).exists()) {
            EPubParser ePubParser = new EPubParser(this.path);
            System.out.println("############ 章节名：" + ePubParser.getContent().getNavigation(INDEX_OF_PLAIN_IMAGE).getTitle() + "############");
            System.out.println("############ 章节位置：" + ePubParser.getContent().getNavigation(INDEX_OF_PLAIN_IMAGE).getHref() + "############");
            long start = System.nanoTime();
            IChapterTypesetter chapterTypesetter = new ChapterTypesetter(ePubParser.getContent().getNavigation(INDEX_OF_PLAIN_IMAGE));
            LinkedList<APageTypesetter> pageTypesetters = chapterTypesetter.typeset(screenWidth,screenHeight,typesetterListener).getPageTypesetters();
            long duration = System.nanoTime() - start;
            DecimalFormat decimalFormat = new DecimalFormat("$,###");
            System.out.println("排版耗时：" + decimalFormat.format(duration).replace("$", "") + "毫微妙");
            System.out.println("共有页数：" + pageTypesetters.size());

            for (APageTypesetter pageTypesetter : pageTypesetters) {
                //pageTypesetter.print();
            }
        } else {
            assertFalse("测试文件不存在", true);
        }
    }

    /*
    文本图片混合排版测试
    取值第二章
    章节文件名称Copyright.xhtml
    章节共有文本段落7个，图片段落一个
    图片位于最后，图片信息204x147
     */
    private final int INDEX_OF_TEXT_IMAGE = 1;

    @Test
    public void testTextImageTypesetter() throws Exception{
        if (new File(this.path).exists()) {
            EPubParser ePubParser = new EPubParser(this.path);
            System.out.println("############ 章节名：" + ePubParser.getContent().getNavigation(INDEX_OF_TEXT_IMAGE).getTitle() + "############");
            System.out.println("############ 章节位置：" + ePubParser.getContent().getNavigation(INDEX_OF_TEXT_IMAGE).getHref() + "############");
            long start = System.nanoTime();
            IChapterTypesetter chapterTypesetter = new ChapterTypesetter(ePubParser.getContent().getNavigation(INDEX_OF_TEXT_IMAGE));
            LinkedList<APageTypesetter> pageTypesetters = chapterTypesetter.typeset(screenWidth,screenHeight,typesetterListener).getPageTypesetters();
            long duration = System.nanoTime() - start;
            DecimalFormat decimalFormat = new DecimalFormat("$,###");
            System.out.println("排版耗时：" + decimalFormat.format(duration).replace("$", "") + "毫微妙");
            System.out.println("共有页数：" + pageTypesetters.size());

            for (APageTypesetter pageTypesetter : pageTypesetters) {
               // pageTypesetter.print();
            }
        } else {
            assertFalse("测试文件不存在", true);
        }
    }



    /*
    文本图片混合排版测试
    取值第四章
    章节文件名称ds00216102.xhtml
    章节共有文本段落7个，图片段落一个
    图片位于最后，图片信息204x147
     */
    private final int INDEX_OF_TEXT_AUDIO_VIDEO = 3;

    @Test
    public void testTextAudioVideoTypesetter() throws Exception{
        if (new File(this.path).exists()) {
            EPubParser ePubParser = new EPubParser(this.path);
            System.out.println("############ 章节名：" + ePubParser.getContent().getNavigation(INDEX_OF_TEXT_AUDIO_VIDEO).getTitle() + "############");
            System.out.println("############ 章节位置：" + ePubParser.getContent().getNavigation(INDEX_OF_TEXT_AUDIO_VIDEO).getHref() + "############");
            long start = System.nanoTime();
            IChapterTypesetter chapterTypesetter = new ChapterTypesetter(ePubParser.getContent().getNavigation(INDEX_OF_TEXT_AUDIO_VIDEO));
            LinkedList<APageTypesetter> pageTypesetters = chapterTypesetter.typeset(screenWidth,screenHeight,typesetterListener).getPageTypesetters();
            long duration = System.nanoTime() - start;
            DecimalFormat decimalFormat = new DecimalFormat("$,###");
            System.out.println("排版耗时：" + decimalFormat.format(duration).replace("$", "") + "毫微妙");
            System.out.println("共有页数：" + pageTypesetters.size());

            for (APageTypesetter pageTypesetter : pageTypesetters) {
                //pageTypesetter.print();
            }
        } else {
            assertFalse("测试文件不存在", true);
        }
    }
}
