package codeforce.div3.r739;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
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
 * @see <a href="https://codeforces.com/contest/1560/problem/E" target="_top">https://codeforces.com/contest/1560/problem/E</a>
 * @since 19/08/21 3:44 PM
 */
public class E {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        String str = sc.next();
        String order = "";
        int[] totalFreq = new int[26];
        for (int i = str.length() - 1; i >= 0; i--) {
          char ch = str.charAt(i);
          if (totalFreq[ch - 'a'] == 0) {
            order = ch + order;
          }
          totalFreq[ch - 'a']++;
        }
        int[] freqInSlot = new int[26];
        boolean valid = true;
        for (int i = order.length() - 1; i >= 0; i--) {
          char ch = order.charAt(i);
          if (totalFreq[ch - 'a'] % (i + 1) != 0)
            valid = false;
          freqInSlot[ch - 'a'] = totalFreq[ch - 'a'] / (i + 1);
        }
        StringBuilder s = new StringBuilder();
        for (char c : str.toCharArray()) {
          if (freqInSlot[c - 'a'] > 0) {
            s.append(c);
            freqInSlot[c - 'a']--;
          } else {
            break;
          }
        }
        if (valid)
          valid = generate(s.toString(), order, str);
        //
        System.out.println(valid ? s + " " + order : -1);
      }
    }
  }

  private static boolean generate(String s, String order, String str) {
    StringBuilder t = new StringBuilder(s);
    Set<Character> removed = new HashSet<>();
    
    for (int i = 0; i < order.length(); i++) {
      removed.add(order.charAt(i));
      StringBuilder cur = new StringBuilder();
      for(char c : s.toCharArray()){
        if(!removed.contains(c))
          cur.append(c);
      }
      t.append(cur);
    }
    return t.toString().equals(str);
  }
  
  /*
  polycarppoycarppoyarppyarppyrpprppp
  
  polycarppoycarppoyarppya | rppy | rppr | ppp
  
  we wont to find the split of given string t so that the first split will be original string
  
  obs: do we need to find all the splits or just the point where first split happens? ideally the first one is only needed but is it
  possible to find just first without not worrying about rest of the splits?
  
  last character would appear in all the slots - so freq of last char in each slot is total freq / slots. how many slots do we have but? it is same as 
  length of distinct characters
  
  what about second last char, it appears in slots - 1 , so we will do total freq / slots - 1
  this way we will find frequency of each letter in each slot
  as we know first slot will have all of them, we keep building first slot from left to right untill all the frequencies are exhausted
  
  
  --
  
  qweqeewew
  
  qweqe | ewe | w
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