package no4_dfsbacktracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Pattern
 * @Description TODO
 * @Author gm
 * @Date 2026/4/3 15:10
 */
public class Pattern {
    private static List result = new ArrayList<>();

    public static void main(String[] args) {
        List<Integer> path = new ArrayList<>();
        int[] choices = new int[0];
        backTrack(path, choices);
    }

    /**
     * backTrack(路径, 选择列表)
     *     ├── 1. 终止条件 → 收集结果
     *     └── 2. for 循环遍历选择列表
     *          ├── (1) 剪枝 — 跳过不合法选择
     *          ├── (2) 做选择 — 加入路径
     *          ├── (3) 递归 — 进入下一层
     *          └── (4) 撤销选择 — 回溯（核心！）
     * @param path
     * @param choices
     */
    private static void backTrack(List<Integer> path, int[] choices) {
        String s = "";
        if ("满足结束条件".equals(s)) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < choices.length; i++) {
            //1.剪枝
            if ("选择不合法".equals(s)) continue;
            //2.做选择
            path.add(choices[i]);
            //3.递归
            int[] 新的选择列表 = new int[0];
            backTrack(path, 新的选择列表);
            //4.撤销选择
            path.remove(path.size() - 1);
        }
    }
}
