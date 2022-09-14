package kickstart.Y2022.round1E.MatchingPalindrome;

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
 * @see <a href="https://codingcompetitions.withgoogle.com/kickstart/round/00000000008cb0f5/0000000000ba82c5" target="_top">https://codingcompetitions.withgoogle.com/kickstart/round/00000000008cb0f5/0000000000ba82c5</a>
 * @since 21/08/22 9:51 AM
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        String str = sc.next();
        String revStr = reverse(str);
        int[] z_str = z_function(str + "?" + revStr);
        int[] z_revStr = z_function(revStr + "?" + str);
        //System.out.println(Arrays.toString(z_str));
        //System.out.println(Arrays.toString(z_revStr));
        int found = -1;
        for (int i = 0; i < n - 1; i++) {
          if (palindrom(str, i, z_str) && palindrom(revStr, n - i - 2, z_revStr)) {
            found = i;
            break;
          }
        }
        if (found == -1)
          found = n - 1;
        System.out.println(str.substring(0, found + 1));
      }
    }
  }

  private static boolean palindrom(String str, int i, int[] z_str) {
    // 0 1 2 3 4 ?
    // 0 1 2 3 4 5 6 7 8 9 10
    return z_str[z_str.length - 1 - i] == i + 1;
  }

  static int[] z_function(String s) {
    int n = s.length();
    int[] z = new int[n];
    for (int i = 1, l = 0, r = 0; i < n; i++) { //[l,r] represents substring which is prefix also
      if (r >= i)
        z[i] = Math.min(r - i + 1, z[i - l]);
      while (i + z[i] < s.length() && s.charAt(i + z[i]) == s.charAt(z[i]))
        z[i]++;
      if (i + z[i] - 1 > r) {
        l = i;
        r = i + z[i] - 1;
      }
    }
    return z;
  }

  static String LongestPalindromicPrefix(String str) {

    // Create temporary String
    String temp = str + '?';

    // Reverse the String str
    str = reverse(str);

    // Append String str to temp
    temp += str;

    // Find the length of String temp
    int n = temp.length();

    // lps[] array for String temp
    int[] lps = new int[n];

    // Initialise every value with zero
    Arrays.fill(lps, 0);

    // Iterate the String temp
    for (int i = 1; i < n; i++) {

      // Length of longest prefix
      // till less than i
      int len = lps[i - 1];

      // Calculate length for i+1
      while (len > 0 && temp.charAt(len) !=
        temp.charAt(i)) {
        len = lps[len - 1];
      }

      // If character at current index
      // len are same then increment
      // length by 1
      if (temp.charAt(i) == temp.charAt(len)) {
        len++;
      }

      // Update the length at current
      // index to len
      lps[i] = len;
    }

    out.println(temp);
    out.println(Arrays.toString(lps));
    return "";

  }

  static String reverse(String input) {
    char[] a = input.toCharArray();
    int l, r = a.length - 1;

    for (l = 0; l < r; l++, r--) {
      char temp = a[l];
      a[l] = a[r];
      a[r] = temp;
    }
    return String.valueOf(a);
  }


  static int[] prefix_function(String s) {
    return prefix_function(s, 0, s.length());
  }

  // s[st..end)
  static int[] prefix_function(String s, int st, int end) {
    //out.println("s = " + s);
    int n = end - st;
    int[] pi = new int[n];
    for (int i = 1; i < n; i++) {
      int j = pi[i - 1];
      while (j > 0 && s.charAt(st + i) != s.charAt(st + j))
        j = pi[j - 1];
      if (s.charAt(st + i) == s.charAt(st + j))
        j++;
      pi[i] = j;
    }
    return pi;
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