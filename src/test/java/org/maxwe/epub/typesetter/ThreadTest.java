package org.maxwe.epub.typesetter;

import junit.framework.TestCase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Pengwei Ding on 2015-09-06 22:27.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class ThreadTest extends TestCase {

    private ExecutorService executorService = Executors.newFixedThreadPool(4);

    public void scheduler(){
        Future<?> submit = executorService.submit(new NextPage());

        while (!submit.isDone()){
            System.out.println("=======");
            try {
                Thread.sleep(1000);
            }catch (Exception e){

            }
        }



        Future<?> submit1 = executorService.submit(new NextPage());

        while (!submit1.isDone()){
            System.out.println("--------");
            try {
                Thread.sleep(1000);
            }catch (Exception e){

            }
        }
    }

    private class NextPage implements Runnable{
        public void run() {
            for (int i=0;i<10;i++){
                System.out.println("nextPage");
                try {
                    Thread.sleep(1000);
                }catch (Exception e){

                }
            }
        }
    }


    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        threadTest.scheduler();
    }
}
