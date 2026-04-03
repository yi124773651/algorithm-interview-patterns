package no5_dynamicprogramming.practice.stage3;

/**
 * @ClassName LeetCode_1143
 * @Description 最长公共子序列
 * 题目：给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。如果不存在公共子序列，返回 0。
 * 示例1：
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace"
 *
 * 示例2：
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 *
 * 提示：1 <= text1.length, text2.length <= 1000; text1 和 text2 仅由小写英文字符组成
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_1143 {

    public static void main(String[] args) {
        System.out.println(dp("abcde", "ace")); // 3
        System.out.println(dp("abc", "def")); // 0
    }

    public static int dp(String text1, String text2) {
        // TODO
        return -1;
    }
}
