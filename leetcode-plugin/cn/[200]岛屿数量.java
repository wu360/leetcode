//给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。 
//
// 示例 1: 
//
// 输入:
//11110
//11010
//11000
//00000
//
//输出: 1
// 
//
// 示例 2: 
//
// 输入:
//11000
//11000
//00100
//00011
//
//输出: 3
// 
//

class Solution200 {
    public static int numIslands(char[][] grid) {
        int ret = 0;
        // 参数校验
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        // 元素默认值是false
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 如果此位置没有被访问过，并且此位置是岛，广度优先遍历
                if (!visited[i][j] && grid[i][j] == 1) {
                    ret++;
                    bfs(grid, visited, i, j);
                }
            }
        }
        for (boolean[] a : visited)
            for (boolean b : a)
                System.out.println(b?1:0);

        return ret;
    }

    /**
     * 广度优先搜索
     *
     * @param grid    网格
     * @param visited 访问标记矩阵
     * @param row     横坐标
     * @param col     纵坐标
     */
    private static void bfs(char[][] grid, boolean[][] visited, int row, int col) {

        if (row >= 0 && row < grid.length // 行合法
                && col >= 0 && col < grid[0].length // 列合法
                && !visited[row][col] // 没有访问过
                && grid[row][col] == 1) { // 是岛上陆地

            // 标记此位置已经访问
            visited[row][col] = true;

            // 上
            bfs(grid, visited, row - 1, col);
            // 右
            bfs(grid, visited, row, col + 1);
            // 下
            bfs(grid, visited, row + 1, col);
            // 左
            bfs(grid, visited, row, col - 1);

        }
    }

    public static void main(String[] args) {
        char[][] grid = {{1, 1, 0, 1, 0}, {1, 1, 0, 1, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 0, 0}};
        int ret = numIslands(grid);
        System.out.println(ret);
    }
}