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
 * @see <a href="https://www.facebook.com/codingcompetitions/hacker-cup/2022/round-1/problems/A1" target="_top">https://www.facebook.com/codingcompetitions/hacker-cup/2022/round-1/problems/A1</a>
 * @since 10/09/22 11:22 pm
 */
public class ProblemA1 {
  //static FastScanner sc = new FastScanner(System.in);
  static FastScanner sc = new FastScanner(new File("/Users/pdoshi/Downloads/consecutive_cuts_chapter_1_input.txt"));

  public static void main(String[] args) throws IOException {
    try (PrintWriter out = new PrintWriter(System.out)) {
      File f = new File("/Users/pdoshi/hackercup/q2022/round1ProblemAOne" + System.currentTimeMillis() + ".output");
      f.createNewFile();

      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        FileWriter fw = new FileWriter(f, true);
        print("Case #" + tt + ": ", fw);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        int[] map = new int[n + 1];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          map[arr[i]] = i;
        }
        int[] brr = new int[n];
        for (int i = 0; i < n; i++) {
          brr[i] = sc.nextInt();
        }
        int offset = map[brr[0]];
        boolean canBeSame = true;
        for (int i = 0; i < n; i++) {
          if (arr[(i + offset) % n] != brr[i])
            canBeSame = false;
        }
        if (!canBeSame) {
          print("NO", fw);
          print("\n", fw);
          fw.close();
          continue;
        }
        if (k == 0) {
          //they should be same from the beginning
          print(offset == 0 ? "YES" : "NO", fw);
        } else if (k == 1) {
          //if it is already same then we can't make any more cut
          if (offset == 0)
            print("NO", fw);
          else
            print("YES", fw);
        } else {
          //k >= 2
          if (n == 2) {
            if (offset == 0) {
              //already same, so we need even k
              print(k % 2 == 0 ? "YES" : "NO", fw);
            } else {
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