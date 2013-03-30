package com.xuan.utils.tabooed;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.xuan.utils.Validators;

/**
 * 对文本内容进行敏感词汇过滤的工具类。
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午10:24:01 $
 */
public abstract class TabooedUtils {

    private static final TabooedTools tabooedTools = TabooedTools.getInstance();

    /**
     * 对文本内容进行过滤，获取所有存在的敏感词汇。
     * 
     * @param content
     *            需要进行过滤的内容
     * @return 过滤的敏感词汇列表
     */
    public static List<String> getTabooedWords(String content) {
        if (Validators.isEmpty(content)) {
            return Collections.emptyList();
        }

        return tabooedTools.getTabooedWords(content);
    }

    /**
     * 对文本内容进行检查，验证是否存在敏感词汇。
     * 
     * @param content
     *            需要进行检查的内容
     * @return 如果存在敏感词汇返回 <code>true</code>，否则返回 <code>false</code>。
     */
    public static boolean isTabooed(String content) {
        return !getTabooedWords(content).isEmpty();
    }

    /**
     * 此方法可以实现在不重启JVM的情况下重新加载存放敏感词汇的资源文件。
     */
    public static void reloadTabooedWords() {
        tabooedTools.initialize();
    }

    /**
     * 此方法用于加载应用程序外部的敏感词汇库，比如数据库里存储的词库（使用该方法后tabooed.words里面的词汇无效）
     * 
     * @param tabooedWords
     */
    public static void setTabooedWordsResource(Collection<String> tabooedWords) {
        tabooedTools.setTabooedWords(tabooedWords);
    }

}
