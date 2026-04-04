package no6_greedy.practice.stage3;

/**
 * @ClassName LeetCode_45
 * @Description 跳跃游戏 II
 * 题目: 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
 * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。返回到达 nums[n - 1] 的最小跳跃次数。
 * <p>
 * 示例1: 输入：nums = [2,3,1,1,4] 输出：2
 * 示例2: 输入：nums = [2,3,0,1,4] 输出：2
 * <p>
 * 提示: 1 <= nums.length <= 10^4; 0 <= nums[i] <= 1000; 保证可以到达 nums[n - 1]
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_45 {

    public static void main(String[] args) {
        LeetCode_45 solution = new LeetCode_45();
        System.out.println(solution.greedy(new int[]{2, 3, 1, 1, 4})); // 旧写法示例: 2
        System.out.println(solution.greedy(new int[]{2, 3, 0, 1, 4})); // 旧写法示例: 2

        System.out.println(solution.greedyStandard(new int[]{2, 3, 1, 1, 4})); // 标准贪心: 2
        System.out.println(solution.greedyStandard(new int[]{2, 3, 0, 1, 4})); // 标准贪心: 2
        System.out.println(solution.greedyStandard(new int[]{0})); // 标准贪心: 0
    }

    /**
     * 保留原始写法，便于和标准贪心对照。
     *
     * 思路演进：
     * 1. 我原来的想法是：当前位置如果是 i，就枚举下一步所有可选位置 i + j。
     * 2. 然后比较每个候选位置的“后续最远可达距离” i + j + nums[i + j]。
     * 3. 谁的后续延伸能力更强，我就优先跳到谁。
     *
     * 这个想法本身并不完全错，因为它已经抓住了一个关键点：
     * 本题不能只看当前能跳多远，还要看跳过去之后的延伸能力。
     *
     * 但这版思路的问题在于：
     * 1. 它本质上还是在“选一个具体落点”，属于局部选点贪心。
     * 2. 本题要求的是最少步数，重点不是“这一跳最终落在哪个点”，
     *    而是“这一跳结束后，整体最远能把边界推进到哪里”。
     * 3. 当前这一步能到达的所有位置，在步数意义上其实属于同一层，
     *    既然同层位置的跳数相同，就不应该过早只选其中某一个点。
     *
     * 这版代码的具体缺陷：
     * 1. maxReached 跨轮次复用，既表示历史最远位置，又被拿来做本轮候选比较基准，语义混乱。
     * 2. 如果本轮所有候选位置都无法让 i + j + nums[i + j] 严格大于旧的 maxReached，
     *    offset 就会一直保持 0，最终导致 i 无法前进，出现死循环。
     * 3. 例如 [3,1,1,1,1]：第一轮后 maxReached 已经是 4，第二轮从下标 3 出发时，
     *    候选下标 4 的后续最远可达位置仍然是 4，不会更新 offset，循环无法结束。
     * 4. 例如 [0]：正确答案应为 0，但这版写法会直接进入循环并且无法前进。
     *
     * 要从这版思路顺畅过渡到标准贪心，关键是完成一个视角转换：
     * 1. 先承认原思路里正确的部分：我已经意识到不能只看当前能跳多远，
     *    还要看跳过去之后的延伸能力。
     * 2. 然后把观察对象从“某一个候选落点”升级为“当前这一跳能覆盖的整段区间”。
     * 3. 再把问题从“下一步跳到哪个点”改成“当前这一跳能把下一层边界推进到哪里”。
     * 4. 这样就能从“选点贪心”自然过渡到“按层推进区间”的标准贪心。
     *
     * 这道题还需要主动打破几个常见惯性：
     * 1. 不要认为贪心一定要立刻选出一个最优落点。
     *    这题真正的贪心不是选点，而是更新下一层最远边界。
     * 2. 不要认为当前站在哪个点最重要。
     *    对这题来说，更重要的是当前步数下整体最远能覆盖到哪里。
     * 3. 不要默认循环变量 i 就代表真实跳跃轨迹。
     *    在标准写法里，i 更像是在扫描当前层，而不是表示每次真实落脚的位置。
     * 4. 不要把“局部最优”简单理解成选出 i + nums[i] 最大的那个点。
     *    本题的局部最优，其实是当前层扫描完以后，下一层边界尽可能远。
     *
     * 结论：
     * 这版代码适合作为错误思路对照，帮助理解为什么本题最终要从“选点”升级为“按层推进区间”。
     */
    public int greedy(int[] nums) {
        /**
         * 分析：
         * 和之前那个跳跃略有不同  这里要求最小跳跃次数
         * [2,3,1,1,4]
         *
         * i = 0  可选1,2  但是 1+nums[1] = 4 > 2+nums[2] = 3
         * 所以应该选择 1
         *目标位置还是  nums.length-1  保证可以到达 nums[n - 1]
         *
         */
        //1排序预处理  不需要
        int minCount = 0;
        int target = nums.length - 1;
        int maxReached = 0;
        for (int i = 0; i < nums.length; ) {
            //位置 i  与nums[i] 关乎下一个节点位置
            minCount++;
            int offset = 0;
            for (int j = 1; j < nums[i] + 1; j++) {
                if (i + j > target) {
                    maxReached = target;
                    continue;
                }
                if (i + j + nums[i + j] > maxReached) {
                    maxReached = i + j + nums[i + j];
                    offset = j;
                }
            }
            if (maxReached > target) {
                break;
            }
            i += offset;
        }
        return minCount;
    }

    /**
     * 标准贪心。
     *
     * 正确思路：
     * 1. 这题要求的是最少跳跃次数，本质上更像“按步数分层推进”。
     * 2. 当前这一步能到达的所有位置，属于同一层，因为它们的跳数相同。
     * 3. 所以不需要在这一层里提前选定某一个具体落点，
     *    只需要扫描这一层，统计下一层最远能扩展到哪里。
     *
     * 核心变量：
     * 1. currentEnd 表示当前这一步最远能覆盖到哪里，也就是当前层的右边界。
     * 2. farthest 表示在当前覆盖区间内继续尝试后，下一步最远能到哪里。
     * 3. minCount 表示跳跃次数，只有当当前层扫描结束时才加 1。
     *
     * 执行过程：
     * 1. 从左到右扫描当前层中的每个位置。
     * 2. 扫描过程中持续更新 farthest。
     * 3. 当 i 走到 currentEnd，说明当前层已经扫描完毕，必须跳一次，
     *    然后把 currentEnd 更新为 farthest，进入下一层。
     *
     * 这种写法的本质不是“每次跳到某个具体下标”，而是“按层推进覆盖范围”。
     */
    public int greedyStandard(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        int minCount = 0;  //跳跃次数  扫描完当前层 才加1
        int currentEnd = 0;//这一步能最远到哪里
        int farthest = 0;//当前覆盖区尝试完毕后，下一步最远能到哪里

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            if (i == currentEnd) {
                minCount++;
                currentEnd = farthest;
            }
        }
        return minCount;
    }
}
