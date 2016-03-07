package org.maxwe.epub.typesetter.impl.dev;

import org.maxwe.epub.typesetter.core.AMeta;

/**
 * Created by Pengwei Ding on 2015-12-26 19:24.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class Word extends AMeta {
    public Word(int startX, int startY, int endX, int endY, int offsetInSection, String value) {
        super(startX, startY, endX, endY, offsetInSection, value);
    }
}
