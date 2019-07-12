//给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。 
//
// 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）: 
//
// 
// 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
// 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。 
// 
//
// 示例: 
//
// 输入: [1,2,3,0,2]
//输出: 3 
//解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出] 
//

import org.junit.jupiter.api.Test;

class Solution309 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int len = prices.length;
        int[] dp = new int[len + 1];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            dp[i + 1] = Math.max(dp[i], prices[i] - min);
        }
        return dp[len - 1];
    }

    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int len = prices.length;
        int[] sell = new int[len];
        int[] buy = new int[len];
        int[] cooldown = new int[len];
        sell[0] = 0;
        buy[0] = -prices[0];
        cooldown[0] = 0;
        for (int i = 1; i < len; i++) {
            cooldown[i] = sell[i - 1]; //若第i天是冷冻期，则第i-1天一定是卖出
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]); //若考虑第i天卖出
            buy[i] = Math.max(cooldown[i - 1] - prices[i], buy[i - 1]); //若考虑第i天买入
        }
        // 要想获得最大利润，最后一天一定是冷冻期或者卖出状态。
        return Math.max(sell[len - 1], cooldown[len - 1]);
    }

    @Test
    public void test() {
//        int[] prices = {7,1,5,3,6,4};
        int[] prices = {7,6,4,3,1};
        System.out.println(maxProfit(prices));
    }
}