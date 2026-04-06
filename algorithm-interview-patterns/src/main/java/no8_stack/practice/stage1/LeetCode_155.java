package no8_stack.practice.stage1;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName LeetCode_155
 * @Description 最小栈
 * <p>
 * 题目: 设计一个支持 push、pop、top 操作，并能在常数时间内检索到最小元素的栈。
 * 实现 MinStack 类：
 * MinStack() 初始化堆栈对象；
 * void push(int val) 将元素 val 推入堆栈；
 * void pop() 删除堆栈顶部的元素；
 * int top() 获取堆栈顶部的元素；
 * int getMin() 获取堆栈中的最小元素。
 * <p>
 * 示例:
 * 输入：["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * 输出：[null,null,null,null,-3,null,0,-2]
 * <p>
 * 提示:
 * -2^31 <= val <= 2^31 - 1;
 * pop、top 和 getMin 操作总是在非空栈上调用；
 * push, pop, top, getMin 最多被调用 3 * 10^4 次。
 * <p>
 * 解题整理：
 * 1. 旧解已经有“维护当前最小值”的意识，这是正确方向。
 * 2. 旧解的问题在于：最小值一旦被弹出，需要重新遍历整个栈，导致 pop 最坏为 O(n)。
 * 3. 标准解的关键升级是：使用一个辅助栈，同步记录“到当前位置为止的最小值”。
 * 4. 这样 push、pop、top、getMin 都能稳定做到 O(1)。
 * 5. 本题最大的思维卡点：minStack 存的不是“当前哪个值最小”，而是“每一层状态下的最小值快照”。
 * 6. 因此 pop 时即使 stack 弹出的不是最小值，minStack 也必须同步弹出当前这一层的最小值记录。
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_155 {

    public static void main(String[] args) {
        System.out.println("===== 旧解（更正版） =====");
        LegacyMinStack legacyMinStack = new LegacyMinStack();
        legacyMinStack.push(-2);
        legacyMinStack.push(0);
        legacyMinStack.push(-3);
        System.out.println(legacyMinStack.getMin()); // -3
        legacyMinStack.pop();
        System.out.println(legacyMinStack.top());    // 0
        System.out.println(legacyMinStack.getMin()); // -2

        System.out.println("===== 标准解 =====");
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // -3
        minStack.pop();
        System.out.println(minStack.top());    // 0
        System.out.println(minStack.getMin()); // -2
    }

    /**
     * 旧解保留，但修正了 top() 的语义错误。
     *
     * 旧解思路：
     * 1. 使用一个普通栈存储所有元素。
     * 2. 使用一个变量 min 记录当前最小值。
     * 3. push 时更新最小值。
     * 4. pop 时如果弹出了最小值，就重新扫描整个栈，找出新的最小值。
     *
     * 旧解的问题：
     * 1. getMin 看起来是 O(1)，但代价被转移到了 pop 上。
     * 2. 一旦弹出的元素等于当前最小值，就需要重新遍历整个栈。
     * 3. 因此它不满足题目想考察的“所有关键操作都为 O(1)”的要求。
     *
     * 这一版仅保留作思路过渡。
     */
    static class LegacyMinStack {
        private final Deque<Integer> stack;
        private int min;

        public LegacyMinStack() {
            stack = new ArrayDeque<>();
            min = Integer.MAX_VALUE;
        }

        public void push(int val) {
            min = Math.min(min, val);
            stack.push(val);
        }

        public void pop() {
            int pop = stack.pop();
            if (pop == min) {
                min = Integer.MAX_VALUE;
                for (int value : stack) {
                    min = Math.min(min, value);
                }
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

    /**
     * 标准解：双栈法。
     *
     * 标准解思路：
     * 1. 主栈负责存储真实数据。
     * 2. 辅助栈负责存储“到当前位置为止的最小值”。
     * 3. 每次 push 时，两个栈一起入栈。
     * 4. 每次 pop 时，两个栈一起出栈。
     * 5. top 看主栈栈顶，getMin 看辅助栈栈顶。
     *
     * 从旧解过渡到标准解的关键一句话：
     * “不要等最小值丢了再重算，而是在每一步都把当前最小值同步保存下来。”
     *
     * 这里最容易卡住的点：
     * 1. minStack 不是“只存真正的最小元素本身”，而是“每一层对应的最小值快照”。
     * 2. 所以 stack 和 minStack 的元素个数永远相同，层级一一对应。
     * 3. stack 当前层弹出的即使不是最小值，minStack 也要弹出这一层对应的最小值记录。
     * 4. 因为被删除的是“这一层状态”，而不是“某个最小值标签”。
     *
     * 例如依次执行 push(3)、push(5)、push(2)、push(4)：
     * 1. stack    = [4, 2, 5, 3]
     * 2. minStack = [2, 2, 3, 3]
     * 3. 此时 pop() 弹出 stack 顶部 4，minStack 同时弹出顶部 2。
     * 4. 这里弹出的 2 不是在说“4 是最小值”，而是在说“包含 4 的这一层状态的最小值是 2”。
     * 5. 这一层状态被删除后，它对应的最小值快照也必须一起删除。
     */
    static class MinStack {
        private final Deque<Integer> stack;
        private final Deque<Integer> minStack;

        public MinStack() {
            stack = new ArrayDeque<>();
            minStack = new ArrayDeque<>();
        }

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty()) {
                minStack.push(val);
            } else {
                minStack.push(Math.min(val, minStack.peek()));
            }
        }

        /**
         * 注意：这里两个栈必须同步弹出。
         *
         * 原因不是“stack 弹出的元素一定是最小值”，而是：
         * 1. stack 弹出的是当前这一层的真实值；
         * 2. minStack 弹出的是当前这一层对应的最小值快照；
         * 3. 两个栈维护的是同一层状态的两份信息，因此必须同步删除。
         */
        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}
