package org.maxwe.epub.typesetter.core;

/**
 * Created by Pengwei Ding on 2015-09-07 15:08.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public interface IPageNumberCalculatorListener {
    void onStart();
    void onProgress(int current);
    void onFinish(int total);
    void onError(int current,Exception exception);
}
