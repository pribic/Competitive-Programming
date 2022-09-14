package kickstart.Y2021.round1D.PrimesAndQueries;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/00000000004361e3/000000000082bcf4" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/00000000004361e3/000000000082bcf4</a>
 * @since 11/07/21 12:22 PM
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        int q = sc.nextInt();
        int p = sc.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextLong();
        }
        SegTree[] segTrees = new SegTree[5];
        for (int s = 1; s <= 4; s++) {
          segTrees[s] = new SegTree();
          for (int i = 1; i <= n; i++) {
            segTrees[s].update(i, calcV(p, arr[i], s + 1));
          }
        }
        while (q-- > 0) {
          int type = sc.nextInt();
          if (type == 1) {
            int pos = sc.nextInt() - 1;
            int newVal = sc.nextInt();
            for (int s = 1; s <= 4; s++)
              segTrees[s].update(pos, calcV(p, newVal, s));
          } else {
            int s = sc.nextInt();
            int l = sc.nextInt();
            int r = sc.nextInt();
            System.out.print(segTrees[s].query(l, r));
          }
        }
        System.out.println();
      }
    }
  }

  static class SegTree {
    int[] arr;

    SegTree(int[] input) {
      int len = input.length;
      int sz = 0;
      while (len > 0) { // 1101
        len >>= 1;
        sz++;
      }
      arr = new int[sz];
      for (int i = 0; i < input.length; i++)
        update(i, input[i]);
    }

    public SegTree() {
      
    }

    void update(int idx, int val) {
      update(idx, val, 1, 1, arr.length);
    }

    void update(int idx, int val, int cur, int l, int r) {
      if (idx < l || idx > r)
        return;
      arr[cur] += val;
      int mid = l + (r - l) / 2;
      update(idx, val, cur * 2, l, mid);
      update(idx, val, cur * 2 + 1, mid + 1, r);
    }

    long query(int l, int r) {
      return query(l, r, 1, 1, arr.length);
    }

    long query(int l, int r, int cur, int lx, int rx) {
      if (lx < l || rx > r)
        return 0;
      if (lx >= l && rx <= r)
        return arr[cur];
      int mid = lx + (rx - lx) / 2;
      return query(l, r, 2 * cur, lx, mid) + query(l, r, 2 * cur + 1, mid + 1, rx);

    }
  }

  private static int calcV(int p, long num, int s) {
    int ans = 0;
    long diff = (long) (Math.pow(num, s) - Math.pow(num % p, s));
    while (diff > 0 && diff % p == 0) {
      ans++;
      diff /= p;
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