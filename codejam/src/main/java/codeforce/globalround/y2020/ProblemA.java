package codeforce.globalround.y2020;

import java.util.Arrays;
import java.util.Scanner;

public class ProblemA {

  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        boolean curSign = arr[0] < 0 ? false : true;
        for (int i = 1; i < n; i++, curSign = !curSign) {
          if(curSign) {
            arr[i] = Math.abs(arr[i]) * -1;
          } else {
            arr[i] = Math.abs(arr[i]);
          }
        }
        Arrays.stream(arr).forEachOrdered(a -> System.out.print(a + " "));
        System.out.println();
      }
    }
  }
}
