package no11_topologicalsort.practice.stage1;

import java.util.*;

/**
 * @ClassName LeetCode_210
 * @Description 课程表 II
 * 题目：现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites 表示先修课程。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，只要返回任意一种就可以。如果不可能完成所有课程，返回一个空数组。
 * <p>
 * 示例1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：[0,1]
 * <p>
 * 示例2：
 * 输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * 输出：[0,2,1,3] 或 [0,1,2,3]
 * <p>
 * 提示：
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * 所有[ai, bi] 互不相同
 *
 * ---------------------------------------------------------
 * 和 LeetCode_207 的关系：
 * 1. 207 问的是“能不能学完”，所以返回 boolean
 * 2. 210 问的是“学习顺序是什么”，所以返回 int[]
 * 3. 两题底层都是拓扑排序，区别只在于：
 *    - 207 只需要判断是否能处理完所有节点
 *    - 210 需要把处理节点的顺序记录下来并返回
 *
 * 题目思维链：
 * 文字题意 -> 依赖关系 -> 有向图 -> 拓扑排序 -> 输出拓扑序
 *
 * 本题核心：
 * 1. [a, b] 表示 b -> a，先学 b 再学 a
 * 2. 入度为 0 的课程，表示当前没有依赖，可以先学
 * 3. 每弹出一个课程，就把它加入答案，并削减它对后继课程的影响
 * 4. 如果最终收集到的课程数不足 numCourses，说明图中有环，返回空数组
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_210 {

    /**
     * 原始写法：先收集到 List，再转换成 int[]。
     * 优点是思路直观，适合刚接触拓扑排序时理解。
     */
    public static int[] topologicalSort(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        int[] inDgree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int currentCourse = prerequisite[0];
            int preCourse = prerequisite[1];
            graph.get(preCourse).add(currentCourse);
            inDgree[currentCourse]++;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < inDgree.length; i++) {
            if (inDgree[i] == 0) {
                queue.offer(i);
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer currentCourse = queue.poll();
            result.add(currentCourse);
            for (Integer next : graph.get(currentCourse)) {
                inDgree[next]--;
                if (inDgree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        if (result.size() == numCourses) {
            int[] resultArr = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                resultArr[i] = result.get(i);
            }
            return resultArr;
        }
        return new int[0];
    }

    /**
     * 更推荐的写法：直接用结果数组记录拓扑序。
     * 核心区别：边遍历边写入答案，少一次 List 到数组的转换。
     */
    public static int[] topologicalSortRecommended(int numCourses, int[][] prerequisites) {
        // 邻接表：preCourse -> currentCourse，表示先学 preCourse，才能学 currentCourse
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // 入度表：记录每门课还剩多少前置依赖
        int[] inDegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int currentCourse = prerequisite[0];
            int preCourse = prerequisite[1];
            graph.get(preCourse).add(currentCourse);
            inDegree[currentCourse]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int course = 0; course < numCourses; course++) {
            if (inDegree[course] == 0) {
                queue.offer(course);
            }
        }

        int[] order = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            int currentCourse = queue.poll();
            order[index++] = currentCourse;

            for (int next : graph.get(currentCourse)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return index == numCourses ? order : new int[0];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(topologicalSort(2, new int[][]{{1, 0}}))); // [0, 1]
        System.out.println(Arrays.toString(topologicalSort(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}))); // [0, 2, 1, 3] or [0, 1, 2, 3]

        System.out.println(Arrays.toString(topologicalSortRecommended(2, new int[][]{{1, 0}}))); // [0, 1]
        System.out.println(Arrays.toString(topologicalSortRecommended(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}))); // [0, 2, 1, 3] or [0, 1, 2, 3]
    }
}
