package no8_stack.practice.stage3;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName LeetCode_394
 * @Description 字符串解码
 * <p>
 * 题目：给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为：k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。
 * <p>
 * 示例1：输入：s = "3[a]2[bc]" 输出："aaabcbc"
 * 示例2：输入：s = "3[a2[c]]" 输出："accaccacc"
 * 示例3：输入：s = "2[abc]3[cd]ef" 输出："abcabccdcdcdef"
 * <p>
 * 提示：1 <= s.length <= 30；s 由小写英文字母、数字和方括号 '[]' 组成；保证输入是有效的
 * -------------------------------------------------------------------------
 * <p>
 * 这份文件刻意做三件事：
 * 1. 保留你的旧解，不直接删掉，方便对照思路演进。
 * 2. 给出这道题的标准双栈解法。
 * 3. 把“如何从旧解平滑过渡到标准解”以及“应该建立什么心理表征”写清楚。
 * <p>
 * 一、你当前旧解的核心问题
 * 1. 你把问题理解成了“遇到 ] 时，把前面的内容弹出来重复几次”。
 * 2. 这个理解只抓住了局部动作，没有抓住“层级上下文”。
 * 3. 一旦出现嵌套、多位数、前缀普通字符，当前写法就容易丢信息或串层。
 * <p>
 * 二、这题真正要维护的不是“字符栈本身”，而是“每一层的上下文”
 * 1. 当前层正在构造什么字符串。
 * 2. 进入这一层之前，上一层已经构造了什么字符串。
 * 3. 当前层最终要重复多少次。
 * <p>
 * 三、最重要的心理表征
 * 1. 遇到字母：把它接到“当前层字符串”后面。
 * 2. 遇到数字：把它接到“当前层重复次数”后面，注意可能是多位数。
 * 3. 遇到 [ ：说明要进入新的一层，把“旧字符串”和“重复次数”压栈保存。
 * 4. 遇到 ] ：说明当前层结束，把“当前层字符串重复 k 次”后，拼回上一层。
 * <p>
 * 四、过渡句
 * - 旧思路：我在处理字符弹栈与局部展开。
 * - 新思路：我在管理递归层级，只不过用两个栈手动模拟递归现场。
 * <p>
 * 五、标准解一句话总结
 * - 数字栈保存“这一层要重复多少次”，字符串栈保存“进入这一层前已经拼好的内容”。
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_394 {

    public static void main(String[] args) {
        LeetCode_394 solution = new LeetCode_394();

        String case1 = "3[a]2[bc]";
        String case2 = "3[a2[c]]";
        String case3 = "2[abc]3[cd]ef";
        String case4 = "10[a]";
        String case5 = "abc3[cd]xyz";

        System.out.println("===== 保留旧解（用于观察局限） =====");
        System.out.println(case1 + " => " + solution.stackSolve(case1));
        System.out.println(case2 + " => " + solution.stackSolve(case2));
        System.out.println(case3 + " => " + solution.stackSolve(case3));
        System.out.println(case4 + " => " + solution.stackSolve(case4));
        System.out.println(case5 + " => " + solution.stackSolve(case5));

        System.out.println("===== 标准双栈解 =====");
        System.out.println(case1 + " => " + solution.standardStackSolve(case1));
        System.out.println(case2 + " => " + solution.standardStackSolve(case2));
        System.out.println(case3 + " => " + solution.standardStackSolve(case3));
        System.out.println(case4 + " => " + solution.standardStackSolve(case4));
        System.out.println(case5 + " => " + solution.standardStackSolve(case5));
    }

    /**
     * 保留你的旧解，不修正逻辑，只用于复盘。
     * <p>
     * 旧解的问题集中在三点：
     * 1. 只弹一个字符作为重复次数，无法处理多位数，比如 10[a]。
     * 2. 只在栈空时把临时结果写入结果集，无法处理前缀普通字符，比如 abc3[cd]xyz。
     * 3. tmpList 跨层复用，嵌套时层级上下文会互相污染。
     */
    public String stackSolve(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        LinkedList<Character> tmpList = new LinkedList<>();
        LinkedList<Character> resultList = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ']') {
                while (!stack.isEmpty() && stack.peek() != '[') {
                    tmpList.addFirst(stack.pop());
                }
                stack.pop();
                int k = Integer.parseInt(stack.pop().toString());
                List<Character> copy = new LinkedList<>(tmpList);
                k--;
                while (k-- > 0) {
                    tmpList.addAll(copy);
                }
                if (stack.isEmpty()) {
                    resultList.addAll(tmpList);
                    tmpList.clear();
                }
                continue;
            }
            stack.push(c);
        }
        LinkedList<Character> stackList = new LinkedList<>();
        while (!stack.isEmpty()) {
            stackList.addFirst(stack.pop());
        }
        resultList.addAll(stackList);

        StringBuilder builder = new StringBuilder();
        for (Character c : resultList) {
            builder.append(c);
        }
        return builder.toString();
    }

    /**
     * 标准双栈解。
     * <p>
     * 心理表征一定要稳定成下面这套：
     * 1. currentBuilder：当前这一层已经解码出的字符串。
     * 2. repeatTimes：当前这一层前面读到的重复次数。
     * 3. countStack：进入每一层时，把这一层对应的重复次数存起来。
     * 4. stringStack：进入每一层时，把进入前的旧字符串存起来。
     * <p>
     * 处理流程：
     * 1. 数字：累积 repeatTimes，支持多位数。
     * 2. 字母：直接拼到 currentBuilder 后。
     * 3. 左括号：说明开始新层，把旧状态压栈，再重置当前层状态。
     * 4. 右括号：说明当前层结束，弹出上一层状态，执行“上一层 + 当前层重复 k 次”。
     * <p>
     * 时间复杂度：O(n + 解码后字符串长度)
     * 空间复杂度：O(n + 解码后字符串长度)
     */
    public String standardStackSolve(String s) {
        Deque<Integer> countStack = new ArrayDeque<>();
        Deque<StringBuilder> stringStack = new ArrayDeque<>();

        StringBuilder currentBuilder = new StringBuilder();
        int repeatTimes = 0;

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            if (Character.isDigit(currentChar)) {
                // 这里是在解析重复次数，并且要支持多位数。
                // 例如读取 "23[a]" 时：
                // 1. 先读到字符 '2'，repeatTimes = 0 * 10 + 2 = 2
                // 2. 再读到字符 '3'，repeatTimes = 2 * 10 + 3 = 23
                // 所以“乘 10”表示把原来的数字整体左移一位，
                // “currentChar - '0'”表示把字符数字转成真正的整数。
                repeatTimes = repeatTimes * 10 + (currentChar - '0');
                continue;
            }

            if (currentChar == '[') {
                countStack.push(repeatTimes);
                stringStack.push(currentBuilder);
                repeatTimes = 0;
                currentBuilder = new StringBuilder();
                continue;
            }

            if (currentChar == ']') {
                int currentLevelRepeatTimes = countStack.pop();
                StringBuilder previousBuilder = stringStack.pop();
                StringBuilder repeatedPart = new StringBuilder();
                for (int j = 0; j < currentLevelRepeatTimes; j++) {
                    repeatedPart.append(currentBuilder);
                }
                currentBuilder = previousBuilder.append(repeatedPart);
                continue;
            }

            currentBuilder.append(currentChar);
        }

        return currentBuilder.toString();
    }
}
