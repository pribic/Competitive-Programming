package codeforce.div3.r686;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
        int t = sc.nextInt();
        while (t-- > 0) {
          int n = sc.nextInt();
          Map<Integer, Integer> valueFrequency = new HashMap<>();
          Map<Integer, Integer> valueIndex = new HashMap<>();
          for (int i = 1; i <= n; i++) {
            int val = sc.nextInt();
            valueIndex.put(val, i);
            valueFrequency.put(val, valueFrequency.getOrDefault(val, 0) + 1);
          }
          
          int min = Integer.MAX_VALUE;
          int ans = -1;
          for(Map.Entry<Integer, Integer> entry : valueFrequency.entrySet()) {
            int freq = entry.getValue();
            int num = entry.getKey();
            if(freq == 1) {
              if(num < min) {
                min = num;
                ans = valueIndex.get(num);
              }
            }
          }
          System.out.println(ans);
        }
    }
  }
  
  static class Pair implements Comparable<Pair> {
    int index;
    int val;

    public Pair(int index, int val) {
      this.index = index;
      this.val = val;
    }

    @Override
    public int compareTo(Pair o) {
      return this.val - o.val;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Pair pair = (Pair) o;
      return index == pair.index &&
        val == pair.val;
    }

    @Override
    public int hashCode() {
      return Objects.hash(index, val);
    }
  }
}
