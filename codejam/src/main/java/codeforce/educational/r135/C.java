package codeforce.educational.r135;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;


/**
 * @author pribic (Priyank Doshi)
 * @see <a href="https://codeforces.com/contest/1728/problem/C" target="_top">https://codeforces.com/contest/1728/problem/C</a>
 * @since 08/09/22 8:27 pm
 */
public class C {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        int[] brr = new int[n];
        for (int i = 0; i < n; i++) {
          brr[i] = sc.nextInt();
        }
        //build a map of value vs index
        Map<Integer, Integer> arrValToFreq = buildFrequencyMap(arr);
        Map<Integer, Integer> brrValToFreq = buildFrequencyMap(brr);
        //remove common elements
        removeCommon(arrValToFreq, brrValToFreq);
        //now apply operation
        List<Integer> aList1 = convertValToList(arrValToFreq);
        List<Integer> bList1 = convertValToList(brrValToFreq);
        //all the elements if they are > 9 will be applied this operation
        int minOp = 0;
        //it contains <10 element now 
        List<Integer> aList2 = new ArrayList<>();
        List<Integer> bList2 = new ArrayList<>();
        for (int num : aList1) {
          if (num < 10)
            aList2.add(num);
          else {
            aList2.add(Integer.toString(num).length());
            minOp++;
          }
        }
        for (int num : bList1) {
          if (num < 10)
            bList2.add(num);
          else {
            bList2.add(Integer.toString(num).length());
            minOp++;
          }
        }
        //aList2 and bList2 has elements < 10 now. 
        arrValToFreq = buildFrequencyMap(convertListToArray(aList2));
        brrValToFreq = buildFrequencyMap(convertListToArray(bList2));
        removeCommon(arrValToFreq, brrValToFreq);
        aList2.clear();
        bList2.clear();
        aList1.clear();
        bList1.clear();

        //
        aList1 = convertValToList(arrValToFreq);
        bList1 = convertValToList(brrValToFreq);
        for (int num : aList1) {
          if (num != 1)
            minOp++;
        }
        for (int num : bList1) {
          if (num != 1)
            minOp++;
        }

        System.out.println(minOp);

      }
    }
  }

  private static int[] convertListToArray(List<Integer> ls) {
    int[] a = new int[ls.size()];
    for (int i = 0; i < a.length; i++) {
      a[i] = ls.get(i);
    }
    return a;
  }

  private static List<Integer> convertValToList(Map<Integer, Integer> arrValToIdx) {
    List<Integer> ls = new ArrayList<>();
    for (int key : arrValToIdx.keySet()) {
      Integer len = arrValToIdx.get(key);
      for (int i = 0; i < len; i++)
        ls.add(key);
    }
    return ls;
  }

  private static void removeCommon(Map<Integer, Integer> aMap, Map<Integer, Integer> bMap) {
    Map<Integer, Integer> tobeRemoved = new HashMap<>();
    for (int key : aMap.keySet()) {
      if (bMap.containsKey(key))
        tobeRemoved.put(key, Math.min(aMap.get(key), bMap.get(key)));
    }
    for (int key : tobeRemoved.keySet()) {
      aMap.put(key, aMap.get(key) - tobeRemoved.get(key));
      bMap.put(key, bMap.get(key) - tobeRemoved.get(key));
      if (aMap.get(key) == 0)
        aMap.remove(key);
      if (bMap.get(key) == 0)
        bMap.remove(key);
    }
  }

  private static Map<Integer, Integer> buildFrequencyMap(int[] arr) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int ar : arr)
      map.put(ar, map.getOrDefault(ar, 0) + 1);
    return map;
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