package kickstart.Y2022.round1E.StudentsAndMentors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import static java.lang.System.out;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/00000000008cb0f5/0000000000ba84ae" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/00000000008cb0f5/0000000000ba84ae</a>
 * @since 21/08/22 9:16 AM
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        Map<Integer, Integer> freq = new TreeMap<>();
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);
        }
        List<Integer> uniques = new ArrayList<>(freq.keySet());
        uniques.add(100000000);
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
          int x = 2 * arr[i];
          // true true true... false false false
          int l = 0;
          int r = uniques.size();
          while (r > l + 1) {
            int mid = l + (r - l) / 2;
            if (uniques.get(mid) <= x)
              l = mid;
            else 
              r = mid;
          }
          if (uniques.get(l) == arr[i]) {
            //found number is same as input, means no number greater than it in its range.
            if (freq.get(uniques.get(l)) > 1)
              ans.append(uniques.get(l)).append(" ");
            else {
              //this number is not done, lets find a smaller than this
              //see if such element exist
              if (uniques.get(0).equals(uniques.get(l))) {
                //means doesn't exist
                ans.append("-1").append(" ");
              } else {
                l = 0;
                r = uniques.size(); 
                while (r > l + 1) {
                  int mid = l + (r - l) / 2;
                  if (uniques.get(mid) <= arr[i] - 1)
                    l = mid;
                  else 
                    r = mid;
                }
                ans.append(uniques.get(l)).append(" ");
              }
            }
          } else {
            ans.append(uniques.get(l)).append(" ");
          }

        }
        System.out.println(ans);
      }
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