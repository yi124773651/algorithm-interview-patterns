package no6_greedy.practice.stage2;

import java.util.Arrays;

/**
 * @ClassName LeetCode_435
 * @Description 无重叠区间
 * 题目: 给定一个区间的集合 intervals，其中 intervals[i] = [starti, endi]。
 * 返回需要移除区间的最小数量，使剩余区间互不重叠。
 * <p>
 * 示例1:
 * 输入：intervals = [[1,2],[2,3],[3,4],[1,3]]
 * 输出：1 解释：移除 [1,3] 后，剩下的区间没有重叠
 * 示例2:
 * 输入：intervals = [[1,2],[1,2],[1,2]]
 * 输出：2
 * <p>
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

    /**
     * 1.排序 预处理  看起来需要
     * 2.满足贪心条件  贪心条件是什么？
     * 3.缩小边界
     *
     * @param intervals
     * @return
     */
    public int greedy(int[][] intervals) {
        // 1.排序 预处理   end很重要  根据end排序
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        //排序的比较器 (a, b) -> a[1] - b[1] 在极端值下可能整数溢出。
        // 本题约束范围小（-5×10⁴ ~ 5×10⁴）不会出问题，但养成好习惯可以写成：
        //Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        /**
         * 每一行看成一个元素
         * intervals = [[1,2],[2,3],[3,4],[1,3]]
         * 排序后
         * [1,2]
         * [2,3]
         * [1,3]
         * [3,4]
         */
        int end = intervals[0][1];
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
//            if (intervals[i][1] <= end) {  //区间有重叠    旧版本判断的是"当前区间的 end ≤ 维护的 end"，这不是正确的重叠判断
            if (intervals[i][0] < end) {  //区间有重叠    注意这个条件  是这个区间的start < 维护的end 说明需要去掉
                //需要去掉
                count++;
            } else { //更新end
                end = intervals[i][1];
            }
        }
        return count;
    }
}
