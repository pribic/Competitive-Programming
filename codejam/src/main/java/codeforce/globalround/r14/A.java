package codeforce.globalround.r14;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import static java.util.Collections.reverseOrder;

/**
 * @author pribic (Priyank Doshi)
 * @since 02/05/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int x = sc.nextInt();
        Set<Integer> weights = new TreeSet<>();
        int sum = 0;
        for (int i = 0; i < n; i++) {
          int val = sc.nextInt();
          weights.add(val);
          sum += val;

        }
        if (sum == x) {
          System.out.println("NO");
        } else {
          System.out.println("YES");
         int extraNum = -1;
         int curSum = 0;
         for(int we : weights) {
           if(curSum + we == x) {
             extraNum = we;
           } else {
             curSum += we;
             System.out.print(we + " ");
           }
         }
         if(extraNum != -1)
           System.out.println(extraNum);
         else
           System.out.println();
        }

      }
    }
  }
}

