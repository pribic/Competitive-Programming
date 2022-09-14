package codeforce.special.goodby2020;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class B {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int n = sc.nextInt();
        Map<Integer, Integer> frequencyMap = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
          int val = sc.nextInt();
          frequencyMap.put(val, frequencyMap.getOrDefault(val, 0) + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
          ans++;
          if (entry.getValue() > 1) {
            if (frequencyMap.containsKey(entry.getKey() + 1)) {
              frequencyMap.put(entry.getKey() + 1, frequencyMap.get(entry.getKey() + 1) + 1);
            } else {
              ans++;
            }
          }
        }
        System.out.println(ans);
      }
    }
  }
}
