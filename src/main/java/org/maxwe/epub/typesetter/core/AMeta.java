package org.maxwe.epub.typesetter.core;

/**
 * Created by Pengwei Ding on 2015-12-26 19:22.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 元数据对象操作方式默认实现
 */
public abstract class AMeta implements IMeta{
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int screenWidth;
    private int screenHeight;
    private int startOffsetInSection;
    private int endOffsetInSection;
    private String value;

    public AMeta() {}

    public AMeta(int startX, int startY, int endX, int endY, int screenWidth, int screenHeight, int startOffsetInSection, int endOffsetInSection, String value) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.startOffsetInSection = startOffsetInSection;
        this.endOffsetInSection = endOffsetInSection;
        this.value = value;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getStartOffsetInSection() {
        return startOffsetInSection;
    }

    public void setStartOffsetInSection(int startOffsetInSection) {
        this.startOffsetInSection = startOffsetInSection;
    }

    public int getEndOffsetInSection() {
        return endOffsetInSection;
    }

    public void setEndOffsetInSection(int endOffsetInSection) {
        this.endOffsetInSection = endOffsetInSection;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public abstract String getValue();
}
