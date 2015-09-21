package org.maxwe.epub.typesetter;

import org.junit.Test;
import org.maxwe.epub.parser.core.IMetadata;
import org.maxwe.epub.parser.core.INavigation;
import org.maxwe.epub.parser.impl.Book;
import org.maxwe.epub.parser.impl.Chapter;
import org.maxwe.epub.typesetter.core.APageTypesetter;
import org.maxwe.epub.typesetter.impl.ChapterTypesetter;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Pengwei Ding on 2015-09-20 22:47.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class MainTest {

    private static String filePath = MainTest.class.getResource("/").getPath() + "sample";

    @Test
    public void test() throws Exception {
        while (true) {
            System.out.println("=========请选择========");
            System.out.println("输入0:打开默认图书，输入路径打开制定的图书");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if (line.equals("")) {
                System.out.println("输入错误，请重新输入");
            } else if (line.equals("0")) {
                openBook(filePath);
            } else {
                File file = new File(line);
                if (file.exists()) {
                    openBook(line);
                } else {
                    System.out.println("文件不存在");
                }
            }
        }
    }

    private void openBook(String filePath) {
        Book book = null;
        try {
            book = new Book(filePath);
        } catch (Exception e) {
            System.out.println("解析图书异常" + e.getMessage());
            return;
        }

        while (true) {
            System.out.println("输入1:查看图书信息；输入2:查看图书目录；输入Q:退出");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("Q")) {
                break;
            } else if (line.equals("1")) {
                IMetadata metadata = book.getMetadata();
                metadata.print();
            } else if (line.equals("2")) {
                openChapter(book);
            }
        }
    }

    private void openChapter(Book book) {
        LinkedList<INavigation> navigations = book.getNavigations();
        for (INavigation navigation : navigations) {
            navigation.print();
        }
        while (true) {
            System.out.println("输入1:查看图书目录；输入章节ID：查看章节内容；输入Q:退出");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("Q")) {
                break;
            } else if (line.equals("1")) {
                for (INavigation navigation : navigations) {
                    navigation.print();
                }
            } else {
                INavigation temp = null;
                for (INavigation navigation : navigations) {
                    if (navigation.getId().equals(line)) {
                        temp = navigation;
                        break;
                    }
                }
                if (temp == null) {
                    System.out.println("输入错误");
                } else {
                    try {
                        TypesetterManager.getInstance().typeset(new ChapterTypesetter(new Chapter(temp.getHref())));
                        LinkedList<APageTypesetter> pageTypesetters = TypesetterManager.getInstance().getPages();
                        for (APageTypesetter pageTypesetter : pageTypesetters) {
                            pageTypesetter.print();
                        }
                    } catch (Exception e) {
                        System.out.println("解析图书异常" + e.getMessage());
                    }
                }
            }
        }
    }
}
