package no11_topologicalsort.practice.stage2;

import java.util.List;

/**
 * @ClassName LeetCode_802
 * @Description 找到最终的安全状态
 * 题目：有一个有 n 个节点的有向图，节点按 0 到 n - 1 编号。图由一个索引从 0 开始的 2D 整数数组 graph 表示，
 * graph[i] 是与节点 i 相邻的节点的整数数组，意味着从节点 i 到节点 graph[i] 中每个节点都有一条边。
 * 如果一个节点没有连出的有向边则它是终端节点。如果从该节点开始的所有可能路径都通向终端节点，则该节点为安全节点。
 * 返回一个由图中所有安全节点组成的数组作为答案，按升序排列。
 *
 * 示例1：输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]] 输出：[2,4,5,6]
 * 示例2：输入：graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]] 输出：[4]
 *
 * 提示：
 * n == graph.length
 * 1 <= n <= 10^4
 * 0 <= graph[i].length <= n
 * 0 <= graph[i][j] <= n - 1
 * graph[i] 按严格递增顺序排列
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_802 {

    public static List<Integer> topologicalSort(int[][] graph) {
        // TODO
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println(topologicalSort(new int[][]{{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}})); // [2, 4, 5, 6]
        System.out.println(topologicalSort(new int[][]{{1, 2, 3, 4}, {1, 2}, {3, 4}, {0, 4}, {}})); // [4]
    }
}
