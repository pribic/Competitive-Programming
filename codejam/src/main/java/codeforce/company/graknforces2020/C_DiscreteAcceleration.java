package codeforce.company.graknforces2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class C_DiscreteAcceleration {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int i = 0; i < t; i++) {
        int n = sc.nextInt();
        long l = sc.nextLong();

        long[] flags = new long[n];
        for (int j = 0; j < n; j++) {
          flags[j] = sc.nextLong();
        }
     //   -------
        float left = 0;
        float right = l;
        for (int x = 0; x < 100; x++) {
          float mid = left + (right - left) / 2;
          //System.out.println(left + " " + mid + " " + right + "=" + n + " " + l);
          //false false true true
          if (f(mid, n, l, flags)) {
            right = mid;
          } else {
            left = mid;
          }
        }
        System.out.println(right);
      }
    }
  }

  /**
   * @param mid
   * @param n
   * @param l     total length
   * @param flags
   * @return
   */
  private static boolean f(float mid, int n, long l, long[] flags) {
    //mid is the time 
    // we need to find how much both car traverse in mid time 
    float car1 = 0;
    float car2 = 0;
    float totalTimeSoFar = 0;
    for (int i = 0; i < flags.length; i++) {
      //find time taken to reach from current point to next flag[i]
      float distance;
      if (i == 0) {
        distance = flags[i];
      } else {
        distance = flags[i] - flags[i - 1];
      }
      float timeTaken = distance / (i + 1 /* current speed*/);

      //check if we can by pass this flag or not.
      if (totalTimeSoFar + timeTaken < mid) {
        //by pass this flag
        totalTimeSoFar += timeTaken;
      } else {
        //we cannot bypass this flag. Just find out how far can we reach with current speed
        float extraDistance = (i + 1) * (mid - totalTimeSoFar);
        if (i == 0) {
          car1 = extraDistance;
        } else {
          car1 = extraDistance + flags[i - 1];
        }
        totalTimeSoFar = mid;
      }

    }
    if (totalTimeSoFar < mid) {
      //we can drive some extra
      car1 = flags[flags.length - 1] + (flags.length + 1) * (mid - totalTimeSoFar);
    }
    totalTimeSoFar = 0;

    for (int i = flags.length - 1; i >= 0; i--) {
      //find time taken to reach from current point to next flag[i]
      float distance;
      if (i == flags.length - 1) {
        distance = l - flags[i];
      } else {
        distance = flags[i + 1] - flags[i];
      }
      float timeTaken = distance / (flags.length - i /* current speed*/);

      //check if we can by pass this flag or not.
      if (totalTimeSoFar + timeTaken < mid) {
        //by pass this flag
        totalTimeSoFar += timeTaken;
      } else {
        //we cannot bypass this flag. Just find out how far can we reach with current speed
        float extraDistance = (flags.length - i) * (mid - totalTimeSoFar);
        if (i == flags.length - 1) {
          car2 = extraDistance;
        } else {
          car2 = extraDistance + (l - flags[i + 1]);
        }
        totalTimeSoFar = mid;
      }
    }
    return car1 + car2 >= l;

  }
}
