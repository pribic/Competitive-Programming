package kickstart.Y2021.round1A.RabbitHouse;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 21/03/21
 */
public class Solution {


  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long cost = 0;
        System.out.print("Case #" + tt + ": ");
        int r = sc.nextInt();
        int c = sc.nextInt();
        long[][] grid = new long[r][c];
        long max = Integer.MIN_VALUE;
        for (int i = 0; i < r; i++) {
          for (int j = 0; j < c; j++) {
            grid[i][j] = sc.nextInt();
            max = Math.max(max, grid[i][j]);
          }
        }
        boolean[][] visited = new boolean[r][c];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < r; i++) {
          for (int j = 0; j < c; j++) {
            if (max == grid[i][j]) {
              queue.addLast(i);
              queue.addLast(j);
            }
          }
        }
        queue.addLast(-1);
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, 1, 0, -1};
        while (queue.size() > 1) {
          if(queue.peek() == -1) {
            max--;
            queue.addLast(-1);
            queue.removeFirst();
          }
          int x = queue.removeFirst();
          int y = queue.removeFirst();
          for (int i = 0; i < dx.length; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            if(isValid(r, c, tx, ty) && !visited[tx][ty]) {
              visited[tx][ty] = true;
              if(grid[tx][ty] < max - 1) {
                cost += (max - 1L - grid[tx][ty]);
                grid[tx][ty] = max - 1;
              }
              queue.addLast(tx);
              queue.addLast(ty);
            }
          }
        }
        System.out.println(cost);
      }
    }
  }

  private static boolean isValid(int r, int c, int i, int j) {
    return i >= 0 && j >= 0 && i < r & j < c;
  }
}