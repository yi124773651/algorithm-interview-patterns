package no10_unionfind.practice.stage1;

import java.util.Arrays;

/**
 * @ClassName LeetCode_684
 * @Description 冗余连接
 * 题目：树可以看成是一个连通且无环的无向图。给定往一棵 n 个节点（节点值 1~n）的树中添加一条边后的图。
 * 返回一条可以删去的边，使得结果图是一个有着 n 个节点的树。如果有多个答案，则返回数组中最后出现的边。
 *
 * 示例1：输入：edges = [[1,2],[1,3],[2,3]] 输出：[2,3]
 * 示例2：输入：edges = [[1,2],[2,3],[3,4],[1,4],[1,5]] 输出：[1,4]
 *
 * 提示：
 * n == edges.length
 * 3 <= n <= 1000
 * edges[i].length == 2
 * 1 <= ai < bi <= edges.length
 * ai != bi
 * 没有重复的边
 * 给定的图是连通的
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_684 {

    public static int[] unionFind(int[][] edges) {
        // TODO
        return new int[0];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(unionFind(new int[][]{{1, 2}, {1, 3}, {2, 3}}))); // [2, 3]
        System.out.println(Arrays.toString(unionFind(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}}))); // [1, 4]
    }
}
