package no10_unionfind.practice.stage3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_399
 * @Description 除法求值
 * 题目：给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和
 * values[i] 共同表示等式 Ai / Bi = values[i]。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出
 * Cj / Dj = ? 的结果作为答案。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
 * <p>
 * 示例：
 * 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0],
 * queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * <p>
 * 提示：
 * 1 <= equations.length <= 20
 * 1 <= queries.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_399 {

    /**
     * 保留你的原始解法，作为“已经想到并查集，但还没进入带权并查集”的中间版本。
     *
     * 这个解法的主要问题有三层：
     * 1. 只取了变量的首字符：
     *    - 题目变量是字符串，不一定只能靠首字母区分
     *    - 用 charAt(0) 会把 "ab" 和 "a" 混成同一个变量
     *
     * 2. 把 values[a] 直接当成“某个变量的全局值”：
     *    - 但这题里的值不是变量本身的绝对值
     *    - 而是“变量到集合代表节点之间的倍率关系”
     *
     * 3. 合并时试图手动扫描整个 parent 数组去修补比例：
     *    - 这不是并查集的标准维护方式
     *    - 比例关系应该随着 find 的路径压缩和 union 的权重公式一起维护
     *
     * 所以这题不是普通并查集，而是“带权并查集”。
     */
    public static double[] wrongAnswer(List<List<String>> equations, double[] values, List<List<String>> queries) {
        UnionFindWrong unionFind = new UnionFindWrong(128);
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            double value = values[i];
            unionFind.union(equation.get(0).charAt(0), equation.get(1).charAt(0), value);
        }
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            int a = query.get(0).charAt(0);
            int b = query.get(1).charAt(0);
            if (!unionFind.isSameSet(a, b)) {
                result[i] = -1.0;
            } else {
                if (a == b) {
                    if (unionFind.find(a) != a || unionFind.size[a] > 1) {
                        result[i] = 1.0;
                    } else {
                        result[i] = -1.0;
                    }
                } else {
                    result[i] = unionFind.values[a] / unionFind.values[b];
                }
            }
        }
        return result;
    }

    /**
     * 从你的直觉分析，过渡到标准带权并查集：
     *
     * 一、你的直觉已经抓到了“分组”
     * 1. a / b = 2，b / c = 3
     * 2. 说明 a、b、c 之间存在同一组内的关系
     * 3. 所以你想到并查集，这个方向本身是对的
     *
     * 二、这题比普通并查集多了什么
     * 1. 普通并查集只关心：是否在同一个集合
     * 2. 这题除了关心“是否同组”，还要关心“同组内的倍率关系”
     * 3. 所以要在并查集上再维护一个“权重”
     *
     * 三、带权并查集的核心含义
     * 1. parent[x]：x 的父节点
     * 2. weight[x]：x / parent[x] 的值
     * 3. 如果一路向上乘过去，就能得到 x / 根节点 的值
     *
     * 四、为什么查询可以用 weight[a] / weight[b]
     * 1. 如果 a 和 b 在同一个集合，设它们公共根为 root
     * 2. 那么 weight[a] 表示 a / root
     * 3. weight[b] 表示 b / root
     * 4. 所以 a / b = (a / root) / (b / root) = weight[a] / weight[b]
     *
     * 五、从不会到会，要跨过的思维障碍
     * 1. 障碍一：把“比例关系”误当成“节点自身数值”
     *    - 节点没有固定绝对值
     *    - 我们只维护相对比例
     *
     * 2. 障碍二：觉得合并后要把整棵树所有节点的值都改一遍
     *    - 不需要
     *    - 只需维护父子之间的比例，结合路径压缩自然得到全局结果
     *
     * 3. 障碍三：忽略了变量是字符串，不是单字符
     *    - 必须先做字符串到编号的映射
     *
     * 六、标准做法
     * 1. 先把所有出现过的变量映射成编号
     * 2. 用带权并查集维护每个变量到根节点的倍率关系
     * 3. 查询时：
     *    - 若变量不存在，返回 -1.0
     *    - 若不在同一集合，返回 -1.0
     *    - 若在同一集合，返回 weight[a] / weight[b]
     */
    public static double[] unionFind(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Integer> variableIndexMap = new HashMap<>();
        int index = 0;
        for (List<String> equation : equations) {
            String a = equation.get(0);
            String b = equation.get(1);
            if (!variableIndexMap.containsKey(a)) {
                variableIndexMap.put(a, index++);
            }
            if (!variableIndexMap.containsKey(b)) {
                variableIndexMap.put(b, index++);
            }
        }

        WeightedUnionFind unionFind = new WeightedUnionFind(index);
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            int a = variableIndexMap.get(equation.get(0));
            int b = variableIndexMap.get(equation.get(1));
            unionFind.union(a, b, values[i]);
        }

        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);
            if (!variableIndexMap.containsKey(a) || !variableIndexMap.containsKey(b)) {
                result[i] = -1.0;
                continue;
            }

            int indexA = variableIndexMap.get(a);
            int indexB = variableIndexMap.get(b);
            if (!unionFind.isSameSet(indexA, indexB)) {
                result[i] = -1.0;
            } else {
                result[i] = unionFind.getRatio(indexA, indexB);
            }
        }
        return result;
    }

    public static class WeightedUnionFind {
        private final int[] parent;
        private final int[] size;

        /**
         * weight[x] 表示 x / parent[x] 的值。
         */
        private final double[] weight;

        public WeightedUnionFind(int n) {
            this.parent = new int[n];
            this.size = new int[n];
            this.weight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
                weight[i] = 1.0;
            }
        }

        /**
         * 查找根节点，并在路径压缩过程中更新权重。
         *
         * 如果原来：
         * x / parent[x] = weight[x]
         * parent[x] / root = 某个值
         *
         * 那么压缩后：
         * x / root = weight[x] * weight[原parent[x]]
         */
        public int find(int x) {
            if (parent[x] != x) {
                int originalParent = parent[x];
                parent[x] = find(parent[x]);
                weight[x] *= weight[originalParent];
            }
            return parent[x];
        }

        /**
         * 已知 x / y = value，合并两个集合。
         *
         * 设：
         * x / rootX = weight[x]
         * y / rootY = weight[y]
         *
         * 若把 rootX 挂到 rootY 下，需要满足：
         * x / y = value
         *
         * 现在真正要补出来的是：
         * rootX / rootY = ?
         *
         * 因为挂接后：
         * weight[rootX] 的定义就是 rootX / rootY
         *
         * 推导过程如下：
         * 1. 由题意可知：
         *    x / y = value
         *
         * 2. 把 x / y 拆成一条从 x 到 y 的比例链：
         *    x / y
         *    = (x / rootX) * (rootX / rootY) * (rootY / y)
         *
         * 3. 代入已知量：
         *    x / rootX = weight[x]
         *    y / rootY = weight[y]
         *    所以 rootY / y = 1 / weight[y]
         *
         * 4. 因此：
         *    value = weight[x] * (rootX / rootY) * (1 / weight[y])
         *
         * 5. 移项可得：
         *    rootX / rootY = value * weight[y] / weight[x]
         *
         * 6. 所以：
         *    weight[rootX] = value * weight[y] / weight[x]
         *
         * 你可以把它记成一句话：
         * 已知的是 x / y，真正要设置的是 rootX / rootY，
         * 所以要把 x、y 到各自根节点的旧比例“消掉”。
         *
         * 同理，如果反过来是把 rootY 挂到 rootX 下，则：
         * weight[rootY] = rootY / rootX = weight[x] / (value * weight[y])
         */
        public void union(int x, int y, double value) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            if (size[rootX] < size[rootY]) {
                parent[rootX] = rootY;
                weight[rootX] = value * weight[y] / weight[x];
                size[rootY] += size[rootX];
            } else {
                parent[rootY] = rootX;
                weight[rootY] = weight[x] / (value * weight[y]);
                size[rootX] += size[rootY];
            }
        }

        public boolean isSameSet(int x, int y) {
            return find(x) == find(y);
        }

        /**
         * 同集合下，x / y = (x / root) / (y / root)
         */
        public double getRatio(int x, int y) {
            find(x);
            find(y);
            return weight[x] / weight[y];
        }
    }

    /**
     * 原始错解中的并查集，保留用于对照学习。
     */
    public static class UnionFindWrong {
        private final int[] parent;
        private final int[] size;
        private final double[] values;

        public UnionFindWrong(int n) {
            this.parent = new int[n];
            this.size = new int[n];
            this.values = new double[n];
            for (int i = 0; i < n; i++) {
                size[i] = 1;
                parent[i] = i;
                values[i] = 1.0;
            }
        }

        public int find(int a) {
            if (parent[a] != a) {
                parent[a] = find(parent[a]);
            }
            return parent[a];
        }

        public void union(int a, int b, double value) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) {
                return;
            }
            if (size[rootA] < size[rootB]) {
                int tmp = rootA;
                rootA = rootB;
                rootB = tmp;
                tmp = a;
                a = b;
                b = tmp;
                value = 1 / value;
            }
            values[rootB] = 1 / value;
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] != i && parent[i] == b) {
                    values[i] = values[i] * values[rootB];
                }
            }
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }

        public boolean isSameSet(int a, int b) {
            return find(a) == find(b);
        }
    }

    public static void main(String[] args) {
        List<List<String>> equations = List.of(List.of("a", "b"), List.of("b", "c"));
        double[] values = {2.0, 3.0};
        List<List<String>> queries = List.of(
                List.of("a", "c"), List.of("b", "a"), List.of("a", "e"),
                List.of("a", "a"), List.of("x", "x")
        );
        System.out.println("错解结果：");
        System.out.println(Arrays.toString(wrongAnswer(equations, values, queries)));
        System.out.println("标准带权并查集结果：");
        System.out.println(Arrays.toString(unionFind(equations, values, queries)));
        // [6.0, 0.5, -1.0, 1.0, -1.0]
    }
}
