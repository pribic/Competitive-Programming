package hackercup.Y2022.qualification;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ProblemC2 {

  public static void main(String[] args) throws IOException {
    try (Scanner sc = new Scanner(System.in)) {
      File f = new File("/Users/pdoshi/hackercup/q2022/problemCOne" + System.currentTimeMillis() + ".output");
      f.createNewFile();

      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        FileWriter fw = new FileWriter(f, true);
        print(fw, "Case #" + tt + ":\n");
        int n = sc.nextInt();
        String c1 = sc.next();
        for (int i = 0; i < n - 1; i++) {
          if (c1.startsWith(".")) {
            print(fw, "-");
          } else {
            print(fw, ".");
          }
          String bs = Integer.toBinaryString(i);
          while (bs.length() < 8)
            bs = "." + bs;
          bs = bs.replaceAll("0", ".");
          bs = bs.replaceAll("1", "-");
          print(fw, bs);
          print(fw, "\n");
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
