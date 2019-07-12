//给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。 
//
// 你可以假设数组是非空的，并且给定的数组总是存在众数。 
//
// 示例 1: 
//
// 输入: [3,2,3]
//输出: 3 
//
// 示例 2: 
//
// 输入: [2,2,1,1,1,2,2]
//输出: 2
// 
//

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class Solution169 {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int num : nums) { // 统计每个元素出现的次数
            Integer count = map.get(num);
            count = count == null ? 1 : ++count;
            map.put(num, count);
            if (map.get(num) > len / 2) return num;
        }
        return 0;
    }

    @Test
    public void test() {
        int[] nums = {2,2,1,1,1,2,2};
        System.out.println(majorityElement(nums));
    }
}