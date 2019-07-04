//中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。 
//
// 例如， 
//
// [2,3,4] 的中位数是 3 
//
// [2,3] 的中位数是 (2 + 3) / 2 = 2.5 
//
// 设计一个支持以下两种操作的数据结构： 
//
// 
// void addNum(int num) - 从数据流中添加一个整数到数据结构中。 
// double findMedian() - 返回目前所有元素的中位数。 
// 
//
// 示例： 
//
// addNum(1)
//addNum(2)
//findMedian() -> 1.5
//addNum(3) 
//findMedian() -> 2 
//
// 进阶: 
//
// 
// 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？ 
// 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？ 
// 
//

import java.util.ArrayList;
import java.util.List;

class MedianFinder {

    /**
     * initialize your data structure here.
     */
    List<Integer> list;

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        list = new ArrayList();
    }

    public void addNum(int num) {
        int left = 0, right = list.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2, val = list.get(mid);
            if (val < num) left = mid + 1;
            else right = mid;
        }
        if (list.size() > 0 && num > list.get(list.size() - 1)) list.add(num);
        else list.add(left, num);
    }

    public double findMedian() {
        int len = list.size();
        if (len % 2 == 1) return (double) list.get(len / 2);
        else return list.get(len / 2) / 2.0 + list.get(len / 2 - 1) / 2.0;
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        medianFinder.addNum(3);
        medianFinder.addNum(6);
        double result = medianFinder.findMedian();
        System.out.println(result);
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */