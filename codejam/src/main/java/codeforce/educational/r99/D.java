package codeforce.educational.r99;

import java.util.Scanner;

public class D {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int n = sc.nextInt();
        int x = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        
        boolean impossible = checkIFImpossible(arr, x);
        if(impossible) {
          System.out.println(-1);
          continue;
        }
        
        int[] max = new int[n];
        max[0] = Integer.MIN_VALUE;
        int runningMax = Integer.MIN_VALUE;
        for(int i = 1; i < n; i++) {
          runningMax = Math.max(runningMax, arr[i-1]);
          max[i] = runningMax;
        }
        
        int cnt = 0;
        
        for(int i = 0; i < n; i++) {
          //lets fix each position
          /**
           * 
           * 2
           * 1 4 7 
           * 
           * 
           * 81
           * 18 324 218 413 324
           * 
           */
        }
      }
    }
  }

  private static boolean checkIFImpossible(int[] arr, int x) {
    int prev  = Integer.MIN_VALUE;
    for(int i = 0 ; i < arr.length; i++) {
      if(arr[i] < prev && arr[i] <= x) {
        return true;
      }
      prev = arr[i];
    }
    return false;
  }
}


