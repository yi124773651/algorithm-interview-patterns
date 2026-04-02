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
 *
 * 示例1：输入：grid = [[2,1,1],[1,1,0],[0,1,1]] 输出：4
 * 示例2：输入：grid = [[2,1,1],[0,1,1],[1,0,1]] 输出：-1
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] 仅为 0、1 或 2
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_994 {

    public static int bfs(int[][] grid) {
        // TODO: 使用BFS模拟橘子腐烂过程，返回最小分钟数
        return 0;
    }

    public static void main(String[] args) {
        int[][] grid1 = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println(bfs(grid1)); // 4

        int[][] grid2 = {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};
        System.out.println(bfs(grid2)); // -1
    }
}
