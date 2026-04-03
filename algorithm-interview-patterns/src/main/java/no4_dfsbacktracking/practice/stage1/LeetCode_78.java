package no4_dfsbacktracking.practice.stage1;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_78
 * @Description 子集
 * 题目: 给你一个整数数组 nums，数组中的元素互不相同。返回该数组所有可能的子集（幂集）。解集不能包含重复的子集。
 * 示例:
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 提示:
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素互不相同
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_78 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(backtrack(nums));
    }

    public static List<List<Integer>> backtrack(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int startIndex = 0;
        LeetCode_78.dfs(nums, path, startIndex, result);
        return result;
    }

    /**
     * 1.子集 vs 全排列，有什么不同？
     * 全排列 [1,2,3] → [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]
     * 子集   [1,2,3] → [], [1], [2], [3], [1,2], [1,3], [2,3], [1,2,3]
     *
     * 关键区别：
     * 全排列：[1,2] 和 [2,1] 是不同的结果（顺序有关）
     * 子集：[1,2] 和 [2,1] 是相同的子集（顺序无关）
     * 那怎么避免重复？→ 每次只往后选，不回头！ 用 startIndex 控制。
     *
     * 2.对照模板，明确四个要素
     * 模板要素	                          全排列（你已经会了 ✅）	                   子集（现在要做的）
     * 路径 path	                          当前已选的数字	                           当前已选的数字（一样）
     * 终止条件	                          path.size() == nums.length	           ⭐ 没有终止条件！每个节点都收集
     * 选择列表	                          每次从 i=0 开始，跳过 used	               每次从 startIndex 开始，只往后选
     * 剪枝	                              used[i] == true 跳过	                    不需要剪枝（startIndex 自动避免重复）
     *
     * 3.决策树
     *                        []              ← 收集 []
     *                 /       |       \
     *               [1]      [2]      [3]    ← 收集 [1],[2],[3]
     *              /   \      |
     *           [1,2] [1,3]  [2,3]          ← 收集 [1,2],[1,3],[2,3]
     *            |
     *         [1,2,3]                        ← 收集 [1,2,3]
     *
     * 注意看：
     * - 选了 1 之后，只能选 2, 3（往后选）
     * - 选了 2 之后，只能选 3（往后选）
     * - 选了 3 之后，没得选了
     * - 每个节点都是一个合法的子集！ 所以不需要终止条件判断，进入函数就收集。
     *
     * 4.和全排列代码对比
     * // ========= 全排列（你写的） =========
     * private static void dfs(...) {
     *     if (path.size() == nums.length) {   // 选满才收集
     *         result.add(new ArrayList<>(path));
     *     }
     *     for (int i = 0; i < nums.length; i++) {  // 每次从0开始
     *         if (used[i]) continue;               // 用used剪枝
     *         path.add(nums[i]);
     *         used[i] = true;
     *         dfs(nums, path, used, result);
     *         path.remove(path.size() - 1);
     *         used[i] = false;
     *     }
     * }
     *
     * // ========= 子集（你要写的） =========
     * private static void dfs(..., int startIndex) {
     *     result.add(new ArrayList<>(path));        // ⭐ 进来就收集！每个节点都是子集
     *
     *     for (int i = startIndex; i < nums.length; i++) {  // ⭐ 从startIndex开始，不回头！
     *         // 不需要剪枝
     *         path.add(nums[i]);                   // 做选择
     *         dfs(nums, path, result, i + 1);      // ⭐ 递归传 i+1，往后走
     *         path.remove(path.size() - 1);        // 撤销选择
     *     }
     * }
     *
     * 只有 3 处不同，我标了 ⭐：
     * #	           全排列	                     子集
     * 1	           选满才收集	                     进来就收集
     * 2	           i = 0 开始 + used 数组	     i = startIndex 开始，不需要 used
     * 3	           递归传 nums, path, used	     递归传 i + 1
     * @param nums
     * @param path
     * @param startIndex
     * @param result
     */
    private static void dfs(int[] nums, List<Integer> path, int startIndex, List<List<Integer>> result) {
        //1.终止条件？ 进来就收集
        result.add(new ArrayList<>(path));
        //2.for循环  选择列表是什么？  [startIndex,n]
        for (int i = startIndex; i < nums.length; i++) {   //从startIndex开始 不需要剪枝
            //1.剪枝  不需要
            //2.做选择
            path.add(nums[i]);
            //3.递归  i -> i+1
            LeetCode_78.dfs(nums, path, i + 1, result);
            //4.撤销选择
            path.remove(path.size() - 1);
        }
    }
}
