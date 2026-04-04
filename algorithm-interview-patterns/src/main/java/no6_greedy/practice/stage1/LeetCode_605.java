package no6_greedy.practice.stage1;

/**
 * @ClassName LeetCode_605
 * @Description 种花问题
 * 题目: 假设有一个很长的花坛，一部分地块种了花，另一部分没有。花不能种在相邻的地块上。
 * 给定一个花坛 flowerbed（0/1数组）和一个数 n，能否在不打破种植规则的情况下种入 n 朵花？
 * <p>
 * 示例1:
 * 输入：flowerbed = [1,0,0,0,1], n = 1
 * 输出：true
 * 示例2:
 * 输入：flowerbed = [1,0,0,0,1], n = 2
 * 输出：false
 * <p>
 * 提示:
 * 1 <= flowerbed.length <= 2 * 10^4;
 * flowerbed[i] 为 0 或 1;
 * flowerbed 中不存在相邻的两朵花;
 * 0 <= n <= flowerbed.length
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_605 {

    public static void main(String[] args) {
        LeetCode_605 solution = new LeetCode_605();

        System.out.println("=== 原始解法(有Bug) ===");
        System.out.println(solution.greedy_original(new int[]{1, 0, 0, 0, 1}, 1)); // 期望true
        System.out.println(solution.greedy_original(new int[]{1, 0, 0, 0, 1}, 2)); // 期望false
        System.out.println(solution.greedy_original(new int[]{0, 0, 0, 0, 0}, 2)); // 期望true，实际false ❌
        System.out.println(solution.greedy_original(new int[]{0}, 1));              // 期望true，实际false ❌

        System.out.println("=== 正确解法 ===");
        System.out.println(solution.greedy(new int[]{1, 0, 0, 0, 1}, 1)); // true ✅
        System.out.println(solution.greedy(new int[]{1, 0, 0, 0, 1}, 2)); // false ✅
        System.out.println(solution.greedy(new int[]{0, 0, 0, 0, 0}, 2)); // true ✅
        System.out.println(solution.greedy(new int[]{0}, 1));              // true ✅
    }

    /**
     * 原始解法（有Bug）
     * Bug1: 每个分支都有break，最多只能种1朵花       -----> continue
     * Bug2: 第一棵花没检查 flowerbed[i] == 0，会在已有花的位置重复种        重大失误： flowerbed[i] == 0都没判断
     * Bug3: 数组长度为1时，第一棵花的条件 i+1 < length 不成立，漏判
     */
    public boolean greedy_original(int[] flowerbed, int n) {
        int newFlowerbedCount = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            //第一棵
            if (i == 0 && i + 1 < flowerbed.length && flowerbed[i + 1] == 0) {
                flowerbed[i] = 1;
                newFlowerbedCount++;
                break;
            }
            //中间的
            if (i > 0 && i < flowerbed.length - 1 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
                flowerbed[i] = 1;
                newFlowerbedCount++;
                break;
            }
            //最后一棵
            if (i > 0 && i == flowerbed.length - 1 && flowerbed[i - 1] == 0) {
                flowerbed[i] = 1;
                newFlowerbedCount++;
                break;
            }
        }
        return newFlowerbedCount >= n;
    }

    /**
     * 正确解法：逐步扫描，局部最优决策
     * 思路：遍历每个位置，当前位置为0且左右邻居都为0（或是边界）就种花
     * 技巧：把边界条件统一 —— 位置0的"左边"视为0，最后一个位置的"右边"视为0
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * 需要明确能种植的条件：当前位置 空 且左边空 且 右边空    左空 又可以拆解为边界 和 中间 右空 同样如此
     * 核心的思想是：将概念化的描述 拆分为实际情况
     */
    public boolean greedy(int[] flowerbed, int n) {
        // 能种的条件：flowerbed[i] == 0 且左右邻居都为0（边界视为0）
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                boolean leftEmpty = (i == 0) || (flowerbed[i - 1] == 0);
                boolean rightEmpty = (i == flowerbed.length - 1) || (flowerbed[i + 1] == 0);
                if (leftEmpty && rightEmpty) {
                    flowerbed[i] = 1;  // 种花
                    count++;
                    if (count >= n) return true; // 提前返回，已经够了
                }
            }
        }
        return count >= n;
    }
}
