package no3_bfs.practice.stage2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName LeetCode_994
 * @Description 腐烂的橘子
 * 题目：在给定的 m x n 网格 grid 中，每个单元格可以有三个值之一：
 * 0 空单元格；1 新鲜橘子；2 腐烂橘子。
 * 每分钟，腐烂的橘子周围4个方向上相邻的新鲜橘子都会腐烂。
 * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
 * <p>
 * 示例1：输入：grid = [[2,1,1],[1,1,0],[0,1,1]] 输出：4
 * 示例2：输入：grid = [[2,1,1],[0,1,1],[1,0,1]] 输出：-1
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] 仅为 0、1 或 2
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_994 {

    /**
     * 错误解法：单次遍历，无法处理链式传播和多源同时扩散
     */
    public static int bfs(int[][] grid) {
        // TODO: 使用BFS模拟橘子腐烂过程，返回最小分钟数
        int minCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) { //如果是 2  腐烂橘子  开始腐蚀周围
                    //需要在周围找到未被腐蚀的橘子才能算参与腐蚀过
                    if (周围存在未腐蚀橘子(grid, i, j)) {
                        minCount++;
                    }
                }
            }
        }
        boolean flag = false;//检查腐蚀完成后是否存在孤立的新鲜句子
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) flag = true;
            }
        }
        if (!flag) return minCount;
        return -1;
    }

    private static boolean 周围存在未腐蚀橘子(int[][] grid, int i, int j) {
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        boolean exist = false;
        for (int[] dir : dirs) {
            int ni = dir[0] + i, nj = dir[1] + j;
            if (ni >= 0 && ni < grid.length && nj >= 0 && nj < grid[0].length && grid[ni][nj] == 1) {
                exist = true;
                grid[ni][nj] = 2;

            }
        }
        return exist;
    }

    /**
     * 问题	                    我的代码	                               优化后
     * 扩散方式	                单次遍历，只腐蚀直接相邻的	               BFS逐层扩散，链式传播
     * 多源同时出发	            按遍历顺序逐个处理	                       所有腐烂橘子同时入队，同轮扩散
     * 时间计算	                每个腐烂橘子+1（错误）	                   每一轮扩散+1（正确）
     * 边界case	                没处理"没有新鲜橘子"的情况	               freshCount == 0 直接返回0
     *
     * 正确解法：多源BFS，所有腐烂橘子同时入队，逐层扩散
     *
     * 关键节点：
     * while (!queue.isEmpty()) {
     *     int size = queue.size();        // ← 关键：记录当前层的节点数
     *     for (int k = 0; k < size; k++) // ← 只处理当前层的节点
     *         ...
     *     minutes++;                      // ← 一层处理完 = 1分钟
     * }
     */
    public static int bfsOptimized(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;

        // 1. 把所有初始腐烂橘子加入队列，同时统计新鲜橘子数量
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        // 没有新鲜橘子，直接返回0
        if (freshCount == 0) return 0;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int minutes = 0;

        // 2. BFS 逐层扩散，每一层代表1分钟
        while (!queue.isEmpty()) {
            int size = queue.size(); // 当前这一轮有多少个腐烂橘子要同时扩散
            boolean infected = false; // 这一轮是否感染了新橘子

            for (int k = 0; k < size; k++) {
                int[] cur = queue.poll();
                for (int[] dir : dirs) {
                    int ni = cur[0] + dir[0], nj = cur[1] + dir[1];
                    if (ni >= 0 && ni < m && nj >= 0 && nj < n && grid[ni][nj] == 1) {
                        grid[ni][nj] = 2;       // 标记为腐烂
                        freshCount--;            // 新鲜橘子减一
                        queue.offer(new int[]{ni, nj}); // 加入队列，下一轮继续扩散
                        infected = true;
                    }
                }
            }

            if (infected) minutes++; // 只有真正感染了新橘子，才算过了1分钟
        }

        // 3. 如果还有新鲜橘子剩余，说明无法全部腐烂
        return freshCount == 0 ? minutes : -1;
    }

    public static void main(String[] args) {
        int[][] grid1 = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println(bfs(grid1)); // 错误解法

        int[][] grid2 = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println(bfsOptimized(grid2)); // 正确解法: 4

        int[][] grid3 = {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};
        System.out.println(bfsOptimized(grid3)); // 正确解法: -1
    }
}
