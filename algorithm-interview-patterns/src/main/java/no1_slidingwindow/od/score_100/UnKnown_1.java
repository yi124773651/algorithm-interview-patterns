package no1_slidingwindow.od.score_100;

/**
 * @ClassName UnKnown_1
 * @Description  恢复数字序列
 *
 *
对于一个连续正整数组成的序列，可以将其拼接成一个字符串，再将字符串里的部分字符打乱顺序。
如序列 8 9 10 11 12，拼接成的字符串为 89101112，打乱一部分字符后得到 90811211，原来的正整数 10 就被拆成了 0 和 1。
现给定一个按如此规则得到的打乱字符的字符串，请将其还原成连续正整数序列，并输出序列中最小的数字。

输入描述
输入一行，为打乱字符的字符串和正整数序列的长度，两者间用空格分隔。字符串长度不超过 200，
正整数不超过 1000，保证输入可以还原成唯一序列。

输出描述
输出一个数字，为序列中最小的数字。

示例 1
输入
19801211 5

输出
8

说明： 正常的数字序列为 8 9 10 11 12 这 5 个数字，最小数字为 8
 * @Author gm
 * @Date 2026/4/3 01:47
 */
public class UnKnown_1 {
    public static void main(String[] args) {
        String input = "19801211";
        int count = 5;
        int result = solution(input, count);
        System.out.println(result);
    }

    private static int solution(String s, int count) {
        // TODO: 算法实现
        return 0;
    }
}
