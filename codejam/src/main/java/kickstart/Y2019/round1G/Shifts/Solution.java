package kickstart.Y2019.round1G.Shifts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import static java.lang.System.out;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/0000000000050e02/000000000018fd5e" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/0000000000050e02/000000000018fd5e</a>
 * @since 31/05/21 11:28 AM
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        int h = sc.nextInt();
        int[] ha = new int[n];
        for (int i = 0; i < n; i++) {
          ha[i] = sc.nextInt();
        }
        int[] hb = new int[n];
        for (int i = 0; i < n; i++) {
          hb[i] = sc.nextInt();
        }
        //int ans = bruteForceBase3(n, h, ha, hb);
        //int ans = mimBase3(n, h, ha, hb);
        int ans = bruteForceWithNegate(n, h, ha, hb);
        System.out.println(ans);
      }
    }
  }

  private static int bruteForceWithNegate(int n, int h, int[] ha, int[] hb) {
    int ans = 0;
    for (int i = 1; i < (1 << n); i++) {
      for (int j = 0; j < 20; j++) {
        if ((i >> j & 1) == 1) {
          //1 means b did not work
          //0 means a did not work
        }
      }
    }
    return ans;
  }

  private static int mimBase3(int n, int h, int[] ha, int[] hb) {
    int ans = 0;
    int sz1 = n / 2;
    int sz2 = n - n / 2;
    List<int[]> first = new ArrayList<>();
    List<int[]> second = new ArrayList<>();
    for (int i = 0; i < Math.pow(3, sz1); i++) {
      int[] cnt = new int[2];
      int base3 = i;
      for (int j = 0; j < sz1; j++) {
        int ld = base3 % 3;
        if (ld != 1) cnt[0] += ha[j];
        if (ld != 0) cnt[1] += hb[j];
        base3 /= 3;
      }
      first.add(cnt);
    }

    for (int i = 0; i < Math.pow(3, sz2); i++) {
      int[] cnt = new int[2];
      int base3 = i;
      for (int j = 0; j < sz2; j++) {
        int ld = base3 % 3;
        if (ld != 1)
          cnt[0] += ha[sz1 + j];
        if (ld != 0)
          cnt[1] += hb[sz1 + j];
        base3 /= 3;
      }
      second.add(cnt);
    }
    second.sort(comparingInt((int[] a) -> a[0]));
    for (int[] one : first) {
      int wa = h - one[0];
      int wb = h - one[1];
      //we need another pair from second such that pair[0] >= wa && pair[1] >= wb

      // find all pair where wa satisfies using bs, then for wb , do linear search
    }
    return ans;
  }

  private static int bruteForceBase3(int n, int h, int[] ha, int[] hb) {
    int ans = 0;
    for (int i = 0; i < Math.pow(3, n); i++) {
      long cnta = 0;
      long cntb = 0;
      int num = i;
      for (int j = 0; j < n; j++) {
        int ld = num % 3;
        switch (ld) {
          case 0: //a will work in jth shift
            cnta += ha[j];
            break;
          case 1: //b will work in jth shift
            cntb += hb[j];
            break;
          case 2: // both will work
            cnta += ha[j];
            cntb += hb[j];
            break;
        }
        num /= 3;
      }
      if (cnta >= h && cntb >= h)
        ans++;
    }
    return ans;
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