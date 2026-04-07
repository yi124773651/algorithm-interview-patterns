package no11_topologicalsort.practice.stage2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName LeetCode_802
 * @Description 找到最终的安全状态
 * 题目：有一个有 n 个节点的有向图，节点按 0 到 n - 1 编号。图由一个下标从 0 开始的 2D 整数数组 graph 表示。
 * graph[i] 是与节点 i 相邻的节点数组，表示从节点 i 到 graph[i] 中每个节点都有一条有向边。
 * 如果一个节点没有连出的有向边，则它是终端节点。如果从该节点开始的所有可能路径都能到达终端节点，则该节点为安全节点。
 * 返回一个由图中所有安全节点组成的数组作为答案，按升序排列。
 *
 * 示例1：
 * 输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * 输出：[2,4,5,6]
 *
 * 示例2：
 * 输入：graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
 * 输出：[4]
 *
 * 提示：
 * n == graph.length
 * 1 <= n <= 10^4
 * 0 <= graph[i].length <= n
 * 0 <= graph[i][j] <= n - 1
 * graph[i] 按严格递增顺序排列
 *
 * ---------------------------------------------------------
 * 这题的关键建模：
 * 1. 终端节点一定是安全节点
 * 2. 如果一个节点的所有后继节点都是安全节点，那么它也是安全节点
 * 3. 如果一个节点能走到环，那么它就不是安全节点
 *
 * 标准做法不是“从每个点都 DFS 试一遍”，而是：
 * 1. 先统计每个节点的出度
 * 2. 终端节点的出度为 0，它们先入队
 * 3. 把原图反过来建图：如果原图是 i -> j，那么反图就是 j -> i
 * 4. 一个安全节点出队后，就去减少它在反图中的前驱节点的出度
 * 5. 哪个前驱节点的出度减到 0，说明它的所有后继都已经安全，它也安全
 *
 * 你可以把这题理解成：
 * 原来的 LeetCode_207 / 210 是“入度为 0 的点先处理”
 * 这题是“出度为 0 的点先处理”
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_802 {

    /**
     * 你原来的思路先保留下来，方便对照理解为什么会卡住。
     *
     * 卡点主要有 3 个：
     * 1. 只判断“能不能走到终端节点”还不够，题目要求是“所有路径都必须走到终端节点”
     * 2. 一旦图中有环，递归检查时就必须区分：正在访问、已经确认安全、已经确认不安全
     * 3. 如果没有记忆化，同一个节点会被反复检查，复杂度会偏高
     *
     * 也就是说，这条思路不是完全错，而是会自然滑向：
     * DFS + 染色 + 记忆化
     *
     * 这已经不是“简单判断一下 endList.contains(next)”能解决的了，
     * 所以做到这里卡住是正常的，说明你已经碰到了这题真正的难点。
     */
    public static List<Integer> topologicalSortMyThought(int[][] graph) {
        int[] outDegree = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            int[] current = graph[i];
            if (current != null && current.length > 0) {
                for (int next : current) {
                    outDegree[i]++;
                }
            }
        }

        // 找到终端节点：没有出边的点
        List<Integer> endList = new ArrayList<>();
        for (int i = 0; i < outDegree.length; i++) {
            if (outDegree[i] == 0) {
                endList.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            boolean isSafe = checkSafe(graph, endList, i, outDegree);
            if (isSafe) {
                result.add(i);
            }
        }
        return result;
    }

    private static boolean checkSafe(int[][] graph, List<Integer> endList, int node, int[] outDegree) {
        int[] nexts = graph[node];
        boolean isSafe = false;
        if (nexts == null || nexts.length == 0) {
            if (endList.contains(node)) {
                isSafe = true;
            }
        } else {
            for (int next : nexts) {
                // 这里就是原思路的卡点：
                // 1. 不是只要某一条路能到终端节点就行，而是所有后继都必须安全
                // 2. 还需要先判断会不会绕回当前递归路径，从而形成环
                // 3. 如果继续补下去，就需要引入 DFS 染色法或记忆化搜索
            }
        }
        return isSafe;
    }

    /**
     * DFS 染色法：这其实就是你原来思路继续往下推后的完整形态。
     *
     * 状态定义：
     * 0：未访问
     * 1：访问中
     * 2：安全节点
     * 3：不安全节点
     *
     * 判断逻辑：
     * 1. 如果 DFS 时再次遇到“访问中”的节点，说明走回当前递归路径，存在环，不安全
     * 2. 如果一个节点的某个后继不安全，那么当前节点也不安全
     * 3. 只有当所有后继都安全时，当前节点才安全
     */
    public static List<Integer> dfsColoring(int[][] graph) {
        int n = graph.length;
        int[] visited = new int[n];
        List<Integer> result = new ArrayList<>();

        for (int node = 0; node < n; node++) {
            if (isSafeByDfs(graph, visited, node)) {
                result.add(node);
            }
        }
        return result;
    }

    private static boolean isSafeByDfs(int[][] graph, int[] visited, int node) {
        if (visited[node] == 1) {
            visited[node] = 3;
            return false;
        }
        if (visited[node] == 2) {
            return true;
        }
        if (visited[node] == 3) {
            return false;
        }

        visited[node] = 1;
        for (int next : graph[node]) {
            if (!isSafeByDfs(graph, visited, next)) {
                visited[node] = 3;
                return false;
            }
        }

        visited[node] = 2;
        return true;
    }

    /**
     * 标准解法：反图 + 出度拓扑排序。
     *
     * 直觉：
     * 1. 终端节点天然安全
     * 2. 如果一个节点所有后继都已经被证明安全，那么它也安全
     * 3. 所以从出度为 0 的点开始，反向一层层推回去最自然
     */
    public static List<Integer> topologicalSort(int[][] graph) {
        int n = graph.length;

        // 反图：如果原图是 from -> to，那么反图中存 to -> from
        List<List<Integer>> reverseGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            reverseGraph.add(new ArrayList<>());
        }

        // 出度表：记录每个节点还剩多少条“指向外部”的边
        int[] outDegree = new int[n];
        for (int from = 0; from < n; from++) {
            outDegree[from] = graph[from].length;
            for (int to : graph[from]) {
                reverseGraph.get(to).add(from);
            }
        }

        // 终端节点先入队：它们天然安全
        Queue<Integer> queue = new ArrayDeque<>();
        for (int node = 0; node < n; node++) {
            if (outDegree[node] == 0) {
                queue.offer(node);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int safeNode = queue.poll();
            result.add(safeNode);

            // 当前安全节点会让它的前驱节点少一个“不安全去向”
            for (int prev : reverseGraph.get(safeNode)) {
                outDegree[prev]--;
                if (outDegree[prev] == 0) {
                    queue.offer(prev);
                }
            }
        }

        // 题目要求按升序返回
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(topologicalSort(new int[][]{{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}})); // [2, 4, 5, 6]
        System.out.println(topologicalSort(new int[][]{{1, 2, 3, 4}, {1, 2}, {3, 4}, {0, 4}, {}})); // [4]

        System.out.println(dfsColoring(new int[][]{{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}})); // [2, 4, 5, 6]
        System.out.println(dfsColoring(new int[][]{{1, 2, 3, 4}, {1, 2}, {3, 4}, {0, 4}, {}})); // [4]
    }
}
