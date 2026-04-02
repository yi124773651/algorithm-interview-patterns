package no3_bfs.practice.stage1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName LeetCode_111
 * @Description 二叉树的最小深度
 * 题目：给定一个二叉树，找出其最小深度。最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 示例1：输入：root = [3,9,20,null,null,15,7] 输出：2
 * 示例2：输入：root = [2,null,3,null,4,null,5,null,6] 输出：5
 *
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

    public static int bfs(TreeNode root) {
        // TODO: 使用BFS找到二叉树的最小深度
        return 0;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        System.out.println(bfs(root1)); // 2

        TreeNode root2 = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.right.right = new TreeNode(4);
        root2.right.right.right = new TreeNode(5);
        root2.right.right.right.right = new TreeNode(6);
        System.out.println(bfs(root2)); // 5
    }
}
