package codeforce.globalround.y2020.r12;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.TreeMap;

public class C1_ErrichTacToe {

  public static void main(String[] args) {
    /**
     * XXXXX
     * XXXXX
     * XXXXX
     * XXXXX
     * XXXXX
     */
    System.out.println(chances(new String[] {"XXXXX","XXXXX","XXXXX", "XXXXX", "XXXXX"}, 2, 2));
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      int k = 0;
      while (t-- > 0) {
        int n = sc.nextInt();
        String[] board = new String[n];
        for (int i = 0; i < n; i++) {
          board[i] = sc.next();
        }
        Map<Integer, Point> map = new TreeMap<>(Collections.reverseOrder());
        int[][] winningChances = new int[n][n];
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
            if (board[i].charAt(j) == '.') {
              continue;
            }
            winningChances[i][j] = chances(board, i, j);
            map.put(winningChances[i][j], new Point(i, j));
            k++;
          }
        }
        System.out.println("winning chances");
        for(int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
            System.out.print(winningChances[i][j] + " ");
          }
          System.out.println();
        }
        System.out.println("winning chances");
        k /= 3;
        for (Map.Entry<Integer, Point> entry : map.entrySet()) {
          if (k-- == 0)
            break;
          Point point = entry.getValue();
          String oldVal = board[point.x];
          String newVal = oldVal.substring(0, point.y) + 'O' + (point.y == n - 1 ? "" : oldVal.substring(point.y + 1));
          board[point.x] = newVal;
        }
        for (String row : board)
          System.out.println(row);
      }
    }
  }

  static class Point {
    int x, y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Point point = (Point) o;
      return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }

  private static int chances(String[] board, int i, int j) {
    int n = board.length;
    int cnt = 0;
    int[][] dx = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {-2, -1, 0}, {-1, 0, 1}, {0, 1, 2}};
    int[][] dy = {{-2, -1, 0}, {-1, 0, 1}, {0, 1, 2}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    for (int t = 0; t < dx.length; t++) {
      if (allPointHasX(board, dx[t], dy[t], i, j)) {
        cnt++;
      }
    }
    return cnt;
  }

  private static boolean allPointHasX(String[] board, int[] dx, int[] dy, int x, int y) {
    int cnt = 0;
    for (int i = 0; i < 3; i++) {  
      try {
        int tx = x + dx[i];
        int ty = y + dy[i];
        if (board[tx].charAt(ty) == 'X')
          cnt++;
      } catch (Exception e) {
        //no op
      }
    }
    return cnt == 3;
  }

}
