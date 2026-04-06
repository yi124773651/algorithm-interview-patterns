package no8_stack.practice.stage3;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName LeetCode_84
 * @Description 柱状图中最大的矩形
 * <p>
 * 题目：给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * 示例1：
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 * <p>
 * 示例2：
 * 输入：heights = [2,4]
 * 输出：4
 * <p>
 * 提示：
 * 1 <= heights.length <= 10^5
 * 0 <= heights[i] <= 10^4
 * -------------------------------------------------------------------------
 * <p>
 * 这份文件刻意保留了两份错误思路，目的是把“为什么错”讲清楚，再自然过渡到标准单调栈。
 * <p>
 * 一、你当前的两个错误版本：
 * 1. stackSolve：尝试用栈做，但它不是标准单调栈，结算逻辑有根本偏差。
 * 2. anotherSolve：不是栈，思路方向更接近暴力枚举，但实现漏掉了宽度为 1 的情况。
 * <p>
 * 二、为什么 anotherSolve 虽然慢，却更接近正确思路：
 * 1. 它在枚举一个区间 [i, j]。
 * 2. 这个区间能形成的矩形高度，取决于区间里的最小高度。
 * 3. 所以它其实是在做：枚举区间，再维护区间最小值。
 * 4. 这已经摸到了本题本质，只是复杂度是 O(n^2)，而且当前实现还有边界漏洞。
 * <p>
 * 三、如何从非栈思路平滑过渡到单调栈：
 * 1. 非栈思路是在问：每个区间的最小高度是多少？
 * 2. 单调栈思路是在问：把某一根柱子当成“这个矩形里最矮的柱子”，它能向左右扩多远？
 * 3. 一旦能找到“某根柱子左边第一个更矮的位置”和“右边第一个更矮的位置”，
 * 这根柱子作为最矮柱时的最大宽度就确定了。
 * 4. 单调栈就是高效地帮你找这两个边界，把 O(n^2) 压到 O(n)。
 * <p>
 * 四、核心过渡句：
 * - 旧思路：我在枚举很多区间，并求区间最小值。
 * - 新思路：我不再枚举区间，而是枚举“最小值是谁”，并直接求它的最大覆盖范围。
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_84 {

    public static void main(String[] args) {
        LeetCode_84 solution = new LeetCode_84();

        int[] case1 = {2, 1, 5, 6, 2, 3};
        int[] case2 = {2, 4};

        System.out.println("===== 保留错误栈解 =====");
        System.out.println(solution.stackSolve(case1));
        System.out.println(solution.stackSolve(case2));

        System.out.println("===== 保留错误非栈解 =====");
        System.out.println(solution.anotherSolve(case1));
        System.out.println(solution.anotherSolve(case2));

        System.out.println("===== 标准单调栈解 =====");
        System.out.println(solution.monotonicStackSolve(case1)); // 10
        System.out.println(solution.monotonicStackSolve(case2)); // 4
    }

    /**
     * 保留你的错误栈解，不做修正，只分析原因。
     * <p>
     * 错误点 1：栈里只存高度，不存下标。
     * - 这题最终要算面积，面积 = 高 * 宽。
     * - 只存高度时，你不知道某个高度能向左、向右扩到哪里，宽度无法严格确定。
     * <p>
     * 错误点 2：遇到更小值时，把整栈全部弹空。
     * - 标准做法不是“一次清空整栈”。
     * - 标准做法是谁比当前柱子高，谁就先结算；而不是把所有历史全部打包结算。
     * <p>
     * 错误点 3：把“弹栈过程中出现的最小高度 * 个数”当成候选面积。
     * - 这不是本题的矩形定义。
     * - 本题不是看“这一批数的最小值乘个数”，而是看“某个柱子作为最矮柱时，它的合法宽度”。
     * <p>
     * 错误点 4：没有处理遍历结束后的剩余栈元素。
     * - 对于递增数组，这些柱子的答案都在最后才能结算。
     * - 不清算就会漏答案。
     */
    public int stackSolve(int[] heights) {
        // 默认进栈，直到找到一个比栈顶小的数字
        int maxArea = Integer.MIN_VALUE;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int height : heights) {
            while (!stack.isEmpty() && height < stack.peek()) {
                int minHeight = Integer.MAX_VALUE;
                int count = 0;
                while (!stack.isEmpty()) {
                    minHeight = Math.min(minHeight, stack.pop());
                    count++;
                }
                if (height >= minHeight) {
                    count++;
                }
                maxArea = Math.max(maxArea, minHeight * count);
            }
            stack.push(height);
        }
        return maxArea;
    }

    /**
     * 保留你的错误非栈解，不做修正，只分析原因。
     * <p>
     * 这版思路其实更接近正确方向：
     * 1. 固定左边界 i。
     * 2. 向右扩展右边界 j。
     * 3. 用 minHeight 维护区间 [i, j] 的最小高度。
     * 4. 面积就是 minHeight * (j - i + 1)。
     * <p>
     * 它的主要错误不在思路，而在实现边界：
     * 1. maxArea 初始化为 Integer.MIN_VALUE，不适合面积题。
     * 2. 内层循环从 j = i + 1 开始，漏掉了宽度为 1 的矩形。
     * 3. 所以像 [2]、[1,1] 这种情况会直接算错。
     * <p>
     * 也正因为它已经接近“区间最小值”视角，所以最适合拿来过渡到标准单调栈。
     */
    public int anotherSolve(int[] heights) {
//        int maxArea = Integer.MIN_VALUE;
        int maxArea = 0;
        //外层 i：固定左边界
        //内层 j：不断向右扩展右边界
        //minHeight：表示区间 [i, j] 的最小高度
        for (int i = 0; i < heights.length; i++) {
            // 以 i 为起点的可能性
            int minHeight = heights[i];
//            for (int j = i + 1; j < heights.length; j++) {
            for (int j = i; j < heights.length; j++) {   //不从i开始会漏掉[2] [1,1]的情况
                minHeight = Math.min(minHeight, heights[j]);
                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }
        return maxArea;
    }

    /**
     * 标准单调栈解。
     * <p>
     * 关键理解：
     * 1. 以某根柱子 heights[mid] 作为“矩形中的最矮柱子”。
     * 2. 它左边第一个更矮的位置记为 left。
     * 3. 它右边第一个更矮的位置记为 right。
     * 4. 那么它能撑起的最大宽度就是 right - left - 1。
     * 5. 面积就是 heights[mid] * (right - left - 1)。
     * <p>
     * 单调栈维护的是“下标对应的高度递增”的栈。
     * 一旦当前高度更小，说明栈顶柱子的右边界找到了，就可以立即结算。
     * ------------------------------------------------------------------------
     * <p>
     * 从你的错误栈解过渡到正确版本，需要刻意跨过下面几个思维障碍：
     * 1. 从“栈里存高度”切换到“栈里存下标”。
     * - 你的旧想法更关注数字本身大小。
     * - 但这题要求的是面积，面积除了高度，还依赖宽度。
     * - 宽度一定和位置有关，所以必须存下标，不能只存高度。
     * <p>
     * 2. 从“来一个更小值就整体清算一批”切换到“谁的右边界先出现，就先结算谁”。
     * - 你的旧解里有一种很强的打包思维：既然来了更小值，就把前面的都一起算掉。
     * - 但正确做法不是整批处理，而是按边界出现的顺序，一个一个结算。
     * - 当前柱子只会让“比它高的那些栈顶元素”失去继续向右扩展的资格。
     * <p>
     * 3. 从“求一批数的最小值乘个数”切换到“求某一根柱子作为最矮柱时的最大覆盖范围”。
     * - 这是最核心的一道坎。
     * - 你的旧解在想：这一坨数字里最矮的是谁，我拿它乘上数量试试看。
     * - 正确思路是：我就盯住某一根柱子，看它左边第一个更矮是谁，右边第一个更矮是谁。
     * - 这两个边界一确定，它作为最矮柱子的最大矩形就唯一确定了。
     * <p>
     * 4. 从“我主动组合矩形”切换到“矩形其实是被边界自然决定的”。
     * - 旧思路容易人为去拼一个矩形，好像在试很多组合。
     * - 新思路要接受：矩形不是瞎拼出来的，而是由“左右第一个更矮元素”自动框出来的。
     * - 单调栈只是把这个边界发现过程高效化。
     * <p>
     * 5. 从“遍历时只看当前”切换到“遍历结束也要强制清算”。
     * - 递增序列的问题最容易暴露这一点。
     * - 有些柱子直到数组结束，都没遇到右边更矮元素。
     * - 所以标准写法会在最后补一个高度 0 的哨兵，逼着栈里剩余元素全部结算。
     * <p>
     * 6. 从“我在维护一堆高度”切换到“我在维护一个还没找到右边界的候选集合”。
     * - 一旦这样理解，单调栈就会顺很多。
     * - 栈里的每个下标，表示这根柱子的右边第一个更矮位置还没有出现。
     * - 当前更小的高度一来，就是在通知这些候选者：你的右边界找到了，可以结算了。
     */
    public int monotonicStackSolve(int[] heights) {
        int maxArea = 0;
        Deque<Integer> indexStack = new ArrayDeque<>();

        for (int i = 0; i <= heights.length; i++) {
            //高度为0  = heights.length 的哨兵 强制清算
            int currentHeight = i == heights.length ? 0 : heights[i];

            while (!indexStack.isEmpty() && currentHeight < heights[indexStack.peek()]) {
                int mid = indexStack.pop();
                int left = indexStack.isEmpty() ? -1 : indexStack.peek();
                int right = i;
                int width = right - left - 1;
                maxArea = Math.max(maxArea, heights[mid] * width);
            }

            indexStack.push(i);
        }

        return maxArea;
    }
}
