package kickstart.Y2021.round1C.AlienGenerator;

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
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/0000000000435c44/00000000007ec1cb" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/0000000000435c44/00000000007ec1cb</a>
 * @since 23/05/21 5:22 PM
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    long curSum = 1;
    int i = 1;
    long[] prebuild = new long[1414215];
    while (curSum <= 1000000000000L) {
      //out.println(i + " " + curSum);
      prebuild[i] = curSum;
      i++;
      curSum += i;
    }
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        long g = sc.nextLong();
        long ans = 1;

        i = 2;
        while (i <= prebuild.length) {
          //check if i can be an ans
          long sum = prebuild[i - 1];
          long remaining = g - sum;
          if(remaining <= 0)
            break;
          if(remaining % i == 0)
            ans++;
          i++;
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