package no8_stack.practice.stage4;

/**
 * @ClassName LeetCode_85
 * @Description 最大矩形
 *
 * 题目: 给定一个仅包含 0 和 1、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 * 示例1: 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 *        输出：6
 * 示例2: 输入：matrix = [["0"]] 输出：0
 *
 * 提示: rows == matrix.length; cols == matrix[0].length; 1 <= rows, cols <= 200;
 *       matrix[i][j] 为 '0' 或 '1'
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_85 {

    public static void main(String[] args) {
        LeetCode_85 solution = new LeetCode_85();
        char[][] matrix1 = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println(solution.stackSolve(matrix1)); // 6

        char[][] matrix2 = {{'0'}};
        System.out.println(solution.stackSolve(matrix2)); // 0
    }

    public int stackSolve(char[][] matrix) {
        // TODO: 使用单调栈解决最大矩形问题
        return 0;
    }
}
