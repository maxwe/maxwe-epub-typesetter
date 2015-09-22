package org.maxwe.epub.typesetter;

import org.maxwe.epub.typesetter.core.APageTypesetter;
import org.maxwe.epub.typesetter.core.IPageNumberCalculatorListener;
import org.maxwe.epub.typesetter.core.ITypesetterListener;

/**
 * Created by Pengwei Ding on 2015-09-22 15:49.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public interface IBookTypesetter {

    void setTypesetterListener(ITypesetterListener typesetterListener);
    void setPageNumberCalculatorListener(IPageNumberCalculatorListener pageNumberCalculatorListener);

    APageTypesetter getNextPage();
    APageTypesetter getPreviousPage();

    void setFont(Object font);
    void setFontSize(int size);

    void search(String query);






}
