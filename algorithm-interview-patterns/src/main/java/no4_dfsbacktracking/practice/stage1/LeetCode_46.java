package no4_dfsbacktracking.practice.stage1;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_46
 * @Description 全排列
 * 题目: 给定一个不含重复数字的数组 nums，返回其所有可能的全排列。
 * 示例:
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 提示:
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数互不相同
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_46 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(backtrack(nums));
    }


    /**
     * backTrack(路径, 选择列表)
     * ├── 1. 终止条件 → 收集结果
     * └── 2. for 循环遍历选择列表
     * ├── (1) 剪枝
     * ├── (2) 做选择
     * ├── (3) 递归
     * └── (4) 撤销选择
     * <p>
     * 模板要素	         全排列中的具体含义
     * 路径 path	         当前已选的数字，如 [1, 2]
     * 选择列表	         nums 全部数字，但要跳过已用过的
     * 终止条件	         path.size() == nums.length（选满了）
     * 剪枝	             used[i] == true → 这个数已经用过，跳过
     * <p>
     *           []
     * /        |        \
     * [1]       [2]       [3]
     * /   \     /   \     /   \
     * [1,2] [1,3] [2,1] [2,3] [3,1] [3,2]
     * |     |     |     |     |     |
     * [1,2,3][1,3,2][2,1,3][2,3,1][3,1,2][3,2,1]  ← 全部到达终止条件，收集结果！
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> backtrack(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        // TODO: 实现回溯逻辑
        LeetCode_46.dfs(nums, path, used, result);
        return result;
    }

    private static void dfs(int[] nums, List<Integer> path, boolean[] used, List<List<Integer>> result) {
        //1.终止条件  path长度 == nums长度
        if (path.size() == nums.length) {
            //收集结果
            result.add(new ArrayList<>(path));//深拷贝
        }
        //2.for循环  循环遍历选择列表 （每次从0开始，排列需要遍布所有位置）
        for (int i = 0; i < nums.length; i++) {
            //(1)剪枝
            if (used[i]) continue;
            //(2)做选择
            path.add(nums[i]);
            used[i] = true;
            //(3)递归
            LeetCode_46.dfs(nums, path, used, result);
            //(4)撤销选择
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    /**
     * 🤔 先想一个生活场景
     * 想象你站在一个迷宫的入口，有 3 条路可以走：
     * 你选了第 1 条路 → 走到尽头发现是死路 → 你怎么办？ → 你会退回来（撤销选择），再试第 2 条路！
     * 递归 = 往前走，撤销选择 = 退回来。 这就是回溯。
     *
     * 第1次进入 dfs:  path=[], used=[F,F,F]
     * │
     * ├─ i=0: 选1 → path=[1], used=[T,F,F]
     * │  │
     * │  第2次进入 dfs:  path=[1], used=[T,F,F]
     * │  │
     * │  ├─ i=0: used[0]=T，已用过，跳过 ❌
     * │  ├─ i=1: 选2 → path=[1,2], used=[T,T,F]
     * │  │  │
     * │  │  第3次进入 dfs:  path=[1,2], used=[T,T,F]
     * │  │  │
     * │  │  ├─ i=0: used[0]=T，跳过 ❌
     * │  │  ├─ i=1: used[1]=T，跳过 ❌
     * │  │  ├─ i=2: 选3 → path=[1,2,3], used=[T,T,T]
     * │  │  │  │
     * │  │  │  第4次进入 dfs:  path=[1,2,3]
     * │  │  │  path.size()==3 ✅ 终止！收集结果 → result=[[1,2,3]]
     * │  │  │  return 回到第3次
     * │  │  │
     * │  │  │  ⭐ 撤销选择: path=[1,2], used=[T,T,F]  ← 把3拿走，退回来！
     * │  │  │
     * │  │  └─ for循环结束，return 回到第2次
     * │  │
     * │  │  ⭐ 撤销选择: path=[1], used=[T,F,F]  ← 把2拿走，退回来！
     * │  │
     * │  ├─ i=2: 选3 → path=[1,3], used=[T,F,T]  ← 退回来后，尝试另一条路！
     * │  │  │
     * │  │  ... 最终得到 [1,3,2]，收集结果
     * │  │
     * │  │  ⭐ 撤销选择: path=[1], used=[T,F,F]
     * │  │
     * │  └─ for循环结束，return 回到第1次
     * │
     * │  ⭐ 撤销选择: path=[], used=[F,F,F]  ← 把1也拿走！
     * │
     * ├─ i=1: 选2 → path=[2], used=[F,T,F]  ← 开始尝试以2开头的排列...
     * │  ...
     *
     * ❓ 为什么需要撤销选择？
     * 看这个关键时刻：
     * path = [1, 2, 3]  ← 收集完结果了
     *
     * 如果不撤销，path 一直是 [1,2,3]，你永远得不到 [1,3,2]、[2,1,3] 这些答案！
     *
     *                     有撤销                          没撤销
     *
     * 收集 [1,2,3] 后:                        收集 [1,2,3] 后:
     * path=[1,2,3]                            path=[1,2,3]
     *    ↓ 撤销3                                 ↓ 不撤销
     * path=[1,2]                              path=[1,2,3]
     *    ↓ 撤销2                              size已经==3，后面全部终止
     * path=[1]                                再也选不了别的了 ❌
     *    ↓ 选3
     * path=[1,3]     ← 可以走新路了！✅        结果永远只有 [[1,2,3]] ❌
     *    ↓ 选2
     * path=[1,3,2]   ← 又一个排列！✅
     *
     * 📌 一句话总结
     *                        作用	                      类比
     * 递归	                  往深处走，自动处理任意层选择	   迷宫里往前走
     * 撤销选择	               退回上一步，尝试其他可能	        走到死路退回来换条路走
     *
     * 🔥 没有递归，你走不远；没有撤销，你回不来。两者缺一不可！
     *
     */
}
