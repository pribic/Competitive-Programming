package codechef.virtualContest;

import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;

/**
 * @author pribic (Priyank Doshi)
 * @since 10/05/21
 */
public class E {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] freq = new int[32];
        for (int i = 0; i < n; i++) {
          int val = sc.nextInt();
          int cnt = 0;
          while (val > 1) {
            cnt++;
            val /= 2;
          }
          freq[cnt]++;
        }
        StringBuilder ans = new StringBuilder();
        int ca = (int) Math.pow(2, 30);
        for (int i = 0; i < q; i++) {
          int num = sc.nextInt();
          int[] freqClone = freq.clone();
          int power = 30;
          int checkAgainst = ca;
          int coins = 0;
          while (checkAgainst > 0 && num > 0) {
            if (checkAgainst <= num && freqClone[power] > 0) {
              //how much max i can take
              int maxICanTake = num / checkAgainst;
              int howMuchWeHave = freqClone[power];
              int take = Math.min(maxICanTake, howMuchWeHave);
              freqClone[power] -= take;
              num -= Math.pow(2, power) * take;
              coins += take;
            }
            checkAgainst /= 2;
            power--;
          }
          if(num == 0) {
            ans.append(coins).append("\n");
          } else {
            ans.append(-1).append("\n");
          }
        }
        out.println(ans);

      }
    }
  }


}
 