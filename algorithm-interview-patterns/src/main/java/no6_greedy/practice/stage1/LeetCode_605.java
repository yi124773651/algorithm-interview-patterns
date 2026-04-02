package no6_greedy.practice.stage1;

/**
 * @ClassName LeetCode_605
 * @Description 种花问题
 * 题目: 假设有一个很长的花坛，一部分地块种了花，另一部分没有。花不能种在相邻的地块上。
 * 给定一个花坛 flowerbed（0/1数组）和一个数 n，能否在不打破种植规则的情况下种入 n 朵花？
 *
 * 示例1: 输入：flowerbed = [1,0,0,0,1], n = 1 输出：true
 * 示例2: 输入：flowerbed = [1,0,0,0,1], n = 2 输出：false
 *
 * 提示: 1 <= flowerbed.length <= 2 * 10^4; flowerbed[i] 为 0 或 1;
 * flowerbed 中不存在相邻的两朵花; 0 <= n <= flowerbed.length
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_605 {

    public static void main(String[] args) {
        LeetCode_605 solution = new LeetCode_605();
        System.out.println(solution.greedy(new int[]{1, 0, 0, 0, 1}, 1)); // true
        System.out.println(solution.greedy(new int[]{1, 0, 0, 0, 1}, 2)); // false
    }

    public boolean greedy(int[] flowerbed, int n) {
        // TODO
        return false;
    }
}
