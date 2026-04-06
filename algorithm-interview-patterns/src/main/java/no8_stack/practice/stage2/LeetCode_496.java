package no8_stack.practice.stage2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_496
 * @Description 下一个更大元素 I
 * <p>
 * 题目: nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置右侧的第一个比 x 大的元素。
 * 给你两个没有重复元素的数组 nums1 和 nums2，下标从 0 开始计数，其中 nums1 是 nums2 的子集。
 * 请你找出 nums1 中每个元素在 nums2 中的下一个更大元素。
 * <p>
 * 示例1:
 * 输入：nums1 = [4,1,2], nums2 = [1,3,4,2]
 * 输出：[-1,3,-1]
 * <p>
 * 示例2:
 * 输入：nums1 = [2,4], nums2 = [1,2,3,4]
 * 输出：[3,-1]
 * <p>
 * 提示:
 * 1 <= nums1.length <= nums2.length <= 1000;
 * 0 <= nums1[i], nums2[i] <= 10^4;
 * nums1 和 nums2 中所有整数互不相同；nums1 中的所有整数同样出现在 nums2 中。
 * <p>
 * 解题整理：
 * 1. 旧解的方向是正确的：先求出 nums2 中每个元素的答案，再回填 nums1。
 * 2. 旧解的问题在于：对 nums2 中每个元素都单独向右查找，整体复杂度是 O(n^2)。
 * 3. 标准解的关键升级是：使用单调栈，维护“还没找到下一个更大元素”的数。
 * 4. 当前元素一旦更大，就能顺手帮栈顶元素结算答案。
 * 5. 这类题的识别信号通常是：“右边第一个更大/更小”“左边第一个更大/更小”“要求整体 O(n)”。
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_496 {

    public static void main(String[] args) {
        LeetCode_496 solution = new LeetCode_496();

        System.out.println("===== 旧解 =====");
        System.out.println(Arrays.toString(solution.stackSolve(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}))); // [-1,3,-1]
        System.out.println(Arrays.toString(solution.stackSolve(new int[]{2, 4}, new int[]{1, 2, 3, 4})));    // [3,-1]

        System.out.println("===== 标准解 =====");
        System.out.println(Arrays.toString(solution.monotonicStackSolve(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}))); // [-1,3,-1]
        System.out.println(Arrays.toString(solution.monotonicStackSolve(new int[]{2, 4}, new int[]{1, 2, 3, 4})));    // [3,-1]
    }

    /**
     * 旧解保留。
     * <p>
     * 旧解思路：
     * 1. 遍历 nums2。
     * 2. 对于每个 nums2[i]，都去它右边线性查找第一个更大的元素。
     * 3. 用 map 记录“当前值 -> 下一个更大元素”。
     * 4. 再遍历 nums1，把答案取出来。
     * <p>
     * 旧解的问题：
     * 1. 每个元素都要单独向右扫描一次。
     * 2. 最坏情况下整体复杂度为 O(n^2)。
     * 3. 虽然结果正确，但还没有用上这题最关键的“单调栈模板”。
     */
    public int[] stackSolve(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            int larger = findFirstLarger(nums2[i], i + 1, nums2);
            map.put(nums2[i], larger);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }
        return result;
    }

    /**
     * 旧解中的辅助方法：从右侧线性查找第一个更大的值。
     */
    private int findFirstLarger(int target, int startIndex, int[] nums2) {
        if (startIndex >= nums2.length) {
            return -1;
        }
        for (int i = startIndex; i < nums2.length; i++) {
            if (nums2[i] > target) {
                return nums2[i];
            }
        }
        return -1;
    }

    /**
     * 标准解：单调栈 + 哈希表。
     * <p>
     * 标准解思路：
     * 1. 用一个栈维护“还没找到下一个更大元素”的数字。
     * 2. 遍历 nums2 的当前数字 num。
     * 3. 如果 num 比栈顶大，说明 num 就是栈顶数字右边第一个更大元素。
     * 4. 于是不断弹栈并记录答案，直到栈空或 num 不再大于栈顶。
     * 5. 最后把当前 num 入栈，表示它的答案还没找到。
     * 6. 遍历结束后，栈里剩下的元素都没有更大值，统一记为 -1。
     * <p>
     * 这里最关键的思维升级：
     * 1. 不是“每个数自己单独去右边找答案”。
     * 2. 而是“当前新来的更大值，顺手帮前面一批还没结算的人统一结算答案”。
     * 3. 栈里装的就是“还没找到答案的人”。
     * <p>
     * 例如 nums2 = [1, 3, 4, 2]：
     * 1. 看到 1，入栈。
     * 2. 看到 3，3 > 1，所以 1 的答案就是 3。
     * 3. 看到 4，4 > 3，所以 3 的答案就是 4。
     * 4. 看到 2，2 无法结算 4，所以 2 入栈。
     * 5. 最后栈中剩余 2 和 4，它们的答案都是 -1。
     */
    public int[] monotonicStackSolve(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nextGreaterMap = new HashMap<>();
        //栈维护“还没找到下一个更大元素”的数字
        // 清楚栈维护的是什么 很重要
        Deque<Integer> stack = new ArrayDeque<>();

        for (int num : nums2) {
            //新入站的数字关键操作是更新之前还没找到右边第一个最大的数字
            while (!stack.isEmpty() && num > stack.peek()) {  //while循环  新的数字 可能是前面多个数字右边第一个大的
                nextGreaterMap.put(stack.pop(), num);
            }
            //新来的还没看右边，默认入栈，还没找到下一个更大元素”的数字
            stack.push(num);
        }
        //while 将栈中留存的都标记为找不到
        while (!stack.isEmpty()) {
            nextGreaterMap.put(stack.pop(), -1);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreaterMap.get(nums1[i]);
        }
        return result;
    }
}
