package no3_bfs.practice.stage1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName LeetCode_102
 * @Description 二叉树的层序遍历
 * 题目：给你二叉树的根节点 root，返回其节点值的层序遍历（逐层地，从左到右访问所有节点）。
 *
 * 示例：输入：root = [3,9,20,null,null,15,7] 输出：[[3],[9,20],[15,7]]
 *
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
        // TODO: 使用BFS层序遍历二叉树
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(bfs(root)); // [[3],[9,20],[15,7]]
    }
}
