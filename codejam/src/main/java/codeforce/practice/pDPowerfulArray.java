package codeforce.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.StringTokenizer;
import java.util.function.Function;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;

/**
 * @author pribic (Priyank Doshi)
 * @since 12/05/21
 */
public class pDPowerfulArray {
  public static void main(String[] args) {
    try (FastReader fr = new FastReader()) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = fr.nextInt();
        int q = fr.nextInt();
        int[] nums = fr.nextIntArray(n);
        Query[] queries = readQueries(fr, q);
        //out.println("Queries before sort " + Arrays.toString(queries));
        int blockSize = 450;//(int) Math.ceil(Math.sqrt(n));
        Arrays.sort(queries, new QueryComparator(blockSize));
        //out.println("Queries after sort " + Arrays.toString(queries));
        long[] ans = processQueries(nums, queries);
        out.println(printArray(ans));
      }
    }
  }

  private static long[] processQueries(int[] nums, Query[] queries) {
    int q = queries.length;
    int l = 0;
    int r = 0;
    int queryIdx = 0;
    int[] frequency = new int[1000000 + 5];
    frequency[nums[0]] = 1;
    long rangeAns = nums[0];
    long[] ans = new long[q];
    Function<Integer, Long> calcAns = num -> (long) frequency[num] * frequency[num] * num;
    while (q-- > 0) {
      Query query = queries[queryIdx++];
      //bring l and r to query.l and query.r
      while (l > query.l) { //expland left
        l--;
        rangeAns -= (long) frequency[nums[l]] * frequency[nums[l]] * nums[l];
        frequency[nums[l]]++;
        rangeAns += (long) frequency[nums[l]] * frequency[nums[l]] * nums[l];
      }
      while (r < query.r) { //expland right
        r++;
        rangeAns -= (long) frequency[nums[r]] * frequency[nums[r]] * nums[r];
        frequency[nums[r]]++;
        rangeAns += (long) frequency[nums[r]] * frequency[nums[r]] * nums[r];
      }
      while (l < query.l) {//shrink left
        rangeAns -= (long) frequency[nums[l]] * frequency[nums[l]] * nums[l];
        frequency[nums[l]]--;
        rangeAns += (long) frequency[nums[l]] * frequency[nums[l]] * nums[l];
        l++;
      }
      while (r > query.r) {//shrink right
        rangeAns -= (long) frequency[nums[r]] * frequency[nums[r]] * nums[r];
        frequency[nums[r]]--;
        rangeAns += (long) frequency[nums[r]] * frequency[nums[r]] * nums[r];
        r--;
      }
      ans[query.idx] = rangeAns;

    }
    return ans;
  }

  private static StringBuilder printArray(long[] ans) {
    StringBuilder sb = new StringBuilder();
    for (long num : ans)
      sb.append(num).append("\n");
    return sb;
  }

  private static Query[] readQueries(FastReader sc, int q) {
    Query[] queries = new Query[q];
    int idx = 0;
    while (q-- > 0) {
      String str = sc.nextLine();
      queries[idx] = new Query(Integer.parseInt(str.split("\\s+")[0]) - 1, Integer.parseInt(str.split("\\s+")[1]) - 1, idx);
      idx++;
    }
    return queries;
  }

  static class Query {
    final int l, r, idx;

    public Query(int l, int r, int idx) {
      this.l = l;
      this.r = r;
      this.idx = idx;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Query.class.getSimpleName() + "[", "]")
        .add("l=" + l)
        .add("r=" + r)
        .toString();
    }
  }

  static class QueryComparator implements Comparator<Query> {
    private int blockSize;

    public QueryComparator(int blockSize) {
      this.blockSize = blockSize;
    }

    @Override
    public int compare(Query q1, Query q2) {
      int b1 = q1.l / blockSize;
      int b2 = q2.l / blockSize;
      if (b1 != b2)
        return Integer.compare(b1, b2);
      return b1 % 2 == 1 ? Integer.compare(q1.r, q2.r) : Integer.compare(q2.r, q1.r);
    }
  }

  static class FastReader implements AutoCloseable {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
      br = new BufferedReader(new
        InputStreamReader(System.in));
    }

    String next() {
      while (st == null || !st.hasMoreElements()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
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

    String nextLine() {
      String str = "";
      try {
        str = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return str;
    }

    public int[] nextIntArray(int n) {
      String[] str = nextLine().split("\\s+");
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = Integer.parseInt(str[i]);
      }
      return arr;
    }

    @Override
    public void close() {
      try {
        br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}