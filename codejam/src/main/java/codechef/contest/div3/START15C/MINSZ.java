package codechef.contest.div3.START15C;

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
 * @see <a href="https://www.codechef.com/START15C/problems/MINSZ" target="_top">https://www.codechef.com/START15C/problems/MINSZ</a>
 * @since 12/10/21 9:42 PM
 */
public class MINSZ {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        long c = sc.nextLong();
        if(c == 0) {
          System.out.println(2);
          System.out.println("1 1");
          continue;
        }
        // 2 -> 10 -> 01 ^ 11
        int bitCount = Long.toBinaryString(c).length();
        int[][] grid = new int[bitCount][bitCount];
        for (int i = 0; i < bitCount; i++) {
          int curXor = 0;
          for (int row = 0; row < i; row++) {
            curXor ^= grid[row][i];
          }
          if (curXor == ((c >> (bitCount - i - 1)) & 1L)) {
            grid[i][i] = 0;
          } else {
            for (int j = i; j < bitCount; j++)
              grid[i][j] = 1;
          }
        }
        Set<Long> nums = new HashSet<>();
        for (int i = 0; i < bitCount; i++) {
          long curNum = 0;
          for (int j = 0; j < bitCount; j++) {
            curNum *= 2;
            curNum += grid[i][j];
          }
          if (curNum != 0)
            nums.add(curNum);
        }
        System.out.println(nums.size());
        StringBuilder ans = new StringBuilder();
        //System.out.println(Long.toBinaryString(c));
        for (long num : nums) {
          ans.append(num).append(" ");
          //System.out.println(String.format("%" + bitCount + "s", Long.toBinaryString(num)));
        }
        System.out.println(ans);
      }
    }
  }
  
  /*
  
101100110

111111111
011111111
001111111
000000000
0000
0000
0000
        
   */

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
/*
1111101000
       111
1111111111
 000011111
  00011111
   0011111
    011111
     11111
      1111
 */