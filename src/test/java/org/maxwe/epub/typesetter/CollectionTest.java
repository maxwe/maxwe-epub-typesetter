package org.maxwe.epub.typesetter;

import junit.framework.TestCase;

/**
 * Created by Pengwei Ding on 2015-09-09 19:17.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class CollectionTest extends TestCase {



    public void delete(String text){
        text = text.substring(4,8);
        System.out.println(text);
    }


    public static void main(String[] args) {
        CollectionTest collectionTest = new CollectionTest();
        String text = "也就是说所操作的数据类型被指定为一个参数";
        collectionTest.delete(text);

        System.out.println(text);
    }
}
