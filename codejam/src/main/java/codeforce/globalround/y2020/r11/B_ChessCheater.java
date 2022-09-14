package codeforce.globalround.y2020.r11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static java.util.Comparator.reverseOrder;

public class B_ChessCheater {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int ii = 0; ii < t; ii++) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        String s = sc.next();
        List<Integer> continuousL = new ArrayList<>();
        int cnt = 0;
        long ans = 0;
        boolean previousW = false;

        //firstL

        int index1 = s.indexOf('W');
        if (index1 == -1) {
          //all L
          // LLLL
          System.out.println(Math.max(0, 2 * k - 1));
          continue;
        }

        int index2 = s.lastIndexOf('W');
        int lastL = s.length() - index2 - 1;

        for (int i = index1; i <= index2; i++) {
          if (s.charAt(i) == 'W') {
            if (cnt != 0)
              continuousL.add(cnt);
            cnt = 0;
            ans++;
            if (previousW)
              ans++;
            previousW = true;
          } else {
            cnt++;
            previousW = false;
          }
        }
        if( k == 0) {
          System.out.println(ans);
          continue;
        }
        Collections.sort(continuousL);
        //  System.out.println(Arrays.toString(continuousL.toArray()));
        for (int x : continuousL) {
          if (x == 1) {
            ans += 3;
            k--;
          } else if (x > 1 && x <= k) {
            ans += (2 * x + 1);
            k -= x;
          } else if (x > k) {
            ans += (2 * k);
            k = 0;
            break;
          }
          if (k <= 0)
            break;
        }

        //use first and last
        ans += (2 * Math.min(k, index1));
        k -= Math.min(k, index1);
        ans += (2 * Math.min(k, lastL));

        System.out.println(ans);
      } //sum of +ve 
      // sum of -ve
      //if both are same, print no.
      //start with positive if they are 
    }
  }
}
/**
 * my targets are those which are L and where W has L before
 * <p>
 * WLLW -> 1 0 1
 * WWW -> 1 2 2
 * +3
 * <p>
 * WLL -> 1 0 0
 * WWL -> 1 2 0
 * +2
 * <p>
 * LLW-> 0 0 1
 * LWW -> 0 1 2
 * +2
 * <p>
 * WLLL
 * <p>
 * 2
 * <p>
 * WLLWLWLLLWLW
 * <p>
 * 2, 1, 1
 * <p>
 * <p>
 * <p>
 * 3 , 3
 * 7 + 4
 * Len(L) * 2 + 1
 * <p>
 * <p>
 * WWWWWWWWWWLWWWW
 **/