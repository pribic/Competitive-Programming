package codeforce.div3.r748;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1593/problem/C" target="_top">https://codeforces.com/contest/1593/problem/C</a>
 * @since 13/10/21 8:26 PM
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] mouse = new int[k];
        for (int i = 0; i < k; i++) {
          mouse[i] = sc.nextInt();
        }
        sort(mouse);
        int reach = 0;
        int cat = 0;
        for (int i = k - 1; i >= 0; i--) {
          if (mouse[i] > cat) {
            reach++;
            cat += n - mouse[i];
          } else {
            break;
          }
        }

        System.out.println(reach);
      }
    }
  }

  private static List<Integer> sort(int[] mouse) {
    List<Integer> list = new ArrayList<>();
    for (int m : mouse) list.add(m);
    Collections.sort(list);
    for (int i = 0; i < list.size(); i++) {
      mouse[i] = list.get(i);
    }
    return list;
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