package no9_prefixsum.practice.stage4;

/**
 * @ClassName LeetCode_437
 * @Description 路径总和 III
 * 题目：给定一个二叉树的根节点 root 和一个整数 targetSum，求该二叉树里节点值之和等于 targetSum 的路径的数目。
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * 示例1：输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8 输出：3
 * 示例2：输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22 输出：3
 *
 * 提示：
 * 二叉树的节点个数在 [0, 1000] 范围内
 * -10^9 <= Node.val <= 10^9
 * -1000 <= targetSum <= 1000
 *
 * @Author gm
 * @Date 2026/4/2 15:30
 */
public class LeetCode_437 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static int prefixSum(TreeNode root, int targetSum) {
        // TODO
        return 0;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);
        System.out.println(prefixSum(root, 8)); // 3
    }
}
