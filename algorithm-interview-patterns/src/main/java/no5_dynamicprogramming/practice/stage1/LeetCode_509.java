package no5_dynamicprogramming.practice.stage1;

/**
 * @ClassName LeetCode_509
 * @Description 斐波那契数
 * 题目：斐波那契数（通常用 F(n) 表示）形成的序列称为斐波那契数列。F(0) = 0，F(1) = 1，F(n) = F(n - 1) + F(n - 2)（n > 1）。给定 n，计算 F(n)。
 * 示例1：输入：n = 2 输出：1
 * 示例2：输入：n = 4 输出：3
 * 提示：0 <= n <= 30
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_509 {

    public static void main(String[] args) {
        System.out.println(dp(2)); // 1
        System.out.println(dp(4)); // 3
    }

    /**
     * 1.定义状态：dp[i] 是 斐波那契数 F(n)
     * 2.状态转移方程： dp[i] = dp[i-1] + dp[i-2]  i> 1
     * 3.初始条件：dp[0] = 0 dp[1]=1
     * 4.遍历顺序 从小到大
     *
     * @param n
     * @return
     */
    public static int dp(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];//n+1数组大小
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {   //条件n+1
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
