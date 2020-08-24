package Q2020.round1B.Problem1;

//package Q2020.round1B.Problem1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    char[] pathC = {'E', 'W', 'N', 'S'};

   // int t = sc.nextInt();
    for (int X = -5; X <= 5; X++) {
      for (int Y = -5; Y <= 5; Y++) {
        String pathS = "";
       // long X = sc.nextLong();//Int();
       // long Y = sc.nextLong();
        long distance = abs(X) + abs(Y);
        List<Long> path = new ArrayList<>();
        long steps = findSteps(distance);
        long lastX = X;
        long lastY = Y;
        for (long step = steps; step > 0; step--) {  
          long validX, validY;
          for (int dir = 0; dir < 4; dir++) {
            long tx = (long) (lastX + dx[dir] * Math.pow(2, step - 1));
            long ty = (long) (lastY + dy[dir] * Math.pow(2, step - 1));
            if (abs(tx) <= abs(X)
              && abs(ty) <= abs(Y)
              && findSteps(abs(tx) + abs(ty)) < step) {
              path.add(tx);
              path.add(ty);
              lastX = tx;
              lastY = ty;
              pathS = pathC[dir] + pathS;
              break;
            }
          }
        }
        int len = pathS.length();
        // System.out.println(Arrays.deepToString(path.toArray()) + " " + pathS);
        if (lastX == 0 && lastY == 0 && len == steps) {
          System.out.println("Case #" + X  + " " + Y  + ": " + pathS);
        } else {
          System.err.println("Case #" + X + " " + Y  + ": " + "IMPOSSIBLE");
        }
      }
    }
    sc.close();
  }

  private static long findSteps(long distance) {
    for (int i = 0; i < 30; i++) {
      long num = (long) Math.pow(2, i);
      if (distance < num)
        return i;
    }
    return 0;
  }

  private static long abs(long x) {
    return Math.abs(x);
  }

}
