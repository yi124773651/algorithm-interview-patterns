package no3_bfs.practice.stage4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName LeetCode_773
 * @Description 滑动谜题
 * 题目：在一个 2 x 3 的板上有 5 块砖和一个空位（用 0 表示）。
 * 一次移动定义为选择 0 与一个相邻数字交换。
 * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
 * 返回最少移动次数，如果不能解开返回 -1。
 *
 * 示例1：输入：board = [[1,2,3],[4,0,5]] 输出：1
 * 示例2：输入：board = [[1,2,3],[5,4,0]] 输出：-1
 * 示例3：输入：board = [[4,1,2],[5,0,3]] 输出：5
 *
 * 提示：
 * board.length == 2
 * board[i].length == 3
 * 0 <= board[i][j] <= 5
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_773 {

    public static int bfs(int[][] board) {
        // TODO: 使用BFS求解滑动谜题的最少移动次数
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(bfs(new int[][]{{1, 2, 3}, {4, 0, 5}})); // 1
        System.out.println(bfs(new int[][]{{1, 2, 3}, {5, 4, 0}})); // -1
        System.out.println(bfs(new int[][]{{4, 1, 2}, {5, 0, 3}})); // 5
    }
}
