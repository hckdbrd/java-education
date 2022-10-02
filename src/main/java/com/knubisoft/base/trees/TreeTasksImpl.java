package com.knubisoft.base.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


public class TreeTasksImpl implements TreeTasks {

    @Override
    public boolean isSameTree(TreeNode node1, TreeNode node2) {
        return node1.equals(node2);
    }

    @Override
    public List<Integer> inorderTraversal(TreeNode node) {
        List<Integer> list = new ArrayList<>();

        if (node == null) {
            return list;
        }

        list.addAll(inorderTraversal(node.left));
        list.add(node.val);
        list.addAll(inorderTraversal(node.right));

        return list;
    }

    @Override
    public boolean isSymmetric(TreeNode node) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;
        deque.addFirst(leftNode);
        deque.addLast(rightNode);
        while (!deque.isEmpty()) {
            leftNode = deque.pollFirst();
            rightNode = deque.pollLast();

            if (leftNode.val != rightNode.val)
                return false;

            if (rightNode.right != null)
                deque.addLast(rightNode.right);

            if (leftNode.left != null)
                deque.addFirst(leftNode.left);
        }
        return true;
    }

    @Override
    public int maxDepth(TreeNode node) {
        return node == null ? 0 : Math.max(maxDepth(node.left), maxDepth(node.right))+1;
    }

    @Override
    public boolean hasPathSum(TreeNode node, int targetSum) {
        if (node == null) return false;
        if (node.left == null && node.right == null) return targetSum == node.val;
        return
           hasPathSum(node.left, targetSum - node.val)
           ||
           hasPathSum(node.right, targetSum - node.val);
    }

    @Override
    public TreeNode invertTree(TreeNode node) {
        if (node == null) return null;

        TreeNode leftNode = invertTree(node.left);

        node.left = invertTree(node.right);
        node.right = leftNode;

        return node;
    }

    @Override
    public int sumOfLeftLeaves(TreeNode node) {
        if(node == null) return 0;
        if(node.left != null &&
           node.left.left == null &&
           node.left.right == null) {
            return
               node.left.val +
               sumOfLeftLeaves(node.left) +
               sumOfLeftLeaves(node.right);
        }
        return sumOfLeftLeaves(node.left) + sumOfLeftLeaves(node.right);
    }

    @Override
    public TreeNode mergeTrees(TreeNode node1, TreeNode node2) {
        if (node1 == null || node2 == null) {
            return node1 == null ? node2 : node1;
        }

        node1.val += node2.val;
        node1.left = mergeTrees(node1.left, node2.left);
        node1.right = mergeTrees(node1.right, node2.right);

        return node1;
    }
}
