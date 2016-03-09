package org.maxwe.epub.typesetter;

import org.maxwe.epub.typesetter.core.IPage;
import org.maxwe.epub.typesetter.impl.dev.Configure;
import org.maxwe.epub.typesetter.impl.dev.TypesetterManager;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Pengwei Ding on 2016-03-01 17:12.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class TestBook {
    private static final String pathDir = TestBook.class.getResource("/").getPath() + "sample/";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("默认阅读图书的路径：" + pathDir + ",确定请输入(Y),退出请输入(N),更换图书请输入路径！");
        Configure configure = new Configure(0, 0, 0, 0);
        while (true) {
            String next = scanner.nextLine();
            if ("Y".equalsIgnoreCase(next)) {
                openBook(pathDir, configure);
            } else if ("N".equalsIgnoreCase(next)) {
                System.out.println("待机状态...,输入(Y)阅读默认图书：" + pathDir + ",更换图书请输入路径！");
            } else {
                if (next == null || !new File(next).exists() || new File(next).isFile() || new File(next).listFiles().length == 0) {
                    System.out.println("输入错误,输入(Y)阅读默认图书：" + pathDir + ",更换图书请输入路径！");
                } else {
                    openBook(next, configure);
                }
            }
        }
    }

    private static void openBook(String bookDir, Configure configure) throws Exception {
        int index = 0;

        TypesetterManager typesetterManager = new TypesetterManager(bookDir,configure);
        LinkedList<IPage> pages = typesetterManager.getPage(TypesetterManager.PageScrolledStatus.current);
//        System.out.println("==============前一页===============");
        if (pages.get(0) == null){
//            System.out.println("----------------马上到首页了------------------");
        }else{
//            System.out.println("===============已经绘制完成内容如下===============");
            pages.get(0).print();
        }

//        System.out.println("==============当前页===============");
        System.out.println("--------第" + index + "页-------- " + pages.get(1).getChapterIndex());
        pages.get(1).print();

//        System.out.println("==============后一页===============");
        if (pages.get(2) == null){
//            System.out.println("----------------马上到尾页了------------------");
        }else{
//            System.out.println("===============已经绘制完成内容如下===============");
//            pages.get(2).print();
        }

        System.out.println("上一页(P),下一页(N),退出(Q)");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String next = scanner.nextLine();

            if ("P".equalsIgnoreCase(next)) {
                pages = typesetterManager.getPage(TypesetterManager.PageScrolledStatus.previous);
//                System.out.println("---------------当前查看内容如下----------------");
                if (typesetterManager.getCurrentPage() != null){
                    System.out.println("--------第" + index + "页-------- " + typesetterManager.getCurrentPage().getChapterIndex());
                    typesetterManager.getCurrentPage().print();
                }
//                System.out.println("===============已经绘制完成内容如下===============");
                if (pages.get(0) != null){
//                    pages.get(0).print();
                }
                System.out.println("上一页(P),下一页(N),退出(Q)");
            } else if ("N".equalsIgnoreCase(next)) {
                index ++;
                pages = typesetterManager.getPage(TypesetterManager.PageScrolledStatus.next);
//                System.out.println("---------------当前查看内容如下----------------");
                if (typesetterManager.getCurrentPage() != null){
                    System.out.println("--------第" + index + "页-------- " + typesetterManager.getCurrentPage().getChapterIndex());
                    typesetterManager.getCurrentPage().print();
                }
//                System.out.println("===============已经绘制完成内容如下===============");
                if (pages.get(0) != null){
//                    pages.get(0).print();
                }
                System.out.println("上一页(P),下一页(N),退出(Q)");
            } else if ("Q".equalsIgnoreCase(next)) {
                System.out.println("待机状态...,输入(Y)阅读默认图书：" + pathDir + ",更换图书请输入路径！");
                break;
            } else {
                System.out.println("输入错误");
                continue;
            }
        }
    }
}
