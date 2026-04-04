package no7_binarysearch.practice.stage2;

/**
 * @ClassName LeetCode_34
 * @Description 题目: 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。你必须设计并实现时间复杂度为 O(log n) 的算法。
 * 示例1:
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * <p>
 * 示例2:
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * <p>
 * 提示:
 * 0 <= nums.length <= 10^5;
 * -10^9 <= nums[i] <= 10^9; nums 是一个非递减数组;
 * -10^9 <= target <= 10^9
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_34 {
    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] single = {0};

        System.out.println("===== 保留错误二分，观察问题 =====");
        print(binarySearchWrong(nums, 8));      // [3,4]，样例碰巧正确
        print(binarySearchWrong(single, 0));    // 错误，正确应为 [0,0]

        System.out.println("===== 你的另外一种方法 =====");
        print(another(nums, 8));                // [3,4]
        print(another(nums, 6));                // [-1,-1]
        print(another(single, 0));              // [0,0]

        System.out.println("===== 标准二分答案 =====");
        print(binarySearch(nums, 8));           // [3,4]
        print(binarySearch(nums, 6));           // [-1,-1]
        print(binarySearch(single, 0));         // [0,0]
    }

    private static void print(int[] result) {
        System.out.println("[" + result[0] + "," + result[1] + "]");
    }

    /**
     * 保留你原来的二分思路，方便对照错误点。
     *
     * 根因分析：
     * 1. 你已经意识到本题本质上不是“找一个值”，而是“找左右边界”，这个方向其实是对的。
     * 2. 但你当前写法有两个关键问题：
     *    第一，区间收缩不严格。
     *    nums[mid] < target 时你写的是 low = mid；
     *    nums[mid] > target 时你写的是 high = mid。
     *    当 low 和 high 相邻时，mid 可能一直等于 low 或 high，区间不会继续缩小，容易死循环。
     * 3. 第二，命中 target 后又向左右线性扩散，时间复杂度退化成 O(n)，不满足题目要求。
     * 4. 第三，while (low < high) 会让单元素区间直接跳过循环。
     *    所以 nums = [0], target = 0 时，你的代码直接返回 [-1,-1]。
     *
     * 从你的错误二分，如何顺畅过渡到标准答案：
     * 1. 你注释里写的“最后一个小于 target”和“第一个大于 target”，其实已经非常接近标准思路了。
     * 2. 再往前走一步，就能把题目拆成两个独立的边界问题：
     *    找第一个大于等于 target 的位置。
     *    找第一个大于 target 的位置。
     * 3. 左边界找到后，如果 nums[left] != target，说明 target 根本不存在，直接返回 [-1,-1]。
     * 4. 如果存在，那么右边界就是“第一个大于 target 的位置 - 1”。
     * 5. 这样整题就不再需要命中后线性扩散，而是两个标准边界二分拼起来。
     *
     * 这里反映出的错误认知：
     * 1. 觉得边界题必须“先命中，再往两边找”。
     *    这是最常见的直觉，但它天然容易退化成线性扫描。
     * 2. 低估了“边界本身就可以直接二分出来”这件事。
     * 3. 只看到了 mid 与 target 的关系，没有把答案定义成一个明确的位置语义。
     *
     * 这里反映出的常见惯性：
     * 1. 普通二分模板的惯性，先想“找到 target”，再想“如何补全左右范围”。
     * 2. 后补边界的惯性，一旦命中，就习惯左右扩散。
     * 3. 对区间必须严格缩小这件事不够敏感，low = mid / high = mid 很容易埋下死循环。
     */
    public static int[] binarySearchWrong(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid;
            } else if (nums[mid] > target) {
                high = mid;
            } else {
                low = mid;
                high = mid;
                while (low >= 0 && nums[low] >= target) {
                    low--;
                }
                while (high < nums.length && nums[high] <= target) {
                    high++;
                }
                break;
            }
        }
        if (high - low > 0) {
            return new int[]{low + 1, high - 1};
        }
        return new int[]{-1, -1};
    }

    /**
     * 这是你“另外一种方法”。
     *
     * 评价：
     * 1. 逻辑上是对的，本质是在找：
     *    从左往右，第一个大于 target 的位置；
     *    从右往左，最后一个小于 target 的位置。
     * 2. 再把这两个位置夹出来，就得到了 target 的左右边界。
     * 3. 但它是双指针线性扫描，时间复杂度是 O(n)，不满足题目要求的 O(log n)。
     *
     * 它为什么值得保留：
     * 1. 说明你已经抓到了“边界题”的本质。
     * 2. 你现在缺的不是思路方向，而是把“线性找边界”升级成“二分找边界”。
     */
    public static int[] another(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low < nums.length && nums[low] <= target) {
            low++;
        }
        while (high >= 0 && nums[high] >= target) {
            high--;
        }
        if (low - high > 1) {
            return new int[]{high + 1, low - 1};
        }
        return new int[]{-1, -1};
    }

    /**
     * 标准答案：
     * 先找左边界，再找右边界。
     * 左边界 = 第一个大于等于 target 的位置。
     * 右边界 = 第一个大于 target 的位置 - 1。
     *
     * 二分边界题通用心法：
     * 1. 不要先想“命中 target 后怎么办”，先想“我要找的边界到底是什么”。
     * 2. 一旦能把问题翻译成：
     *    第一个大于等于 target 的位置
     *    第一个大于 target 的位置
     *    那么这题就已经成功一半了。
     * 3. 边界题的核心不是相等时立刻返回，而是继续收缩区间，逼出最左或最右边界。
     * 4. 循环结束后返回 low，通常是因为 low 停在“第一个满足条件的位置”。
     *
     * 这题模板可以记成：
     * left = lowerBound(nums, target)
     * right = upperBound(nums, target) - 1
     * 如果 left 越界，或者 nums[left] != target，说明不存在 target。
     */
    public static int[] binarySearch(int[] nums, int target) {
        int left = lowerBound(nums, target);
        if (left == nums.length || nums[left] != target) {
            return new int[]{-1, -1};
        }

        int right = upperBound(nums, target) - 1;
        return new int[]{left, right};
    }

    /**
     * 找到第一个大于等于 target 的位置。
     */
    private static int lowerBound(int[] nums, int target) {
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

    /**
     * 找到第一个大于 target 的位置。
     */
    private static int upperBound(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}
