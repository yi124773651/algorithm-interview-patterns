package no6_greedy.practice.stage2;

/**
 * @ClassName LeetCode_55
 * @Description 跳跃游戏
 * 题目: 给你一个非负整数数组 nums，你最初位于数组的第一个下标。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 * <p>
 * 示例1:
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 示例2:
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * <p>
 * 提示:
 * 1 <= nums.length <= 10^4;
 * 0 <= nums[i] <= 10^5
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_55 {

    public static void main(String[] args) {
        LeetCode_55 solution = new LeetCode_55();

        System.out.println("=== 原始解法(有Bug) ===");
        System.out.println(solution.greedy_original(new int[]{2, 3, 1, 1, 4})); // 期望true
        System.out.println(solution.greedy_original(new int[]{3, 2, 1, 0, 4})); // 期望false
        System.out.println(solution.greedy_original(new int[]{5, 0, 0, 0, 0})); // 期望true，实际false ❌
        System.out.println(solution.greedy_original(new int[]{1, 2, 0, 0, 4})); // 期望true，实际false ❌

        System.out.println("=== 正确解法 ===");
        System.out.println(solution.greedy(new int[]{2, 3, 1, 1, 4})); // true ✅
        System.out.println(solution.greedy(new int[]{3, 2, 1, 0, 4})); // false ✅
        System.out.println(solution.greedy(new int[]{5, 0, 0, 0, 0})); // true ✅
        System.out.println(solution.greedy(new int[]{1, 2, 0, 0, 4})); // true ✅
    }

    /**
     * 原始解法（有Bug）
     * Bug1: 每次直接跳nums[i]步，但nums[i]是"最大"跳跃长度，不是"必须"跳那么远
     * 没有读懂题目意思：nums[i]是"最大"跳跃长度，不是"必须"跳那么远
     * Bug2: nums[i]+i > length-1 时应该是能到终点(return true)，却当失败处理了
     */
    public boolean greedy_original(int[] nums) {
        boolean result = false;
        for (int i = 0; i < nums.length;) {
            if (i == nums.length - 1) {
                result = true;
                break;
            }
            if (nums[i] == 0 || nums[i] + i > nums.length - 1) {
                break;
            }
            i = nums[i] + i;
        }
        return result;
    }

    /**
     * 正确解法：维护可达边界（模式四）
     * 思路：不纠结"跳到哪个具体位置"，而是维护"最远能到达的位置"
     *       遍历每个位置，如果当前位置可达，就用 i + nums[i] 更新最远边界
     *       如果最远边界 >= 终点，直接返回 true
     *       如果当前位置超过最远边界，说明走不到这里，返回 false
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public boolean greedy(int[] nums) {
        int maxReach = 0; // 当前能到达的最远位置
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) return false;           // 到不了当前位置，失败
            maxReach = Math.max(maxReach, i + nums[i]); // 更新最远可达
            if (maxReach >= nums.length - 1) return true; // 能到终点，提前返回
        }
        return true;
    }


    /**
     * 第一步：你原来的理解
     * nums[i] = 必须跳的步数（固定路径）
     * 位置:  0 → 2 → 3 → 4     只有一条路，走不通就失败
     *
     * 这本质上是模拟一条确定路径，不需要贪心。
     *
     * 第二步：现在你明白了 nums[i] 是"最大长度"
     * nums = [2, 3, 1, 1, 4]
     * 在位置0，nums[0]=2，可以跳1步或2步
     *
     *                  ┌→ 位置1 → 又可以跳1/2/3步 → ...
     *   位置0 ─────────┤
     *                  └→ 位置2 → 又可以跳1步 → ...
     *
     *  到这里你自然会想：每个位置都有多种选择，我该跳到哪？
     *  这就变成了一棵决策树，暴力解是 DFS/BFS，时间复杂度爆炸。
     *
     * 第三步：关键转换——从"选哪条路"到"能到哪里"
     *
     * 这一步是思维跳跃的关键。问自己一个问题：
     * 题目问的是什么？ 问的是"能不能到终点"，不是"走哪条路到终点"。
     * 既然不关心具体路径，那就不需要选择跳到哪。
     * 只需要知道：从位置 0 出发，所有能到达的位置有哪些？
     *
     * nums = [2, 3, 1, 1, 4]
     *
     * i=0: nums[0]=2, 能跳到 {1, 2}     → 最远到 2
     *      那位置 0、1、2 都是"可达的"
     *
     * i=1: 位置1可达，nums[1]=3, 能跳到 {2,3,4} → 最远到 4
     *      那位置 0、1、2、3、4 都是"可达的"
     *
     * 4 >= 终点 → true！
     *
     * 你看，根本不需要做选择。遍历每个可达位置，不断扩展"最远能到哪"就行了：
     * i=0          i=1          i=2          i=3          i=4
     *  ├───reach=2──┤
     *  ├──────────────────reach=4──────────────────────────┤ ✅ 覆盖终点
     *
     *  总结：思维过渡路线图
     *  你的原始思路                  正确思路
     * ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
     *
     * nums[i]是固定步数       →    nums[i]是最大步数
     *     ↓                            ↓
     * 只有一条路可走          →    每个位置有多种选择
     *     ↓                            ↓
     * 模拟具体路径            →    暴力需要DFS/BFS（太慢）
     *                                  ↓
     *                     💡 题目只问"能不能到"，不问"怎么到"
     *                                  ↓
     *                          不需要选具体路径
     *                                  ↓
     *                          只维护"最远能到哪"
     *                                  ↓
     *                             maxReach ✅
     *
     * 核心领悟：
     * 当题目只关心可达性、不关心具体路径时，
     * 把"选择跳到哪"转化为"最远能覆盖到哪"，
     * 多种选择就被一个 max 操作替代了——这正是贪心能生效的原因。
     *
     */
}
