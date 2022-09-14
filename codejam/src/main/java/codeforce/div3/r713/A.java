package codeforce.div3.r713;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 10/04/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : arr) {
          map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for(Integer key : map.keySet()) {
          if(map.get(key) == 1) ans = key;
        }
        for(int i = 0; i < n; i++) {
          if(arr[i] == ans) {
            ans = i;
            break;
          }
        }
        System.out.println(ans + 1);
      }
    }
  }
}