package no1_slidingwindow.practice.stage2;

import java.util.HashSet;

/**
 * @ClassName LeetCode_3
 * @Description 题目: 给定字符串s，找出不含重复字符的最长子串的长度。
 * 输入: "abcabcbb"  输出: 3 (abc)
 * @Author gm
 * @Date 2026/4/1 17:41
 */
public class LeetCode_3 {
    public static void main(String[] args) {
        String input = "abcabcbb";
        int result = LeetCode_3.slidingWindow(input);
        System.out.println(result);
    }

    private static int slidingWindow(String s) {
        //容器：使用set去重
        HashSet<Character> window = new HashSet<>();
        int result = 0, left = 0;
        //[left,right] 维护的是一个不含重复字符的窗口
        for (int right = 0; right < s.length(); right++) {
            //收缩窗口：如果新字符 s.charAt(right) 已经在 window 中存在，说明有重复了。
            // 此时不断从左边移除字符、left++，直到窗口内不再包含这个重复字符
            //--------------------------------------------------------
            //为什么收缩用 while 而不是 if？
            // 因为重复字符可能不在窗口最左边。比如 right=6 时遇到 b，但窗口是 {a,b,c}，b 在中间，
            // 需要连续移除 a 和 b 两个字符（left 从3移到5），才能消除重复。
            //1.先判断收缩条件，使用while
            while (window.contains(s.charAt(right))) {
                window.remove(s.charAt(left));
                left++;
            }
            //2.扩展窗口，更新状态
            window.add(s.charAt(right));
            //更新结果
            result = Math.max(result, right - left + 1);
        }
        return result;
    }
}
