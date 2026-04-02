package no2_twopointertechnique.practice.stage2;

/**
 * @ClassName LeetCode_11
 * @Description 题目: 给定一个长度为 n 的整数数组 height。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i])。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。返回容器可以储存的最大水量。
 * <p>
 * 示例 1：
 * <p>
 * 输入：height = [1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：选择第 2 条线（高度8）和第 9 条线（高度7），容器宽度为 7，水量 = 7 * 7 = 49。
 * <p>
 * 示例 2：
 * <p>
 * 输入：height = [1,1]
 * 输出：1
 * <p>
 * 提示：
 * n == height.length
 * 2 <= n <= 10^5
 * 0 <= height[i] <= 10^4
 * @Author gm
 * @Date 2026/4/2 15:00
 */
public class LeetCode_11 {
    public static void main(String[] args) {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        int result = LeetCode_11.twoPointer(height);
        System.out.println(result);
    }

    private static int twoPointer(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
//            int a = right - left;
//            int b = Math.min(height[left], height[right]);
            int width = right - left;
            int minHeight = Math.min(height[left], height[right]);
            int area = width * minHeight;
            if (area > maxArea) maxArea = area;
//            if (b == height[left]) { //left高度小  决定如何下一步收缩左边还是右边是关键
            if (height[left] <= height[right]) { //left高度小  决定如何下一步收缩左边还是右边是关键
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
