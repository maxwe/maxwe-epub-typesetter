package org.maxwe.epub.typesetter.core;

import java.util.LinkedHashMap;

/**
 * Created by Pengwei Ding on 2015-09-08 16:30.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public interface IText extends ISection {

    LinkedHashMap<Integer,LinkedHashMap<Integer,String>> getTextMap();
}
