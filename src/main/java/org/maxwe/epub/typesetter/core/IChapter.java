package org.maxwe.epub.typesetter.core;

import java.util.LinkedList;

/**
 * Created by Pengwei Ding on 2015-12-26 16:04.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @章节对象
 */
public interface IChapter {

    /**
     * 对应文件的路径
     *
     * @return
     */
    String getChapterFilePath();

    /**
     * 章节ID
     *
     * @return
     */
    String getChapterId();

    /**
     * 章节名称
     *
     * @return
     */
    String getChapterName();

    /**
     * 章节在图书中的顺序
     *
     * @return
     */
    int getIndexInBook();

    /**
     * 排版后的页面
     *
     * @return
     */
    LinkedList<IPage> getPages();

}
