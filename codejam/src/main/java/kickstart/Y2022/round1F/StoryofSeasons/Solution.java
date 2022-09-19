package kickstart.Y2022.round1F.StoryofSeasons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import static java.lang.Long.compare;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/00000000008cb409/0000000000bef319" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/00000000008cb409/0000000000bef319</a>
 * @since 18/09/22 11:32 pm
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int d = sc.nextInt();
        int n = sc.nextInt();
        int x = sc.nextInt();
        int[] quantity = new int[n];
        int[] maturityTime = new int[n];
        long[] value = new long[n];
        Integer[] id = new Integer[n];
        long[][] all = new long[n][4];
        for (int i = 0; i < n; i++) {
          all[i][0] = sc.nextInt(); // quantity
          all[i][1] = sc.nextInt(); // maturity
          all[i][2] = sc.nextInt(); // value || profit
          all[i][3] = i; // id
        }
        Arrays.sort(all, comparingLong(a -> a[1]));
        //System.out.println(Arrays.deepToString(all));
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((i, j) -> compare(all[j][2], all[i][2]));
        int idx = 0; // currently examing idx element
        long ans = 0L;
        int remainingX = x;
        //which days are important to us
        for (int i = 0; i < n; i++) {
          int j = i;
          while (j < n && all[j][1] == all[i][1])
            j++;
          //[i, j) contains same maturity
          long sameCnt = j - i;
          long totalQuantity = sameCnt * all[i][0];
          //pick top one with max profit
          int pick = priorityQueue.remove();
          long q = all[pick][0];
          ans += all[pick][2] * Math.min(remainingX, q);
          all[pick][0] -= Math.min(remainingX, q); // we consumed these much
          if (q > remainingX)
            priorityQueue.add(pick);
          if (q >= remainingX)
            remainingX = x; //reset for next day
          else {
            remainingX -= q; // still on same day
            i++; // to stay on same day
          }
        }
        System.out.println(ans);
      }
    }
  }

  static class SegmentTree { // [) - inclusive - exclusive range query

    int size;
    long[] arr;

    int inputSize;

    public static long neutralElement = Long.MIN_VALUE / 2;

    public SegmentTree(int n) {
      size = 1;
      inputSize = n;
      while (size < n) size *= 2;
      arr = new long[2 * size - 1];
      Arrays.fill(arr, neutralElement);
    }

    public void print() {
      //Arrays.stream(arr).forEachOrdered(x -> System.out.print(x + " "));
    }

    private void build(long[] input, int x, int lx, int rx) {
      if (rx - lx == 1) {
        if (lx < inputSize)
          arr[x] = input[lx];
        return;
      }
      int mid = (lx + rx) / 2;
      build(input, 2 * x + 1, lx, mid);
      build(input, 2 * x + 2, mid, rx);
      arr[x] = op(arr[2 * x + 1], arr[2 * x + 2]);
    }

    public void build(long[] arr) {
      build(arr, 0, 0, size);
    }

    private void set(int index, long val, int x, int lx, int rx) {
      if (rx - lx == 1) {
        arr[x] = val;
        return;
      }
      int mid = (lx + rx) / 2;
      if (index < mid) {
        set(index, val, 2 * x + 1, lx, mid);
      } else {
        set(index, val, 2 * x + 2, mid, rx);
      }
      arr[x] = op(arr[2 * x + 1], arr[2 * x + 2]);
    }

    public void set(int index, long val) {
      set(index, val, 0, 0, size);
    }

    public long get(int l, int r) {
      return get(l, r, 0, 0, size);
    }

    public long get(int l, int r, int x, int lx, int rx) {
      if (lx >= r || l >= rx)
        return neutralElement;
      if (lx >= l && rx <= r)
        return arr[x];
      int mid = (lx + rx) / 2;
      long max1 = get(l, r, 2 * x + 1, lx, mid);
      long max2 = get(l, r, 2 * x + 2, mid, rx);
      return op(max1, max2);
    }

    private long op(long a, long b) {
      return Math.max(a, b);
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