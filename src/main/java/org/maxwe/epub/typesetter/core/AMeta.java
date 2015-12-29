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
    private int offsetInSection;
    private String value;

    public AMeta() {}

    public AMeta(int startX, int startY, int endX, int endY,int offsetInSection, String value) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.offsetInSection = offsetInSection;
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
        return this.getEndX() - this.getStartX();
    }

    public int getScreenHeight() {
        return this.getEndY() - this.getStartY();
    }

    public int getOffsetInSection() {
        return offsetInSection;
    }

    public void setOffsetInSection(int offsetInSection) {
        this.offsetInSection = offsetInSection;
    }

    public String getValue(){
        return this.value;
    }
}
