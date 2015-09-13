package org.maxwe.epub.typesetter.core;

/**
 * Created by Pengwei Ding on 2015-09-07 15:08.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public interface TypesetterListener {
    void onStart(IPageTypesetter pageTypesetter);
    void onProgress(IPageTypesetter pageTypesetter);
    void onFinish(IPageTypesetter pageTypesetter);
    void onError(IPageTypesetter pageTypesetter,Exception exception);
}
