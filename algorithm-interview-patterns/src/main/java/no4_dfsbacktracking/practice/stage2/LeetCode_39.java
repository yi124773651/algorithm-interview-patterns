package no4_dfsbacktracking.practice.stage2;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_39
 * @Description 组合总和
 * 题目: 给你一个无重复元素的整数数组 candidates 和一个目标整数 target，找出 candidates 中可以使数字和为目标数 target 的所有不同组合。同一个数字可以无限制重复被选取。
 * 示例:
 *   输入：candidates = [2,3,6,7], target = 7
 *   输出：[[2,2,3],[7]]
 * 提示:
 *   1 <= candidates.length <= 30
 *   2 <= candidates[i] <= 40
 *   1 <= target <= 40
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_39 {

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println(backtrack(candidates, target));
    }

    public static List<List<Integer>> backtrack(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int sum = 0;
        int startIndex = 0;
        // TODO: 实现回溯逻辑
        return result;
    }
}
