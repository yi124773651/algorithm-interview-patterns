package no1_slidingwindow.practice.stage2;

/**
 * @ClassName LeetCode_424
 * @Description 给你一个字符串 s 和一个整数 k 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 k 次。
 * 在执行上述操作后，返回 包含相同字母的最长子字符串的长度。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 * 示例 2：
 * <p>
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 * 可能存在其他的方法来得到同样的结果。
 * <p>
 * 提示：
 * 1 <= s.length <= 105
 * s 仅由大写英文字母组成
 * 0 <= k <= s.length
 * @Author gm
 * @Date 2026/4/1 18:34
 */
public class LeetCode_424 {
    public static void main(String[] args) {
//        String s = "ABAB";
//        int k = 2;
        String s = "AABABBA";
        int k = 1;
        int result = LeetCode_424.slidingWindow(s, k);
        System.out.println(result);
    }

    private static int slidingWindow(String s, int k) {
        int maxCount = 0;//记录出现频次最高
        int left = 0, right;
        int[] count = new int[26];//替代hashmap   注意:对于字母可以用数组来代替map进行频率统计
        for (right = 0; right < s.length(); right++) {
            //1.扩容，更新状态
            int currentIndex = s.charAt(right) - 'A'; //注意如何找到数组下标
            count[currentIndex]++;
            maxCount = Math.max(maxCount, count[currentIndex]);
            //2.判断是否需要缩容
            if (right - left + 1 - maxCount > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
            //3.更新结果  循环结束后 right - left就是最大窗口
        }
        return right - left;
    }
}
