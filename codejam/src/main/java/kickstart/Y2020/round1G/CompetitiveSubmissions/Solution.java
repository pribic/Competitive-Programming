package kickstart.Y2020.round1G.CompetitiveSubmissions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {

    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        String s = sc.next();
        long start1 = System.currentTimeMillis();

        List<Integer> kick = new ArrayList<>(100001);
        List<Integer> start = new ArrayList<>(100001);
        int prev = 0;
        for (int i = 0; i < s.length();) {
          int val1 = s.indexOf("KICK", prev);
          if (val1 == -1)
            break;
          kick.add(val1);
          i = val1 + 1;
          prev = i;
        }
        prev = 0;
        for (int i = 0; i < s.length();) {
          int val1 = s.indexOf("START", prev);
          if (val1 == -1)
            break;
          start.add(val1);
          i = val1 + 1;
          prev = i;
        }
        int ans = 0;
        int indexS = 0;
        for (Integer kickIndex : kick) {
          while (indexS < start.size() && kickIndex > start.get(indexS)) {
            indexS++;
          }
          ans += (start.size() - indexS);
        }
        System.out.println(ans);
        //System.out.println(System.currentTimeMillis() - start1);
      }
    }

  }


}
