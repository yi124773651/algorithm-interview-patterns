package no10_unionfind.practice.stage1;

/**
 * @ClassName LeetCode_547
 * @Description 省份数量
 * 题目：有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，
 * 那么城市 a 与城市 c 间接相连。省份是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，返回矩阵中省份的数量。
 *
 * 示例1：输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]] 输出：2
 * 示例2：输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]] 输出：3
 *
 * 提示：
 * 1 <= n <= 200
 * n == isConnected.length
 * isConnected[i][j] 为 1 或 0
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_547 {

    public static int unionFind(int[][] isConnected) {
        // TODO
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(unionFind(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}})); // 2
        System.out.println(unionFind(new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}})); // 3
    }
}
