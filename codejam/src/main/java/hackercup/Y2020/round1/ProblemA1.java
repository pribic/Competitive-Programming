package hackercup.Y2020.round1;

import java.util.Arrays;
import java.util.Scanner;

public class ProblemA1 {

  public static final int MODULO = 1_000_000_000 + 7;

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n, k, w;
        n = sc.nextInt();
        k = sc.nextInt();
        w = sc.nextInt();
        Pair[] pair = new Pair[n];
        for (int j = 0; j < k; j++) {
          pair[j] = new Pair();
          pair[j].l = sc.nextInt();
        }
        int AL, BL, CL, DL;

        AL = sc.nextInt();
        BL = sc.nextInt();
        CL = sc.nextInt();
        DL = sc.nextInt();

        for (int j = 0; j < k; j++) {
          pair[j].h = sc.nextInt();
        }

        int AH, BH, CH, DH;

        AH = sc.nextInt();
        BH = sc.nextInt();
        CH = sc.nextInt();
        DH = sc.nextInt();

        for (int i = k; i < n; i++) {
          pair[i] = new Pair();
          pair[i].l = next(AL, pair[i - 2].l, BL, pair[i - 1].l, CL, DL);
          pair[i].h = next(AH, pair[i - 2].h, BH, pair[i - 1].h, CH, DH);
        }

        Arrays.sort(pair);

        System.out.println("Arrays.toString(pair) = " + Arrays.toString(pair));

        int ans = 1;

        int[] arr = new int[pair[n - 1].l + w + 1];

        int[] prefixSum = new int[arr.length + 1];

        prefixSum[0] = 0;


        //- 2 3 3 3 
        //0 0 2 5 8 11  
        for (int i = 0; i < n; i++) {
          Pair p = pair[i];
          for (int j = p.l; j < p.l + w; j++) {
            arr[j] = Math.max(arr[j], p.h);
            prefixSum[j + 1] = (prefixSum[j] + arr[j]) % MODULO;
          }
          ans = (ans * prefixSum[p.l + w + 1]) % MODULO;
        }
        System.out.println(ans);
      }
    }


  }

  static class SegTree {

    int size;

    int[] operations;

    int NO_OPERATION = 0;

    public SegTree(int n) {
      size = 1;
      while (size < n) size *= 2;
      operations = new int[2 * size - n];
    }


    void propogate(int x, int lx, int rx) {
      if ((rx - lx > 1) && operations[x] != NO_OPERATION) {
        operations[2 * x + 1] = Math.max(operations[2 * x + 1], operations[x]);
        operations[2 * x + 2] = Math.max(operations[2 * x + 2], operations[x]);
        operations[x] = NO_OPERATION;
      }
    }

    public int get(int l, int r) {
      return get(l, r, 0, 0, size);
    }

    private int get(int l, int r, int x, int lx, int rx) {
      propogate(x, lx, rx);
      //entirely in
      if (lx >= l && rx <= r) {
        return operations[x];
      }

      //entirely out
      if (rx <= l || lx >= r)
        return 0;

      //go both ways.

      int mid = (lx + rx) / 2;

      int max1 = get(l, r, 2 * x + 1, lx, mid);
      int max2 = get(l, r, 2 * x + 2, mid, rx);

      return Math.max(max1, max2);
    }

    public void operation(int l, int r, int val) {
      operations(l, r, val, 0, 0, size);
    }

    private void operations(int l, int r, int val, int x, int lx, int rx) {
      propogate(x, lx, rx);

      if (lx >= r || rx <= l)
        return;

      if (lx >= l && rx <= r) {
        operations[x] = Math.max(operations[x], val);
        return;
      }

      int mid = (lx + rx) / 2;

      operations(l, r, val, 2 * x + 1, lx, mid);
      operations(l, r, val, 2 * x + 2, mid, rx);
    }
  }

  static int next(int a, int b, int c, int d, int e, int f) {
   // System.out.println("next");
    return (a * b + c * d + e) % f + 1;
  }

  static class Pair implements Comparable<Pair> {
    int l, h;

    public Pair() {

    }

    public Pair(int l, int h) {
      this.l = l;
      this.h = h;
    }

    @Override
    public int compareTo(Pair o) {
      return this.l - o.l;
    }

    @Override
    public String toString() {
      return l + " " + h;
    }
  }

  //TODO modulo
  private static int perimeter(int w, int h) {
    return (2 * w % MODULO + 2 * h % MODULO) % MODULO;
  }

}
