package codeforce.div4.r817;

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
 * @see <a href="https://codeforces.com/contest/1722/problem/D" target="_top">https://codeforces.com/contest/1722/problem/D</a>
 * @since 30/08/22 8:53 PM
 */
public class D {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String str = sc.next();
        // 0 1 2 3 4 5 6 n=7

        List<Integer> benifits = new ArrayList<>();
        long maxBenifits = 0;
        long curBenifit = 0;
        for (int i = 0; i < n / 2; i++) {
          if (str.charAt(i) == 'L') {
            //this has potential
            benifits.add(n - i - 1 - i);
          }
          if (str.charAt(i) == 'R')
            curBenifit += n - i - 1;
          else
            curBenifit += i;
          maxBenifits += n - i - 1;
        }
        
        for (int i = n / 2 + n % 2; i < n; i++) {
          if (str.charAt(i) == 'R') {
            benifits.add(i - (n - i - 1));
          }
          if (str.charAt(i) == 'R')
            curBenifit += n - i - 1;
          else
            curBenifit += i;
          maxBenifits += i;

        }
        if (n % 2 == 1) {
          curBenifit += n / 2;
        }
        Collections.sort(benifits);
        StringBuilder ans = new StringBuilder();
        //System.out.println(benifits);
        //System.out.println("curBenifit = " + curBenifit);
        long cs = 0;
        for (int i = 0; i < n; i++) {
          if (i + 1 > benifits.size())
            ans.append(cs + curBenifit).append(" ");
          else {
            if (benifits.size() - i - 1 < benifits.size())
              cs += benifits.get(benifits.size() - i - 1);
            ans.append(cs + curBenifit).append(" ");
          }
        }
        System.out.println(ans);

      }
    }
  }
  /*
  L R R R L L L R L L R L
  0 1 2 3 4 5 6 7 8 9 10 11
  0 + 10 + 9 + 8 + 4 + 5 + 6 + 4 +  8 + 9 + 1 + 11
  
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