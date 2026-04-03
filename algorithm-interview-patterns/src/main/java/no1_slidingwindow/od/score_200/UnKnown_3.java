package no1_slidingwindow.od.score_200;

/**
 * @ClassName UnKnown_3
 * @Description 敌情监控
 * 题目描述
 * H国最近在和M国打仗，H国间谍头子 Peter 负责监视敌国 M 的兵力部署情况。M 国沿边境线布置了 N 个营地，
 * Peter 的任务就是监视这些营地的兵力部署情况。中央情报局要研究敌情，所以 Peter 要汇报一段兵营中哪个连续的 K 个营地
 * 驻兵最少之和是多少，可以作为攻击的突破点，
 * 例如：“Peter、快汇报第 2 个营地到第 10 个营地中连续的 3 座兵营人数之和最少”；
 * 而且每个营地的人数在不断变化。
 * 请你帮 Peter 设计一个程序，快速计算汇报到的结果。
 *
 * 输入描述
 * 第一行 3 个正整数：N K L。
 * N (N <= 50000)，表示敌人营地兵营的数量，每个营地编号为 1, 2, 3 ..N.K 表示连续的兵营数，
 * L表示后续要问的命令数量。接下来有 N 个正整数，第 i 个正整数 ai 代表第 i 个营地里开始时有 ai 个人 (1 <=ai <=50000)。
 * 接下来有 L 条命令。命令有 3 种形式：
 * 1.Add i j：
 * i 和 j 为正整数，表示第 i 个营地增加 j 个人 (j 不超过 30)。
 * 2.Sub i j：i 和 j 为正整数，表示第 i 个营地减少 j 个人 (j 不超过 30)。
 * 3.Query i j：i 和 j 为正整数，i <= j，表示询问第 i 个营地到第 j 个营地，连续 K 个兵营人数之和最少的总人数。
 * 其中：j - i + 1 >= K，每组数据最多有 50 条命令。
 *
 * 输出描述
 * 对于每个 Query 询问，输出一个整数并回车，表示询问的该段中的连续的 K 座兵营人数之和最少的数量，这个数保持在 int 以内。
 * 示例 1
 *
 * 输入
 * 5 2 3
 * 1 2 2 3 3
 * Query 1 3
 * Add 3 6
 * Query 2 4
 *
 * 输出
 * 3
 * 10
 *
 * 说明
 * 第一行第一个正整数 5，表示敌人有 5 个营地，第二个正整数 2，表示连续营地的数量是 2，第 3 个正整数 3，表示后续会有 3 条询问的命令；
 * 接下来一行有 5 个正整数，分别表示每个营地里开始的人数，第 1 个营地开始有 1 个人，第 2 个营地开始有 2 个人，第 3 个营地开始有 2 个人，第 4 个营地开始有 3 个人，第 5 个营地开始有 3 个人；
 * 接下来每行有一条命令：
 * 第 1 条命令：Query 1 3 表示要查询第 1 到第 3 个营地中连续 2 个营地人数之和最少的情况，即 1+2=3，输出是 3；
 * 第 2 条命令：Add 3 6 表示第 3 个营地增加 6 个人，无需输出；
 * 第 3 条命令：Query 2 4 表示查询第 2 到第 4 个营地中连续 2 个营地人数之和最少的情况，此时营地 3 的人数变为 2+6=8，候选有 2+8=10 和 8+3=11，最少是 10，输出是 10。
 * @Author gm
 * @Date 2026/4/3 02:03
 */
public class UnKnown_3 {
    public static void main(String[] args) {
        int n = 5, k = 2;
        int[] camps = new int[]{1, 2, 2, 3, 3};

        // Query 1 3 -> 输出 3
        System.out.println(query(camps, k, 1, 3));
        // Add 3 6
        add(camps, 3, 6);
        // Query 2 4 -> 输出 10
        System.out.println(query(camps, k, 2, 4));
    }

    private static int query(int[] camps, int k, int i, int j) {
        // TODO: 算法实现，查询第i到第j个营地中连续K个营地人数之和的最小值
        return 0;
    }

    private static void add(int[] camps, int i, int j) {
        // TODO: 第i个营地增加j个人
    }

    private static void sub(int[] camps, int i, int j) {
        // TODO: 第i个营地减少j个人
    }
}
