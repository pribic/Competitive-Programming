package codechef.snackdown.y2021.round1A;

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
 * @see <a href="https://www.codechef.com/SNCK1A21/problems/BINFLIP" target="_top">https://www.codechef.com/SNCK1A21/problems/BINFLIP</a>
 * @since 23/10/21 8:32 PM
 */
public class BINFLIP {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        long k = sc.nextInt();
        if (k == 0) {
          System.out.println("YES");
          System.out.println(0);
          continue;
        }
        long kk = k;
        List<Long> powers = new ArrayList<>();
        long cur = 1;
        while (cur <= n) {
          powers.add(cur);
          cur *= 2;
        }
        int ansI = -1;
        int ansJ = -1;
        outer:
        for (int i = 0; i < powers.size(); i++) {
          //nums from i till end -
          long curSum = 0;
          for (int j = i; j < powers.size(); j++) {
            curSum += powers.get(j);
            if (curSum == k) {
              ansI = i;
              ansJ = j;
              break outer;
            }
          }
          k += powers.get(i);
        }
        if (ansI == -1) {
          System.out.println("NO");
          continue;
        }
        System.out.println("YES");
        //System.out.println(powers);
        //System.out.println(ansI + " " + ansJ);
        k = kk;
        System.out.println(ansJ + 1);
        for (int i = 0; i < ansI; i++) {
          // +
          System.out.println(k + 1);
          k += powers.get(i);
        }
        for (int i = ansI; i <= ansJ; i++) {
          // -
          System.out.println(k - powers.get(i) + 1);
          k -= powers.get(i);
        }
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