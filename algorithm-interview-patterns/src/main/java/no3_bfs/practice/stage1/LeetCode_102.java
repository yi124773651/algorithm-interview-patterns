package no3_bfs.practice.stage1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName LeetCode_102
 * @Description 二叉树的层序遍历
 * 题目：给你二叉树的根节点 root，返回其节点值的层序遍历（逐层地，从左到右访问所有节点）。
 * <p>
 * 示例：输入：root = [3,9,20,null,null,15,7] 输出：[[3],[9,20],[15,7]]
 * <p>
 * 提示：
 * 树中节点数目在范围 [0, 2000] 内
 * -1000 <= Node.val <= 1000
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_102 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static List<List<Integer>> bfs(TreeNode root) {
        //使用BFS层序遍历二叉树
        List<List<Integer>> result = new ArrayList<>();
        //边界条件
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            //关键：记录每层的个数
            int levelSize = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(list);
        }
        return result;
    }

    /**
     * 核心规则
     * 对于数组中下标为 i 的节点（从 0 开始）：
     * <p>
     * 左子节点下标 = 2 * i + 1
     * 右子节点下标 = 2 * i + 2
     * null 表示该位置没有节点
     * <p>
     * 下标:  0  1   2    3     4     5   6
     * 值:   [3, 9, 20, null, null, 15,  7]
     * <p>
     * 3
     * / \
     * 9   20
     * /  \
     * 15    7
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(bfs(root)); // [[3],[9,20],[15,7]]
    }
}
