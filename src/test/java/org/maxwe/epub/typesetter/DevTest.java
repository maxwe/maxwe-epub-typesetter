package org.maxwe.epub.typesetter;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pengwei Ding on 2016-03-08 17:03.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class DevTest {
    /**
     * /////////////////////////////////////////线程相关的测试///////////////////////////////////////////////////////////
     */
    private ExecutorService executorService = new ThreadPoolExecutor(2, 2, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(2),
            new ThreadPoolExecutor.DiscardOldestPolicy());
    public void threadTest(){
        for (int index = 0; index < 51; index++) {
            executorService.execute(new Runnable() {
                public void run() {
                    System.out.println("执行任务1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            executorService.execute(new Runnable() {
                public void run() {
                    System.out.println("执行任务2");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println("循环暂停");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("执行结束");
    }

    /**
     * /////////////////////////////////////////集合相关的测试///////////////////////////////////////////////////////////
     */
    private LinkedList<String> previousPageQueue = new LinkedList();
    public void collectionTest(){
        LinkedList<String> strings1 = new LinkedList();
        strings1.add("0");
        strings1.add("1");
        strings1.add("2");
        LinkedList<String> strings2 = new LinkedList();
        strings2.add("a");
        strings2.add("b");
        strings2.add("c");
        previousPageQueue.addAll(0,strings1);
        previousPageQueue.addAll(0,strings2);

        previousPageQueue.addAll(strings1);
        previousPageQueue.addAll(strings2);

        System.out.println(previousPageQueue.toString());
    }
    public static void main(String[] args) {
        //new DevTest().threadTest();
        new DevTest().collectionTest();
    }
}
