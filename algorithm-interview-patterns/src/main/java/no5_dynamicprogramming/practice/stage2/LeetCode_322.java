package no5_dynamicprogramming.practice.stage2;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_322
 * @Description 零钱兑换
 * 题目：给你一个整数数组 coins 表示不同面额的硬币和一个整数 amount 表示总金额。计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。每种硬币的数量是无限的。
 * <p>
 * 示例 1：
 * 输入：coins = [1,2,5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * <p>
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * <p>
 * 提示：1 <= coins.length <= 12; 1 <= coins[i] <= 2^31 - 1; 0 <= amount <= 10^4
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_322 {

    public static void main(String[] args) {
        System.out.println(dp(new int[]{1, 2, 5}, 11)); // 3
        System.out.println(dp(new int[]{2}, 3)); // -1
//        System.out.println(backTrace(new int[]{1, 2, 5}, 11)); // 3
//        System.out.println(backTrace(new int[]{2}, 3)); // -1
    }

    /**
     * 1.定义状态  d[i]是什么  以i为结尾 coins[i] 结尾  凑成总额所需的最小金币数量
     * 2.状态转移函数  d[i]  和 j<i时d[j]的关系？    coins[i]和coins[j] 的关系 ?
     * 如果coins[i] 比前面的都小 d[i] = min{d[j]}
     * 如果coins[j] 大于前面任意coins[j]  有进一步缩小的可能  关键是coins[j] 这个金额能不能被 前面最小个数金币组合中的元素 代替
     * 关键是每步最小的组合 需要被保存
     * 3.起始条件 d[1] =11
     * 4.遍历顺序  从小到大   错误思路 ×
     * ---------------------------------------------------------------------------------------------------
     * DFS vs DP 的关系
     *                     DFS（你的做法）	                        DP
     * 方向	             自顶向下：从 11 开始拆	                自底向上：从 0 开始填
     * 核心	             枚举所有组合，取最小	                    每个金额只算一次最优
     * 时间	             指数级，非常慢	                        O(amount × coins.length)
     * 本质	             同一个递推关系	                        同一个递推关系
     * <p>
     * <p>
     * DP的思路
     * 凑 amount=11, coins=[1,2,5]
     * 凑 11 的最少硬币数 = min(
     * 凑 11-1=10 的最少硬币数 + 1,   ← 选了面值1
     * 凑 11-2=9  的最少硬币数 + 1,   ← 选了面值2
     * 凑 11-5=6  的最少硬币数 + 1    ← 选了面值5
     * )
     * <p>
     * 1. 定义状态： dp[i] = 凑成金额 i 所需的最少硬币数
     * <p>
     * 2. 状态转移方程：
     * dp[i] = min(dp[i - coin] + 1)   对每个 coin in coins，且 i >= coin
     * <p>
     * 3. 初始条件： dp[0] = 0（凑 0 元需要 0 枚硬币）
     * <p>
     * 4. 遍历顺序： 从小到大，i = 1 到 amount
     * <p>
     * 举例推演
     * coins = [1, 2, 5], amount = 11
     * <p>
     * dp[0]  = 0                          → 0枚
     * dp[1]  = dp[1-1]+1 = dp[0]+1 = 1   → 1枚(1)
     * dp[2]  = min(dp[1]+1, dp[0]+1) = 1 → 1枚(2)
     * dp[3]  = min(dp[2]+1, dp[1]+1) = 2 → 2枚(1+2)
     * dp[5]  = min(dp[4]+1, dp[3]+1, dp[0]+1) = 1 → 1枚(5)
     * ...
     * dp[11] = min(dp[10]+1, dp[9]+1, dp[6]+1) = 3 → 3枚(5+5+1)
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int dp(int[] coins, int amount) {
        int[] dp = new int[amount + 1];  //  关键：dp[i] 表示 凑足金额i所需的最小硬币数量   注意数组大小  amount + 1
        //初始化一个不可能这么大的值  amount + 1
        // 不可能有这么多数量的硬币 凑成amount元
        for (int i = 0; i < dp.length; i++) {
            dp[i] = amount + 1;
        }
        dp[0] = 0;//起始条件：凑足 0元需要0个硬币
        //从凑1块钱开始
        for (int i = 1; i < amount + 1; i++) {
            for (int coin : coins) {
                //coins = {1,2,5}  必须要加判断 i>=coin  否则数组越界了 dp[i-coin]
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static int backTrace(int[] coins, int amount) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        int sum = 0;
        LeetCode_322.dfs(coins, amount, sum, path, result);
        int minCount = Integer.MAX_VALUE;
        for (List<Integer> list : result) {
            minCount = Math.min(minCount, list.size());
        }
        return minCount == Integer.MAX_VALUE ? -1 : minCount;
    }

    /**
     * 空间复杂度较大
     * 每次选一枚硬币，剩余金额减少，继续选，直到金额为 0
     * 问题一：生成了大量重复排列
     * 每次 for 从 i=0 开始，会产生 [1,5,5]、[5,1,5]、[5,5,1] 这些重复排列，虽然不影响结果（size 都一样），但浪费大量时间。
     * <p>
     * 问题二：把所有路径都存下来了
     * 只需要最小长度，没必要用 result 存所有组合。可以用一个全局 minCount 就够了。
     * <p>
     * 不过这些都是性能问题，结果是对的。
     *
     * @param coins
     * @param amount
     * @param sum
     * @param path
     * @param result
     */
    private static void dfs(int[] coins, int amount, int sum, List<Integer> path, List<List<Integer>> result) {
        //结束条件
        if (sum > amount) return;
        //收集条件
        if (sum == amount) result.add(new ArrayList<>(path));
        //for循环
        for (int i = 0; i < coins.length; i++) {
            //1.剪枝
            if (sum > amount) continue;
            //2.做选择
            sum += coins[i];
            path.add(coins[i]);
            //3.递归
            LeetCode_322.dfs(coins, amount, sum, path, result);
            //4.撤销选择
            sum -= coins[i];
            path.remove(path.size() - 1);
        }
    }
}
