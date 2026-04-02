package no10_unionfind.practice.stage3;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName LeetCode_399
 * @Description 除法求值
 * 题目：给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和
 * values[i] 共同表示等式 Ai / Bi = values[i]。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出
 * Cj / Dj = ? 的结果作为答案。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
 *
 * 示例：
 * 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0],
 *       queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
 *
 * 提示：
 * 1 <= equations.length <= 20
 * 1 <= queries.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_399 {

    public static double[] unionFind(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // TODO
        return new double[0];
    }

    public static void main(String[] args) {
        List<List<String>> equations = List.of(List.of("a", "b"), List.of("b", "c"));
        double[] values = {2.0, 3.0};
        List<List<String>> queries = List.of(
                List.of("a", "c"), List.of("b", "a"), List.of("a", "e"),
                List.of("a", "a"), List.of("x", "x")
        );
        System.out.println(Arrays.toString(unionFind(equations, values, queries)));
        // [6.0, 0.5, -1.0, 1.0, -1.0]
    }
}
