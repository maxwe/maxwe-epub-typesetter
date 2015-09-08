package org.maxwe.epub.typesetter;

import junit.framework.TestCase;

/**
 * Created by Pengwei Ding on 2015-09-06 22:27.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class ThreadTest extends TestCase {

    private Thread pageNumberThread = new Thread(new PageNumberTask());
    private Thread nextPageThread = new Thread(new NextPageTask());
    private Thread previousThread = new Thread(new PreviousPageTask());

    private class PageNumberTask implements Runnable{
        public void run() {
            while (true){
                typesetter(this);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class NextPageTask implements Runnable{
        public void run() {
            while (true){
                typesetter(this);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class PreviousPageTask implements Runnable{
        public void run() {
            while (true){
                typesetter(this);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void calculatorPageNumber(){
        this.pageNumberThread.start();
    }


    public synchronized void typesetter(Runnable runnable){
        if (runnable instanceof PageNumberTask){
            System.out.println("=============页码计算线程在执行================");
        }else if(runnable instanceof NextPageTask){
            System.out.println("=============向后排版线程在执行================");
        }else if(runnable instanceof PreviousPageTask){
            System.out.println("=============向前排版线程在执行================");
        }
    }

    public void typesetter(){
        try {
            Thread.sleep(3000);

            if (this.pageNumberThread.isAlive()){
                this.pageNumberThread.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.nextPageThread.start();
        this.previousThread.start();

        System.out.println();
    }


    public static void main(String[] args) {

        ThreadTest threadTest = new ThreadTest();
        threadTest.calculatorPageNumber();
        threadTest.typesetter();
    }
}
