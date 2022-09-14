package hackercup.Y2022.round1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://www.facebook.com/codingcompetitions/hacker-cup/2022/round-1/problems/A2" target="_top">https://www.facebook.com/codingcompetitions/hacker-cup/2022/round-1/problems/A2</a>
 * @since 10/09/22 11:22 pm
 */
public class ProblemB1 {
  //static FastScanner sc = new FastScanner(System.in);
  static FastScanner sc = new FastScanner(new File("/Users/pdoshi/Downloads/watering_well_chapter_2_input.txt"));
  static int mod = (int) 1e9 + 7;
  static int p = 53;
  static int maxPoint = 3000000 + 1;//2 * 500000 + 1;

  public static void main(String[] args) throws IOException {
    try (PrintWriter out = new PrintWriter(System.out)) {
      File f = new File("/Users/pdoshi/hackercup/q2022/round1ProblemBTwo" + System.currentTimeMillis() + ".output");
      f.createNewFile();

      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        FileWriter fw = new FileWriter(f, true);
        print("Case #" + tt + ": ", fw);
        int n = sc.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        Set<Integer> distVals = new TreeSet<>();
        for (int i = 0; i < n; i++) {
          x[i] = sc.nextInt();
          y[i] = sc.nextInt();
          distVals.add(x[i]);
          distVals.add(y[i]);
        }
        //read queries
        int q = sc.nextInt();
        int[][] queries = new int[q][2];
        for (int i = 0; i < q; i++) {
          queries[i][0] = sc.nextInt();
          queries[i][1] = sc.nextInt();
          distVals.add(queries[i][0]);
          distVals.add(queries[i][1]);
        }
        Map<Integer, Integer> mapping = new TreeMap<>();
        int id = 1;
        for (int val : distVals)
          mapping.put(val, id++);
        
        //(x - y) ^ 2 -> x^2 - 2 * x * y + y^2
        long[] xPresent = new long[maxPoint];
        long[] yPresent = new long[maxPoint];
        long[] xNormal = new long[maxPoint];
        long[] yNormal = new long[maxPoint];
        long[] xSquare = new long[maxPoint];
        long[] ySquare = new long[maxPoint];
        for (int i = 0; i < n; i++) {
          int xKey = mapping.get(x[i]);
          int yKey = mapping.get(y[i]);
          xPresent[xKey]++;
          yPresent[yKey]++;
          xNormal[xKey] = add(xNormal[xKey], x[i]);
          yNormal[yKey] = add(yNormal[yKey], y[i]);
          xSquare[xKey] = add(xSquare[xKey], mul(x[i], x[i]));
          ySquare[yKey] = add(ySquare[yKey], mul(y[i], y[i]));
        }
        long[] psXPresent = ps(xPresent);
        long[] psYPresent = ps(yPresent);
        long[] psXNormal = ps(xNormal);
        long[] psYNormal = ps(yNormal);
        long[] psXSquare = ps(xSquare);
        long[] psYSquare = ps(ySquare);
       /* System.out.println("psXPresent = " + Arrays.toString(psXPresent));
        System.out.println("psYPresent = " + Arrays.toString(psYPresent));
        System.out.println("psXNormal = " + Arrays.toString(psXNormal));
        System.out.println("psYNormal = " + Arrays.toString(psYNormal));
        System.out.println("psXSquare = " + Arrays.toString(psXSquare));
        System.out.println("psYSquare = " + Arrays.toString(psYSquare));*/
        long sum = 0;
        for(int[] qq : queries) {
          int qx = mapping.get(qq[0]);
          int qy = mapping.get(qq[1]);
          //System.out.println(qx + " " + qy);
          long sumC = 0;
          //solve for x left
          long pointCntx = add(rangeSum(psXPresent, 0, qx - 1), rangeSum(psXPresent, qx + 1, maxPoint - 1)); // total points on left and right
          //System.out.println("pointCntx = " + pointCntx);
          sumC = add(sumC, mul(qq[0], qq[0], pointCntx));
          sumC = add(sumC, (add(rangeSum(psXSquare, 0, qx - 1), rangeSum(psXSquare, qx + 1, maxPoint - 1))));
          sumC = sub(sumC, mul(2, qq[0], (add(rangeSum(psXNormal, 0, qx - 1), rangeSum(psXNormal, qx + 1, maxPoint - 1)))));
          //System.out.println("sumC = " + sumC);
          //solve for y down
          long pointCnty = add(rangeSum(psYPresent, 0, qy - 1), rangeSum(psYPresent, qy + 1, maxPoint - 1)); // total points on left and right
          sumC = add(sumC, mul(qq[1], qq[1], pointCnty));
          sumC = add(sumC, (add(rangeSum(psYSquare, 0, qy - 1), rangeSum(psYSquare, qy + 1, maxPoint - 1))));
          sumC = sub(sumC, mul(2, qq[1], (add(rangeSum(psYNormal, 0, qy - 1), rangeSum(psYNormal, qy + 1, maxPoint - 1)))));
          //solve for y up
          //System.out.println("sumC = " + sumC);
          sum = add(sum, sumC);
          //System.out.println("sum = " + sum);
        }
        print(sum, fw);
        print("\n", fw);
        fw.close();
      }
    }
  }

  private static long[] ps(long[] arr) {
    long cs = 0;
    long[] ans = new long[arr.length];
    for (int i = 0; i < arr.length; i++) {
      cs = add(cs, arr[i]);
      ans[i] = cs;
    }
    return ans;
  }

  private static long rangeSum(long[] ps, int l, int r) {
    if (inside(0, ps.length - 1, l) && inside(0, ps.length - 1, r))
      return l == 0 ? ps[r] : ps[r] - ps[l - 1];
    else return 0;
  }

  private static boolean inside(int x, int y, int i) {
    return x <= i && i <= y;
  }

  /*
  5 1 2 2 3
  2 2 3 5 1
   */
  private static boolean same(long[] hashA, long[] hashB, int[] arr, int[] brr, int l1, int r1, int l2, int r2) {
    //System.out.println("indexes : " + l1 + " " + r1 + " " + l2 + " " + r2);
    if (r1 - l1 != r2 - l2)
      System.out.println("error: invalid indexes : " + l1 + " " + r1 + " " + l2 + " " + r2);
    long sub1 = sub(hashA[r1], l1 - 1 >= 0 ? hashA[l1 - 1] : 0);
    long hash1 = div(sub1, fastExpo(p, l1));
    long sub2 = sub(hashB[r2], l2 - 1 >= 0 ? hashB[l2 - 1] : 0);
    long hash2 = div(sub2, fastExpo(p, l2));
    //System.out.println("hash1 = " + hash1);
    //System.out.println("hash2 = " + hash2);
    return hash1 % mod == hash2 % mod;
  }

  static long add(long a, long b) {
    return ((a + b) % mod);
  }

  static long sub(long a, long b) {
    return (a - b + mod) % mod;
  }

  static long mul(long a, long b) {
    return ((a * b) % mod);
  }

  static long mul(long a, long b, long c) {
    return mul(mul(a, b), c);
  }

  static int div(long a, long b) {
    return (int) ((mul(a, modInv(b))) % mod);
  }

  static private long modInv(long n) {
    return fastExpo(n, mod - 2);
  }

  private static long fastExpo(long n, long power) {
    if (power == 0)
      return 1;
    if (power == 1)
      return n;
    //long res = fastExpo(n, power / 2) % mod;
    if (power % 2 == 0) {
      long res = fastExpo(n, power / 2);
      return mul(res, res);
    } else {
      long res = fastExpo(n, power / 2);
      res = mul(res, res);
      return mul(n, res);
    }
  }


  private static long[] hash(int[] arr) {

    long hashV = 0;
    long p_pow = 1;
    long[] hashArray = new long[arr.length];
    for (int i = 0; i < arr.length; i++) {
      hashV = add(hashV, mul(arr[i], p_pow));
      hashArray[i] = hashV;
      p_pow = mul(p_pow, p);
    }
    return hashArray;
  }
  // K = 1
  // 1 2 
  // 1 2


  private static void print(Object o, FileWriter fw) throws IOException {
    System.out.print(o);
    fw.write(o.toString());
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