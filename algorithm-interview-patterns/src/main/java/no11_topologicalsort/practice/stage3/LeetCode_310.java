package no11_topologicalsort.practice.stage3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName LeetCode_310
 * @Description 最小高度树
 * 题目：树是一个无向图，其中任何两个顶点只通过一条路径连接。给定一个树，其中包含 n 个标记为 0 到 n - 1 的节点，
 * 以及一个有 n - 1 条无向边的数组 edges。你可以选择树的任何节点作为根，找到具有最小高度的树并返回它们的根节点标签列表。
 *
 * 示例1：
 * 输入：n = 4, edges = [[1,0],[1,2],[1,3]]
 * 输出：[1]
 *
 * 示例2：
 * 输入：n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * 输出：[3,4]
 *
 * 提示：
 * 1 <= n <= 2 * 10^4
 * edges.length == n - 1
 * 0 <= ai, bi < n
 * ai != bi
 * 所有 (ai, bi) 互不相同
 * 给定的输入保证是一棵树
 *
 * ---------------------------------------------------------
 * 这题的关键不是“拓扑序”，而是“树的中心”。
 *
 * 你可以这样理解：
 * 1. 如果把某个节点当根，树高就是它到最远节点的距离
 * 2. 想让树高最小，本质上就是找整棵树最中间的位置
 * 3. 一棵树的中心最多只有 2 个
 *
 * 怎么找中心？
 * 1. 先找到所有叶子节点，也就是度为 1 的节点
 * 2. 一层一层删除叶子节点
 * 3. 删除后，新的叶子节点继续进入下一层
 * 4. 最后剩下的 1 个或 2 个节点，就是最小高度树的根
 *
 * 为什么代码里最后一个 result 就是答案？
 * 1. while 循环每执行一轮，处理的都是“当前最外层”的所有叶子节点
 * 2. 所以每一轮的 result，记录的就是“这一层被剥掉的节点”
 * 3. 树会一层一层向中心收缩，越晚被剥掉，说明越靠近中心
 * 4. 当循环结束时，最后一轮处理的那一层，已经是最靠近中心的一层了
 * 5. 这一层只可能有 1 个节点或 2 个节点，它们就是整棵树的中心
 *
 * 你可以把它想成剥洋葱：
 * 1. 第一轮剥掉最外皮
 * 2. 第二轮剥掉次外层
 * 3. 一直剥到最后，手里剩下的芯，就是答案
 *
 * 例如链表形状的树：0 - 1 - 2 - 3 - 4
 * 1. 第一轮删除 0 和 4
 * 2. 第二轮删除 1 和 3
 * 3. 最后一轮只剩 2
 * 4. 所以 2 就是最小高度树的根
 *
 * 例如偶数长度链：0 - 1 - 2 - 3
 * 1. 第一轮删除 0 和 3
 * 2. 最后一轮剩下 1 和 2
 * 3. 所以答案有两个：[1, 2]
 *
 * 对比前几题：
 * 1. LeetCode_207 / 210：处理入度为 0 的节点
 * 2. LeetCode_802：处理出度为 0 的节点
 * 3. LeetCode_310：处理度为 1 的叶子节点
 *
 * 一句话记忆：
 * 不断从树的最外层往里剥，最后剩下的就是树中心。
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_310 {

    public static List<Integer> topologicalSort(int n, int[][] edges) {
        if (n == 1) {
            List<Integer> result = new ArrayList<>();
            result.add(0);
            return result;
        }

        // 无向图邻接表
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // 度数组：记录每个节点当前连接的边数
        int[] degree = new int[n];
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
            degree[a]++;
            degree[b]++;
        }

        // 所有叶子节点先入队
        Queue<Integer> queue = new ArrayDeque<>();
        for (int node = 0; node < n; node++) {
            if (degree[node] == 1) {
                queue.offer(node);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();

            // 这里重新创建 result，表示开始处理“新的一层叶子”
            // 循环结束后，result 中保留的就是“最后一层被处理的叶子”
            // 而最后一层叶子，其实就是整棵树收缩后的中心节点
            result = new ArrayList<>();

            // 一次处理当前整层叶子
            for (int i = 0; i < size; i++) {
                int leaf = queue.poll();
                result.add(leaf);

                for (int neighbor : graph.get(leaf)) {
                    degree[neighbor]--;
                    if (degree[neighbor] == 1) {
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(topologicalSort(4, new int[][]{{1, 0}, {1, 2}, {1, 3}})); // [1]
        System.out.println(topologicalSort(6, new int[][]{{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}})); // [3, 4]
    }
}
