package kickstart.Y2021.round1F.TrashBins;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/0000000000435bae/0000000000887c32" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/0000000000435bae/0000000000887c32</a>
 * @since 22/08/21 9:16 AM
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        char[] bins = sc.next().toCharArray();
        int[] left = new int[n];
        int[] right = new int[n];
        int prev = Integer.MIN_VALUE / 2;
        for (int i = 0; i < n; i++) {
          if (bins[i] == '1') {
            left[i] = i;
            prev = i;
          } else {
            left[i] = prev;
          }
        }
        int next = Integer.MAX_VALUE / 2;
        for (int i = n - 1; i >= 0; i--) {
          if (bins[i] == '1') {
            right[i] = i;
            next = i;
          } else {
            right[i] = next;
          }
        }
        long ans = 0;
        for(int i = 0; i < n; i++) {
          ans += Math.min(i - left[i], right[i] - i);
        }
        System.out.println(ans);
      }
    }

  }


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