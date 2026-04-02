package no6_greedy.practice.stage3;

/**
 * @ClassName LeetCode_134
 * @Description 加油站
 * 题目: 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
 * 你从其中的一个加油站出发，开始时油箱为空。给定两个整数数组 gas 和 cost，
 * 如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 *
 * 示例1: 输入：gas = [1,2,3,4,5], cost = [3,4,5,1,2] 输出：3
 * 示例2: 输入：gas = [2,3,4], cost = [3,4,3] 输出：-1
 *
 * 提示: n == gas.length == cost.length; 1 <= n <= 10^5; 0 <= gas[i], cost[i] <= 10^4
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_134 {

    public static void main(String[] args) {
        LeetCode_134 solution = new LeetCode_134();
        System.out.println(solution.greedy(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2})); // 3
        System.out.println(solution.greedy(new int[]{2, 3, 4}, new int[]{3, 4, 3})); // -1
    }

    public int greedy(int[] gas, int[] cost) {
        // TODO
        return -1;
    }
}
