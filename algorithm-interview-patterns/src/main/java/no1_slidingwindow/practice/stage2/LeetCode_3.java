package no1_slidingwindow.practice.stage2;

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
        int result = 0, left = 0;
        for (int right = 0; right < s.length(); right++) {
            //1.判断收缩条件
            //2.扩展窗口，更新状态
            //3.更新结果
        }
        // TODO
        return result;
    }
}
