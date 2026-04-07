package no11_topologicalsort.practice.stage4;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @ClassName LeetCode_329
 * @Description 矩阵中的最长递增路径
 * 题目：给定一个 m x n 整数矩阵 matrix，找出其中最长递增路径的长度。对于每个单元格，你可以往上下左右四个方向移动，
 * 不能对角线方向移动或移动到边界外。
 *
 * 示例1：
 * 输入：matrix =
 * [[9,9,4],
 *  [6,6,8],
 *  [2,1,1]]
 * 输出：4
 * 解释：最长递增路径为 [1, 2, 6, 9]
 *
 * 示例2：
 * 输入：matrix =
 * [[3,4,5],
 *  [3,2,6],
 *  [2,2,1]]
 * 输出：4
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 2^31 - 1
 *
 * ---------------------------------------------------------
 * 这题虽然是矩阵题，但本质上也可以看成图题：
 * 1. 每个格子都是一个节点
 * 2. 如果相邻格子值更大，就可以从当前格子走向它
 * 3. 因为边总是从小值走向大值，所以这张图一定无环，是一张 DAG
 *
 * 因此这题有两种经典解法：
 * 1. DFS + 记忆化搜索
 * 2. 拓扑排序分层
 *
 * 拓扑排序分层为什么可行？
 * 1. 如果把边定义成“小值 -> 大值”，那么入度为 0 的点就是递增路径的起点
 * 2. 一层层做 BFS，每一层都表示“路径长度增加了 1”
 * 3. 最后总共处理了多少层，最长递增路径就是多少
 *
 * 一句话记忆：
 * DFS 版是在问“从这个点出发能走多远”；
 * 拓扑版是在问“整张 DAG 一共能推进多少层”。
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_329 {

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * DFS + 记忆化搜索。
     * memo[i][j] 表示从 (i, j) 出发的最长递增路径长度。
     */
    public static int topologicalSort(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] memo = new int[m][n];

        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dfs(matrix, i, j, memo));
            }
        }
        return max;
    }

    private static int dfs(int[][] matrix, int i, int j, int[][] memo) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        int max = 1;
        for (int[] dir : DIRS) {
            int newI = i + dir[0];
            int newJ = j + dir[1];
            if (newI < 0 || newI >= matrix.length || newJ < 0 || newJ >= matrix[0].length) {
                continue;
            }
            if (matrix[newI][newJ] > matrix[i][j]) {
                max = Math.max(max, 1 + dfs(matrix, newI, newJ, memo));
            }
        }

        memo[i][j] = max;
        return max;
    }

    /**
     * 拓扑排序分层写法。
     *
     * 建模方式：
     * 1. 从小值格子指向大值格子
     * 2. 统计每个格子的入度：有多少个更小的邻居可以走到它
     * 3. 所有入度为 0 的格子先入队，它们是递增路径的起点
     * 4. 每处理完一层，说明路径长度增加 1
     * 5. 最终层数就是最长递增路径长度
     */
    public static int topologicalSortByBfs(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] inDegree = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int[] dir : DIRS) {
                    int newI = i + dir[0];
                    int newJ = j + dir[1];
                    if (newI < 0 || newI >= m || newJ < 0 || newJ >= n) {
                        continue;
                    }
                    if (matrix[newI][newJ] > matrix[i][j]) {
                        inDegree[newI][newJ]++;
                    }
                }
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (inDegree[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;

            for (int k = 0; k < size; k++) {
                int[] current = queue.poll();
                int i = current[0];
                int j = current[1];

                for (int[] dir : DIRS) {
                    int newI = i + dir[0];
                    int newJ = j + dir[1];
                    if (newI < 0 || newI >= m || newJ < 0 || newJ >= n) {
                        continue;
                    }
                    if (matrix[newI][newJ] > matrix[i][j]) {
                        inDegree[newI][newJ]--;
                        if (inDegree[newI][newJ] == 0) {
                            queue.offer(new int[]{newI, newJ});
                        }
                    }
                }
            }
        }

        return level;
    }

    public static void main(String[] args) {
        System.out.println(topologicalSort(new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}})); // 4
        System.out.println(topologicalSort(new int[][]{{3, 4, 5}, {3, 2, 6}, {2, 2, 1}})); // 4

        System.out.println(topologicalSortByBfs(new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}})); // 4
        System.out.println(topologicalSortByBfs(new int[][]{{3, 4, 5}, {3, 2, 6}, {2, 2, 1}})); // 4
    }
}
