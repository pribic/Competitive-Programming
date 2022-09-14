package codeforce.div2.r696;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 19/01/21
 */
public class C {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[1000000 + 1];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 2 * n; i++) {
          int val = sc.nextInt();
          arr[val]++;
          max = Math.max(max, val);
        }
        int d1 = 0;
        boolean flag = false;
        for(int i = 1000000; i >= 0; i++) {
          if(arr[i] > 0 && arr[i] < max && arr[max - i] > 0) {
            flag = true;
            d1 = i;
            break;
          }
        }
        
        if(flag) {
          //start with max
          for(int i = 0; i < n; i++) {
            System.out.println(max + " " + d1);
            
          }
        }
      }
    }
  }
}