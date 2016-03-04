package org.maxwe.epub.typesetter;

import org.maxwe.epub.typesetter.core.IPage;
import org.maxwe.epub.typesetter.impl.Chapter;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Pengwei Ding on 2016-03-01 17:12.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class TestBook {
    private static final String path = TestBook.class.getResource("/").getPath() + "sample/OEBPS/Text/";
    private static final String pathFile = TestBook.class.getResource("/").getPath() + "sample/OEBPS/Text/ds00216105.xhtml";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("默认阅读图书的路径：" + path + ",确定请输入(Y),退出请输入(N),更换图书请输入路径！");
        Configure configure = new Configure(0, 0, 0, 0);
        while (true) {
            String next = scanner.nextLine();
            if ("Y".equalsIgnoreCase(next)) {
                openBook(path, configure);
            } else if ("N".equalsIgnoreCase(next)) {
                System.out.println("待机状态...,输入(Y)阅读默认图书：" + path + ",更换图书请输入路径！");
            } else {
                if (next == null || !new File(next).exists() || new File(next).isFile() || new File(next).listFiles().length == 0) {
                    System.out.println("输入错误,输入(Y)阅读默认图书：" + path + ",更换图书请输入路径！");
                } else {
                    openBook(next, configure);
                }
            }
        }
    }

    private static void openBook(String bookPath, Configure configure) throws Exception {
        Scanner scanner = new Scanner(System.in);
        File[] files = new File(bookPath).listFiles();
        LinkedList<IPage> pages = new LinkedList<IPage>();
        long startTime = System.currentTimeMillis();

        for (int index = 0; index < files.length; index++) {
            org.maxwe.epub.parser.core.IChapter parserChapter = new org.maxwe.epub.parser.impl.Chapter(files[index].getAbsolutePath(), index);
            org.maxwe.epub.typesetter.core.IChapter chapter = new Chapter(parserChapter, configure, 0, 0, 2560, 960).typeset();
            pages.addAll(chapter.getPages());
        }
//        org.maxwe.epub.parser.core.IChapter parserChapter = new org.maxwe.epub.parser.impl.Chapter(pathFile);
//        org.maxwe.epub.typesetter.core.IChapter chapter = new Chapter(parserChapter,configure, 0, 0, 480, 960).typeset();
//        pages.addAll(chapter.getPages());
        System.out.println("排版耗时：" + (System.currentTimeMillis() - startTime));
        System.out.println(path + "初始化完成");
        int index;
        for (index = 0; index < pages.size(); index++) {
            IPage iPage = pages.get(index);
            if (configure.getChapterIndex() == iPage.getChapterIndex() &&
                    configure.getParagraphIndex() == iPage.getStartParagraphIndexInChapter() &&
                    configure.getSectionIndex() == iPage.getStartSectionIndexInParagraph() &&
                    configure.getMateIndex() == iPage.getStartMetaIndexInSection()) {
                break;
            }
        }

        pages.get(index).print();
        System.out.println("上一页(P),下一页(N),退出(Q)");
        while (true) {
            String next = scanner.nextLine();
            if ("P".equalsIgnoreCase(next)) {
                index--;
                if (index < 0) {
                    index = 0;
                }
                IPage iPage = pages.get(index);
                iPage.print();
                configure.setChapterIndex(iPage.getChapterIndex());
                configure.setParagraphIndex(iPage.getStartParagraphIndexInChapter());
                configure.setSectionIndex(iPage.getStartSectionIndexInParagraph());
                configure.setMateIndex(iPage.getStartMetaIndexInSection());
                System.out.println("上一页(P),下一页(N),退出(Q)");
            } else if ("N".equalsIgnoreCase(next)) {
                index++;
                if (index >= pages.size()) {
                    index = pages.size() - 1;
                }
                IPage iPage = pages.get(index);
                iPage.print();
                configure.setChapterIndex(iPage.getChapterIndex());
                configure.setParagraphIndex(iPage.getStartParagraphIndexInChapter());
                configure.setSectionIndex(iPage.getStartSectionIndexInParagraph());
                configure.setMateIndex(iPage.getStartMetaIndexInSection());
                System.out.println("上一页(P),下一页(N),退出(Q)");
            } else if ("Q".equalsIgnoreCase(next)) {
                System.out.println("待机状态...,输入(Y)阅读默认图书：" + path + ",更换图书请输入路径！");
                break;
            } else {
                System.out.println("输入错误");
                continue;
            }
        }
    }
}
