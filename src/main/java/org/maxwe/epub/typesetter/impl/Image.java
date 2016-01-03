package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.typesetter.core.AMeta;

/**
 * Created by Pengwei Ding on 2015-12-26 19:25.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class Image extends AMeta {
    public Image(int startX, int startY, int endX, int endY, int offsetInSection, String value) {
        super(startX, startY, endX, endY, offsetInSection, value);
    }
}
