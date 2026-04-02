package no3_bfs.practice.stage3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName LeetCode_127
 * @Description 单词接龙
 * 题目：给你两个单词 beginWord 和 endWord，以及一个字典 wordList。
 * 找到从 beginWord 到 endWord 的最短转换序列的长度。
 * 每次转换只能改变一个字母，转换过程中的中间单词必须在字典中。
 *
 * 示例1：输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5 解释：hit -> hot -> dot -> dog -> cog
 * 示例2：输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：0
 *
 * 提示：
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_127 {

    public static int bfs(String beginWord, String endWord, List<String> wordList) {
        // TODO: 使用BFS找到最短单词转换序列的长度
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(bfs("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"))); // 5
        System.out.println(bfs("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log"))); // 0
    }
}
