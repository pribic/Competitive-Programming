package codejam.Y2022.round1C.LetterBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codingcompetitions.withgoogle.com/codejam/round/000000000087711b/0000000000acd59d" target="_top">https://codingcompetitions.withgoogle.com/codejam/round/000000000087711b/0000000000acd59d</a>
 * @since 25/04/22 4:08 PM
 */
public class Solution {
  static FastScanner sc = new FastScanner(System.in);
  static private List<Integer> idd = new ArrayList<>();

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      Scanner sc = new Scanner(System.in);
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        idd = new ArrayList<>();
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        String[] blocks = new String[n];
        String[] original = new String[n];
        boolean valid = true;
        for (int i = 0; i < n; i++) {
          original[i] = sc.next();
          blocks[i] = shrink(original[i]);
          if (!valid(blocks[i]))
            valid = false;
        }
        if (!valid)
          System.out.println("IMPOSSIBLE");
        else {
          //System.out.println("--");
          //
          //now we know that all the letters inside are valid and present in that single word only
          // now we are just concerned about first and last letter of each word.
          //get rid of those strings which are same
          List<String> str = new ArrayList<>(Arrays.asList(blocks));
          int prev = str.size();
          int cur = str.size() + 1;
          /*while (prev != cur) {
            outer:
            for (int i = 0; i < str.size(); i++) { // dd ab bc
              //if all letters are same
              String curStr = str.get(i);
              if (allsame(curStr)) {
                //find any other string that starts/ends with this letter
                for (int j = 0; j < str.size(); j++) {
                  if (i != j) {
                    String tempStr = str.get(j);
                    if (tempStr.charAt(0) == curStr.charAt(0)) {
                      // club them and omit this
                      str.remove(i);
                      if (j > i)
                        str.remove(j - 1);
                      else
                        str.remove(j);
                      str.add(curStr + tempStr);
                      continue outer;
                    } else if (tempStr.charAt(tempStr.length() - 1) == curStr.charAt(0)) {
                      // club them and omit this
                      str.remove(i);
                      if (j > i)
                        str.remove(j - 1);
                      else
                        str.remove(j);
                      str.add(tempStr + curStr);
                      continue outer;

                    }
                  }
                }
              }
            }

            prev = cur;
            cur = str.size();
          }*/
          // System.out.println(str);

          //
          blocks = new String[str.size()];
          str.toArray(blocks);
          n = blocks.length;
          HashSet<Integer>[] presents = new HashSet[26];
          for (int i = 0; i < 26; i++) {
            presents[i] = new HashSet<>();
          }
          for (int i = 0; i < n; i++) {
            for (char c : blocks[i].toCharArray())
              presents[c - 'A'].add(i);
          }
          for (int i = 0; i < n; i++) {
            String curStr = blocks[i];
            for (int j = 1; j < curStr.length() - 1; j++) {
              //it shall be present in only 1 set
              if (presents[curStr.charAt(j) - 'A'].size() > 1)
                valid = false;
            }
          }
          if (!valid)
            System.out.println("IMPOSSIBLE");
          else {
            //we start with each string
            boolean[] visited = new boolean[blocks.length];
            for (int i = 0; i < blocks.length; i++) {
              dfs(blocks, i, visited, new ArrayList<>(Arrays.asList(i)));
            }
            if (idd.size() > 0) {
              for (int ii : idd)
                System.out.print(original[ii]);
              System.out.println();
            } else
              System.out.println("IMPOSSIBLE");
          }
        }
      }
    }
  }

  private static void dfs(String[] blocks, int idx, boolean[] visited, List<Integer> indexes) {
    if (idd.size() > 0)
      return;
    if (visited[idx])
      return;
    visited[idx] = true;
    if (allvisited(visited) && valid(build(blocks, indexes))) {
      idd = new ArrayList<>(indexes);
      return;
    }
    //try to append something after this
    //is there any other string with same letter as this
    boolean tryany = false;
    String str = blocks[indexes.size() - 1];
    for (int i = 0; i < blocks.length; i++) {
      if (!visited[i] && blocks[i].charAt(0) == str.charAt(str.length() - 1)) { // possible candidate
        tryany = true;
        if (valid(str + blocks[i])) {
          List<Integer> temp = new ArrayList<>(indexes);
          temp.add(i);
          dfs(blocks, i, visited, temp);
        }
      }
    }
    if (!tryany) {
      //try all
      for (int i = 0; i < blocks.length; i++) {
        if (!visited[i]) { // possible candidate
          List<Integer> temp = new ArrayList<>(indexes);
          temp.add(i);
          dfs(blocks, i, visited, temp);
        }
      }
    }
    visited[idx] = false;
  }

  private static String build(String[] blocks, List<Integer> indexes) {
    StringBuilder ans = new StringBuilder();
    for (int ii : indexes)
      ans.append(blocks[ii]);
    return ans.toString();
  }

  private static boolean allvisited(boolean[] visited) {
    int cnt = 0;
    for (boolean b : visited)
      if (b)
        cnt++;
    return cnt == visited.length;
  }

  private static String shrink(String next) {
    StringBuilder sb = new StringBuilder();
    sb.append(next.charAt(0));
    for (int i = 1; i < next.length(); i++)
      if (sb.charAt(sb.length() - 1) != next.charAt(i))
        sb.append(next.charAt(i));
    return sb.toString();
  }

  private static boolean anysame(List<String> str) {
    for (String s : str)
      if (allsame(s))
        return true;
    return false;
  }

  private static boolean allsame(String s) {
    for (char c : s.toCharArray())
      if (c != s.charAt(0))
        return false;
    return true;
  }

  static boolean valid(String str) { // if a given string has same character sparse/not continuous
    if (str.isEmpty())
      return true;
    boolean[] present = new boolean[26];
    char prev = str.charAt(0);
    present[prev - 'A'] = true;
    for (int i = 1; i < str.length(); i++) {
      char cur = str.charAt(i);
      if (cur != prev && present[cur - 'A'])
        return false;
      present[cur - 'A'] = true;
      prev = cur;
    }
    return true;
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