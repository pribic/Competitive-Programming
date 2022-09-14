package codeforce.globalround.y2021.r15;

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
 * @see <a href="aa" target="_top">aa</a>
 * @since 27/07/21 11:49 AM
 */
public class aa {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String str = sc.next();
        System.out.println(Arrays.toString(new aa().solve("255255255255")));
        System.out.println(Arrays.toString(new aa().solve("2542540123")));
      }
    }
  }

  List<String> ans = new ArrayList<>();

  public String[] solve(String ip) {
    boolean[] dot = new boolean[ip.length() + 1];
    solve(ip, 1, dot, 3);
    String[] ans1 = new String[ans.size()];
    int idx = 0;
    for (String s : ans) ans1[idx] = ans.get(idx++);
    return ans1;
  }

  // st indicates from where i want to start
  void solve(String ip, int st, boolean[] dot, int availDot) {
    //System.out.println(ip + " " + st + " " + Arrays.toString(dot) + " " + availDot);
    if (st > ip.length() || availDot < 0)
      return;
    if (availDot == 0) {
      //System.out.println(ip + " " + st + " " + Arrays.toString(dot) + " " + availDot);
      String cur = "";
      int[] dotIndex = new int[3];
      int idx = 0;
      for (int i = 0; i < dot.length; i++) {
        if (dot[i])
          dotIndex[idx++] = i;
      }
      int start = 0;
      boolean valid = true;
      for (int i = 0; i < 3 && valid; i++) {
        int end = dotIndex[i];
        String str = ip.substring(start, end);
        Integer val = Integer.valueOf(str);
        if (val < 0 || val > 255 || (str.length() > 1 && str.startsWith("0")))
          valid = false;
        cur += ip.substring(start, end) + '.';
        start = end;
      }
      String str = ip.substring(start);
      Integer val = Integer.valueOf(str);
      if (val < 0 || val > 255 || ip.length() - start > 3 || (str.length() > 1 && str.startsWith("0")))
        valid = false;
      else
        cur += ip.substring(start);
      if (valid)
        ans.add(cur);

    } else {
      for (int i = 1; i <= 3 && st + i - 1 < dot.length; i++) {
        dot[st + i - 1] = true;
        solve(ip, st + i, dot, availDot - 1);
        dot[st + i - 1] = false;
      }
      //dot[st] = false;
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