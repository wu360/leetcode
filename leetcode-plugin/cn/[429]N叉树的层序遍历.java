//给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其层序遍历: 
//
// [
//     [1],
//     [3,2,4],
//     [5,6]
//]
// 
//
// 
//
// 说明: 
//
// 
// 树的深度不会超过 1000。 
// 树的节点总数不会超过 5000。 
//

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

class Solution429 {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> reslist = new LinkedList<List<Integer>>();
        List<Integer> list = new LinkedList<Integer>();
        if (root == null) {
            return reslist;
        }
        int curCount = 0, curNum = 1, nextCount = 1;
        Queue<Node> queue = new LinkedList<Node>();
        Node t;
        queue.offer(root);
        while (!queue.isEmpty()) {
            t = queue.poll();
            list.add(t.val);
            if (t.children != null) {
                for (Node node : t.children) {
                    if (node != null) {
                        queue.offer(node);
                    }
                    nextCount++;
                }
            }
            if (++curCount == curNum) {
                reslist.add(list);//增加元素，每次在0位置插入元素，其他往后顺延
                list = new LinkedList<Integer>();
                curNum = nextCount;
            }

        }
        return reslist;
    }

    public List<List<Integer>> levelOrder2(Node root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }
        nbfs(root, lists);
        return lists;
    }

    public void nbfs(Node root, List<List<Integer>> lists) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node lastNode = root;
        Node last = root;
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.children != null) {
                for (Node node1 : node.children) {
                    queue.add(node1);
                    lastNode = node1;
                }
            }
            list.add(node.val);
            if (node == last) {
                last = lastNode;
                lists.add(list);
                list = new ArrayList<>();
            }
        }
    }

    @Test
    public void test() {
        Node n3_1 = new Node(5, null);
        Node n3_2 = new Node(6, null);
        List<Node> list3 = new ArrayList<>();
        list3.add(n3_1);
        list3.add(n3_2);
        Node n2_1 = new Node(3, list3);
        Node n2_2 = new Node(2, null);
        List<Node> list2 = new ArrayList<>();
        list2.add(n2_1);
        list2.add(n2_2);
        Node root = new Node(1, list2);
        levelOrder(root);
    }

}