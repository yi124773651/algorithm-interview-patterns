package no8_stack.practice.stage3;

/**
 * @ClassName LeetCode_394
 * @Description 字符串解码
 *
 * 题目: 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。
 *
 * 示例1: 输入：s = "3[a]2[bc]" 输出："aaabcbc"
 * 示例2: 输入：s = "3[a2[c]]" 输出："accaccacc"
 * 示例3: 输入：s = "2[abc]3[cd]ef" 输出："abcabccdcdcdef"
 *
 * 提示: 1 <= s.length <= 30; s 由小写英文字母、数字和方括号 '[]' 组成; 保证输入是有效的
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_394 {

    public static void main(String[] args) {
        LeetCode_394 solution = new LeetCode_394();
        System.out.println(solution.stackSolve("3[a]2[bc]"));    // aaabcbc
        System.out.println(solution.stackSolve("3[a2[c]]"));     // accaccacc
        System.out.println(solution.stackSolve("2[abc]3[cd]ef")); // abcabccdcdcdef
    }

    public String stackSolve(String s) {
        // TODO: 使用栈解决字符串解码问题
        return "";
    }
}
