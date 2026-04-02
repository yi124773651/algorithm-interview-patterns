package no2_twopointertechnique.practice.stage1;

/**
 * @ClassName LeetCode_125
 * @Description 题目: 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，
 * 短语正着读和反着读都一样，则认为该短语是一个回文串。返回 true 或 false。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "A man, a plan, a canal: Panama"
 * 输出：true
 * 解释："amanaplanacanalpanama" 是回文串。
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "race a car"
 * 输出：false
 * 解释："raceacar" 不是回文串。
 * <p>
 * 示例 3：
 * <p>
 * 输入：s = " "
 * 输出：true
 * 解释：在移除非字母数字字符之后，s 是一个空字符串 "" 。空字符串正着反着读都一样，所以是回文串。
 * <p>
 * 提示：
 * 1 <= s.length <= 2 * 10^5
 * s 仅由可打印的 ASCII 字符组成
 * @Author gm
 * @Date 2026/4/2 15:00
 */
public class LeetCode_125 {
    public static void main(String[] args) {
//        String s = "A man, a plan, a canal: Panama";
        String s = "race a car";
        boolean result = LeetCode_125.twoPointer(s);
        System.out.println(result);
    }

    private static boolean twoPointer(String s) {
        //所有大写字符转换为小写字符、并移除所有非字母数字字符之后
        // 【需要学习常规处理方案】
        //两种方案：
        // 1.先清洗     String cleaned = s.toLowerCase().replaceAll("[^a-z0-9]", "");
        // 2.不清洗利用api跳过不符题意的字符
        //两个关键api:
        // - Character.isLetterOrDigit(ch) — 判断是否为字母或数字
        // - Character.toLowerCase(ch) — 单字符转小写
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
            if (!(Character.toLowerCase(s.charAt(left)) == Character.toLowerCase(s.charAt(right)))) return false;
            left++;
            right--;

        }
        return true;
    }
}
