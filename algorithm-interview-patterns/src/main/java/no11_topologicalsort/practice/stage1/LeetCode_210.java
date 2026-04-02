package no11_topologicalsort.practice.stage1;

import java.util.Arrays;

/**
 * @ClassName LeetCode_210
 * @Description 课程表 II
 * 题目：现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites 表示先修课程。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，只要返回任意一种就可以。如果不可能完成所有课程，返回一个空数组。
 *
 * 示例1：输入：numCourses = 2, prerequisites = [[1,0]] 输出：[0,1]
 * 示例2：输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]] 输出：[0,2,1,3] 或 [0,1,2,3]
 *
 * 提示：
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * 所有[ai, bi] 互不相同
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_210 {

    public static int[] topologicalSort(int numCourses, int[][] prerequisites) {
        // TODO
        return new int[0];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(topologicalSort(2, new int[][]{{1, 0}}))); // [0, 1]
        System.out.println(Arrays.toString(topologicalSort(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}))); // [0, 2, 1, 3] or [0, 1, 2, 3]
    }
}
