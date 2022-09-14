package codeforce.div3.r710;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.util.Collections.sort;
import static java.util.Comparator.comparingInt;

/**
 * @author pribic (Priyank Doshi)
 * @since 25/03/21
 */
public class D {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
          map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        List<Pair> pairs = new ArrayList<>(map.size());
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
          if(entry.getValue()>1) {
            pairs.add(new Pair(entry.getKey(), entry.getValue()));
          } else {
            index++;
          }
        }
        sort(pairs, comparingInt(p -> p.freq));
        int l = 0; 
        int r = pairs.size() - 1;
        while (r > l) {
          Pair right = pairs.get(r);
          Pair left = pairs.get(l);
          if(right.freq == left.freq) {
            index += 2;
            l++;
            r--;
          } else if(right.freq > left.freq) {
            right.freq -= (left.freq - 1);
            index++;
            l++;
            if(right.freq == 1) {
              r--;
              index++;
            }
          } else {
            left.freq -= (right.freq - 1);
            index++;
            r--;
            if(left.freq == 1) {
              l++;
              index++;
            }
          }
        }
        int remaining = -1;
        for(Pair p : pairs) {
          if(p.freq > 1) {
            remaining = p.freq;
            break;
          }
        }
        if(remaining == -1) {
          System.out.println(index % 2);
        } else {
          if(remaining >= index) {
            remaining -= index;
            System.out.println(remaining);
          } else {
            index -= remaining;
            System.out.println(index % 2);
          }
          
        }
        // 2 2 2  3 1 4 5 6 7
      }
    }
  }

  static class Pair {
    int num, freq;

    public Pair(int num, int freq) {
      this.num = num;
      this.freq = freq;
    }
  }
}