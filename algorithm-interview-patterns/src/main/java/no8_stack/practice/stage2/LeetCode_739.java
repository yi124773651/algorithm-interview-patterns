package no8_stack.practice.stage2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_739
 * @Description 每日温度
 * <p>
 * 题目：给定一个整数数组 temperatures 表示每天的温度，返回一个数组 answer，
 * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。
 * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 示例1：
 * 输入：temperatures = [73,74,75,71,69,72,76,73]
 * 输出：[1,1,4,2,1,1,0,0]
 * <p>
 * 示例2：
 * 输入：temperatures = [30,40,50,60]
 * 输出：[1,1,1,0]
 * <p>
 * 提示：
 * 1 <= temperatures.length <= 10^5
 * 30 <= temperatures[i] <= 100
 * <p>
 * 解题整理：
 * 1. 旧解的方向其实已经对了：本质上就是在维护“还没找到右侧更高温度的那些天”。
 * 2. 旧解额外拆成了“温度栈 + 下标栈 + Map”，所以能做对，但结构偏重。
 * 3. 标准单调栈写法会进一步收敛：只保留“下标栈”。
 * 4. 因为下标本身就能反查温度，所以没必要再单独存一个温度栈。
 * 5. 因为答案数组本来就要返回，所以也没必要先放进 Map 再回填。
 * 6. 理解上的平滑过渡是：
 *    - 你原来栈里维护的是“还没结算的人”；
 *    - 优化后仍然维护“还没结算的人”；
 *    - 只是把“人”的表示方式，从“温度值 + 下标”收敛成“只存下标”；
 *    - 结算时直接写入 result，而不是先写 Map。
 * 7. 所以这不是推翻旧解，而是把旧解压缩成更标准、更利落的面试写法。
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_739 {

    public static void main(String[] args) {
        LeetCode_739 solution = new LeetCode_739();

        int[] case1 = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] case2 = {30, 40, 50, 60};

        System.out.println("===== 保留旧解 =====");
        System.out.println(Arrays.toString(solution.stackSolve(case1))); // [1,1,4,2,1,1,0,0]
        System.out.println(Arrays.toString(solution.stackSolve(case2))); // [1,1,1,0]

        System.out.println("===== 优化版：标准单调栈 =====");
        System.out.println(Arrays.toString(solution.monotonicStackSolve(case1))); // [1,1,4,2,1,1,0,0]
        System.out.println(Arrays.toString(solution.monotonicStackSolve(case2))); // [1,1,1,0]
    }

    /**
     * 保留旧解。
     * <p>
     * 这版的核心思路已经接近单调栈：
     * 1. 用栈保存“还没找到下一个更高温度”的元素。
     * 2. 当前温度一旦更高，就帮前面的人结算答案。
     * 3. 只是这里拆成了两个栈，再额外借助 Map 存中间结果。
     * <p>
     * 旧解的价值：
     * 1. 它能帮助你先建立“新来的元素，去结算前面未结算元素”的直觉。
     * 2. 这是过渡到标准单调栈解法最关键的一步。
     */
    public int[] stackSolve(int[] temperatures) {
        // 栈里保存“还没找到下一个更高温度”的温度值
        Deque<Integer> stack = new ArrayDeque<>();
        // 与温度栈同步保存对应下标
        Deque<Integer> indexStack = new ArrayDeque<>();
        // 暂存每个位置的答案：下标 -> 等待天数
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > stack.peek()) {
                stack.pop();
                int preIndex = indexStack.pop();
                map.put(preIndex, i - preIndex);
            }

            // 新的元素入栈，表示它的答案还没找到
            stack.push(temperatures[i]);
            indexStack.push(i);
        }

        while (!stack.isEmpty()) {
            stack.pop();
            map.put(indexStack.pop(), 0);
        }

        int[] result = new int[temperatures.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = map.get(i);
        }
        return result;
    }

    /**
     * 优化版：标准单调栈。
     * <p>
     * 相比旧解，优化点只有两个，但非常关键：
     * 1. 栈里只存“下标”，因为有了下标就能同时拿到位置和温度。
     * 2. 直接把答案写入 result，不再额外使用 Map。
     * <p>
     * 这样就完成了从“能做对”到“写得标准”的平滑升级。
     */
    public int[] monotonicStackSolve(int[] temperatures) {
        int[] result = new int[temperatures.length];
        // 栈里保存“还没找到右侧更高温度”的下标，且对应温度从栈顶到栈底递增观察时是递减的
        Deque<Integer> indexStack = new ArrayDeque<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!indexStack.isEmpty() && temperatures[i] > temperatures[indexStack.peek()]) {
                int preIndex = indexStack.pop();
                result[preIndex] = i - preIndex;
            }
            indexStack.push(i);
        }

        return result;
    }
}
