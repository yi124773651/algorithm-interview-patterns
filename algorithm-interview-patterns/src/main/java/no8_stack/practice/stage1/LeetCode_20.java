package no8_stack.practice.stage1;

/**
 * @ClassName LeetCode_20
 * @Description 有效的括号
 *
 * 题目: 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s，判断字符串是否有效。
 * 有效字符串需满足：左括号必须用相同类型的右括号闭合；左括号必须以正确的顺序闭合；
 * 每个右括号都有一个对应的相同类型的左括号。
 *
 * 示例1: 输入：s = "()" 输出：true
 * 示例2: 输入：s = "()[]{}" 输出：true
 * 示例3: 输入：s = "(]" 输出：false
 *
 * 提示: 1 <= s.length <= 10^4; s 仅由括号 '()[]{}' 组成
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_20 {

    public static void main(String[] args) {
        LeetCode_20 solution = new LeetCode_20();
        System.out.println(solution.stackSolve("()"));       // true
        System.out.println(solution.stackSolve("()[]{}"));   // true
        System.out.println(solution.stackSolve("(]"));       // false
    }

    public boolean stackSolve(String s) {
        // TODO: 使用栈解决有效括号问题
        return false;
    }
}
