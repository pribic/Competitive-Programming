package kickstart.Y2020.round1C.Problem3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author pdoshi
 * <p>
 * perfect subarray
 * <p>
 * https://codingcompetitions.withgoogle.com/kickstart/round/000000000019ff43/00000000003381cb
 */
public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    for (int tt = 1; tt <= T; tt++) {
      int N = sc.nextInt();
      int[] arr = new int[N];
      int max = Integer.MIN_VALUE;
      for (int i = 0; i < N; i++) {
        arr[i] = sc.nextInt();
        max = Math.max(max, arr[i]);
      }

      //frequency map

      Map<Integer, Integer> frequencyMap = new HashMap<>();
      int sum = 0;
      for (int i = 0; i < N; i++) {
        sum += arr[i];
        frequencyMap.put(sum, frequencyMap.getOrDefault(sum, 0) + 1);
      }

      Set<Integer> s = new LinkedHashSet<>();
      //pre compute all perfect squares upto max
      int index = 1;
      while (true) {
        int val = index * index;
        if (val > max)
          break;
        s.add(val);
        index++;
      }


      //find number of subarrays ending at i having perfect sum
      int[] r = new int[N];
      for (int i = 0; i < N; i++) {
        sum = 0;
        for (int j = i; j >= 0; j--) {
          sum += arr[j];
          if (s.contains(sum)) {
            r[i]++;
          }
        }
      }


      int prefix_sum = 0;
      for (int i = 0; i < N; i++) {
        prefix_sum += arr[i];
        for (int perfect_square : s) {
          r[i] += frequencyMap.getOrDefault(prefix_sum - perfect_square, 0);
        }
      }

      int ans = 0;
      for (int i = 0; i < N; i++) {
        ans += r[i];
      }

      System.out.println(String.format("Case #%d: %d", tt, ans));


    }
    sc.close();
  }

}

