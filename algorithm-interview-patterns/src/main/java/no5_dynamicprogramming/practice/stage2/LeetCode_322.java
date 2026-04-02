package no5_dynamicprogramming.practice.stage2;

/**
 * @ClassName LeetCode_322
 * @Description 零钱兑换
 * 题目：给你一个整数数组 coins 表示不同面额的硬币和一个整数 amount 表示总金额。计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。每种硬币的数量是无限的。
 * 示例1：输入：coins = [1,2,5], amount = 11 输出：3 解释：11 = 5 + 5 + 1
 * 示例2：输入：coins = [2], amount = 3 输出：-1
 * 提示：1 <= coins.length <= 12; 1 <= coins[i] <= 2^31 - 1; 0 <= amount <= 10^4
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_322 {

    public static void main(String[] args) {
        System.out.println(dp(new int[]{1, 2, 5}, 11)); // 3
        System.out.println(dp(new int[]{2}, 3)); // -1
    }

    public static int dp(int[] coins, int amount) {
        // TODO
        return -1;
    }
}
