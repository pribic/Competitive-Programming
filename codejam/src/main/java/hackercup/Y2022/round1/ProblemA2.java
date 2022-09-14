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
import java.util.StringTokenizer;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://www.facebook.com/codingcompetitions/hacker-cup/2022/round-1/problems/A2" target="_top">https://www.facebook.com/codingcompetitions/hacker-cup/2022/round-1/problems/A2</a>
 * @since 10/09/22 11:22 pm
 */
public class ProblemA2 {
  //static FastScanner sc = new FastScanner(System.in);
  static FastScanner sc = new FastScanner(new File("/Users/pdoshi/Downloads/consecutive_cuts_chapter_2_input.txt"));
  static int mod = (int) 1e9 + 7;
  static int p = 53;

  public static void main(String[] args) throws IOException {
    try (PrintWriter out = new PrintWriter(System.out)) {
      File f = new File("/Users/pdoshi/hackercup/q2022/round1ProblemATwo" + System.currentTimeMillis() + ".output");
      f.createNewFile();

      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        FileWriter fw = new FileWriter(f, true);
        print("Case #" + tt + ": ", fw);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        int[] brr = new int[n];
        for (int i = 0; i < n; i++) {
          brr[i] = sc.nextInt();
        }
        long[] hashB = hash(brr);
        long[] hashA = hash(arr);
        int cnt = hashA[n - 1] == hashB[n - 1] ? 1 : 0;
        for (int i = 1; i < n; i++) {
          //now we consider arr[i..n-1] and brr[0 .. n - 1 - 1]
          // arr[0, i - 1] and brr[n - i, n - 1]
          if (same(hashA, hashB, arr, brr, i, n - 1, 0, n - 1 - i) && same(hashA, hashB, arr, brr, 0, i - 1, n - i, n - 1))
            cnt++;
        }
        if (cnt == 0) { // no matching starting point at all
          print("NO", fw);
          print("\n", fw);
          fw.close();
          continue;
        }
        if (k == 0) {
          //they should be same from the beginning
          print(Arrays.equals(arr, brr) ? "YES" : "NO", fw);
        } else if (k == 1) {
          //if it is already same then we can't make any more cut
          //1 2 1 2
          // 1 2 1 2
          //if they are already equal, then we need to see if we can make another cut somewhere to make them equal
          if (cnt > 1) {
            print("YES", fw);
          } else { //cnt == 1 
            print(Arrays.equals(arr, brr) ? "NO" : "YES", fw);
          }
        } else {
          //k >= 2
          if (n == 2) { // if both elements are same then we can have any value of k
            if (arr[0] == brr[0] && arr[1] == brr[1] && arr[0] == arr[1]) {
              print("YES", fw); // a -> 2 2, b -> 2 2 -> both arrays are same and same number is repeating
            } else if (Arrays.equals(arr, brr)) { // 3 5 & 3 5 
              //already same, so we need even k
              // 1 1 1 -> 2 2 2 then we can make any number of cut
              print(k % 2 == 0 ? "YES" : "NO", fw);
            } else { // 3 5 & 5 3
              //already not same, we need off k
              print(k % 2 == 1 ? "YES" : "NO", fw);
            }
          } else { // n >= 3
            print("YES", fw);
          }
        }
        print("\n", fw);
        fw.close();
      }
    }
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