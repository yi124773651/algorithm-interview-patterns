package no2_twopointertechnique.practice.stage2;

/**
 * @ClassName LeetCode_75
 * @Description
 * 题目: 给定一个包含红色、白色和蓝色（用 0、1 和 2 分别表示）共 n 个元素的数组 nums，
 * 原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 *
 * 示例 1：
 *
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 *
 * 示例 2：
 *
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 *
 * 进阶：你能想出一个仅使用常数空间的一趟扫描算法吗？（荷兰国旗问题，三指针）
 * @Author gm
 * @Date 2026/4/2 15:00
 */
public class LeetCode_75 {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        LeetCode_75.twoPointer(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    private static void twoPointer(int[] nums) {
        int left = 0, right = nums.length - 1, cur = 0;
        while (cur <= right) {
            // TODO
        }
    }
}
