package no9_prefixsum.practice.stage3;

/**
 * @ClassName LeetCode_1109
 * @Description 航班预订统计（差分数组）
 * 题目：这里有 n 个航班，它们分别从 1 到 n 进行编号。有一份航班预订表 bookings，表中第 i 条预订记录
 * bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti（包含 firsti 和 lasti）的每个航班上预订了 seatsi 个座位。
 * 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
 *
 * 示例：
 * 输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * 输出：[10,55,45,25,25]
 *
 * 提示：
 * 1 <= n <= 2 * 10^4
 * 1 <= bookings.length <= 2 * 10^4
 * bookings[i].length == 3
 * 1 <= firsti <= lasti <= n
 * 1 <= seatsi <= 10^4
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_1109 {

    public static int[] difference(int[][] bookings, int n) {
        // TODO
        return new int[n];
    }

    public static void main(String[] args) {
        int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        int[] result = difference(bookings, 5);
        for (int val : result) {
            System.out.print(val + " "); // 10 55 45 25 25
        }
        System.out.println();
    }
}
