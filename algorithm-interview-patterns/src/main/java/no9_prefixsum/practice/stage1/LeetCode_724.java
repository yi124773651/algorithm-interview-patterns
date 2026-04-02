package no9_prefixsum.practice.stage1;

/**
 * @ClassName LeetCode_724
 * @Description 寻找数组的中心下标
 * 题目：给你一个整数数组 nums，请计算数组的中心下标。数组中心下标是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
 * 如果中心下标位于数组最左端，那么左侧数之和视为 0。如果不存在返回 -1。
 *
 * 示例1：输入：nums = [1,7,3,6,5,6] 输出：3
 * 示例2：输入：nums = [1,2,3] 输出：-1
 *
 * 提示：
 * 1 <= nums.length <= 10^4
 * -1000 <= nums[i] <= 1000
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_724 {

    public static int prefixSum(int[] nums) {
        // TODO
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(prefixSum(new int[]{1, 7, 3, 6, 5, 6})); // 3
        System.out.println(prefixSum(new int[]{1, 2, 3})); // -1
    }
}
