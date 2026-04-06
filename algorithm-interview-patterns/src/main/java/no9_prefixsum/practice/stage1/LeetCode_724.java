package no9_prefixsum.practice.stage1;

/**
 * @ClassName LeetCode_724
 * @Description 寻找数组的中心下标
 * 题目：给你一个整数数组 nums，请计算数组的中心下标。
 * 数组中心下标是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果中心下标位于数组最左端，那么左侧数之和视为 0。如果不存在返回 -1。
 * <p>
 * 示例1：
 * 输入：nums = [1,7,3,6,5,6]
 * 输出：3
 * <p>
 * 示例2：
 * 输入：nums = [1,2,3]
 * 输出：-1
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^4
 * -1000 <= nums[i] <= 1000
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_724 {

    /**
     * 原解：思路正确，可以通过本题。
     *
     * 这份写法的优点：
     * 1. 已经抓住了前缀和的核心思想。
     * 2. 能把暴力枚举每个位置左右和的 O(n^2)，优化为 O(n)。
     * 3. 按从左到右遍历，天然满足“返回最左侧中心下标”的要求。
     *
     * 但如果从“更标准”的角度看，还可以继续优化：
     * 1. 方法名 prefixSum 不够贴题，面试中更推荐命名为 pivotIndex。
     * 2. 注释“右边 - 左边”不够准确，这里其实是在分别计算左右两侧和。
     * 3. 这题虽然能用前缀和数组做，但还能进一步优化到 O(1) 额外空间。
     *
     * 所以这份原解可以这样评价：
     * “思路是正确的，前缀和也用对了；
     *  只是还可以进一步朝着更贴题、更省空间的标准写法演进。”
     */
    public static int prefixSum(int[] nums) {
        // preSum[i] 表示前 i 个元素的和，因此数组长度为 nums.length + 1
        int[] preSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        for (int i = 1; i < preSum.length; i++) {
            int left = preSum[i - 1];
            int right = preSum[preSum.length - 1] - preSum[i];
            if (left == right) {
                return i - 1;
            }
        }
        return -1;
    }

    /**
     * 标准解：总和 + 左侧和。
     *
     * 核心思想：
     * 1. 先求整个数组总和 total。
     * 2. 遍历到下标 i 时，leftSum 表示 i 左侧元素和。
     * 3. 那么右侧元素和就是 total - leftSum - nums[i]。
     * 4. 如果 leftSum == rightSum，则 i 就是中心下标。
     *
     * 这个写法比前缀和数组更标准的原因：
     * 1. 时间复杂度仍然是 O(n)。
     * 2. 额外空间从 O(n) 优化到了 O(1)。
     * 3. 命名与题意更贴近，表达更自然。
     */
    public static int pivotIndex(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }

        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int rightSum = total - leftSum - nums[i];
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

    /**
     * 如何从你的原解流畅过渡到标准解？
     *
     * 可以按照下面这个节奏来讲：
     * 1. 暴力做法是：枚举每个下标，再分别求左侧和与右侧和，时间复杂度是 O(n^2)。
     * 2. 因为左右两段和会重复计算，所以可以先想到用前缀和，把时间优化到 O(n)。
     * 3. 你的原解已经完成了这一步，说明思路完全正确。
     * 4. 继续观察会发现：这题并不一定非要把整个前缀和数组都存下来。
     * 5. 因为判断条件本质上只是“左边和 == 右边和”，所以只要维护总和和左侧和就够了。
     * 6. 于是就能把空间复杂度进一步降到 O(1)，这就是更标准的写法。
     *
     * 一句话过渡模板：
     * “我先用前缀和把区间重复计算消掉，已经能在线性时间内解决；
     *  再进一步，由于这题只关心左右两边是否相等，其实不需要完整前缀和数组，
     *  只维护总和和左侧和，就能把空间优化到 O(1)。”
     */

    /**
     * 这题的通用判断模板。
     *
     * 适用题型：
     * 1. 需要判断某个位置左右两侧是否满足某种和关系。
     * 2. 不要求频繁区间查询，只需要单次线性扫描。
     *
     * 通用模板：
     * 1. 先统计 total。
     * 2. 遍历数组，维护 leftSum。
     * 3. 当前下标 i 的右侧和为 total - leftSum - nums[i]。
     * 4. 若 leftSum == rightSum，返回当前位置。
     * 5. 否则更新 leftSum += nums[i]。
     */
    public static int pivotIndexTemplate(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }

        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int rightSum = total - leftSum - nums[i];
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 7, 3, 6, 5, 6};
        int[] nums2 = {1, 2, 3};

        System.out.println("===== 保留你的原解 =====");
        System.out.println(prefixSum(nums1)); // 3
        System.out.println(prefixSum(nums2)); // -1

        System.out.println("===== 更标准的写法 =====");
        System.out.println(pivotIndex(nums1)); // 3
        System.out.println(pivotIndex(nums2)); // -1

        System.out.println("===== 通用模板演示 =====");
        System.out.println(pivotIndexTemplate(nums1)); // 3
        System.out.println(pivotIndexTemplate(nums2)); // -1
    }
}
