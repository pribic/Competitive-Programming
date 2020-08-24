package hackercup.Y2020.qualification;

import com.sun.tools.example.debug.tty.TTY;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ProblemA {

  public static void main(String[] args) throws IOException {
    try (Scanner sc = new Scanner(System.in)) {
      File f = new File("/Users/pdoshi/hackercup/q2020/problemA" + System.currentTimeMillis() + ".output");
      f.createNewFile();

      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String in = sc.next();
        String out = sc.next();
        int[][] ans = new int[n][n];


        for (int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
            ans[i][j] = 2000; // initialize with some higher number
          }
        }

        ans[0][1] = 1;
        for (int i = 1; i < n - 1; i++) {
          ans[i][i - 1] = ans[i][i + 1] = 1;
        }
        ans[n - 1][n - 2] = 1;

        for (int i = 0; i < n; i++) {
          ans[i][i] = 0;
        }

        //remove edge as per input

        //inbound 
        for (int i = 0; i < n; i++) {
          if (in.charAt(i) == 'N') {
            if (i - 1 >= 0)
              ans[i - 1][i] = 2000;
            if (i + 1 < n)
              ans[i + 1][i] = 2000;
          }
        }

        //outbound
        for (int i = 0; i < n; i++) {
          if (out.charAt(i) == 'N') {
            if (i - 1 >= 0)
              ans[i][i - 1] = 2000;
            if (i + 1 < n)
              ans[i][i + 1] = 2000;
          }
        }

        

        //floyd warshall starts
        for (int k = 0; k < n; k++) {
          for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
              ans[i][j] = Math.min(ans[i][j], ans[i][k] + ans[k][j]);
            }
          }
        }
        //ends
        FileWriter fw = new FileWriter(f, true);

        System.out.println("Case #" + tt + ":");
        fw.append("Case #" + tt + ":\n");
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
            if (i == j) {
              System.out.print('Y');
              fw.append('Y');
            } else if (ans[i][j] > 1000) {
              System.out.print('N');
              fw.append('N');
            } else {
              System.out.print('Y');
              fw.append('Y');
            }

          }
          System.out.println();
          fw.append("\n");
        }
        fw.close();
      }
    }
  }

  private static void print(int j, int start) {
    System.out.println("marking false i=" + j + ", j=" + start);
  }
}
