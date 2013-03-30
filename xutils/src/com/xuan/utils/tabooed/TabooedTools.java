package com.xuan.utils.tabooed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 敏感词汇过滤工具类，使用了 <a href="http://zh.wikipedia.org/wiki/确定有限状态自动机">DFA</a> 算法来实现词汇过滤。
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午10:23:37 $
 */
public class TabooedTools {

    private static final TabooedTools instance = new TabooedTools();

    private Collection<String> tabooedWords;
    private final Node rootNode = new Node('R');

    private TabooedTools() {
        initialize();
    }

    public static TabooedTools getInstance() {
        return instance;
    }

    public synchronized void setTabooedWords(Collection<String> tabooedWords) {
        this.tabooedWords = tabooedWords;
        claerNode();
        // 创建字符节点树
        createNodeTree();

    }

    public synchronized void initialize() {
        TabooedWords tws = new TabooedWords();
        tws.initialize();
        tabooedWords = tws.getTabooedWords();

        // 创建字符节点树
        createNodeTree();
    }

    public List<String> getTabooedWords(String content) {
        Set<String> tabooedWords4Content = new LinkedHashSet<String>();
        searchWord(content, tabooedWords4Content);
        return new ArrayList<String>(tabooedWords4Content);
    }

    private void searchWord(String content, Set<String> tabooedWords4Content) {
        // 这个是检测的中间状态列表
        List<Character> tempWords = new ArrayList<Character>();

        int index = 0;
        Node node = rootNode;
        char[] chars = content.toCharArray();
        while (index < chars.length) {
            char currentChar = chars[index];

            node = findNode(node, currentChar);
            if (node == null) { // 如果找不到后续节点则进行回溯
                node = rootNode;
                index = index - tempWords.size();
                tempWords.clear();
            }
            else if (isNodeFinish(node, index, chars, tempWords.size())) {
                // 如果节点终结的话, 就将敏感词汇保存到结果集中
                StringBuilder sb = new StringBuilder();
                tempWords.add(currentChar);
                for (char c : tempWords) {
                    sb.append(c);
                }
                tabooedWords4Content.add(sb.toString());

                index = index - tempWords.size() + 1;
                tempWords.clear();
                node = rootNode;
            }
            else { // 找到匹配的敏感字符后将当前字符保存到临时列表中
                tempWords.add(currentChar);
            }

            index++;
        }
    }

    /**
     * 判断字符节点是否终结.
     * 
     * @param node
     * @param index
     * @param chars
     * @param matchCount
     * @return true/false
     */
    private boolean isNodeFinish(Node node, int index, char[] chars, int matchCount) {
        boolean isFinish = (node.flag == Node.FLAG_FINISH);
        if (!isFinish) {
            return false;
        }

        isFinish = (index == chars.length - 1 || !isAlpha(chars[index + 1]));
        if (!isFinish) {
            return false;
        }

        if (index - 1 - matchCount < 0) {
            return true;
        }

        return (index == 1 || !isAlpha(chars[index - 1 - matchCount]));
    }

    /**
     * 创建敏感字符的节点树
     */
    private void createNodeTree() {
        for (String str : tabooedWords) {
            char[] chars = str.toCharArray();
            if (chars.length > 0) {
                insertNode(rootNode, chars, 0);
            }
        }
    }

    /**
     * 插入字符节点.
     * 
     * @param parent
     *            父节点
     * @param chars
     *            过滤字符串的char数组
     * @param index
     *            待插入字符的索引
     */
    private void insertNode(Node parent, char[] chars, int index) {
        Node node = findNode(parent, chars[index]);

        // 如果找不到已经存在的节点, 则创建一个节点
        if (node == null) {
            node = new Node(chars[index]);
            parent.addChild(node);
        }

        // 如果是最后一个字符, 则将节点标记为结束
        if (index == (chars.length - 1)) {
            node.flag = Node.FLAG_FINISH;
        }
        else {
            insertNode(node, chars, ++index);
        }
    }

    /**
     * 清除根节点的子节点
     */
    private void claerNode() {
        rootNode.getChilds().clear();
    }

    /**
     * 查找字符节点.
     * 
     * @param parent
     *            父节点
     * @param c
     *            字符
     * @return 返回和字符所匹配的节点
     */
    private Node findNode(Node parent, char c) {
        if (c >= 'A' && c <= 'Z') {
            c = (char) (c + 32);
        }

        Node node = parent.getChild(c);
        return node;
    }

    /**
     * 判断字符是否是英文字母.
     * 
     * @param c
     *            字符
     * @return true/false
     */
    private static boolean isAlpha(char c) {
        return c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z';
    }

    /**
     * 表示一个字符节点的类.
     */
    private static class Node {

        static final int FLAG_CONTINUE = 0; // 标记敏感字符延续
        static final int FLAG_FINISH = 1; // 标记敏感字符终结

        char c;
        int flag;

        Map<Character, Node> nodeMap = new HashMap<Character, Node>();

        Node(char c) {
            this(c, FLAG_CONTINUE);
        }

        Node(char c, int flag) {
            this.c = c;
            this.flag = flag;
        }

        void addChild(Node node) {
            nodeMap.put(node.c, node);
        }

        Node getChild(char c) {
            return nodeMap.get(c);
        }

        Map<Character, Node> getChilds() {
            return nodeMap;
        }
    }

}
