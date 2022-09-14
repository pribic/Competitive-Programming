package codeforce.div3.r797;

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
 * @see <a href="https://codeforces.com/contest/1690/problem/F" target="_top">https://codeforces.com/contest/1690/problem/F</a>
 * @since 07/06/22 10:06 PM
 */
public class F {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String str = sc.next();
        int[] perm = new int[n];
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
          perm[i] = sc.nextInt() - 1;
          uf.union(i, perm[i]);
        }
        long lcmC = 1;
        for (int i = 0; i < n; i++) {
          int leader = uf.get(i);
          if(i != leader)
            continue;
          StringBuilder sb = new StringBuilder();
          sb.append(str.charAt(i));
          int nxt = perm[i];
          while(nxt != i) {
            //consume nxt 
            sb.append(str.charAt(nxt));
            nxt = perm[nxt];
          }
          //System.out.println("sb = " + sb);
          //find period
          int curPeriod = sb.length();
          for(int period = 1; period <= sb.length() / 2; period++) {
            if(sb.length() % period == 0) {
              //period is a division, so it could be one the possible period
              boolean flag = true;
              for(int ii = 0; flag && ii < sb.length(); ii++)
                if(sb.charAt(ii) != sb.charAt(ii % period))
                  flag = false;
              if(flag) {
                //System.out.println("period = " + period);
                curPeriod = period;
                break;
              }
            }
          }
          lcmC = lcm(lcmC, curPeriod);
        }
        System.out.println(lcmC);

      }
    }
  }

  private static long gcd(long a, long b) {
    return b == 0 ? a : gcd(b, a % b);
  }

  private static long lcm(long a, long b) {
    return a * b / gcd(a, b);
  }

  static class UnionFind { // 0 based indexing
    int[] parent;
    int[] size;
    int sz;

    UnionFind(int n) {
      parent = new int[n];
      size = new int[n];
      Arrays.fill(size, 1);
      for (int i = 0; i < parent.length; i++)
        parent[i] = i;
      sz = n;
    }

    int get(int x) {
      return parent[x] == x ? x : get(parent[x]);
    }

    boolean union(int a, int b) {
      int pa = get(a);
      int pb = get(b);
      if (pa == pb)
        return false;
      //we want pa to be new parent
      if (size[pa] < size[pb]) {
        int t = pa;
        pa = pb;
        pb = t;
      }
      size[pa] += size[pb];
      parent[pb] = pa;
      sz--;
      return true;
    }

    int getSize() {
      return sz;
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