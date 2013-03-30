package com.xuan.utils.tabooed;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashSet;

import com.xuan.utils.FileUtils;
import com.xuan.utils.Validators;

/**
 * 敏感词汇类. 此类会读取资源文件(默认为tabooed.words)中的词汇.
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午10:24:31 $
 */
public class TabooedWords {

    private final Collection<String> tabooedWords = new HashSet<String>();

    /**
     * 从默认的敏感词汇文件中读取词汇, 初始化敏感词汇列表.
     */
    public synchronized void initialize() {
        InputStream in = TabooedWords.class.getClassLoader().getResourceAsStream("tabooed.words");
        initialize(in, "UTF-8");
    }

    /**
     * 从指定的敏感词汇输入流中读取词汇, 初始化敏感词汇列表.
     * 
     * @param in
     *            敏感词汇输入流
     * @param charset
     *            编码方式
     */
    public synchronized void initialize(InputStream in, String charset) {

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in, charset));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (!Validators.isEmpty(line)) {
                    tabooedWords.add(line.toLowerCase());
                }
            }
        }
        catch (Exception e) {
            // Ignore
        }
        finally {
            FileUtils.close(reader);
        }
    }

    /**
     * 获取所有读取到的敏感词汇.
     * 
     * @return 敏感词汇
     */
    public Collection<String> getTabooedWords() {
        return tabooedWords;
    }

}
