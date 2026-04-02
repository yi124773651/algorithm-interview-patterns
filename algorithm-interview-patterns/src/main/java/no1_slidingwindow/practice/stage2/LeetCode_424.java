package no1_slidingwindow.practice.stage2;

/**
 * @ClassName LeetCode_424
 * @Description 给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。
 * 在执行上述操作后，返回 包含相同字母的最长子字符串的长度。
 *
 * 示例 1：
 *
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 * 示例 2：
 *
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 *
 * 提示：
 * 1 <= s.length <= 105
 * s 仅由大写英文字母组成
 * 0 <= k <= s.length
 * @Author gm
 * @Date 2026/4/1 18:34
 */
public class LeetCode_424 {
    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;
        int result = LeetCode_424.slidingWindow(s, k);
        System.out.println(result);
    }

    private static int slidingWindow(String s, int k) {
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            //1.扩展窗口，更新状态
            //2.判断是否需要缩容
            //3.更新结果
        }
        // TODO
        return 0;
    }
}
