package codeforce.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;

/**
 * @author pribic (Priyank Doshi)
 * @since 13/05/21
 */
public class p429DDestiny {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        Query[] queries = readQueries(sc, q);
        int[] ans = processQueries(arr, queries);
        for (int num : ans)
          System.out.println(num);

      }
    }
  }

  private static int[] processQueries(int[] arr, Query[] queries) {
    int[] ans = new int[queries.length];
    int blockSize = (int) ceil(sqrt(arr.length));
    Arrays.sort(queries, new MosComparator(blockSize));
    int l = 0;
    int r = 0;
    Map<Integer, Set<Integer>> frequencyVsNum = new HashMap<>();
    Map<Integer, Integer> numVsFrequency = new HashMap<>();
    numVsFrequency.put(arr[0], 1);
    frequencyVsNum.put(1, new HashSet<>(asList(arr[0])));
    int q = queries.length;
    int queryIdx = 0;
    while (q-- > 0) {
      Query curQuery = queries[queryIdx++];
      while (l > curQuery.l) { // expand left
        expandOrShrink(arr[--l], frequencyVsNum, numVsFrequency, 1);
      }
      while (r < curQuery.r) { // expand right
        expandOrShrink(arr[++r], frequencyVsNum, numVsFrequency, 1);

      }
      while (l < curQuery.l) { //shrink left
        expandOrShrink(arr[l++], frequencyVsNum, numVsFrequency, -1);
      }
      while (r > curQuery.r) { // shrink right
        expandOrShrink(arr[r--], frequencyVsNum, numVsFrequency, -1);
      }
      int limit = (curQuery.r - curQuery.l + 1) / curQuery.k;
      //we want numbers whose frequency is > limit
      ans[curQuery.idx] = frequencyVsNum.entrySet().stream()
        .filter(entry -> entry.getKey() > limit)
        .flatMap(entry -> entry.getValue().stream())
        .mapToInt(i -> i)
        .min().orElseGet(() -> -1);
    }
    return ans;
  }

  private static void expandOrShrink(int num, Map<Integer, Set<Integer>> frequencyVsNum, Map<Integer, Integer> numVsFrequency, int diff) {
    if(frequencyVsNum.containsKey(numVsFrequency.get(num)))
      frequencyVsNum.get(numVsFrequency.get(num)).remove(num);
    numVsFrequency.put(num, numVsFrequency.getOrDefault(num, 0) + diff);
    frequencyVsNum.putIfAbsent(numVsFrequency.get(num), new HashSet<>());
    frequencyVsNum.get(numVsFrequency.get(num)).add(num);

  }

  static class MosComparator implements Comparator<Query> {
    final int blockSize;

    public MosComparator(int blockSize) {
      this.blockSize = blockSize;
    }

    @Override
    public int compare(Query q1, Query q2) {
      int b1 = q1.l / blockSize;
      int b2 = q2.l / blockSize;
      if (b1 != b2)
        return Integer.compare(b1, b2);
      return b1 % 2 == 0 ? Integer.compare(q1.r, q2.r) : Integer.compare(q2.r, q1.r);
    }
  }

  private static Query[] readQueries(Scanner sc, int q) {
    Query[] queries = new Query[q];
    for (int i = 0; i < q; i++) {
      queries[i] = new Query(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt(), i);
    }
    return queries;
  }

  static class Query {
    final int l, r, k, idx;

    public Query(int l, int r, int k, int idx) {
      this.l = l;
      this.r = r;
      this.k = k;
      this.idx = idx;
    }
  }
}