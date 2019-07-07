//给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。 
//
// 例如: 
//给定二叉树: [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层次遍历结果： 
//
// [
//  [3],
//  [9,20],
//  [15,7]
//]
// 
//
package cn;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution102 {
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ret = new ArrayList();

        if (root == null) return ret;

        Queue<TreeNode> q = new LinkedList();
        q.add(root);
        while (!q.isEmpty()) {
            int levelCount = q.size();
            List<Integer> list = new ArrayList();
            while (levelCount > 0) {
                TreeNode temp = q.peek();
                list.add(temp.val);
                q.poll();
                if (temp.left != null) q.add(temp.left);
                if (temp.right != null) q.add(temp.right);
                levelCount--;
            }
            ret.add(list);
        }
        return ret;
    }


    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();

        levelOrderHelper(root, 0, list);

        return list;
    }

    private void levelOrderHelper(TreeNode root, int depth, List<List<Integer>> list) {
        if (root == null)
            return;
        // 如采用中序/后序遍历，需将if改成while
        if (depth >= list.size()) {
            list.add(new ArrayList<>());
        }

        list.get(depth).add(root.val);

        levelOrderHelper(root.left, depth + 1, list);
        levelOrderHelper(root.right, depth + 1, list);
    }

    @Test
    public void test() {
        String str = "[3,9,20,null,null,15,7]";
        TreeNode tree = TreeNode.mkTree(str);
        levelOrder(tree);
    }
}