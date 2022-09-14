package hackercup.Y2022.qualification;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ProblemA {

  public static void main(String[] args) throws IOException {
    try (Scanner sc = new Scanner(System.in)) {
      File f = new File("/Users/pdoshi/hackercup/q2022/problemA" + System.currentTimeMillis() + ".output");
      f.createNewFile();

      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        FileWriter fw = new FileWriter(f, true);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] fr = new int[101];
        for (int i = 0; i < n; i++) {
          fr[sc.nextInt()]++;
        }
        boolean valid = true;
        int cntone = 0;
        for (int ff : fr) {
          if (ff > 2)
            valid = false;
          else if (ff == 2)
            k--;
          else if (ff == 1)
            cntone++;
        }
        if (valid && cntone > 2 * k)
          valid = false;
        System.out.print("Case #" + tt + ": ");
        fw.append("Case #" + tt + ": ");
        System.out.println(valid ? "YES" : "NO");
        fw.append(valid ? "YES\n" : "NO\n");
        fw.close();
      }
    }
  }

}
