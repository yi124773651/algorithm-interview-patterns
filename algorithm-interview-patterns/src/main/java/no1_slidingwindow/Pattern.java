package no1_slidingwindow;

import java.util.HashMap;

/**
 * @ClassName Pattern
 * @Description 滑动窗口模板算法
 * @Author gm
 * @Date 2026/4/1 17:24
 */
public class Pattern {
    public static void main(String[] args) {
        int result = slidingWindow("message");
        System.out.println(result);
    }

    private static int slidingWindow(String s) {
        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0, result = 0;
        for (int right = 0; right < s.length(); right++) {
            //1.先扩容，更新状态
            window.merge(s.charAt(right), 1, Integer::sum);
            //2.判断是否需要收缩，根据题意 用while收缩
            boolean needShrink = false;
            while (needShrink) {
                window.merge(s.charAt(left), -1, Integer::sum);
                left++;
            }
            //3.更新结果
            result = Math.max(result, right - left + 1);
        }
        return result;
    }
}
