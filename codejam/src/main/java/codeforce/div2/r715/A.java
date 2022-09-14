package codeforce.div2.r715;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 16/04/21
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
        List<Integer>[] nums = new List[2];
        nums[0] = new ArrayList<>();
        nums[1] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
          nums[arr[i] % 2].add(arr[i]);
        }
          for(List<Integer> list : nums) {
            for(int num : list) {
              System.out.print(num + " ");
            }
        }
        System.out.println();
      }
    }
  }
}