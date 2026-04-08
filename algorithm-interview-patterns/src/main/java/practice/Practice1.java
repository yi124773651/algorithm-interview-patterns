package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 题目说明：
 * 1. 保留原始错解，方便对照错误原因。
 * 2. 保留在线性扫描思路上的修正版，逻辑正确，但复杂度较高。
 * 3. 保留标准解，使用排序加大根堆优化。
 */
public class Practice1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }
            String[] split = line.split("\\s+");
            int n = Integer.parseInt(split[0]);
            int m = Integer.parseInt(split[1]);
            int[] tables = new int[n];
            int[][] customers = new int[m][2];

            line = reader.readLine().trim();
            split = line.split("\\s+");
            for (int i = 0; i < tables.length; i++) {
                tables[i] = Integer.parseInt(split[i]);
            }

            for (int i = 0; i < m; i++) {
                line = reader.readLine().trim();
                split = line.split("\\s+");
                customers[i][0] = Integer.parseInt(split[0]);
                customers[i][1] = Integer.parseInt(split[1]);
            }

            System.out.println(solveStandard(tables, customers));
        }
    }

    /**
     * 你的原始错解，按原逻辑保留。
     *
     * 问题在于：
     * - 记录的最大金额和最终标记为已使用的客户，可能不是同一个客户。
     * - 因此可能把同一个高金额客户重复计算。
     *
     * 复杂度：O(n * m)
     */
    private static long solveWrongGreedy(int[] tables, int[][] customers) {
        int[] sortedTables = Arrays.copyOf(tables, tables.length);
        int[][] sortedCustomers = copyCustomers(customers);

        Arrays.sort(sortedTables);
        Arrays.sort(sortedCustomers, new Comparator<int[]>() {
            @Override
            public int compare(int[] first, int[] second) {
                if (first[0] == second[0]) {
                    return second[1] - first[1];
                }
                return first[0] - second[0];
            }
        });

        int[] tableMatched = new int[sortedCustomers.length];
        long totalAmount = 0L;
        for (int i = 0; i < sortedTables.length; i++) {
            int customerNumber = sortedTables[i];
            int subMaxAmount = 0;
            int tempIndex = -1;
            for (int j = 0; j < sortedCustomers.length; j++) {
                if (sortedCustomers[j][0] > customerNumber || tableMatched[j] == 1) {
                    continue;
                }
                subMaxAmount = Math.max(subMaxAmount, sortedCustomers[j][1]);
                if (tempIndex != -1) {
                    tableMatched[tempIndex] = 0;
                }
                tableMatched[j] = 1;
                tempIndex = j;
            }
            totalAmount += subMaxAmount;
        }
        return totalAmount;
    }

    /**
     * 在原错解思路上修正后的版本。
     *
     * 修正点：
     * - 扫描时只负责找最优客户下标。
     * - 扫描结束后，再统一标记最优客户已被使用。
     *
     * 复杂度：O(n * m)
     */
    private static long solveFixedGreedy(int[] tables, int[][] customers) {
        int[] sortedTables = Arrays.copyOf(tables, tables.length);
        int[][] sortedCustomers = copyCustomers(customers);

        Arrays.sort(sortedTables);
        Arrays.sort(sortedCustomers, new Comparator<int[]>() {
            @Override
            public int compare(int[] first, int[] second) {
                if (first[0] == second[0]) {
                    return second[1] - first[1];
                }
                return first[0] - second[0];
            }
        });

        boolean[] used = new boolean[sortedCustomers.length];
        long totalAmount = 0L;

        for (int tableCapacity : sortedTables) {
            int bestIndex = -1;
            int bestAmount = 0;
            for (int i = 0; i < sortedCustomers.length; i++) {
                if (used[i] || sortedCustomers[i][0] > tableCapacity) {
                    continue;
                }
                if (sortedCustomers[i][1] > bestAmount) {
                    bestAmount = sortedCustomers[i][1];
                    bestIndex = i;
                }
            }
            if (bestIndex != -1) {
                used[bestIndex] = true;
                totalAmount += bestAmount;
            }
        }

        return totalAmount;
    }

    /**
     * 大根堆学习笔记：
     *
     * 一、把大根堆想成什么
     * - 把它想成一个“候选池”。
     * - 池子里放的是“当前已经有资格被选择的人”。
     * - 这个池子最强的能力是：可以很快取出当前最大的值。
     * - 本题里，这个“最大值”就是金额最大。
     *
     * 二、什么时候想到大根堆
     * - 候选对象不是一次性固定给你的，而是随着过程推进不断加入。
     * - 每推进一步，都需要从“当前候选集合”里拿出最优值。
     * - 如果你在循环里反复做“从可选对象中找最大值”，就要想到大根堆。
     *
     * 三、本题里的脑中画面
     * - 桌子从小到大依次到来。
     * - 能坐进当前桌子的客户，陆续进入候选池。
     * - 每来一张桌子，就从候选池里拿走当前最赚钱的客户。
     * - 候选池就是大根堆。
     *
     * 四、相比线性扫描的优势
     * - 线性扫描：每张桌子都把所有客户重新看一遍。
     * - 大根堆：每个客户只入堆一次、出堆一次。
     * - 因此复杂度从 O(n * m) 优化到 O((n + m) log m)。
     *
     * 五、经验口诀
     * - 动态加入候选人，反复取当前最大：用大根堆。
     * - 动态加入候选人，反复取当前最小：用小根堆。
     */
    private static long solveStandard(int[] tables, int[][] customers) {
        int[] sortedTables = Arrays.copyOf(tables, tables.length);
        int[][] sortedCustomers = copyCustomers(customers);

        Arrays.sort(sortedTables);
        Arrays.sort(sortedCustomers, new Comparator<int[]>() {
            @Override
            public int compare(int[] first, int[] second) {
                if (first[0] == second[0]) {
                    return second[1] - first[1];
                }
                return first[0] - second[0];
            }
        });

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((first, second) -> second - first);
        long totalAmount = 0L;
        int customerIndex = 0;

        for (int tableCapacity : sortedTables) {
            while (customerIndex < sortedCustomers.length && sortedCustomers[customerIndex][0] <= tableCapacity) {
                maxHeap.offer(sortedCustomers[customerIndex][1]);
                customerIndex++;
            }
            if (!maxHeap.isEmpty()) {
                totalAmount += maxHeap.poll();
            }
        }

        return totalAmount;
    }

    private static int[][] copyCustomers(int[][] customers) {
        int[][] copiedCustomers = new int[customers.length][2];
        for (int i = 0; i < customers.length; i++) {
            copiedCustomers[i][0] = customers[i][0];
            copiedCustomers[i][1] = customers[i][1];
        }
        return copiedCustomers;
    }
}