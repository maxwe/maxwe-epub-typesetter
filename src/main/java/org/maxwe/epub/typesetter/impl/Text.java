package org.maxwe.epub.typesetter.impl;

import org.maxwe.epub.parser.core.ISection;
import org.maxwe.epub.typesetter.constant.LayoutStyle;
import org.maxwe.epub.typesetter.core.IText;

import java.util.LinkedHashMap;

/**
 * Created by Pengwei Ding on 2015-09-05 16:12.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class Text implements IText {

    private final int singleTextWidht = 25;
    private final int singleTextHeight = 25;
    private final int lineSpace = 25;
    private final int wordSpace = 0;


    private int startX;
    private int startY;

    private int width;
    private int height;

    private int fontSize;

    private LayoutStyle layoutStyle;

    private LinkedHashMap<Integer, LinkedHashMap<Integer, String>> textMap = new LinkedHashMap<Integer, LinkedHashMap<Integer, String>>();

    public Text setStartX(int x) {
        this.startX = x;
        return this;
    }

    public Text setStartY(int y) {
        this.startY = y;
        return this;
    }

    public Text setStartPoint(int x, int y) {
        this.startX = x;
        this.startY = y;
        return this;
    }

    public Text setTypesetterAreaWidth(int width) {
        this.width = width;
        return this;
    }

    public Text setTypesetterAreaHeight(int height) {
        this.height = height;
        return this;
    }

    public Text setTypesetterArea(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public Text setTypeface(Object typeface) {

        return this;
    }

    public Text setFontSize(int size) {
        this.fontSize = size;
        return this;
    }

    public Text setLayoutStyle(LayoutStyle layoutStyle) {
        this.layoutStyle = layoutStyle;
        return this;
    }

    public <E extends ISection> void typeset(E section) {
        org.maxwe.epub.parser.impl.Text text = (org.maxwe.epub.parser.impl.Text)section;
        /**
         * 绘制的行数
         */
        int lineNumber = 0;
        /**
         * 每行绘制的列数
         */
        int columnNum = 0;
        /**
         * 排版字数总量的计数器
         */
        int counter = 0;

        int currentLineY = this.startY;
        while (currentLineY + this.singleTextHeight <= this.startY + this.height ){
            LinkedHashMap<Integer,String> innerLine = new LinkedHashMap<Integer, String>();
            int currentLineX = this.startX;
            while (currentLineX + this.singleTextWidht <= this.startX + this.width){

                currentLineX = currentLineX + this.singleTextWidht;
            }
            currentLineY = currentLineY + this.lineSpace;
        }
    }

    public LinkedHashMap<Integer, LinkedHashMap<Integer, String>> getTextMap() {
        return this.textMap;
    }
}
