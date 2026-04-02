package no4_dfsbacktracking.practice.stage3;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_131
 * @Description 分割回文串
 * 题目: 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串。返回 s 所有可能的分割方案。
 * 示例:
 *   输入：s = "aab"
 *   输出：[["a","a","b"],["aa","b"]]
 * 提示:
 *   1 <= s.length <= 16
 *   s 仅由小写英文字母组成
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_131 {

    public static void main(String[] args) {
        String s = "aab";
        System.out.println(backtrack(s));
    }

    public static List<List<String>> backtrack(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        int startIndex = 0;
        // TODO: 实现回溯逻辑
        return result;
    }
}
