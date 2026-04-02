package no6_greedy.practice.stage2;

/**
 * @ClassName LeetCode_435
 * @Description 无重叠区间
 * 题目: 给定一个区间的集合 intervals，其中 intervals[i] = [starti, endi]。
 * 返回需要移除区间的最小数量，使剩余区间互不重叠。
 *
 * 示例1: 输入：intervals = [[1,2],[2,3],[3,4],[1,3]] 输出：1 解释：移除 [1,3] 后，剩下的区间没有重叠
 * 示例2: 输入：intervals = [[1,2],[1,2],[1,2]] 输出：2
 *
 * 提示: 1 <= intervals.length <= 10^5; intervals[i].length == 2;
 * -5 * 10^4 <= starti < endi <= 5 * 10^4
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_435 {

    public static void main(String[] args) {
        LeetCode_435 solution = new LeetCode_435();
        System.out.println(solution.greedy(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}})); // 1
        System.out.println(solution.greedy(new int[][]{{1, 2}, {1, 2}, {1, 2}})); // 2
    }

    public int greedy(int[][] intervals) {
        // TODO
        return 0;
    }
}
