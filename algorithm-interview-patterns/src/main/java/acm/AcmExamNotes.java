package acm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 考前突击速记：常见题型信号、推荐模板与 ACM 模式注意点。
 * 这个类用于在本地代码库中持久化保存考前笔记。
 */
public class AcmExamNotes {

    public static void main(String[] args) {
        printCommonPatterns();
        System.out.println();
        printAcmTips();
    }

    private static void printCommonPatterns() {
        Map<String, String> patternMap = new LinkedHashMap<>();
        patternMap.put("滑动窗口", "连续子数组、连续子串、固定长度 k、最长或最短满足条件");
        patternMap.put("双指针", "有序数组两端逼近、原地去重、回文判断、三数之和");
        patternMap.put("二分查找", "查找边界、最小可行值、最大可行值、答案具有单调性");
        patternMap.put("广度优先搜索", "最短步数、层序遍历、从起点向四周扩散");
        patternMap.put("深度优先搜索/回溯", "全排列、组合、子集、搜索全部方案");
        patternMap.put("动态规划", "第 i 个状态依赖前面的状态、求最优值、计数问题");
        patternMap.put("栈", "括号匹配、最近更大元素、单调栈");
        patternMap.put("前缀和", "区间和、子数组和为 k、快速求连续区间和");
        patternMap.put("贪心", "每一步做局部最优，希望导向整体最优");

        System.out.println("=== 牛客机试高频模式速记 ===");
        for (Map.Entry<String, String> entry : patternMap.entrySet()) {
            System.out.println(entry.getKey() + "：" + entry.getValue());
        }
    }

    private static void printAcmTips() {
        String[] tips = new String[]{
                "ACM 模式核心：自己写 main、自己处理输入、自己输出结果。",
                "看到多组输入时，优先考虑 while 读取直到 EOF。",
                "输出只能打印结果，不能带额外提示语。",
                "求和、前缀和、计数结果尽量用 long，避免 int 溢出。",
                "字符串分割优先使用 split(\"\\\\s+\")，兼容多个空格。",
                "做题先判断属于哪个模板，再补边界条件。",
                "卡题超过 10 到 15 分钟及时换题，先保住中低难度分数。"
        };

        System.out.println("=== ACM 模式注意点 ===");
        for (String tip : tips) {
            System.out.println(tip);
        }
    }
}