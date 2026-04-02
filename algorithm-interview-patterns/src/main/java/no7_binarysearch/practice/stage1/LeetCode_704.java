package no7_binarysearch.practice.stage1;

/**
 * @ClassName LeetCode_704
 * @Description
 * 题目: 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * 示例1: 输入：nums = [-1,0,3,5,9,12], target = 9 输出：4
 * 示例2: 输入：nums = [-1,0,3,5,9,12], target = 2 输出：-1
 * 提示: 1 <= nums.length <= 10^4; -10^4 < nums[i], target < 10^4; nums 中的所有元素是不重复的; nums 按升序排列
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_704 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        System.out.println(binarySearch(nums, 9));  // 4
        System.out.println(binarySearch(nums, 2));  // -1
    }

    public static int binarySearch(int[] nums, int target) {
        // TODO
        return -1;
    }
}
