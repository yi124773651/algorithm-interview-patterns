package no4_dfsbacktracking.practice.stage2;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_77
 * @Description 组合
 * 题目: 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 示例:
 * 输入：n = 4, k = 2
 * 输出：[[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 * 提示:
 * 1 <= n <= 20
 * 1 <= k <= n
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_77 {

    public static void main(String[] args) {
        int n = 4;
        int k = 2;
        System.out.println(backtrack(n, k));
    }

    public static List<List<Integer>> backtrack(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int startIndex = 1;
        // TODO: 实现回溯逻辑
        LeetCode_77.dfs(n, k, startIndex, path, result);
        return result;
    }

    private static void dfs(int n, int k, int startIndex, List<Integer> path, List<List<Integer>> result) {
        //1.终止条件  path.size == k
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return; //加上逻辑更清晰
        }
        //2.for循环
        for (int i = startIndex; i < n + 1; i++) {
            // if (n - startIndex < k - 1) break;    ×
            // 剩余元素 = n - i + 1
            // 还需要选 = k - path.size()
            // 如果剩余 < 还需要选，就剪掉
            //1.剪枝
            if (n - i + 1 < k - path.size()) break;
            //2.做选择
            path.add(i);
            //3.递归
            LeetCode_77.dfs(n, k, i + 1, path, result);
            //4.撤销选择
            path.remove(path.size() - 1);
        }
    }
}
