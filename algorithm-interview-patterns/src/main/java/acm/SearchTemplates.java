package acm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 机试常用查找模板。
 *
 * 重点掌握：
 * 1. 二分查找
 * 2. 左右边界查找
 * 3. 哈希查找
 */
public class SearchTemplates {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 2, 2, 4, 7, 9};
        int target = 2;

        System.out.println("基础二分结果：" + binarySearch(nums, target));
        System.out.println("左边界结果：" + leftBound(nums, target));
        System.out.println("右边界结果：" + rightBound(nums, target));

        int[] hashSample = new int[]{3, 1, 4, 1, 5, 9, 1};
        System.out.println("目标值出现次数：" + countByHash(hashSample, 1));
        System.out.println("两数之和下标：" + Arrays.toString(twoSum(hashSample, 10)));
    }

    /**
     * 有序数组基础二分查找。
     */
    private static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 查找目标值最左侧位置。
     */
    private static int leftBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int answer = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                if (nums[mid] == target) {
                    answer = mid;
                }
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }

    /**
     * 查找目标值最右侧位置。
     */
    private static int rightBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int answer = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                if (nums[mid] == target) {
                    answer = mid;
                }
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }

    /**
     * 哈希统计频次。
     */
    private static int countByHash(int[] nums, int target) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        return countMap.getOrDefault(target, 0);
    }

    /**
     * 两数之和：返回两个下标，找不到返回 {-1, -1}。
     */
    private static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int index = 0; index < nums.length; index++) {
            int need = target - nums[index];
            if (indexMap.containsKey(need)) {
                return new int[]{indexMap.get(need), index};
            }
            indexMap.put(nums[index], index);
        }
        return new int[]{-1, -1};
    }
}
