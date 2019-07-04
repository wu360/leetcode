//设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。 
//
// 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。 
//
// 示例: 
//
// 
//int k = 3;
//int[] arr = [4,5,8,2];
//KthLargest kthLargest = new KthLargest(3, arr);
//kthLargest.add(3);   // returns 4
//kthLargest.add(5);   // returns 5
//kthLargest.add(10);  // returns 5
//kthLargest.add(9);   // returns 8
//kthLargest.add(4);   // returns 8
// 
//
// 说明: 
//你可以假设 nums 的长度≥ k-1 且k ≥ 1。 
//

import java.util.PriorityQueue;

class KthLargest {

    final PriorityQueue<Integer> minheap;
    final int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        minheap = new PriorityQueue<>(k);

        for (int i : nums) {
            add(i);
        }
    }

    public int add(int val) {
        if (minheap.size() < k) {
            minheap.offer(val);
        } else if (minheap.peek() < val) {
            minheap.poll();
            minheap.offer(val);
        }
        return minheap.peek();
    }

    public static void main(String[] args) {
        int a[] = new int[]{4, 5, 8, 2};
        int k = 3;
        KthLargest kthLargest = new KthLargest(k, a);

        int result = kthLargest.add(3);
        System.out.println(result);
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */