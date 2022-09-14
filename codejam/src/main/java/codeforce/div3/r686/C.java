package codeforce.div3.r686;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class C {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, List<Integer>> indexes = new HashMap<>();
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);
          if (indexes.containsKey(arr[i]))
            indexes.get(arr[i]).add(i);
          else {
            List<Integer> ii = new ArrayList<>();
            ii.add(i);
            indexes.put(arr[i], ii);
          }
        }
        long ans = freq.size() == 1 ? 0 : Integer.MAX_VALUE;
        for (Map.Entry<Integer, List<Integer>> entry : indexes.entrySet()) {
          List<Integer> index = entry.getValue();
          long cnt = 0;
          long prev = -1;
          for (int x : index) {
            if ( x > prev + 1)
              cnt++;
            prev = x;
          }
          if(prev != n - 1)
            cnt++;
          ans = Math.min(ans, cnt);
        }
        System.out.println(ans);
      }
    }
  }
}
