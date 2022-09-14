package codeforce.globalround.y2020.r11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.util.Collections.reverseOrder;

public class A_AvoidingZero {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int i = 0; i < t; i++) {
        int n = sc.nextInt();
        List<Integer> arr = new ArrayList<>(n);
        int sumP = 0;
        int sumN = 0;
        for (int j = 0; j < n; j++) {
          int val = sc.nextInt();
          arr.add(val);
          if (val < 0)
            sumN += val;
          if (val > 0)
            sumP += val;
        }
        if (sumP + sumN == 0) {
          System.out.println("NO");
        } else {
          System.out.println("YES");
          if (Math.abs(sumP) > Math.abs(sumN)) {
            //print all + then all -
            arr.stream().filter(a -> a > 0).forEachOrdered(a -> System.out.print(a + " "));
            arr.stream().filter(a -> a == 0).forEachOrdered(a -> System.out.print(a + " "));
            arr.stream().filter(a -> a < 0).forEachOrdered(a -> System.out.print(a + " "));
          } else {
            arr.stream().filter(a -> a < 0).forEachOrdered(a -> System.out.print(a + " "));
            arr.stream().filter(a -> a == 0).forEachOrdered(a -> System.out.print(a + " "));
            arr.stream().filter(a -> a > 0).forEachOrdered(a -> System.out.print(a + " "));
          }
          System.out.println();
        }
      } //sum of +ve 
      // sum of -ve
      //if both are same, print no.
      //start with posive if they are 
    }
  }
}
