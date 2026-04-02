package no4_dfsbacktracking.practice.stage1;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_46
 * @Description 全排列
 * 题目: 给定一个不含重复数字的数组 nums，返回其所有可能的全排列。
 * 示例:
 *   输入：nums = [1,2,3]
 *   输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 提示:
 *   1 <= nums.length <= 6
 *   -10 <= nums[i] <= 10
 *   nums 中的所有整数互不相同
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_46 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(backtrack(nums));
    }

    public static List<List<Integer>> backtrack(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        // TODO: 实现回溯逻辑
        return result;
    }
}
