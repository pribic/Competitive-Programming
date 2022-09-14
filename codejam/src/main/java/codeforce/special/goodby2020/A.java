package codeforce.special.goodby2020;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class A {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        Set<Integer> area = new HashSet<>();
        for(int i = 0; i < n-1; i++) {
          for(int j = i + 1; j < n; j++) {
            //System.out.println(Math.abs(arr[i] - arr[j]));
            area.add(Math.abs(arr[i] - arr[j]));
          }
        }
        System.out.println(area.size());
      }
    }
  }
}
