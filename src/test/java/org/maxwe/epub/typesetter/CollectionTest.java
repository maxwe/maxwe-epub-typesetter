package org.maxwe.epub.typesetter;

import junit.framework.TestCase;

/**
 * Created by Pengwei Ding on 2015-09-09 19:17.
 * Email: www.dingpengwei@foxmail.com www.dingpegnwei@gmail.com
 * Description: @TODO
 */
public class CollectionTest extends TestCase {
//
///**
// * 段落排版
// */
//    for (int i = chapterTypesetter.getParagraphOffset(); i < sizeOfParagraphs && ; i++ ) {
//        /**
//         * 片段排版
//         */
//        LinkedList<ISection> sections = paragraphs.get(i).getSections();
//        int sizeOfSection = sections.size();
//        for (int j = chapterTypesetter.getOffset(); j < sizeOfSection; j++) {
//            /**
//             * 元素排版
//             */
//            ISection section = sections.get(j);
//            if (section instanceof Text) {
//                TextTypesetter textTypesetter = new TextTypesetter();
//
//                this.sectionTypesetters.add(textTypesetter);
//                Text text = (Text) section;
//
//                String text1 = text.getText();
//                text1.length();
//            } else if (section instanceof Image) {
//                ImageTypesetter imageTypesetter = new ImageTypesetter();
//                this.sectionTypesetters.add(imageTypesetter);
//                /**
//                 * Image是单元素对象，排版完成后重置偏移量
//                 */
//                chapterTypesetter.setOffset(0);
//            } else if (section instanceof Audio) {
//                AudioTypesetter audioTypesetter = new AudioTypesetter();
//                this.sectionTypesetters.add(audioTypesetter);
//                /**
//                 * Audio是单元素对象，排版完成后重置偏移量
//                 */
//                chapterTypesetter.setOffset(0);
//            } else if (section instanceof Video) {
//                VideoTypesetter videoTypesetter = new VideoTypesetter();
//                this.sectionTypesetters.add(videoTypesetter);
//                /**
//                 * Video是单元素对象，排版完成后重置偏移量
//                 */
//                chapterTypesetter.setOffset(0);
//            }
//            /**
//             * 一个片段排版完成后片段排版偏移量增加1
//             */
//            chapterTypesetter.setSectionOffset(j + 1);
//        }
//        /**
//         * 一个段落排版完成后段落排版偏移量增加1
//         */
//        chapterTypesetter.setParagraphOffset(i + 1);
//    }
}
