package no1_slidingwindow.practice.stage3;

/**
 * @ClassName LeetCode_76
 * @Description 给定两个字符串 s 和 t，长度分别是 m 和 n，返回 s 中的 最短窗口 子串，
 * 使得该子串包含 t 中的每一个字符（包括重复字符）。如果没有这样的子串，返回空字符串 ""。
 *
 * 示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * 示例 2：
 *
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 示例 3:
 *
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，因此没有符合条件的子字符串，返回空字符串。
 *
 * 提示：
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s 和 t 由英文字母组成
 *
 * 进阶：你能设计一个在 O(m + n) 时间内解决此问题的算法吗？
 * @Author gm
 * @Date 2026/4/1 18:37
 */
public class LeetCode_76 {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String result = LeetCode_76.slidingWindow(s, t);
        System.out.println(result);
    }

    private static String slidingWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            //1.扩展窗口
            //2.缩小窗口（当窗口满足条件时尝试收缩）
            //3.更新最小覆盖子串
        }
        // TODO
        return "";
    }
}
