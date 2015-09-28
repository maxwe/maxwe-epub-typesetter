package org.maxwe.epub.typesetter.core;

/**
 * Created by Pengwei Ding on 2015-09-07 15:08.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public interface ITypesetterListener {
    void onStart();
    void onProgress(APageTypesetter pageTypesetter);
    void onFinish();
    void onError(Exception exception);
}