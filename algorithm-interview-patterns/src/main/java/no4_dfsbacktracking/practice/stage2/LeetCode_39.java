package no4_dfsbacktracking.practice.stage2;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_39
 * @Description 组合总和
 * 题目: 给你一个无重复元素的整数数组 candidates 和一个目标整数 target，找出 candidates 中可以使数字和为目标数 target 的所有不同组合。同一个数字可以无限制重复被选取。
 * 示例:
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 提示:
 * 1 <= candidates.length <= 30
 * 2 <= candidates[i] <= 40
 * 1 <= target <= 40
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
        LeetCode_39.dfs(candidates, target, sum, startIndex, path, result);
        return result;
    }

    /**
     *               递归传参        选择列表起点       原因
     * 全排列(46)     无 startIndex    i = 0           顺序有关，每次都要从头选
     * 子集(78)       i + 1           startIndex      顺序无关，不能回头选
     * 组合总和(39)   i（不是 i+1！）  startIndex       顺序无关 + 同一个数可以重复选 ⭐
     *
     * 一个小优化建议（可选）
     * 可以先对 candidates 排序，然后在 for 循环里加一行强剪枝，效率会更高：
     *
     * // 排序后，如果当前 sum + candidates[i] > target，后面更大的数也不用试了
     * if (sum + candidates[i] > target) break;  // 直接 break，不是 continue！
     *
     * @param candidates
     * @param target
     * @param sum
     * @param startIndex
     * @param path
     * @param result
     */
    private static void dfs(int[] candidates, int target, int sum, int startIndex, List<Integer> path, List<List<Integer>> result) {
        //1.结束条件  sum > target   这里没有正确区分结束条件和 收集条件
        if (sum > target) return;
        //收集条件
        if (sum == target) {
            result.add(new ArrayList<>(path));
        }

        //2.for循环
        for (int i = startIndex; i < candidates.length; i++) {
            //1.剪枝 不需要
            //2.做选择
            sum += candidates[i];
            path.add(candidates[i]);
            //3.递归
//            LeetCode_39.dfs(candidates, target, sum, startIndex, path, result);  这里递归的参数传错了  同一个数字可以重复选
            LeetCode_39.dfs(candidates, target, sum, i, path, result);
            //4.撤销选择
            sum -= candidates[i];
            path.remove(path.size() - 1);
        }
    }
}
