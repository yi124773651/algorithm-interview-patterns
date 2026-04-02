package no3_bfs.practice.stage2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName LeetCode_200
 * @Description 岛屿数量
 * 题目：给你一个由 '1'（陆地）和 '0'（水）组成的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或垂直方向上相邻的陆地连接形成。
 * <p>
 * 示例1：输入：grid = [["1","1","1","1","0"],["1","1","0","1","0"],["1","1","0","0","0"],["0","0","0","0","0"]] 输出：1
 * 示例2：输入：grid = [["1","1","0","0","0"],["1","1","0","0","0"],["0","0","1","0","0"],["0","0","0","1","1"]] 输出：3
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_200 {


    /**
     * Queue 入队起点 → while 队列非空 → 取出节点 → 处理 → 把"邻居"入队
     * <p>
     * <p>
     * 关键跳跃只有一个：把二维网格看成一张图。
     * 网格:                    图的视角:
     * 1 1 0                    (0,0)—(0,1)  (0,2)
     * 1 0 0                      |
     * 0 0 1                    (1,0)        (2,2)
     * <p>
     * - 每个格子 = 一个节点
     * - 上下左右相邻的格子 = 这个节点的"邻居"（类比树的 left/right，只不过变成了4 个方向）
     * - 一片相连的 '1' = 一个连通分量 = 一个岛屿
     * <p>
     * 从零推导解法
     * 第 1 步：怎么找"一个岛屿"的全部陆地？
     * 跟树的 BFS 完全一样，只是"邻居"从 left/right 变成了上下左右：
     * // 树：邻居是 left, right
     * if (node.left != null) queue.offer(node.left);
     * if (node.right != null) queue.offer(node.right);
     * <p>
     * // 网格：邻居是上下左右 4 个方向
     * int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
     * for (int[] d : dirs) {
     * int newRow = row + d[0];
     * int newCol = col + d[1];
     * // 检查边界 + 是否是陆地'1'
     * }
     * <p>
     * 第 2 步：怎么避免重复访问？
     * 树天然不会重复（父→子是单向的）。但网格里 A 能到 B，B 也能到 A，会死循环。
     * 所以需要标记已访问——最简单的方式是把访问过的 '1' 改成 '0'。
     * <p>
     * 第 3 步：怎么数出"几个岛屿"？
     * 遍历整个网格，每发现一个没被访问过的 '1'，就启动一次 BFS 把整个岛"淹掉"，计数 +1。
     * <p>
     * 伪代码框架
     * count = 0
     * 遍历每个格子 (i, j):
     * if grid[i][j] == '1':
     * count++                    // 发现新岛屿
     * BFS 从 (i,j) 出发,         // 把这个岛所有 '1' 标记为 '0'
     * 把整个岛都"淹掉"
     * return count
     * <p>
     * 总结：你缺的不是基础知识，是这个对应关系
     * --------------------------------------------------------------------
     * 树的 BFS	                      网格的 BFS
     * 起点：root	                  起点：任意一个 '1' 格子
     * 邻居：left, right	              邻居：上下左右 4 个方向
     * 天然无环，不需标记	              需要标记已访问（改 '0' 或 visited 数组）
     * 一次 BFS 遍历整棵树	          一次 BFS 遍历一个岛，外层循环找所有岛
     * --------------------------------------------------------------------
     * <p>
     * 核心就是：外层双重循环找起点 + 内层 BFS 淹岛 + 计数。
     *
     * @param grid
     * @return
     */
    public static int bfs(char[][] grid) {
        // dfs深度
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    //从(i,j)出发，把整个岛淹掉
//                    dfs淹岛(grid, i, j);
                    bfs淹岛(grid, i, j);
                }
            }
        }
        return count;
    }

    /**
     * 我的直觉是"外层循环从左上角扫到右下角，所以上面和左边的一定已经处理过了"。
     * 但这个推理有个漏洞——淹岛的起点不一定能只往右下就覆盖整个岛。
     * 就用这个例子：
     * 0 1
     * 1 1
     * 这种情况就会漏掉右下角
     *
     * @param grid
     * @param i
     * @param j    只考虑了右、下  四个方向都需要 否则特殊情况会漏调
     */
//    private static void yanmo(char[][] grid, int i, int j) {
//        grid[i][j] = '0';
//        if (i < grid.length - 1 && grid[i + 1][j] == '1') {
//            grid[i + 1][j] = '0';
//            yanmo(grid, i + 1, j);
//        }
//        if (j < grid[i].length - 1 && grid[i][j + 1] == '1') {
//            grid[i][j + 1] = '0';
//            yanmo(grid, i, j + 1);
//        }
//    }
    private static void dfs淹岛(char[][] grid, int i, int j) {
//        if (i < 0 || i > grid.length || j < 0 || j > grid[i].length || ...)  边界判断用了 > 应该是 >=
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == '0') return;
        //淹掉
        grid[i][j] = '0';
        dfs淹岛(grid, i - 1, j);//上
        dfs淹岛(grid, i + 1, j);//下
        dfs淹岛(grid, i, j - 1);//左
        dfs淹岛(grid, i, j + 1);//右
    }

    private static void bfs淹岛(char[][] grid, int i, int j) {
        Queue<int[]> queque = new LinkedList<>();
        queque.offer(new int[]{i, j});
        grid[i][j] = '0';
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        while (!queque.isEmpty()) {
            int[] node = queque.poll();
            for (int[] dir : dirs) {
                int ni = node[0] + dir[0], nj = node[1] + dir[1];
                if (ni >= 0 && ni < grid.length && nj >= 0 && nj < grid[0].length && grid[ni][nj] == '1') {
                    grid[ni][nj] = '0';  // ← 加这一行  非常重要  入队时要标记
                    queque.offer(new int[]{ni, nj});
                }
            }
        }
    }


    public static void main(String[] args) {
        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println(bfs(grid1)); // 1

        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(bfs(grid2)); // 3
    }
}
