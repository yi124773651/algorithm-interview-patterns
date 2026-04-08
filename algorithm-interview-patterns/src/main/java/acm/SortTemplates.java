package acm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 机试常用排序模板。
 *
 * 重点不是手写快排，而是掌握：
 * 1. 基础排序
 * 2. 自定义排序
 * 3. 排序后扫描
 */
public class SortTemplates {

    public static void main(String[] args) {
        demoIntArraySort();
        demoStringSort();
        demoIntervalSortAndMerge();
    }

    /**
     * 基础整数数组排序。
     */
    private static void demoIntArraySort() {
        int[] nums = new int[]{5, 2, 8, 1, 3};
        Arrays.sort(nums);
        System.out.println("升序排序结果：" + Arrays.toString(nums));
    }

    /**
     * 字符串数组自定义排序：先按长度升序，再按字典序升序。
     */
    private static void demoStringSort() {
        String[] words = new String[]{"pear", "a", "apple", "to", "tea"};
        Arrays.sort(words, (first, second) -> {
            if (first.length() != second.length()) {
                return first.length() - second.length();
            }
            return first.compareTo(second);
        });
        System.out.println("字符串排序结果：" + Arrays.toString(words));
    }

    /**
     * 区间类题目的常见模板：先按起点排序，再线性合并。
     */
    private static void demoIntervalSortAndMerge() {
        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] merged = mergeIntervals(intervals);
        System.out.println("合并区间结果：" + Arrays.deepToString(merged));
    }

    /**
     * 合并重叠区间。
     */
    private static int[][] mergeIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }

        Arrays.sort(intervals, (first, second) -> first[0] - second[0]);
        List<int[]> mergedList = new ArrayList<>();
        int[] current = new int[]{intervals[0][0], intervals[0][1]};

        for (int index = 1; index < intervals.length; index++) {
            int[] next = intervals[index];
            if (next[0] <= current[1]) {
                current[1] = Math.max(current[1], next[1]);
            } else {
                mergedList.add(current);
                current = new int[]{next[0], next[1]};
            }
        }
        mergedList.add(current);

        return mergedList.toArray(new int[mergedList.size()][]);
    }
}
