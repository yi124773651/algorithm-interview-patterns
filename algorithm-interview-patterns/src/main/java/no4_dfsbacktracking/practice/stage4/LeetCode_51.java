package no4_dfsbacktracking.practice.stage4;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_51
 * @Description N皇后
 * 题目: 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。给你一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 示例:
 *   输入：n = 4
 *   输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 提示:
 *   1 <= n <= 9
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_51 {

    public static void main(String[] args) {
        int n = 4;
        System.out.println(backtrack(n));
    }

    public static List<List<String>> backtrack(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1];
        boolean[] diag2 = new boolean[2 * n - 1];
        // TODO: 实现回溯逻辑
        return result;
    }
}
