package no11_topologicalsort.practice.stage4;

/**
 * @ClassName LeetCode_329
 * @Description 矩阵中的最长递增路径
 * 题目：给定一个 m x n 整数矩阵 matrix，找出其中最长递增路径的长度。对于每个单元格，你可以往上下左右四个方向移动，
 * 不能对角线方向移动或移动到边界外。
 *
 * 示例1：输入：matrix = [[9,9,4],[6,6,8],[2,1,1]] 输出：4 解释：最长递增路径为 [1, 2, 6, 9]
 * 示例2：输入：matrix = [[3,4,5],[3,2,6],[2,2,1]] 输出：4
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 2^31 - 1
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_329 {

    public static int topologicalSort(int[][] matrix) {
        // TODO
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(topologicalSort(new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}})); // 4
        System.out.println(topologicalSort(new int[][]{{3, 4, 5}, {3, 2, 6}, {2, 2, 1}})); // 4
    }
}
