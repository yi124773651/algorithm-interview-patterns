package no11_topologicalsort.practice.stage1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName LeetCode_207
 * @Description 课程表
 * 题目：你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1。在选修某些课程之前需要一些先修课程。
 * 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] 表示如果要学习课程 ai 则必须先学习课程 bi。
 * 请你判断是否可能完成所有课程的学习。
 *
 * 示例1：输入：numCourses = 2, prerequisites = [[1,0]] 输出：true
 * 示例2：输入：numCourses = 2, prerequisites = [[1,0],[0,1]] 输出：false
 *
 * 提示：
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * 所有先修课程对 prerequisites[i] 互不相同
 *
 * ---------------------------------------------------------
 * 你可以这样训练自己的思维触发器，以后做题时，先问自己这 5 个问题：
 *
 * 这是不是“对象之间有先后依赖”？
 * 能不能把对象看成点、依赖看成边？
 * 题目是不是在问“能否全部完成 / 是否有合法顺序”？
 * 这是不是等价于“图中是否有环”？
 * 我是用入度 + 队列，还是用 DFS 染色判环？
 *
 * 如果这 5 个问题一路都能答“是”，基本就是拓扑排序题。
 *
 * 文字题意 -> 依赖关系 -> 有向图 -> 判环 / 拓扑序 -> 入度法或 DFS 染色法
 *
 * 两种方法的直觉对比：
 * 1. 入度法：从外往里剥，谁当前没有依赖，就先学谁
 * 2. DFS 染色法：沿依赖链往下走，看会不会绕回当前路径上的节点
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_207 {

    public static boolean topologicalSort(int numCourses, int[][] prerequisites) {
        // 邻接表：pre -> course，表示先学 pre，才能学 course
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // 入度表：记录每门课还剩多少前置依赖
        int[] inDegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int pre = prerequisite[1];
            graph.get(pre).add(course);
            inDegree[course]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int course = 0; course < numCourses; course++) {
            if (inDegree[course] == 0) {
                queue.offer(course); // 入度为 0，表示当前没有依赖，可以先学
            }
        }

        int count = 0; // 记录已经完成学习的课程数量
        while (!queue.isEmpty()) {
            int currentCourse = queue.poll();
            count++;

            for (int next : graph.get(currentCourse)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        return count == numCourses;
    }

    public static boolean dfsDetectCycle(int numCourses, int[][] prerequisites) {
        // 邻接表：pre -> course，表示先学 pre，才能学 course
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int pre = prerequisite[1];
            graph.get(pre).add(course);
        }

        // DFS 染色法思路：
        // 1. 沿着当前课程的依赖链一路往下搜索
        // 2. 如果搜索过程中又回到“当前递归路径上”的节点，说明图中有环
        // 3. 只要存在环，就不可能完成所有课程
        // 0：未访问，1：访问中，2：已完成
        int[] visited = new int[numCourses];

        // 图可能不是连通的，所以每门课都要尝试作为 DFS 起点
        for (int course = 0; course < numCourses; course++) {
            if (hasCycle(graph, visited, course)) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasCycle(List<List<Integer>> graph, int[] visited, int course) {
        // 遇到访问中的节点，说明绕回了当前递归路径上的祖先节点，存在环
        if (visited[course] == 1) {
            return true;
        }

        // 遇到已完成节点，说明它的后续路径之前已经验证过无环，直接复用结果
        if (visited[course] == 2) {
            return false;
        }

        // 进入当前节点，先标记为访问中
        visited[course] = 1;
        for (int next : graph.get(course)) {
            if (hasCycle(graph, visited, next)) {
                return true;
            }
        }

        // 当前节点所有后继都检查完，没有发现环，标记为已完成
        visited[course] = 2;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(topologicalSort(2, new int[][]{{1, 0}})); // true
        System.out.println(topologicalSort(2, new int[][]{{1, 0}, {0, 1}})); // false

        System.out.println(dfsDetectCycle(2, new int[][]{{1, 0}})); // true
        System.out.println(dfsDetectCycle(2, new int[][]{{1, 0}, {0, 1}})); // false
    }
}
