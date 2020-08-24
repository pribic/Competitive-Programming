package codeforce.educational.r93;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class ProblemA {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int i = 0; i < t; i++) {
        int n = sc.nextInt();
        int min1 = -1, min2= -1;
        int x=-1,y=-1,z=-1;
        boolean found = false;
        for (int j = 0; j < n; j++) {
          int val = sc.nextInt();
          if(min1 == -1) {
            min1 = val;
            x = j + 1;
            continue;
          }
          
          if(val == min1) {
            min2 = val;
            y = j + 1;
            continue;
          }
          if(min2 == -1) {
            min2 = val;
            y = j + 1;
            continue;
          }
          if(val == min2)
            continue;
          if(val >= (min1 + min2)) {
            z = j + 1;
            found = true;
          }
        }
        if(found) {
          System.out.println(x + " " + y + " " + z);
        }
         else {
          System.out.println("-1");
        }
      }
    }
  }
}
