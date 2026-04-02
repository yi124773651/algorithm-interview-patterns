package no8_stack.practice.stage3;

/**
 * @ClassName LeetCode_84
 * @Description 柱状图中最大的矩形
 *
 * 题目: 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * 示例1: 输入：heights = [2,1,5,6,2,3] 输出：10 解释：最大的矩形为图中红色区域，面积为 10
 * 示例2: 输入：heights = [2,4] 输出：4
 *
 * 提示: 1 <= heights.length <= 10^5; 0 <= heights[i] <= 10^4
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_84 {

    public static void main(String[] args) {
        LeetCode_84 solution = new LeetCode_84();
        System.out.println(solution.stackSolve(new int[]{2, 1, 5, 6, 2, 3})); // 10
        System.out.println(solution.stackSolve(new int[]{2, 4}));              // 4
    }

    public int stackSolve(int[] heights) {
        // TODO: 使用单调栈解决柱状图中最大矩形问题
        return 0;
    }
}
