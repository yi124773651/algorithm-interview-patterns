package no7_binarysearch.practice.stage2;

/**
 * @ClassName LeetCode_33
 * @Description
 * 题目: 整数数组 nums 按升序排列，数组中的值互不相同。在传递给函数之前，nums 在预先未知的某个下标 k 上进行了旋转。给你旋转后的数组 nums 和一个整数 target，如果 nums 中存在这个目标值，则返回它的下标，否则返回 -1。你必须设计一个时间复杂度为 O(log n) 的算法。
 * 示例1: 输入：nums = [4,5,6,7,0,1,2], target = 0 输出：4
 * 示例2: 输入：nums = [4,5,6,7,0,1,2], target = 3 输出：-1
 * 提示: 1 <= nums.length <= 5000; -10^4 <= nums[i] <= 10^4; nums 中的每个值都独一无二; nums 肯定会在某个点上旋转
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_33 {
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(binarySearch(nums, 0));  // 4
        System.out.println(binarySearch(nums, 3));  // -1
    }

    public static int binarySearch(int[] nums, int target) {
        // TODO
        return -1;
    }
}
