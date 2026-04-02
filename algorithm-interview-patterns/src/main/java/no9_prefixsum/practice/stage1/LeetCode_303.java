package no9_prefixsum.practice.stage1;

/**
 * @ClassName LeetCode_303
 * @Description 区域和检索 - 数组不可变
 * 题目：给定一个整数数组 nums，处理以下类型的多个查询：计算索引 left 和 right（包含 left 和 right）之间的 nums 元素的和。
 * 实现 NumArray 类：NumArray(int[] nums) 使用数组 nums 初始化对象；int sumRange(int left, int right) 返回数组 nums 中索引 left 和 right 之间的元素的总和。
 *
 * 示例：
 * 输入：["NumArray","sumRange","sumRange","sumRange"] [[[-2,0,3,-5,2,-1]],[0,2],[2,5],[0,5]]
 * 输出：[null,1,-1,-3]
 *
 * 提示：
 * 1 <= nums.length <= 10^4
 * -10^5 <= nums[i] <= 10^5
 * 0 <= left <= right < nums.length
 * 最多调用 10^4 次 sumRange
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_303 {

    static class NumArray {
        public NumArray(int[] nums) {
            // TODO
        }

        public int sumRange(int left, int right) {
            // TODO
            return 0;
        }
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(0, 2)); // 1
        System.out.println(numArray.sumRange(2, 5)); // -1
        System.out.println(numArray.sumRange(0, 5)); // -3
    }
}
