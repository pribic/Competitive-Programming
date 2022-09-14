package codeforce.div3.r756;

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
 * @see <a href="a" target="_top">a</a>
 * @since 26/11/21 12:02 PM
 */
public class aa {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    out.println(new Solution().solve(new int[]{1, 9, 1, 2})); // ( 9 - 1 ) * ( 1 + 2 )
  }

  // 8 * 0 + 4 * 6
  static class Solution {
    public boolean solve(int[] nums) {
      return solve(nums, 0, new ArrayList<>(), new ArrayList<>());
    }

    private boolean solve(int[] nums, int idx, List<Integer> indexes, List<Character> op) {
      if (indexes.size() == 4 && op.size() == 3) {
        int eval = evaluate(nums, indexes, op);
        if (eval == 24) {
          out.println("indexes = " + indexes);
          out.println("op = " + op);
        }
        return eval == 24;
      }
      for (int i = 0; i < 4; i++)
        if (!indexes.contains(i)) {
          indexes.add(i);
          if (i == 3) {
            if (solve(nums, idx + 1, indexes, op))
              return true;
          } else {
            for (char c : new char[]{'+', '-', '*', '/'}) {
              op.add(c);
              if (solve(nums, idx + 1, indexes, op))
                return true;
              op.remove(op.size() - 1);
            }
          }
          indexes.remove(indexes.size() - 1);
        }
      return false;
    }

    int evaluate(int[] nums, List<Integer> num, List<Character> op) {
      int ans = nums[0];
      int opi = 0;
      for (int i = 1; i < 4; i++, opi++) {
        char c = op.get(opi);
        int val = nums[num.get(i)];
        switch (c) {
          case '+':
            ans = ans + val;
            break;
          case '-':
            ans = ans - val;
            break;
          case '*':
            ans = ans * val;
            break;
          case '/':
            if (val == 0)
              return -1;
            ans = ans / val;
            break;
        }
      }

      return ans;
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