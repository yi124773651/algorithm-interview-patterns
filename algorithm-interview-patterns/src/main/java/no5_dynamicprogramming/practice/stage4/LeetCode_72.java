package no5_dynamicprogramming.practice.stage4;

/**
 * @ClassName LeetCode_72
 * @Description 编辑距离
 * 题目：给你两个单词 word1 和 word2，请返回将 word1 转换成 word2 所使用的最少操作数。你可以对一个单词进行三种操作：插入一个字符、删除一个字符、替换一个字符。
 * 示例1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：horse -> rorse -> rose -> ros
 * 示例2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 提示：0 <= word1.length, word2.length <= 500; word1 和 word2 由小写英文字母组成
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_72 {

    public static void main(String[] args) {
        System.out.println(dp("horse", "ros")); // 3
        System.out.println(dp("intention", "execution")); // 5
    }

    public static int dp(String word1, String word2) {
        // TODO
        return -1;
    }
}
