package no7_binarysearch.practice.stage1;

/**
 * @ClassName LeetCode_35
 * @Description
 * 题目: 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * 示例1:
 * 输入：nums = [1,3,5,6], target = 5
 * 输出：2
 * <p>
 * 示例2:
 * 输入：nums = [1,3,5,6], target = 2
 * 输出：1
 * <p>
 * 示例3:
 * 输入：nums = [1,3,5,6], target = 7
 * 输出：4
 * <p>
 * 提示:
 * 1 <= nums.length <= 10^4;
 * -10^4 <= nums[i] <= 10^4;
 * nums 为无重复元素的升序排列数组;
 * -10^4 <= target <= 10^4
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_35 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};

        System.out.println("===== 保留原答案，观察结果 =====");
        System.out.println("target = 5，原答案返回：" + binarySearch(nums, 5));  // 2
        System.out.println("target = 2，原答案返回：" + binarySearch(nums, 2));  // 1
        System.out.println("target = 7，原答案返回：" + binarySearch(nums, 7));  // 4
        System.out.println("target = 0，原答案返回：" + binarySearch(nums, 0));  // 错误，正确应为 0
        System.out.println("target = 1，原答案返回：" + binarySearch(nums, 1));  // 错误，正确应为 0
        System.out.println("target = 6，原答案返回：" + binarySearch(nums, 6));  // 错误，正确应为 3

        System.out.println("===== 标准答案结果 =====");
        System.out.println("target = 5，标准答案返回：" + searchInsert(nums, 5));  // 2
        System.out.println("target = 2，标准答案返回：" + searchInsert(nums, 2));  // 1
        System.out.println("target = 7，标准答案返回：" + searchInsert(nums, 7));  // 4
        System.out.println("target = 0，标准答案返回：" + searchInsert(nums, 0));  // 0
        System.out.println("target = 1，标准答案返回：" + searchInsert(nums, 1));  // 0
        System.out.println("target = 6，标准答案返回：" + searchInsert(nums, 6));  // 3
    }

    /**
     * 保留你的原答案，方便对照错误点。
     *
     * 错误原因：
     * 1. while (low < high) 会在区间只剩一个元素时直接退出，漏掉最后一个位置的判断。
     * 2. 循环结束后，low 不一定停在“小于 target 的位置”，也可能停在等于或大于 target 的位置。
     * 3. 因此 return low + 1 这个结论不成立，会导致部分边界情况返回错误。
     *
     * 从这个错误方法，如何顺畅过渡到标准答案：
     * 1. 你原本的目标，其实不是“只找 target 是否存在”，而是“找到 target 应该在的位置”。
     * 2. 一旦把题目重新表述为“找插入位置”，就更适合继续改写成：
     *    找到数组中第一个大于等于 target 的位置。
     * 3. 这样二分的分支逻辑就会自然变成：
     *    如果 nums[mid] < target，答案一定在右边，low = mid + 1。
     *    如果 nums[mid] >= target，mid 可能就是答案，但左边也可能还有更靠前的位置，所以 high = mid - 1。
     * 4. 当问题被定义为“找边界”之后，循环结束时的 low 就是被二分过程推出来的最终答案，而不是事后猜一个 low + 1。
     *
     * 这里反映出的错误认知：
     * 1. 把“找目标值”和“找插入边界”混在一起了。
     *    找值时，常见做法是命中后直接返回。
     *    找边界时，更重要的是维护区间含义，而不是一命中就结束。
     * 2. 过早给 low 下了结论。
     *    你默认 low 最终一定落在“小于 target 的最后一个位置”。
     *    但这个结论并不是循环天然保证的，需要通过循环不变量来证明。
     * 3. 只看过程，不看收尾。
     *    你关注了 low 和 high 在循环中如何移动，却没有严格分析循环结束时 low、high 各自表示什么。
     *
     * 这里反映出的常见惯性：
     * 1. 普通二分模板套用惯性。
     *    看到有序数组，就先写“相等返回，不等继续缩区间”的标准查找模板。
     * 2. 后处理补答案的惯性。
     *    当前面的循环没有直接产出答案时，就容易在最后补一个 low + 1 或 high - 1。
     *    这种经验式返回最容易在边界条件上出错。
     * 3. 样例驱动过强，边界驱动不足。
     *    5、2、7 这些样例能过，并不代表逻辑正确。
     *    真正应该主动补的是：比最小值小、等于最小值、等于最大值、插到末尾 这几类边界。
     *
     * 以后做这类题，建议先问自己三个问题：
     * 1. 我到底是在找“某个具体值”，还是在找“某个边界位置”？
     * 2. low 和 high 在每一轮循环后，分别排除了哪一部分区间？
     * 3. 循环结束时，为什么返回 low 是对的，而不是 low + 1、high 或 high + 1？
     */
    public static int binarySearch(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        // 这里假设 low 最终一定停在小于 target 的位置，但这个假设是错误的。
        return low + 1;
    }

    /**
     * 标准答案：
     * 本题本质上是查找“第一个大于等于 target 的位置”。
     * 循环结束后，low 正好就是目标值应该插入的位置。
     *
     * 和上面的错误写法相比，标准答案的关键不在于技巧更复杂，
     * 而在于从一开始就把答案定义成一个明确边界。
     *
     * 这样写的好处：
     * 1. nums[mid] == target 时，不必立即返回，因为当前 mid 已经满足“大于等于 target”，
     *    但左边也许还有更靠前的满足位置，所以继续向左收缩。
     * 2. 整个过程都在维护“答案边界”，因此循环结束后无需额外猜测，直接返回 low 即可。
     *
     * 二分边界题通用心法：
     * 1. 先别急着写代码，先判断这题是“找值”还是“找边界”。
     *    找值：通常是 nums[mid] == target 直接返回。
     *    找边界：通常不能急着返回，而是要继续收缩区间。
     * 2. 把题目翻译成一句边界语言。
     *    常见翻译方式有：
     *    第一个大于等于 target 的位置。
     *    第一个大于 target 的位置。
     *    最后一个小于等于 target 的位置。
     *    最后一个小于 target 的位置。
     * 3. 明确循环不变量。
     *    每轮循环结束后，要能解释 low 左边一定是什么，high 右边一定是什么。
     *    如果这句话说不清，说明二分还没有真正写明白。
     * 4. 最后先想返回值，再写循环。
     *    如果你说不清为什么最终返回 low 或 high，那大概率会写出 low + 1 这种经验式补丁。
     * 5. 样例验证要主动压边界。
     *    至少检查：比最小值小、等于最小值、落在中间、等于最大值、比最大值大。
     *
     * 这一题可以沉淀成一个模板：
     * 目标：找第一个大于等于 target 的位置。
     * 判断：
     * 如果 nums[mid] < target，答案在右边。
     * 如果 nums[mid] >= target，mid 可能就是答案，继续压左边界。
     * 结束：
     * 循环结束后 low 就是答案。
     */
    public static int searchInsert(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}
