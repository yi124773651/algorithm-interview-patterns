package no4_dfsbacktracking.practice.stage1;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_78
 * @Description 子集
 * 题目: 给你一个整数数组 nums，数组中的元素互不相同。返回该数组所有可能的子集（幂集）。解集不能包含重复的子集。
 * 示例:
 *   输入：nums = [1,2,3]
 *   输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 提示:
 *   1 <= nums.length <= 10
 *   -10 <= nums[i] <= 10
 *   nums 中的所有元素互不相同
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_78 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(backtrack(nums));
    }

    public static List<List<Integer>> backtrack(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int startIndex = 0;
        // TODO: 实现回溯逻辑
        return result;
    }
}
