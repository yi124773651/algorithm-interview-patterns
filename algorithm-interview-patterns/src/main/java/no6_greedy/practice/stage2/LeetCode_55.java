package no6_greedy.practice.stage2;

/**
 * @ClassName LeetCode_55
 * @Description 跳跃游戏
 * 题目: 给你一个非负整数数组 nums，你最初位于数组的第一个下标。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 *
 * 示例1: 输入：nums = [2,3,1,1,4] 输出：true
 * 示例2: 输入：nums = [3,2,1,0,4] 输出：false
 *
 * 提示: 1 <= nums.length <= 10^4; 0 <= nums[i] <= 10^5
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_55 {

    public static void main(String[] args) {
        LeetCode_55 solution = new LeetCode_55();
        System.out.println(solution.greedy(new int[]{2, 3, 1, 1, 4})); // true
        System.out.println(solution.greedy(new int[]{3, 2, 1, 0, 4})); // false
    }

    public boolean greedy(int[] nums) {
        // TODO
        return false;
    }
}
