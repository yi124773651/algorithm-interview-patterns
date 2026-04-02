package no10_unionfind.practice.stage4;

/**
 * @ClassName LeetCode_765
 * @Description 情侣牵手
 * 题目：n 对情侣坐在连续排列的 2n 个座位上，想要牵到对方的手。人和座位用 0 到 2n-1 的整数表示，
 * 情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2n-2, 2n-1)。
 * 每次交换可以选择任意两人让他们站起来交换座位。返回最少交换座位的次数，以便每对情侣可以并肩坐在一起。
 *
 * 示例1：输入：row = [0,2,1,3] 输出：1
 * 示例2：输入：row = [3,2,0,1] 输出：0
 *
 * 提示：
 * 2n == row.length
 * 2 <= n <= 30
 * row 是 [0, 2n - 1] 的一个全排列
 * 0 <= row[i] < 2n
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_765 {

    public static int unionFind(int[] row) {
        // TODO
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(unionFind(new int[]{0, 2, 1, 3})); // 1
        System.out.println(unionFind(new int[]{3, 2, 0, 1})); // 0
    }
}
