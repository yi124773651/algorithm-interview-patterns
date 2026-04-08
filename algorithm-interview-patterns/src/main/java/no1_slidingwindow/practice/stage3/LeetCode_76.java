package no1_slidingwindow.practice.stage3;

/**
 * @ClassName LeetCode_76
 * @Description 给定两个字符串 s 和 t，长度分别是 m 和 n，返回 s 中的 最短窗口 子串，使得该子串包含 t 中的每一个字符（包括重复字符）。如果没有这样的子串，返回空字符串 ""。
 * <p>
 * 测试用例保证答案唯一。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * 示例 2：
 * <p>
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 * 示例 3:
 * <p>
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 * <p>
 * 提示：
 * <p>
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s 和 t 由英文字母组成
 * <p>
 * <p>
 * 进阶：你能设计一个在 O(m + n) 时间内解决此问题的算法吗？
 * @Author gm
 * @Date 2026/4/1 18:37
 */
public class LeetCode_76 {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String result = LeetCode_76.slidingWindow2(s, t);
        System.out.println(result);
    }

    /**
     * O(m + n) 版本
     *
     * @param s
     * @param t
     * @return
     */
    private static String slidingWindow2(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";
        int[] need = new int[128];
        int needCount = 0;
        //记录频次和所需字符数量
        for (char c : t.toCharArray()) {
            if (need[c] == 0) needCount++;
            need[c]++;
        }
        int left = 0, start = 0, minLen = Integer.MAX_VALUE;
        //符合要求的窗口字符数  数量符合要求
        int valid = 0;
        int[] window = new int[128];
        for (int right = 0; right < s.length(); right++) {
            //1.扩容
            char c = s.charAt(right);
            window[c]++;
            //如果加入该符号需要且加入后数量已经满足要求
            if (need[c] > 0 && need[c] == window[c]) {
                valid++;
            }
            //2.缩小窗口 条件
            while (needCount == valid) {
                //更新最小字符串长度
                int currentLen = right - left + 1;
                if (currentLen < minLen) minLen = currentLen;
                start = left;
                //去除最左的字符
                char c1 = s.charAt(left);
                //如果去除该字符使得不满足所需字符数量
                if (need[c1] > 0 && window[c1] == need[c1]) valid--;
                window[c1]--;
                left++;
            }
        }
//        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, minLen);  容易错
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    private static String slidingWindow(String s, String t) {
        //边界条件
        if (s == null || t == null || s.length() < t.length()) return "";
        //大写字母	65–90	A, B, C, ..., Z  小写字母	97–122	a, b, c, ..., z
        int[] need = new int[128];//如果包含特殊字符 可以new int[128]
        //记录频次
        for (char c : t.toCharArray()) {
            need[c]++;
        }
        int[] window = new int[128];
        int left = 0, start = 0, minLen = Integer.MAX_VALUE;
        for (int right = 0; right < s.length(); right++) {
            //1.扩容
            window[s.charAt(right)]++;
            //2.检查是否需要缩容
            while (checkNeedShrink(need, window)) {
                //更新最小长度
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }
                //最左元素出去
                window[s.charAt(left)]--;
                left++;
            }

        }
        // 关键修正：如果 minLen 没变过，说明没找到，返回 ""
//        return s.substring(start, start + minLen);
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    /**
     * 只要有一种字符数量不够 就不能缩容
     *
     * @param need
     * @param window
     * @return
     */
    private static boolean checkNeedShrink(int[] need, int[] window) {
        for (int i = 0; i < 128; i++) {
            if (need[i] > window[i]) return false;
        }
        return true;
    }
    /**
     * 错误原因：
     *
     * 1.贪心缩减的局限性： 你的代码逻辑是“如果左/右边界的字符是多余的，就缩减它”。
     * 但这忽略了一个关键点：有时候为了得到更短的子串，你必须先放弃左边的一个“必要”字符，然后在右边寻找下一个相同的字符来替换它。
     * - 例如：s = "BAAAABC", t = "ABC"。
     * - 按照你的逻辑，左边的 'B' 是必要的（window[B] == need[B]），所以 left 永远不会右移。但实际上，真正的最短子串是末尾的 "BC"
     * 之前的某一部分（如果后面还有 A 的话）。
     *
     * 2.无法处理“跳跃式”的最优解：
     * - 这种双指针对撞（left++, right--）通常用于处理有序数组或特定单调性问题。
     * - 对于“最小覆盖子串”，标准做法是单向滑窗（right 负责寻找可行解，left 负责优化寻找最优解）。
     *
     * 3.返回值错误：
     * - s.substring(left, right) 在 Java 中是不包含 right 下标字符的。如果要包含 right，应该是 substring(left, right + 1)。
     *
     * @param s
     * @param t
     * @return
     */
//    private static String slidingWindow(String s, String t) {
//        String result = "";
//        //处理边界条件
//        if (s.length() < t.length()) {
//            return result;
//        }
//        //s.length() >= t.length() //统计品次
//        int[] need = new int[58];
//        for (char c : t.toCharArray()) {
////            need[c-'a']++;   大写字母	65–90	A, B, C, ..., Z  小写字母	97–122	a, b, c, ..., z
//            need[c - 'A']++;
//        }
//        int[] window = new int[58];
//        for (char c : s.toCharArray()) {
//            window[c - 'A']++;
//        }
//        //先去除特殊情况 整个字符串都不满足子串要求
//        for (int i = 0; i < need.length; i++) {
//            if (need[i] > window[i]) {
//                return result;
//            }
//        }
//        int left = 0, right = s.length() - 1;
//        //窗口从最大往小缩
//        while (left < right) {
//            //先判断左边能不能去掉
//            int leftIndex = s.charAt(left) - 'A';
//            if (window[leftIndex] > need[leftIndex]) {
//                window[leftIndex]--;
//                left++;
//            }
//            //再判断右边能不能去掉
//            int rightIndex = s.charAt(right) - 'A';
//            if (window[rightIndex] > need[rightIndex]) {
//                window[rightIndex]--;
//                right--;
//            }
//        }
//        return s.substring(left, right);
//    }


}
