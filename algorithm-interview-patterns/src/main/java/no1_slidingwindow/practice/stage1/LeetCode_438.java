package no1_slidingwindow.practice.stage1;

import java.util.*;

/**
 * @ClassName LeetCode_438
 * @Description 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *
 * 示例 2:
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 *
 * 提示:
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 * @Author gm
 * @Date 2026/4/1 18:28
 */
public class LeetCode_438 {
    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> result = LeetCode_438.slidingWindow(s, p);
        System.out.println(result);
    }

    private static List<Integer> slidingWindow(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            //1.扩展窗口
            //2.判断是否需要缩小窗口
            //3.判断结果
        }
        // TODO
        return result;
    }
}
