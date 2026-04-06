package no10_unionfind.practice.stage1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName LeetCode_684
 * @Description 冗余连接
 * 题目：树可以看成是一个连通且无环的无向图。给定往一棵 n 个节点（节点值 1~n）的树中添加一条边后的图。
 * 返回一条可以删去的边，使得结果图是一个有着 n 个节点的树。如果有多个答案，则返回数组中最后出现的边。
 * <p>
 * 示例1：
 * 输入：edges =
 * [[1,2],
 * [1,3],
 * [2,3]]
 * 输出：[2,3]
 * <p>
 * 示例2：
 * 输入：edges =
 * [[1,2],
 * [2,3],
 * [3,4],
 * [1,4],
 * [1,5]]
 * 输出：[1,4]
 * <p>
 * 提示：
 * n == edges.length
 * 3 <= n <= 1000
 * edges[i].length == 2
 * 1 <= ai < bi <= edges.length
 * ai != bi
 * 没有重复的边
 * 给定的图是连通的
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_684 {

    /**
     * 先保留你的原始思路，作为“错误但有价值的中间台阶”。
     *
     * 一、你的直觉从哪里来
     * 1. 题目要找“多出来的一条边”
     * 2. 你自然会去观察：是不是某个点被连接了很多次
     * 3. 于是你把注意力放在“某个节点是否重复出现”上
     *
     * 这个思路不是胡思乱想，它说明你已经抓到了“冗余”和“重复”的感觉。
     * 但问题在于：
     * 你抓到的是“点的重复出现”，而题目真正要找的是“边导致成环”。
     *
     * 二、为什么这个错解不稳定
     * 1. 这是无向图，边 [a,b] 和 [b,a] 的地位一样
     * 2. 因此不能只看 edge[1] 出现次数，因为第二个位置没有特殊意义
     * 3. 一个点连接次数很多，可能完全合法，比如树中的中心点
     * 4. 冗余边的真正特征不是“某个点多次出现”，而是“这条边连接了两个已经连通的点”
     *
     * 三、这个错解对学习有什么价值
     * 1. 它帮你从“表面重复”开始思考
     * 2. 然后逼着你继续追问：
     *    - 到底什么叫冗余？
     *    - 为什么有些重复连接没问题，有些重复连接会出错？
     * 3. 当你把问题追到这里，就会自然过渡到“环”和“连通性”
     * 4. 而一旦题目核心变成“连通性”，并查集就是标准工具
     *
     * 四、这个方法为什么能过部分样例
     * 1. 某些测试里，恰好冗余边的某个端点确实重复出现得比较明显
     * 2. 所以会给人一种“我好像做对了”的错觉
     * 3. 但只要换一个结构，它就会失效
     *
     * 五、反例
     * edges = [[1,4],[3,4],[1,3],[1,2],[4,5]]
     * 正确答案是 [1,3]
     * 但这个错解会更倾向从“第二个端点重复”去判断，逻辑依据不成立
     */
    public static int[] wrongAnswer(int[][] edges) {
        int n = edges.length;
        int[] tags = new int[n + 1];

        // 你的原始做法：
        // 1. 试图通过“某些节点重复出现”来定位冗余边
        // 2. 这里尤其统计了 edge[1] 出现次数
        // 3. 但这只是边在数组里的书写位置，不是图论上的有效判定标准
        tags[1] = 1;
        for (int[] edge : edges) {
            tags[edge[1]]++;
        }

        List<Integer> endList = new ArrayList<>();
        for (int i = 0; i < tags.length; i++) {
            if (tags[i] > 1) {
                endList.add(i);
            }
        }

        for (int i = edges.length - 1; i >= 0; i--) {
            if (endList.contains(edges[i][1])) {
                return edges[i];
            }
        }
        return new int[0];
    }

    /**
     * 从你的直觉分析，如何流畅过渡到标准并查集：
     *
     * 一、先承认你的直觉并不离谱
     * 1. 你已经意识到：题目里有一条“多余”的边
     * 2. 你也在尝试找“重复”信息
     * 3. 这说明你的方向已经靠近“结构异常”了
     *
     * 二、第一步纠偏：从“点重复”切换到“边是否造成环”
     * 1. 树的关键性质：连通 + 无环
     * 2. 现在比树多了一条边，那么多出来的这条边一定让图里出现了环
     * 3. 所以题目真正问的是：哪条边让图第一次形成环
     *
     * 三、第二步纠偏：从“看图形感觉”切换到“看连通关系”
     * 1. 如果一条边连接的是两个原本不连通的点，那么它是正常扩展
     * 2. 如果一条边连接的是两个原本已经连通的点，那么它会构成环
     * 3. 所以判断标准变成一句话：
     *    - 加入当前边之前，这两个点是否已经属于同一个连通块
     *
     * 四、第三步纠偏：既然核心是“连通块”，就该想到并查集
     * 1. 并查集就是专门处理“谁和谁属于同一组”的结构
     * 2. 它最适合回答两类问题：
     *    - 两个点现在是否连通
     *    - 如果不连通，怎么把它们合并成一个集合
     *
     * 五、从“不会”到“会”要跨过的思维障碍
     * 1. 障碍一：把题目表面现象当成本质
     *    - 表面现象：某些点重复出现
     *    - 本质：某条边让已经连通的两个点再次相连
     *
     * 2. 障碍二：总想直接找答案那条边，而不是维护过程状态
     *    - 并查集不是直接猜答案
     *    - 而是边遍历、边维护“当前哪些点已经连成一片”
     *
     * 3. 障碍三：觉得“连通块”太抽象
     *    - 实际上很简单：
     *    - 同一个集合里的点，说明它们已经能互相到达
     *    - 集合代表节点相同，说明它们在同一个连通块里
     *
     * 4. 障碍四：看到模板就想背，不知道为什么这样写
     *    - 真正要记住的不是代码，而是过程：
     *    - 先判断是否已连通
     *    - 已连通：当前边多余
     *    - 未连通：把两个集合合并
     *
     * 六、标准并查集方法
     * 1. 初始时，每个节点自己单独是一个集合
     * 2. 按顺序遍历每条边 [a, b]
     * 3. 如果 a 和 b 已经在同一个集合里，当前边就是冗余边
     * 4. 如果不在同一个集合里，就执行合并
     *
     * 七、为什么这题顺序遍历就能得到答案
     * 1. 题目保证原图是“树 + 1 条边”
     * 2. 所以只会多出一个环
     * 3. 当你遍历到那条真正让环闭合的边时，会发现它的两个端点已经连通
     * 4. 因此直接返回当前边即可
     */
    public static int[] unionFind(int[][] edges) {
        int n = edges.length;
        UnionFind unionFind = new UnionFind(n + 1);

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            // 并查集判环的核心：
            // 如果当前边的两个端点已经属于同一个集合，
            // 再连一条边就会形成环，因此当前边就是冗余边。
            if (unionFind.isSameSet(a, b)) {
                return edge;
            }

            // 如果两个点原本不连通，则把两个集合合并。
            unionFind.union(a, b);
        }
        return new int[0];
    }

    /**
     * 并查集通用模板说明
     *
     * 一、核心概念
     * 1. 每个集合都有一个代表节点，也可理解为“老大”
     * 2. 同一个集合里的所有点，最终都会追溯到同一个代表节点
     * 3. 只要能快速判断两个点的代表是否相同，就能知道它们是否连通
     *
     * 二、模板三件事
     * 1. 初始化：每个点先自成一个集合
     * 2. 查找：找到某个点所属集合的代表节点
     * 3. 合并：把两个不同集合并成一个集合
     *
     * 三、常见适用题型
     * 1. 连通块数量
     * 2. 是否成环
     * 3. 两点是否连通
     * 4. 账户合并、朋友圈、岛屿归并等
     */
    private static class UnionFind {
        /**
         * parent[i] 表示节点 i 的父节点。
         * 如果 parent[i] == i，说明 i 就是当前集合的代表节点。
         */
        private final int[] parent;

        /**
         * size[i] 表示以 i 为代表的集合大小。
         * 只有当 i 是代表节点时，这个值才有意义。
         *
         * 作用：合并时让小集合挂到大集合，降低树高。
         */
        private final int[] size;

        /**
         * 初始化模板：
         * 1. 每个节点的父节点先指向自己
         * 2. 每个节点单独构成一个集合，大小为 1
         */
        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        /**
         * 查找模板：找到 node 所属集合的代表节点。
         *
         * 路径压缩：
         * 1. 如果当前节点不是代表，就继续向上找
         * 2. 找到代表后，让沿途节点直接指向代表
         * 3. 这样下次查找会更快
         */
        public int find(int node) {
            if (parent[node] != node) {
                parent[node] = find(parent[node]);
            }
            return parent[node];
        }

        /**
         * 判断两个节点是否已经属于同一个集合。
         *
         * 在这道题里，它等价于：
         * 当前边的两个端点在加入这条边之前，是否已经连通。
         */
        public boolean isSameSet(int a, int b) {
            return find(a) == find(b);
        }

        /**
         * 合并两个节点所在的集合。
         *
         * 通用步骤：
         * 1. 先找到两个节点的代表节点
         * 2. 如果代表相同，说明本来就在一个集合里，直接返回
         * 3. 如果不同，就执行“小挂大”合并
         */
        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) {
                return;
            }

            if (size[rootA] < size[rootB]) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }

            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }
    }

    public static void main(String[] args) {
        System.out.println("错解示例：");
        System.out.println(Arrays.toString(wrongAnswer(new int[][]{{1, 2}, {1, 3}, {2, 3}}))); // [2, 3]
        System.out.println(Arrays.toString(wrongAnswer(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}}))); // [1, 4]
        System.out.println(Arrays.toString(wrongAnswer(new int[][]{{1, 4}, {3, 4}, {1, 3}, {1, 2}, {4, 5}}))); // 不稳定，不能作为标准做法

        System.out.println("标准并查集：");
        System.out.println(Arrays.toString(unionFind(new int[][]{{1, 2}, {1, 3}, {2, 3}}))); // [2, 3]
        System.out.println(Arrays.toString(unionFind(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}}))); // [1, 4]
        System.out.println(Arrays.toString(unionFind(new int[][]{{1, 4}, {3, 4}, {1, 3}, {1, 2}, {4, 5}}))); // [1, 3]
    }
}
