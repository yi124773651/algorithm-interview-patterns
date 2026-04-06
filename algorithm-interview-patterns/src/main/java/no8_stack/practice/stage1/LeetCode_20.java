package no8_stack.practice.stage1;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName LeetCode_20
 * @Description 有效的括号
 * <p>
 * 题目: 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s，判断字符串是否有效。
 * 有效字符串需满足：左括号必须用相同类型的右括号闭合；左括号必须以正确的顺序闭合；
 * 每个右括号都有一个对应的相同类型的左括号。
 * <p>
 * 示例1:
 * 输入：s = "()"
 * 输出：true
 * <p>
 * 示例2:
 * 输入：s = "()[]{}"
 * 输出：true
 * <p>
 * 示例3:
 * 输入：s = "(]"
 * 输出：false
 * <p>
 * 提示:
 * 1 <= s.length <= 10^4;
 * s 仅由括号 '()[]{}' 组成
 * <p>
 * 解题整理：
 * 1. 旧解已经抓住了“使用栈处理括号匹配”的核心思路。
 * 2. 旧解的问题不在于方向错误，而在于“非法右括号没有第一时间返回 false”。
 * 3. 标准解的关键升级是：左括号负责入栈，右括号负责校验；一旦不匹配立刻结束。
 * 4. 这一题可以作为“栈处理配对/嵌套问题”的入门模板。
 * 5. 实现栈时更推荐使用 ArrayDeque，因为它比 LinkedList 更轻量，做栈更合适。
 *  - 栈 / 队列：优先 ArrayDeque
 *  - 链表结构需求明显：再考虑 LinkedList
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_20 {

    public static void main(String[] args) {
        LeetCode_20 solution = new LeetCode_20();

        System.out.println("===== 旧解 =====");
        System.out.println(solution.stackSolve("()"));       // true
        System.out.println(solution.stackSolve("()[]{}"));   // true
        System.out.println(solution.stackSolve("(]"));       // false
        System.out.println(solution.stackSolve("([)]"));     // false

        System.out.println("===== 标准解 =====");
        System.out.println(solution.standardSolve("()"));       // true
        System.out.println(solution.standardSolve("()[]{}"));   // true
        System.out.println(solution.standardSolve("(]"));       // false
        System.out.println(solution.standardSolve("([)]"));     // false

        System.out.println("===== 模板解 =====");
        System.out.println(solution.templateSolve("()"));       // true
        System.out.println(solution.templateSolve("()[]{}"));   // true
        System.out.println(solution.templateSolve("(]"));       // false
        System.out.println(solution.templateSolve("([)]"));     // false
    }

    /**
     * 旧解保留。
     *
     * 旧解思路：
     * 1. 遇到能和栈顶匹配的右括号，就弹栈。
     * 2. 否则把当前字符压栈。
     * 3. 最后通过栈是否为空来判断是否合法。
     *
     * 旧解的问题：
     * 1. 它把“匹配失败的右括号”也压入了栈中。
     * 2. 没有做到“发现非法情况立即返回 false”。
     * 3. 本题由于输入受限为括号字符，所以很多情况下最终结果仍然正确。
     * 4. 但从思维模型上看，它不如标准解清晰，也不便于迁移到同类题目。
     */
    public boolean stackSolve(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (')' == c && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
                continue;
            } else if (']' == c && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
                continue;
            } else if ('}' == c && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop();
                continue;
            }
            stack.push(c);
        }
        return stack.isEmpty();
    }

    /**
     * 标准解。
     *
     * 标准解思路：
     * 1. 左括号入栈，表示“等待未来某个右括号来匹配”。
     * 2. 右括号出现时，必须先确认栈非空。
     * 3. 再检查栈顶左括号与当前右括号是否匹配。
     * 4. 只要出现一次不匹配，立刻返回 false。
     * 5. 遍历结束后，栈为空才说明所有括号都成功配对。
     *
     * 从旧解过渡到标准解的关键只有一句：
     * “不要把非法右括号压栈，而是立即判错返回 false。”
     */
    public boolean standardSolve(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
                continue;
            }

            if (stack.isEmpty()) {
                return false;
            }

            char top = stack.pop();
            if ((c == ')' && top != '(')
                    || (c == ']' && top != '[')
                    || (c == '}' && top != '{')) {
                return false;
            }
        }

        return stack.isEmpty();
    }

    /**
     * 这类题目的模板解。
     *
     * 适用场景：
     * 1. 成对符号匹配。
     * 2. 嵌套结构是否合法。
     * 3. 需要处理“最近尚未完成匹配”的元素。
     *
     * 模板口诀：
     * 1. 左边入栈。
     * 2. 右边校验。
     * 3. 非法即返。
     * 4. 最后判空。
     */
    public boolean templateSolve(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (current == '(' || current == '[' || current == '{') {
                stack.push(current);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();
                if (!isMatch(top, current)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    /**
     * 判断一对左右括号是否匹配。
     */
    private boolean isMatch(char left, char right) {
        return (left == '(' && right == ')')
                || (left == '[' && right == ']')
                || (left == '{' && right == '}');
    }
}
