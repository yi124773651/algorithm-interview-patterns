package no11_topologicalsort.practice.stage3;

import java.util.List;

/**
 * @ClassName LeetCode_310
 * @Description 最小高度树
 * 题目：树是一个无向图，其中任何两个顶点只通过一条路径连接。给定一个树，其中包含 n 个标记为 0 到 n-1 的节点，
 * 以及一个有 n-1 条无向边的数组 edges。你可以选择树的任何节点作为根，找到具有最小高度的树并返回它们的根节点标签列表。
 *
 * 示例1：输入：n = 4, edges = [[1,0],[1,2],[1,3]] 输出：[1]
 * 示例2：输入：n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]] 输出：[3,4]
 *
 * 提示：
 * 1 <= n <= 2 * 10^4
 * edges.length == n - 1
 * 0 <= ai, bi < n
 * ai != bi
 * 所有 (ai, bi) 互不相同
 * 给定的输入保证是一棵树
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_310 {

    public static List<Integer> topologicalSort(int n, int[][] edges) {
        // TODO
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println(topologicalSort(4, new int[][]{{1, 0}, {1, 2}, {1, 3}})); // [1]
        System.out.println(topologicalSort(6, new int[][]{{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}})); // [3, 4]
    }
}
