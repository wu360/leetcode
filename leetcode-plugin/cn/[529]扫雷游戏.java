//让我们一起来玩扫雷游戏！ 
//
// 给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。 
//
// 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板： 
//
// 
// 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。 
// 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的方块都应该被递归地揭露。 
// 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。 
// 如果在此次点击中，若无更多方块可被揭露，则返回面板。 
// 
//
// 
//
// 示例 1： 
//
// 输入: 
//
//[['E', 'E', 'E', 'E', 'E'],
// ['E', 'E', 'M', 'E', 'E'],
// ['E', 'E', 'E', 'E', 'E'],
// ['E', 'E', 'E', 'E', 'E']]
//
//Click : [3,0]
//
//输出: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'M', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//解释:
//
// 
//
// 示例 2： 
//
// 输入: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'M', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//Click : [1,2]
//
//输出: 
//
//[['B', '1', 'E', '1', 'B'],
// ['B', '1', 'X', '1', 'B'],
// ['B', '1', '1', '1', 'B'],
// ['B', 'B', 'B', 'B', 'B']]
//
//解释:
//
// 
//
// 
//
// 注意： 
//
// 
// 输入矩阵的宽和高的范围为 [1,50]。 
// 点击的位置只能是未被挖出的方块 ('M' 或者 'E')，这也意味着面板至少包含一个可点击的方块。 
// 输入面板不会是游戏结束的状态（即有地雷已被挖出）。 
// 简单起见，未提及的规则在这个问题中可被忽略。例如，当游戏结束时你不需要挖出所有地雷，考虑所有你可能赢得游戏或标记方块的情况。 
//

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

class Solution529 {
    public char[][] updateBoard(char[][] board, int[] click) {
        // 获取地图的长宽
        int m = board.length;
        int n = board[0].length;
        // 广度优先遍历 利用队列
        Queue<int[]> queue = new LinkedList<>();
        // 将起点加入队列
        queue.add(click);
        // 栈不为空 则未遍历完全
        while (!queue.isEmpty()) {
            // 按照队列先进先出的原则依次遍历
            int[] poll = queue.poll();
            int row = poll[0], col = poll[1];
            // 点到炸弹区域
            if (board[row][col] == 'M') { // Mine
                board[row][col] = 'X';
            } else {
                // 点到非炸弹区域 可能是数字区域也可能是空白区域
                int count = 0;
                // 将该点的周围全部访问
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        // 遍历到自己
                        if (i == 0 && j == 0)
                            continue;
                        int r = row + i, c = col + j;
                        // 遍历超出边界
                        if (r < 0 || r >= m || c < 0 || c < 0 || c >= n)
                            continue;
                        // 发现有炸弹
                        if (board[r][c] == 'M' || board[r][c] == 'X')
                            count++;
                    }
                }

                // 如果周围有炸弹则这个区域不是空白区域 停止BFS
                if (count > 0) {
                    // 该区域周围的炸弹数量就是该区域的数字大小
                    board[row][col] = (char) (count + '0');
                    // 周围无炸弹 是空白区域
                } else {
                    board[row][col] = 'B';
                    // 将该区域周围全部遍历
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if (i == 0 && j == 0)
                                continue;
                            int r = row + i, c = col + j;
                            if (r < 0 || r >= m || c < 0 || c < 0 || c >= n)
                                continue;
                            // 发现未被访问区域 加入队列
                            if (board[r][c] == 'E') {
                                queue.add(new int[]{r, c});
                                board[r][c] = 'B';
                            }
                        }
                    }
                }
            }
        }
        return board;
    }

    @Test
    public void test() {
        char[][] board = {{'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'M', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}};

        for (char[] cs : board) {
            for (char c : cs) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println("-------------");
        int[] click = {3, 0};
        Solution529 minesweeperSolution = new Solution529();
        char[][] updateBoard = minesweeperSolution.updateBoard(board, click);
        for (char[] cs : updateBoard) {
            for (char c : cs) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}