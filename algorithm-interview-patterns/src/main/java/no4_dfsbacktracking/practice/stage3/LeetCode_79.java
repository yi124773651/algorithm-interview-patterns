package no4_dfsbacktracking.practice.stage3;

/**
 * @ClassName LeetCode_79
 * @Description 单词搜索
 * 题目: 给定一个 m x n 二维字符网格 board 和一个字符串单词 word。如果 word 存在于网格中返回 true，否则返回 false。单词必须按照字母顺序通过相邻的单元格内的字母构成（上下左右相邻）。同一个单元格内的字母不允许被重复使用。
 * 示例:
 *   输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 *   输出：true
 * 提示:
 *   m == board.length
 *   n == board[i].length
 *   1 <= m, n <= 6
 *   1 <= word.length <= 15
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_79 {

    public static void main(String[] args) {
        char[][] board = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
        System.out.println(backtrack(board, word));
    }

    public static boolean backtrack(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        // TODO: 实现回溯逻辑
        return false;
    }
}
