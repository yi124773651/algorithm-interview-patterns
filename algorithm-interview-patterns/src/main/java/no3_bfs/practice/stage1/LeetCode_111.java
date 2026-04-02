package no3_bfs.practice.stage1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName LeetCode_111
 * @Description 二叉树的最小深度
 * 题目：给定一个二叉树，找出其最小深度。最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 示例1：输入：root = [3,9,20,null,null,15,7] 输出：2
 * 示例2：输入：root = [2,null,3,null,4,null,5,null,6] 输出：5
 * <p>
 * 提示：
 * 树中节点数目在范围 [0, 10^5] 内
 * -1000 <= Node.val <= 1000
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_111 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 这是dfs递归  是求最大深度的  不太适合求最小深度
     *
     * @param root
     * @return
     */
    public static int dfs(TreeNode root) {
        //使用dfs找到二叉树的最小深度
        //边界条件
        if (root == null) return 0;
        int minLevel = 1;
        if (root.left != null && root.right != null) {
            minLevel += Math.min(dfs(root.left), dfs(root.right));
        } else if (root.left == null) {
            minLevel += dfs(root.right);
        } else {
            minLevel += dfs((root.left));
        }
        return minLevel;
    }

    /**
     * bfs
     *
     * @param root
     */
    public static int bfs(TreeNode root) {
        if (root == null) return 0;
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            depth++;
            int levelSize = queue.size();  //很关键的一点！！！
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                //找到第一个叶子节点
                if (node.left == null && node.right == null) return depth;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return depth;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
//        System.out.println(dfs(root1)); // 2
        System.out.println(bfs(root1)); // 2

        TreeNode root2 = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.right.right = new TreeNode(4);
        root2.right.right.right = new TreeNode(5);
        root2.right.right.right.right = new TreeNode(6);
//        System.out.println(dfs(root2)); // 5
        System.out.println(bfs(root2)); // 5
    }
}
