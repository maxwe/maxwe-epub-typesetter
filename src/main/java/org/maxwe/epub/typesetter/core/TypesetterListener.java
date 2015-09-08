package org.maxwe.epub.typesetter.core;

/**
 * Created by Pengwei Ding on 2015-09-07 15:08.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public interface TypesetterListener {
    void onStart(IPage page);
    void onProgress(IPage page);
    void onFinish(IPage page);
    void onError(IPage page,Exception exception);
}
