package no6_greedy.practice.stage4;

/**
 * @ClassName LeetCode_135
 * @Description 分发糖果
 * 题目: n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * 你需要按照以下要求给孩子们分发糖果：每个孩子至少分配到 1 个糖果；
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。请你给每个孩子分发糖果，计算并返回需要准备的最少糖果数目。
 *
 * 示例1: 输入：ratings = [1,0,2] 输出：5
 * 示例2: 输入：ratings = [1,2,2] 输出：4
 *
 * 提示: n == ratings.length; 1 <= n <= 2 * 10^4; 0 <= ratings[i] <= 2 * 10^4
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_135 {

    public static void main(String[] args) {
        LeetCode_135 solution = new LeetCode_135();
        System.out.println(solution.greedy(new int[]{1, 0, 2})); // 5
        System.out.println(solution.greedy(new int[]{1, 2, 2})); // 4
    }

    public int greedy(int[] ratings) {
        // TODO
        return 0;
    }
}
