package codejam.Y2022.round1C.squary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codingcompetitions.withgoogle.com/codejam/round/000000000087711b/0000000000acd59d" target="_top">https://codingcompetitions.withgoogle.com/codejam/round/000000000087711b/0000000000acd59d</a>
 * @since 25/04/22 4:08 PM
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      Scanner sc = new Scanner(System.in);
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        int k = sc.nextInt();
        long[] arr = new long[n];
        long basesum = 0;
        int zc = 0;
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextLong();
          basesum += arr[i];
          zc = zc + (arr[i] == 0 ? 1 : 0);
        }
        long cursum = 0;
        for (int i = 0; i < n - 1; i++) {
          for (int j = i + 1; j < n; j++) {
            cursum += arr[i] * arr[j];
          }
        }
        if (basesum == 0) {
          if (zc == n)
            System.out.println(0);
          else {
            System.out.println("IMPOSSIBLE");
          }
        } else if (cursum % basesum == 0) {
          System.out.println(-(cursum / basesum));
        } else {
          System.out.println("IMPOSSIBLE");
        }
      }
    }
  }
  /*
 
 (a)a+(2a+b)b+(2a+2b+c)c+(2a+2b+2c+d)d.
0 = ab + ac + bc + (a + b + c)d
d = - (ab + ac + bc) / (a + b + c)
*/


  static class FastScanner {
    BufferedReader br;
    StringTokenizer st;

    public FastScanner(File f) {
      try {
        br = new BufferedReader(new FileReader(f));
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }

    public FastScanner(InputStream f) {
      br = new BufferedReader(new InputStreamReader(f), 32768);
    }

    String next() {
      while (st == null || !st.hasMoreTokens()) {
        String s = null;
        try {
          s = br.readLine();
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (s == null)
          return null;
        st = new StringTokenizer(s);
      }
      return st.nextToken();
    }

    boolean hasMoreTokens() {
      while (st == null || !st.hasMoreTokens()) {
        String s = null;
        try {
          s = br.readLine();
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (s == null)
          return false;
        st = new StringTokenizer(s);
      }
      return true;
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    long nextLong() {
      return Long.parseLong(next());
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }
  }
}