package no2_twopointertechnique.practice.stage2;

/**
 * @ClassName LeetCode_75
 * @Description 题目: 给定一个包含红色、白色和蓝色（用 0、1 和 2 分别表示）共 n 个元素的数组 nums，
 * 原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 * <p>
 * 进阶：你能想出一个仅使用常数空间的一趟扫描算法吗？（荷兰国旗问题，三指针）
 * @Author gm
 * @Date 2026/4/2 15:00
 */
public class LeetCode_75 {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
//        int[] nums = new int[]{2, 0, 1};
//        LeetCode_75.sort(nums);  简单的排序
        LeetCode_75.twoPointer(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    private static void sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
    }

    /**
     * 维护三个指针，把数组划分成四个区间：
     * <p>
     * [0, left)    → 全是 0
     * [left, cur)  → 全是 1
     * [cur, right] → 未处理
     * (right, end] → 全是 2
     * <p>
     * nums[cur]	操作
     * 0	        和 left 位置交换，left++，cur++
     * 1	        不动，cur++
     * 2	        和 right 位置交换，right--，cur 不动
     * <p>
     * 关键：遇 0 换左、遇 2 换右、遇 1 跳过，以及换右时 cur 不前进。
     * <p>
     * 目标：分三段
     * ↓
     * 从两段问题（两种颜色）找灵感：一个指针 + 遍历
     * ↓
     * 三段 → 左右各一个指针，中间一个遍历指针
     * ↓
     * 想清楚交换后 cur 动不动 → 左交换动，右交换不动
     * left：下一个 0 应该放的位置
     * right：下一个 2 应该放的位置
     * cur：当前正在看的元素
     *
     * @param nums
     */
    private static void twoPointer(int[] nums) {
        int left = 0, right = nums.length - 1, cur = 0;
        while (cur <= right) {
            if (nums[cur] == 0) {
                //需要和左边交换
                int tmp = nums[cur];
                nums[cur] = nums[left];
                nums[left] = tmp;
                left++;
                cur++;
            } else if (nums[cur] == 2) {
                //需要和右边交换
                int tmp = nums[cur];
                nums[cur] = nums[right];
                nums[right] = tmp;
                right--;    //左边是扫描过来的 已经算有序了 右边丢过来的不知道是什么  cur不能动
            } else {
                cur++;
            }
        }
    }
}
