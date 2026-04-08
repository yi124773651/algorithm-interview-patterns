package acm;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 牛客机试高频题模板总表。
 *
 * 目标：
 * 1. 把最常见的题型模板集中到一个文件中
 * 2. 方便考前快速翻阅和复制
 * 3. 每个方法都尽量保持“可直接迁移”的写法
 */
public class AcmHotProblems {

    public static void main(String[] args) {
        int[] fixedWindowSample = new int[]{1, 12, -5, -6, 50, 3};
        System.out.println("固定窗口最大和：" + maxFixedWindowSum(fixedWindowSample, 4));

        System.out.println("无重复字符最长子串：" + lengthOfLongestSubstring("abcabcbb"));

        int[] twoPointerSample = new int[]{1, 2, 4, 6, 10};
        System.out.println("两数之和下标：" + Arrays.toString(twoSumSorted(twoPointerSample, 8)));

        int[] binarySearchSample = new int[]{1, 2, 2, 2, 4, 7, 9};
        System.out.println("二分左边界：" + leftBound(binarySearchSample, 2));

        int[] prefixSumSample = new int[]{1, 1, 1};
        System.out.println("和为 k 的子数组个数：" + subarraySum(prefixSumSample, 2));

        int[] dailyTemperaturesSample = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println("单调栈结果：" + Arrays.toString(dailyTemperatures(dailyTemperaturesSample)));

        System.out.println("爬楼梯：" + climbStairs(5));
    }

    /**
     * 固定滑动窗口：长度为 k 的最大子数组和。
     */
    private static int maxFixedWindowSum(int[] nums, int k) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            if (right - left + 1 > k) {
                sum -= nums[left];
                left++;
            }
            if (right - left + 1 == k) {
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    /**
     * 不定长滑动窗口：无重复字符的最长子串。
     */
    private static int lengthOfLongestSubstring(String text) {
        Map<Character, Integer> countMap = new HashMap<>();
        int left = 0;
        int maxLength = 0;
        for (int right = 0; right < text.length(); right++) {
            char current = text.charAt(right);
            countMap.put(current, countMap.getOrDefault(current, 0) + 1);
            while (countMap.get(current) > 1) {
                char leftChar = text.charAt(left);
                countMap.put(leftChar, countMap.get(leftChar) - 1);
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    /**
     * 双指针：有序数组中查找和为 target 的两个下标。
     */
    private static int[] twoSumSorted(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left, right};
            }
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 二分查找：查找最左边界。
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
     * 前缀和 + 哈希：和为 k 的子数组个数。
     */
    private static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixCountMap = new HashMap<>();
        prefixCountMap.put(0, 1);
        int prefixSum = 0;
        int count = 0;
        for (int num : nums) {
            prefixSum += num;
            count += prefixCountMap.getOrDefault(prefixSum - k, 0);
            prefixCountMap.put(prefixSum, prefixCountMap.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }

    /**
     * 单调栈：每日温度。
     */
    private static int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int index = 0; index < temperatures.length; index++) {
            while (!stack.isEmpty() && temperatures[index] > temperatures[stack.peek()]) {
                int previousIndex = stack.pop();
                answer[previousIndex] = index - previousIndex;
            }
            stack.push(index);
        }
        return answer;
    }

    /**
     * 动态规划：爬楼梯。
     */
    private static int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int previous = 1;
        int current = 2;
        for (int step = 3; step <= n; step++) {
            int next = previous + current;
            previous = current;
            current = next;
        }
        return current;
    }
}