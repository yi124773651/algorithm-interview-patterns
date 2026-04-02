package no10_unionfind.practice.stage2;

/**
 * @ClassName LeetCode_990
 * @Description 等式方程的可满足性
 * 题目：给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，
 * 并采用两种不同的形式之一："xi==yi" 或 "xi!=yi"。判断是否能够给变量赋值从而满足所有的方程，可以则返回 true。
 *
 * 示例1：输入：["a==b","b!=a"] 输出：false
 * 示例2：输入：["b==a","a==b"] 输出：true
 *
 * 提示：
 * 1 <= equations.length <= 500
 * equations[i].length == 4
 * equations[i][0] 是小写字母
 * equations[i][1] 是 '=' 或 '!'
 * equations[i][2] 是 '='
 * equations[i][3] 是小写字母
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_990 {

    public static boolean unionFind(String[] equations) {
        // TODO
        return false;
    }

    public static void main(String[] args) {
        System.out.println(unionFind(new String[]{"a==b", "b!=a"})); // false
        System.out.println(unionFind(new String[]{"b==a", "a==b"})); // true
    }
}
