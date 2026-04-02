package no11_topologicalsort.practice.stage1;

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
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_207 {

    public static boolean topologicalSort(int numCourses, int[][] prerequisites) {
        // TODO
        return false;
    }

    public static void main(String[] args) {
        System.out.println(topologicalSort(2, new int[][]{{1, 0}})); // true
        System.out.println(topologicalSort(2, new int[][]{{1, 0}, {0, 1}})); // false
    }
}
