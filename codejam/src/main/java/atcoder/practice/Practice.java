package atcoder.practice;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author pribic (Priyank Doshi)
 * @since 02/05/21
 */
public class Practice {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[][] grid = new int[n][5];
        int[] max = new int[5];
        Arrays.fill(max, Integer.MIN_VALUE);
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < 5; j++) {
            grid[i][j] = sc.nextInt();
          }
        }
        int[][] runningMax = new int[n][5];
        for (int col = 0; col < 5; col++) {
          int curMax = Integer.MIN_VALUE;
          for (int row = 0; row < n; row++) {
            curMax = Math.max(curMax, grid[row][col]);
            runningMax[row][col] = curMax;
          }
        }
        int ans = Integer.MIN_VALUE;
        for (int choice1 = n - 1; choice1 >= 2; choice1--) {
          for (int choice2 = choice1 - 1; choice2 >= 1; choice2--) {
            int max1 = Math.max(grid[choice1][0], grid[choice2][0]);
            int max2 = Math.max(grid[choice1][1], grid[choice2][1]);
            int max3 = Math.max(grid[choice1][2], grid[choice2][2]);
            int max4 = Math.max(grid[choice1][3], grid[choice2][3]);
            int max5 = Math.max(grid[choice1][4], grid[choice2][4]);

            max1 = Math.max(max1, runningMax[choice2 - 1][0]);
            max2 = Math.max(max2, runningMax[choice2 - 1][1]);
            max3 = Math.max(max3, runningMax[choice2 - 1][2]);
            max4 = Math.max(max4, runningMax[choice2 - 1][3]);
            max5 = Math.max(max5, runningMax[choice2 - 1][4]);
            ans = Math.max(ans, IntStream.of(max1, max2, max3, max4, max5).min().getAsInt());
            DebugOutput.print3("choice1", choice1, "choice2", choice2, "ans", ans);
          }
        }
        System.out.println(ans);
      }
    }
  }

  static class OutPutLocal {
    private static void print(int[][] grid) {
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[i].length; j++) {
          System.out.print(grid[i][j] + " ");
        }
        System.out.println();
      }
    }
  }

  static class DebugOutput {
    static boolean enablePrintGlobal = false;
    static boolean enablePrint = true;

    private static void print1(boolean enable, String name1, Object val) {
      enablePrint = enable;
      print1(name1, val);
      enablePrint = !enable;
    }

    private static void print1(String name1, Object val) {
      if (enablePrint)
        printRaw(printStr(new String[]{name1}, new Object[]{val}));
    }

    private static void print2(boolean enable, String name1, Object val1, String name2, Object val2) {
      enablePrint = enable;
      print2(name1, val1, name2, val2);
      enablePrint = !enable;
    }

    private static void print2(String name1, Object val1, String name2, Object val2) {
      if (enablePrint)
        printRaw(printStr(new String[]{name1, name2}, new Object[]{val1, val2}));
    }

    private static void print3(boolean enable, String name1, Object val1, String name2, Object val2, String name3, Object val3) {
      enablePrint = enable;
      print3(name1, val1, name2, val2, name3, val3);
      enablePrint = !enable;
    }

    private static void print3(String name1, Object val1, String name2, Object val2, String name3, Object val3) {
      if (enablePrint)
        printRaw(printStr(new String[]{name1, name2, name3}, new Object[]{val1, val2, val3}));
    }

    private static void printRaw(Object obj) {
      if (enablePrintGlobal)
        System.err.println(obj);
    }

    private static String printStr(String[] names, Object[] vals) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < names.length; i++) {
        sb.append(names[i]).append(" = ").append(vals[i]).append(" ");
      }
      return sb.toString();
    }
  }
}