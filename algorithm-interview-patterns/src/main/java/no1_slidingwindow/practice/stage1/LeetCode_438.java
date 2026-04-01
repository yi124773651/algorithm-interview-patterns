package no1_slidingwindow.practice.stage1;

import java.util.*;

/**
 * @ClassName LeetCode_438
 * @Description 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * <p>
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * <p>
 * 示例 2:
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 * <p>
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


    /**
     * 你的思路大方向是对的，但有几个问题需要指出：
     * 第1点：构建 p 的频次 map — 正确，没问题。
     * <p>
     * 第2点：逐个扫描判断是否匹配 — 思路有问题。你说的是"对每个 left 位置，取 [left, left + p.length) 的子串，构建 map 去和 p 的 map 比较"。这样做功能上正确，但每次都重新统计窗口内字符频次，时间复杂度是 O(n * m)，没有利用到滑动窗口的优势。
     * 滑动窗口的做法应该是：
     * - 窗口右边进一个字符 → 更新 window map
     * - 窗口大小超过 p.length() 时，左边移出一个字符 → 更新 window map
     * - 窗口大小正好等于 p.length() 时，比较两个 map
     * 这样每个字符只处理一次，时间复杂度 O(n)。
     * <p>
     * 第3点：循环边界 right < s.length() - p.length() — 错误。
     * right 应该遍历到 s.length() - 1，即条件是 right < s.length()。你当前代码里 right 是逐字符移动的指针，不是窗口起始位置。用 s.length() - p.length() 作为边界会漏掉最后几个字符。
     * <p>
     * 总结一下正确思路，结合你 Pattern.java 里的模板：
     * - 构建 p 的频次 map (need)
     * - right 从 0 遍历到 s.length() - 1，每次把 s.charAt(right) 加入 window map
     * - 当窗口大小 right - left + 1 > p.length() 时，移出 s.charAt(left)，left++（收缩）
     * - 当窗口大小 == p.length() 时，比较 window map 和 need map，相等则加入结果集
     * 这才是标准的固定窗口大小的滑动窗口做法。
     *
     * @param s
     * @param p
     * @return
     */
    private static List<Integer> slidingWindow(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int left = 0;
        //1.构建p的频次map
        HashMap<Character, Integer> need = new HashMap<>();
        for (char c : p.toCharArray()) {
            need.merge(c, 1, Integer::sum);
        }
        HashMap<Character, Integer> window = new HashMap<>();
        /**
         * matched 计数器：
         * 右边字符进窗口后，如果该字符频次刚好等于 need 中的频次 → matched++
         * 左边字符出窗口前，如果该字符频次正好等于 need 中的频次（即将被破坏）→ matched--
         * 当 matched == need.size() 时，所有字符频次都匹配，记录结果
         * 这样每次滑动窗口的判断从 O(k) 降到了 O(1)。
         */
        int matched = 0; // 记录window中有多少个字符的频次已经与need匹配
        for (int right = 0; right < s.length(); right++) {
            //1.扩充窗口
            char in = s.charAt(right);
            window.merge(in, 1, Integer::sum);
            if (window.get(in).equals(need.get(in))) {
                matched++;
            }
            //2.判断是否需要缩小窗口  特别需要注意窗口大小：right - left + 1
            while (right - left + 1 > p.length()) {
                char out = s.charAt(left);
                if (window.get(out).equals(need.get(out))) {
                    matched--;
                }
                window.merge(out, -1, Integer::sum);
                left++;
            }
            //3.判断结果
            if (matched == need.size()) {
                result.add(left);
            }
        }
        return result;
    }
}
