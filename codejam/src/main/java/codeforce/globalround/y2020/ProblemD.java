package codeforce.globalround.y2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProblemD {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        int[] freq = new int[n + 1];
        for (int i = 0; i < n; i++) {
          int input = sc.nextInt();
          freq[input]++;
          arr[i] = input;
        }

        int mex = -1;
        for (int i = 0; i < freq.length; i++) {
          if (freq[i] == 0)
            mex = i;
        }

        if (mex == -1)
          mex = n;

        while (true) {
          System.out.print((mex) + " ");
          int old;
          if (mex >= arr.length) {
            old = arr[arr.length - 1];
            arr[arr.length - 1] = mex;
          }
          else {
            old = arr[mex];
            arr[mex] = mex;
          }
          freq[old]--;
          freq[mex]++;
          if (freq[old] == 0) {
            mex = Math.min(mex, old);
          }
        }
      }
    }
  }


}
