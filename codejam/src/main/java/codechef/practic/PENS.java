package codechef.practic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://www.codechef.com/ICPCIN19/problems/PENS" target="_top">https://www.codechef.com/ICPCIN19/problems/PENS</a>
 * @since 11/11/21 7:11 PM
 */
public class PENS {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        String str = sc.next();
        List<String> pens = new ArrayList<>();
        for (int i = 0; i < k; i++)
          pens.add(sc.next());
        int[] dp = new int[1 << 20];
        for (int i = 0; i < pens.size(); i++)
          dp[mask(pens.get(i))] = i + 1;
        for (int mask = (1 << 20) - 1; mask >= 1; mask--) {
          for (int i = 0; i < 20; i++) {
            if (!set(mask, i) && dp[mask | (1 << i)] != 0) {
              dp[mask] = dp[mask | (1 << i)];
              break;
            }
          }
        }
        int curmask = 0;
        List<Triplet> ans = new ArrayList<>(); // we store [start, end) and pen number as 1 d array here.
        int startIdx = 0;
        int lastPen = -1;
        for (int i = 0; i < str.length(); i++) {
          curmask |= (1 << (str.charAt(i) - 'a'));
          if (dp[curmask] == 0) { // need to use new pen
            ans.add(new Triplet(startIdx, i, lastPen));
            startIdx = i;
            curmask = 1 << (str.charAt(i) - 'a');
          }
          lastPen = dp[curmask];
        }
        ans.add(new Triplet(startIdx, n, lastPen));
        System.out.println(generateAns(ans));
      }
    }

  }

  private static StringBuilder generateAns(List<Triplet> ans) {
    StringBuilder sb = new StringBuilder();
    for (Triplet triplet : ans)
      for (int st = triplet.st; st < triplet.end; st++)
        sb.append(triplet.penId).append(" ");
    return sb;
  }

  static class Triplet {
    int st, end, penId;

    public Triplet(int st, int end, int penId) {
      this.st = st;
      this.end = end;
      this.penId = penId;
    }
  }

  private static boolean set(int mask, int i) {
    return ((mask >> i) & 1) == 1;
  }

  static int mask(String s) {
    int mask = 0;
    for (char c : s.toCharArray())
      mask |= (1 << (c - 'a'));
    return mask;
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