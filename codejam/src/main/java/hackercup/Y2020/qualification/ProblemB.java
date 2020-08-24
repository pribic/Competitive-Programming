package hackercup.Y2020.qualification;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ProblemB {

  public static void main(String[] args) throws IOException {
    try (Scanner sc = new Scanner(System.in)) {
      File f = new File("/Users/pdoshi/hackercup/q2020/problemB" + System.currentTimeMillis() + ".output");
      f.createNewFile();

      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String str = sc.next();

        int diff = 0;
        for (char c : str.toCharArray()) {
          if (c == 'A')
            diff++;
          else
            diff--;
        }
        FileWriter fw = new FileWriter(f, true);

        String ans = "Case #" + tt + ": " + (Math.abs(diff) <= 1 ? 'Y' : 'N');
        System.out.println(ans);
        fw.write(ans + "\n");
        fw.close();
      }
    }
  }

  private static void print(int j, int start) {
    System.out.println("marking false i=" + j + ", j=" + start);
  }
}
