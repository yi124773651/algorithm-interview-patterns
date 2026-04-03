package no5_dynamicprogramming.practice.stage2;

/**
 * @ClassName LeetCode_300
 * @Description 最长递增子序列
 * 题目：给你一个整数数组 nums，找到其中最长严格递增子序列的长度。
 *
 * 示例1：
 * 输入：
 * nums = [10,9,2,5,3,7,101,18]
 * 输出：
 * 4
 * 解释：最长递增子序列是 [2,3,7,101]
 *
 * 示例2：
 * 输入：
 * nums = [0,1,0,3,2,3]
 * 输出：
 * 4
 *
 * 提示：1 <= nums.length <= 2500; -10^4 <= nums[i] <= 10^4
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_300 {

    public static void main(String[] args) {
        //dp ---> 子序列
        System.out.println(dp(new int[]{10, 9, 2, 5, 3, 7, 101, 18})); // 4
        System.out.println(dp(new int[]{0, 1, 0, 3, 2, 3})); // 4
        //滑动窗口 ---> 子数组
//        System.out.println(slidingWindow(new int[]{10, 9, 2, 5, 3, 7, 101, 18})); // 3
//        System.out.println(slidingWindow(new int[]{0, 1, 0, 3, 2, 3})); // 2
    }

    /**
     * 1.定义状态：d[i] 是数组长度为i时的最长递增子序列的长度
     * 修正：dp[i] = 以 nums[i] 结尾的最长递增子序列长度
     * 2. 状态转移方程:
     * d[i]  max{d[1],d[2],....d[i-1]}  (1)使得最大值更大 max + 1  d[i] = d[i-1] + 1 (2)没有仍为max d[i] = d[i-1]
     * 3.边界条件：i = 1时  d[1]=1
     * 4.遍历范围：从小到大
     * <p>
     * ----------------------------------------------------------------
     * 状态转移：
     * 对于每个 i，往前找所有 j < i，如果 nums[j] < nums[i]，说明可以接在 j 后面：
     * dp[i] = max(dp[j]) + 1   其中 j < i 且 nums[j] < nums[i]
     * 如果前面没有比它小的，dp[i] = 1（自己单独一个）
     * <p>
     * 举例：
     * nums = [10, 9, 2, 5, 3, 7, 101, 18]
     *         ↓   ↓  ↓  ↓  ↓  ↓   ↓   ↓
     * dp   = [1,  1, 1, 2, 2, 3,  4,  4]
     * <p>
     * dp[3]=2: nums[3]=5, 前面比5小的有2, dp[2]+1=2
     * dp[5]=3: nums[5]=7, 前面比7小的有2,5,3, 最大dp是dp[3]=2, 所以2+1=3
     * dp[6]=4: nums[6]=101, 前面比101小的最大dp是dp[5]=3, 所以3+1=4
     * <p>
     * ---------------------------------------------------------------------
     * dp[4] 对应 nums[4] = 3：
     * 往前找比 3 小的：只有 nums[2] = 2
     * dp[2] = 1
     * 所以 dp[4] = dp[2] + 1 = 2
     * 子序列就是 [2, 3]，长度为 2。✅
     * <p>
     * <p>
     * 你可能会问：为什么 dp[4] 和 dp[3] 都是 2？
     * -------------------------------------------------------------------
     * 索引	       值	            能接在谁后面	       dp值	            对应子序列
     * dp[3]	   5	                 2	            2	              [2, 5]
     * dp[4]	   3	                 2	            2	              [2, 3]
     * <p>
     * 虽然都是长度 2，但代表的是不同的子序列。后面 dp[5]=3 时，7 既可以接在 5 后面 [2,5,7]，也可以接在 3 后面 [2,3,7]，都是长度 3。
     * <p>
     * 是找小于i的j中dp值 最大的  在其基础上 + 1
     *
     * @param nums
     * @return
     */
    public static int dp(int[] nums) {
        int n = nums.length;
        int maxLen = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1; //至少包含自己
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen,dp[i]);
        }
        return maxLen;
    }


    /**
     * 不适合  没有区分 连续递增子数组 和 子序列
     * <p>
     * nums = [10, 9, 2, 5, 3, 7, 101, 18]
     * <p>
     * 子序列 [2, 3, 7, 101] ✅ 不连续，但递增
     * 连续子数组 [2, 5] 或 [3, 7, 101] 只能取相邻的
     * <p>
     * 结论：这题不能用滑动窗口。
     *
     * @param nums
     * @return
     */
    public static int slidingWindow(int[] nums) {
        int maxSize = 0;
        int left = 0;
        //是在 i 前面找使得 nums[i] > num[j] 的所有j中 dp值最大的 在其基础上+1 如果找不到 d[i] = 1
        for (int right = 0; right < nums.length; right++) {
            if (right > 0 && nums[right] < nums[right - 1]) {
                left = right;
            }
            maxSize = Math.max(maxSize, right - left + 1);
        }
        return maxSize;
    }

}
