package org.maxwe.epub.typesetter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pengwei Ding on 2016-03-09 18:02.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class Secret {
    private static final String path = TestBook.class.getResource("/").getPath() + "sample/3500";
    private static final Map<String,String> mapKeyToValue = new HashMap<String, String>();
    private static final Map<String,String> mapValueToKey = new HashMap<String, String>();
    public static void main(String[] args) throws Exception{
        long start= System.currentTimeMillis();
        String text = "hahaha今天工作效率比较高，我写了200行代码，吊儿郎当今天工作效率比较高，我写了200行代码，吊儿郎当";
        File file = new File(path);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String readLine = bufferedReader.readLine();

        for (int index=0;index<readLine.length();index++){
            String substring = readLine.substring(index, index + 1);
            mapKeyToValue.put(index+ "",substring);
            mapValueToKey.put(substring,index + "");
        }

        String secret = "";
        for (int index =0;index<text.length();index++){
            String substring = text.substring(index, index + 1);
            String key = mapValueToKey.get(substring);
            if (key == null){
                secret = secret + substring;
            }else{
                secret = secret + key;
            }
        }
        System.out.println("sec: " + secret);
        long l = System.currentTimeMillis() - start;
        System.out.println(l);

    }
}
