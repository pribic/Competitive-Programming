package codeforce.div3.r690;

import java.util.Scanner;

public class A {

  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)) {
         int t = sc.nextInt();
         while (t-- > 0) {
           int n = sc.nextInt();
           long[] arr = new long[n];
           for (int i = 0; i < n; i++) {
             arr[i] = sc.nextLong();
           }
           int l = 0;
           int r = n - 1;
           while (l <= r) {
             if(l == r) {
               System.out.print(arr[l] + " ");
             } else {
               System.out.print(arr[l] + " " + arr[r] + " ");
             }
             l++;
             r--;
           }
           System.out.println();
         }
    }
  }
}
