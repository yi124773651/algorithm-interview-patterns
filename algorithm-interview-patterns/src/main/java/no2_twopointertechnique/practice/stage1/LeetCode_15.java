package no2_twopointertechnique.practice.stage1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName LeetCode_15
 * @Description 题目: 找出数组中所有和为0的三元组，不重复。
 * 输入: [-1,0,1,2,-1,-4]  输出: [[-1,-1,2],[-1,0,1]]
 * @Author gm
 * @Date 2026/4/2 14:50
 */
public class LeetCode_15 {
    public static void main(String[] args) {
        int[] input = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = LeetCode_15.twoPointer(input);
        System.out.println(result);
    }

    private static List<List<Integer>> twoPointer(int[] nums) {
        Arrays.sort(nums);//排序 很重要
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) continue; //重复元素需要跳过
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int total = nums[i] + nums[left] + nums[right];
                if (total > 0) {
                    right--;
                } else if (total < 0) {
                    left++;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left + 1] == nums[left]) left++;
                    while (left < right && nums[right - 1] == nums[right]) right--;
                    left++;
                    right--;
                }
            }
        }
        return result;
    }
}
