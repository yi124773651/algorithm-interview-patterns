package no6_greedy.practice.stage1;

/**
 * @ClassName LeetCode_455
 * @Description 分发饼干
 * 题目: 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。每个孩子最多只能给一块饼干。
 * 每个孩子 i 都有一个胃口值 g[i]；每块饼干 j 都有一个尺寸 s[j]。
 * 如果 s[j] >= g[i]，可以满足孩子。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 *
 * 示例1: 输入：g = [1,2,3], s = [1,1] 输出：1
 * 示例2: 输入：g = [1,2], s = [1,2,3] 输出：2
 *
 * 提示: 1 <= g.length <= 3 * 10^4; 0 <= s.length <= 3 * 10^4; 1 <= g[i], s[j] <= 2^31 - 1
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_455 {

    public static void main(String[] args) {
        LeetCode_455 solution = new LeetCode_455();
        System.out.println(solution.greedy(new int[]{1, 2, 3}, new int[]{1, 1})); // 1
        System.out.println(solution.greedy(new int[]{1, 2}, new int[]{1, 2, 3})); // 2
    }

    public int greedy(int[] g, int[] s) {
        // TODO
        return 0;
    }
}
