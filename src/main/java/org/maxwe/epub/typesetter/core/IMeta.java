package org.maxwe.epub.typesetter.core;

/**
 * Created by Pengwei Ding on 2015-12-26 19:21.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: 元数据对象操作方式
 * 单个文字、单个图片、单个音频、单个视频应该实现该接口
 */
public interface IMeta {
    /**
     * 屏幕属性操作
     * 元数据在屏幕上X轴的起始位置
     * @return
     */
    int getStartX();
    /**
     * 屏幕属性操作
     * 元数据在屏幕上Y轴的起始位置
     * @return
     */
    int getStartY();
    /**
     * 屏幕属性操作
     * 元数据在屏幕上X轴的结束位置
     * @return
     */
    int getEndX();
    /**
     * 屏幕属性操作
     * 元数据在屏幕上Y轴的结束位置
     * @return
     */
    int getEndY();
    /**
     * 屏幕属性操作
     * 元数据在屏幕上X轴可占用的最大位置
     * @return
     */
    int getScreenWidth();
    /**
     * 屏幕属性操作
     * 元数据在屏幕上Y轴可占用的最大位置
     * @return
     */
    int getScreenHeight();

    /**
     * 解析属性操作
     * 元数据在解析属性中的片段属性中的起始位置
     * @return
     */
    int getStartOffsetInSection();
    /**
     * 解析属性操作
     * 元数据在解析属性中的片段属性中的结束位置
     * @return
     */
    int getEndOffsetInSection();

    /**
     * 实际的数值字符
     * @return
     */
    String getValue();

}
