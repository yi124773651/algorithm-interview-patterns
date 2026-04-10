package no7_binarysearch.practice.stage2;

/**
 * @ClassName LeetCode_33
 * @Description 题目: 整数数组 nums 按升序排列，数组中的值互不相同。在传递给函数之前，nums 在预先未知的某个下标 k 上进行了旋转。
 * 给你旋转后的数组 nums 和一个整数 target，如果 nums 中存在这个目标值，则返回它的下标，否则返回 -1。
 * 你必须设计一个时间复杂度为 O(log n) 的算法。
 * <p>
 * 示例1:
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * <p>
 * 示例2:
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * <p>
 * 提示:
 * 1 <= nums.length <= 5000;
 * -10^4 <= nums[i] <= 10^4;
 * nums 中的每个值都独一无二;
 * nums 肯定会在某个点上旋转
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_33 {
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int[] counterExample = {1, 2, 3, 0};

        System.out.println("===== 保留原思路，观察错误结果 =====");
        System.out.println("target = 0，原思路返回：" + binarySearchWrong(nums, 0));  // 4
        System.out.println("target = 3，原思路返回：" + binarySearchWrong(nums, 3));  // -1
        System.out.println("反例 target = 3，原思路返回：" + binarySearchWrong(counterExample, 3));  // 错误，正确应为 2

        System.out.println("===== 标准答案结果 =====");
        System.out.println("target = 0，标准答案返回：" + binarySearch(nums, 0));  // 4
        System.out.println("target = 3，标准答案返回：" + binarySearch(nums, 3));  // -1
        System.out.println("反例 target = 3，标准答案返回：" + binarySearch(counterExample, 3));  // 2
    }

    /**
     * 保留你的原思路，方便对照错误点。
     *
     * 根因分析：
     * 1. 你意识到旋转数组不是整体有序，而是被切成两段有序，这个方向是对的。
     * 2. 但代码没有先判断“左半边有序还是右半边有序”，而是直接根据 nums[mid] 和 target 的大小关系，
     *    再去比较 nums[low] 或 nums[high] 与 target 的大小。
     * 3. 这样做的问题是：你并没有先确认当前应该依赖哪一段有序区间，因此收缩方向可能错。
     *
     * 反例：
     * nums = [1, 2, 3, 0], target = 3
     * 第一次循环：
     * low = 0, high = 3, mid = 1, nums[mid] = 2
     * 因为 nums[mid] < target，进入下面的 else 分支；
     * 又因为 nums[high] = 0 < 3，于是你把 high = mid - 1。
     * 这一步直接把真正答案 index = 2 所在的右半边丢掉了。
     *
     * 从这个错误方法，如何顺畅过渡到标准答案：
     * 1. 你原本已经发现“整体不有序，但局部有序”，差的只是再往前走一步：
     *    每轮先判断哪一半有序。
     * 2. 一旦知道左半边有序，就判断 target 是否落在 [nums[low], nums[mid]) 之间。
     *    如果落在其中，去左边；否则去右边。
     * 3. 一旦知道右半边有序，就判断 target 是否落在 (nums[mid], nums[high]] 之间。
     *    如果落在其中，去右边；否则去左边。
     * 4. 这样每一次收缩，都是基于“有序区间 + target 是否落在该区间”做决策，
     *    而不是基于几个数值大小关系做经验判断。
     *
     * 这里反映出的错误认知：
     * 1. 认为 nums[mid] 和 target 的大小关系，足以决定往哪边走。
     *    在普通有序数组里，这成立；在旋转数组里，这不够。
     * 2. 把 nums[low] 或 nums[high] 当成了稳定参照物。
     *    实际上在旋转数组中，它们所在的一侧是否有序，需要先判断，不能直接拿来推方向。
     * 3. 只抓住了“分成两段有序”，但没有把这个性质落实成“先找有序半边，再判断 target 是否落入”。
     *
     * 这里反映出的常见惯性：
     * 1. 把普通二分的比较逻辑，直接平移到旋转数组。
     * 2. 看到 low、mid、high 三个点，就急着比较数值大小，而不是先确认区间结构。
     * 3. 过样例后容易停止，但没有主动补“短数组 + 旋转点靠后 + target 落在被切开的有序段”这种反例。
     *
     * 以后做旋转数组二分，建议先问自己三个问题：
     * 1. 当前 low..mid 和 mid..high，哪一半一定有序？
     * 2. target 是否落在这段有序区间里？
     * 3. 如果不在这段有序区间里，另一半就一定要保留，凭什么？
     */
    public static int binarySearchWrong(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                if (nums[low] > target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else {
                if (nums[high] < target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 标准答案：
     * 每轮至少有一半区间是有序的，先识别哪一半有序，再判断 target 是否落在这段有序区间中。
     *
     * 为什么这才是稳定写法：
     * 1. 决策依据不是“某几个数谁大谁小”，而是“哪一半有序 + target 是否在该有序区间内”。
     * 2. 一旦判断出有序半边，就能像普通二分那样安全排除另一半，不会误删答案。
     *
     * 旋转数组二分通用心法：
     * 1. 先别急着比较 target 和 nums[mid] 的大小，先判断哪一半有序。
     * 2. 左半边有序的判定条件通常是 nums[low] <= nums[mid]。
     * 3. 右半边有序的判定条件通常是 nums[mid] <= nums[high]。
     * 4. 判断出有序半边后，再判断 target 是否落在这段有序区间内。
     * 5. 只要 target 不在当前有序半边里，就去另一半，逻辑会稳定很多。
     *
     * 这题模板可以记成：
     * 1. 如果左半边有序：
     *    target 在左半边范围内，high = mid - 1；
     *    否则 low = mid + 1。
     * 2. 如果右半边有序：
     *    target 在右半边范围内，low = mid + 1；
     *    否则 high = mid - 1。
     */
    public static int binarySearch(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[low] <= nums[mid]) {  //左半边有序
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {//右半边有序
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
