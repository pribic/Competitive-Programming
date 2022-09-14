package hackercup.Y2022.qualification;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ProblemB1 {

  public static void main(String[] args) throws IOException {
    try (Scanner sc = new Scanner(System.in)) {
      File f = new File("/Users/pdoshi/hackercup/q2022/problemBOne" + System.currentTimeMillis() + ".output");
      f.createNewFile();

      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        FileWriter fw = new FileWriter(f, true);
        int r = sc.nextInt();
        int c = sc.nextInt();
        char[][] grid = new char[r][c];
        for (int i = 0; i < r; i++)
          grid[i] = sc.next().toCharArray();
        System.out.print("Case #" + tt + ": ");
        fw.append("Case #" + tt + ": ");
        if (!tree(grid)) {
          print(fw, "Possible\n");
          //in this case, we print grid as it is
          for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
              print(fw, grid[i][j] + "");
            }
            print(fw, "\n");
          }
        } else {
          //we have atleast one tree
          if (r == 1 || c == 1)
            print(fw, "Impossible\n");
          else {
            print(fw, "Possible\n");
            printAllTree(fw, r, c);
          }
        }
        fw.close();
      }
    }

  }

  public static void print(FileWriter fw, String s) throws IOException {
    System.out.print(s);
    fw.append(s);
  }

  private static void printAllTree(FileWriter fw, int r, int c) throws IOException {
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        System.out.print("^");
        fw.append("^");
      }
      System.out.println();
      fw.append("\n");
    }
  }

  private static boolean tree(char[][] grid) {
    for (char[] row : grid)
      for (char cell : row)
        if (cell == '^')
          return true;
    return false;
  }

}
