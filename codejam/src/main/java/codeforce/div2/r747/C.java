package codeforce.div2.r747;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
 * @see <a href="https://codeforces.com/contest/1594/problem/C" target="_top">https://codeforces.com/contest/1594/problem/C</a>
 * @since 08/10/21 8:54 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        char c = sc.next().charAt(0);
        String str = sc.next();
        if (allSame(str, c)) {
          System.out.println(0);
        } else {
          if (str.charAt(n - 1) == c) {
            System.out.println(1);
            System.out.println(n);
          } else {
            str = '.' + str;
            List<Integer> idxes = new ArrayList<>();
            for (int i = 1; i <= n; i++)
              if (str.charAt(i) == c)
                idxes.add(i);
            int[] suffixSum = new int[str.length()]; // sS[i] = number of another character from [i + 1, end]
            int curSum = 0;
            for (int i = str.length() - 1; i > 0; i--) {
              suffixSum[i] = curSum;
              if (str.charAt(i) != c)
                curSum++;
            }
            boolean found = false;
            int ansI = -1;
            if (idxes.size() > 0 && idxes.get(idxes.size() - 1) * 2 >= str.length()) {
              found = true;
              ansI = idxes.get(idxes.size() - 1);
            }

            for (int i = idxes.size() - 1; !found && i >= 0; i--) {
              int another = 0;
              for (int idx = idxes.get(i) * 2; idx < str.length(); idx += idxes.get(i)) {
                if (str.charAt(idx) != c) {
                  another++;
                }
              }

              //check if another is same as all another after this
              if (another == suffixSum[i]) {
                found = true;
                ansI = i;
              }
            }
            if (found) {
              System.out.println(1);
              System.out.println(ansI);
            } else {
              System.out.println(2);
              System.out.println(n + " " + (n - 1));
            }
          }
        }
      }
    }
  }

  private static boolean allSame(String str, char c) {
    for (char ch : str.toCharArray())
      if (ch != c)
        return false;
    return true;
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