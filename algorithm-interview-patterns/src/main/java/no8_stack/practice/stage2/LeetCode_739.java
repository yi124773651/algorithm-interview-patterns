package no8_stack.practice.stage2;

import java.util.Arrays;

/**
 * @ClassName LeetCode_739
 * @Description 每日温度
 *
 * 题目: 给定一个整数数组 temperatures 表示每天的温度，返回一个数组 answer，
 * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。
 * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 示例1: 输入：temperatures = [73,74,75,71,69,72,76,73] 输出：[1,1,4,2,1,1,0,0]
 * 示例2: 输入：temperatures = [30,40,50,60] 输出：[1,1,1,0]
 *
 * 提示: 1 <= temperatures.length <= 10^5; 30 <= temperatures[i] <= 100
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_739 {

    public static void main(String[] args) {
        LeetCode_739 solution = new LeetCode_739();
        System.out.println(Arrays.toString(solution.stackSolve(new int[]{73, 74, 75, 71, 69, 72, 76, 73}))); // [1,1,4,2,1,1,0,0]
        System.out.println(Arrays.toString(solution.stackSolve(new int[]{30, 40, 50, 60})));                  // [1,1,1,0]
    }

    public int[] stackSolve(int[] temperatures) {
        // TODO: 使用单调栈解决每日温度问题
        return new int[temperatures.length];
    }
}
