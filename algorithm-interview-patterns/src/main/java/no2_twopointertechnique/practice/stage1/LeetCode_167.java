package no2_twopointertechnique.practice.stage1;

/**
 * @ClassName LeetCode_167
 * @Description 题目: 给你一个下标从 1 开始的整数数组 numbers，该数组已按非递减顺序排列，
 * 请你从数组中找出满足相加之和等于目标数 target 的两个数。返回下标（下标从1开始）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：numbers = [2,3,4], target = 6
 * 输出：[1,3]
 * <p>
 * 示例 3：
 * <p>
 * 输入：numbers = [-1,0], target = -1
 * 输出：[1,2]
 * <p>
 * 提示：
 * 2 <= numbers.length <= 3 * 10^4
 * -1000 <= numbers[i] <= 1000
 * numbers 按非递减顺序排列
 * -1000 <= target <= 1000
 * 仅存在一个有效答案
 * @Author gm
 * @Date 2026/4/2 15:00
 */
public class LeetCode_167 {
    public static void main(String[] args) {
        int[] numbers = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] result = LeetCode_167.twoPointer(numbers, target);
        System.out.println("[" + result[0] + "," + result[1] + "]");
    }

    private static int[] twoPointer(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            //两个while画蛇添足了：提示->仅存在一个有效答案
//            while (left < right && left > 0 && numbers[left] == numbers[left - 1]) left++;
//            while (left < right && right < numbers.length - 1 && numbers[right] == numbers[right + 1]) right--;
            int total = numbers[left] + numbers[right];
            if (total > target) {
                right--;
            } else if (total < target) {
                left++;
            } else {
                return new int[]{left + 1, right + 1};
            }
        }
        return new int[]{-1, -1};
    }
}
