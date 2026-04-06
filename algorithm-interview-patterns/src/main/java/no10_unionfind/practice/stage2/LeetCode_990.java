package no10_unionfind.practice.stage2;

/**
 * @ClassName LeetCode_990
 * @Description 等式方程的可满足性
 * 题目：给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，
 * 并采用两种不同的形式之一："xi==yi" 或 "xi!=yi"。判断是否能够给变量赋值从而满足所有的方程，可以则返回 true。
 * <p>
 * 示例1：
 * 输入：["a==b","b!=a"]
 * 输出：false
 * <p>
 * 示例2：
 * 输入：["b==a","a==b"]
 * 输出：true
 * <p>
 * 提示：
 * 1 <= equations.length <= 500
 * equations[i].length == 4
 * equations[i][0] 是小写字母
 * equations[i][1] 是 '=' 或 '!'
 * equations[i][2] 是 '='
 * equations[i][3] 是小写字母
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_990 {

    /**
     * 保留你的原始解法，作为“容易想到、但不够严谨”的中间版本。
     *
     * 一、你的直觉是怎么来的
     * 1. 看到一个方程，就立刻处理它
     * 2. 如果是相等关系，就合并
     * 3. 如果是不等关系，就当场判断两者是否已经在同一个集合里
     *
     * 这个想法很自然，因为它符合“边读边做”的直觉。
     * 但问题在于：
     * 这题里的相等关系具有“传递性”，而这种传递性可能在后面才显现出来。
     *
     * 二、这个错解为什么会错
     * 反例：
     * a!=c, b==c, a==b
     *
     * 按这个错解的顺序：
     * 1. 先看到 a!=c，当时 a 和 c 还不在同一个集合，觉得没问题
     * 2. 后面看到 b==c，把 b 和 c 合并
     * 3. 再看到 a==b，把 a 也合并进去
     * 4. 最终 a、b、c 已经在同一个集合里
     * 5. 这时最开始的 a!=c 实际上已经冲突了，但错解不会回头重新检查
     *
     * 三、错解的根因
     * 1. 你把“不等式判断”做得太早了
     * 2. 不等式不是局部关系，而是依赖“所有等式都合并完成之后”的最终连通状态
     * 3. 所以这题不能单次顺序混着处理，必须分阶段
     */
    public static boolean wrongAnswer(String[] equations) {
        UnionFind unionFind = new UnionFind(26);
        for (String equation : equations) {
            int a = equation.charAt(0) - 'a';
            char opt1 = equation.charAt(1);
            int b = equation.charAt(3) - 'a';
            if (opt1 == '=') {
                unionFind.union(a, b);
            } else {
                if (unionFind.isSameSet(a, b)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 从你的直觉分析，如何过渡到标准并查集：
     *
     * 一、先肯定你的出发点
     * 1. 你已经抓到了这题和“连通性”有关
     * 2. 也已经知道：
     *    - 相等关系要合并
     *    - 不等关系要检查是否冲突
     * 3. 所以你离标准解法其实只差一步
     *
     * 二、关键纠偏：这题不是“边遍历边判定”，而是“先建事实，再验冲突”
     * 1. 所有 == 都是在建立事实：谁和谁必须属于同一组
     * 2. 所有 != 都是在检查约束：这两个变量最终不能属于同一组
     * 3. 只有当所有“必须相等”的事实都建立完后，才能去判断“不等约束”是否被破坏
     *
     * 三、从不会到会，要跨过哪些思维障碍
     * 1. 障碍一：看到条件就想立刻处理
     *    - 有些题可以边读边做
     *    - 但这题的“不等式”依赖最终状态，不能过早下结论
     *
     * 2. 障碍二：低估了“相等的传递性”
     *    - a==b，b==c，会推出 a==c
     *    - 所以某两个变量当前不连通，不代表最终不连通
     *
     * 3. 障碍三：把并查集只理解为“合并工具”，没理解它也是“查询最终归属”的工具
     *    - 并查集不只是 union
     *    - 更重要的是：在所有合并结束后，用 find / isSameSet 去做最终判定
     *
     * 四、标准并查集方法
     * 1. 第一阶段：遍历所有 equations，把所有 == 全部合并
     * 2. 第二阶段：再次遍历所有 equations，检查所有 != 是否冲突
     * 3. 只要存在一个 != 的两端点已经属于同一个集合，就返回 false
     * 4. 如果所有 != 都不冲突，就返回 true
     *
     * 五、这类题以后怎么识别
     * 只要题目在说：
     * 1. 哪些对象必须相等 / 属于同一组
     * 2. 哪些对象不能相等 / 不能属于同一组
     * 3. 让你判断所有约束能否同时成立
     *
     * 那就要想到：
     * 先合并所有“必须相等”的关系，再检查“禁止相等”的关系。
     */
    public static boolean unionFind(String[] equations) {
        UnionFind unionFind = new UnionFind(26);

        // 第一阶段：先处理所有等式。
        // 含义：先把所有“必须相等”的变量归并成若干集合。
        for (String equation : equations) {
            int a = equation.charAt(0) - 'a';
            int b = equation.charAt(3) - 'a';
            char op = equation.charAt(1);
            if (op == '=') {
                unionFind.union(a, b);
            }
        }

        // 第二阶段：再检查所有不等式是否冲突。
        // 含义：此时集合关系已经稳定，检查“不能相等”的两个变量是否被合并到了同一组。
        for (String equation : equations) {
            int a = equation.charAt(0) - 'a';
            int b = equation.charAt(3) - 'a';
            char op = equation.charAt(1);
            if (op == '!' && unionFind.isSameSet(a, b)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 并查集通用模板
     *
     * 一、核心概念
     * 1. 每个集合都有一个代表节点
     * 2. 只要两个节点的代表相同，就说明它们属于同一个集合
     * 3. 因此并查集特别适合做“分组”和“连通性”判定
     *
     * 二、模板能力
     * 1. union(a, b)：把 a 和 b 所在集合合并
     * 2. find(x)：找到 x 所在集合的代表节点
     * 3. isSameSet(a, b)：判断 a 和 b 是否属于同一个集合
     *
     * 三、适用场景
     * 1. 连通块统计
     * 2. 判环
     * 3. 关系归类
     * 4. 约束一致性判断
     */
    public static class UnionFind {
        /**
         * parent[i] 表示节点 i 的父节点。
         * 如果 parent[i] == i，说明 i 是当前集合的代表节点。
         */
        private final int[] parent;

        /**
         * size[i] 表示以 i 为代表的集合大小。
         * 用于“小挂大”优化，降低树高。
         */
        private final int[] size;

        public UnionFind(int n) {
            this.size = new int[n];
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                size[i] = 1;
                parent[i] = i;
            }
        }

        /**
         * 查找代表节点。
         *
         * 路径压缩：
         * 查找过程中，把沿途节点直接挂到代表节点下面，
         * 让后续查询更快。
         */
        public int find(int a) {
            if (parent[a] != a) {
                parent[a] = find(parent[a]);
            }
            return parent[a];
        }

        /**
         * 合并两个集合。
         *
         * 步骤：
         * 1. 先找两个节点各自的代表
         * 2. 如果代表相同，说明本来就在同一集合里，无需处理
         * 3. 如果不同，就把小集合挂到大集合上
         */
        public void union(int a, int b) {
            int parentA = find(a);
            int parentB = find(b);
            if (parentA == parentB) {
                return;
            }
            if (size[parentA] < size[parentB]) {
                int tmp = parentA;
                parentA = parentB;
                parentB = tmp;
            }
            parent[parentB] = parentA;
            size[parentA] += size[parentB];
        }

        /**
         * 判断两个节点是否属于同一个集合。
         */
        public boolean isSameSet(int a, int b) {
            return find(a) == find(b);
        }
    }

    public static void main(String[] args) {
        System.out.println("错解结果：");
        System.out.println(wrongAnswer(new String[]{"a==b", "b!=a"})); // false
        System.out.println(wrongAnswer(new String[]{"b==a", "a==b"})); // true
        System.out.println(wrongAnswer(new String[]{"a!=c", "b==c", "a==b"})); // true，错误

        System.out.println("标准并查集结果：");
        System.out.println(unionFind(new String[]{"a==b", "b!=a"})); // false
        System.out.println(unionFind(new String[]{"b==a", "a==b"})); // true
        System.out.println(unionFind(new String[]{"a!=c", "b==c", "a==b"})); // false
    }
}
