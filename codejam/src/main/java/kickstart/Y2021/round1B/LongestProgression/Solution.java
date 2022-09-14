package kickstart.Y2021.round1B.LongestProgression;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 19/04/21
 */
public class Solution {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        long[] numbers = new long[n];
        for (int i = 0; i < n; i++) {
          numbers[i] = sc.nextInt();
        }
        if (n == 2 || n == 3) {
          System.out.println(n);
          continue;
        }
        long longestLen = 2;
        Pair[] endsAt = new Pair[n];
        Pair[] startsAt = new Pair[n];
        for (int i = 0; i < n; i++) {
          if (i == 0) {
            endsAt[i] = new Pair(Long.MAX_VALUE, 1);
          } else {
            long curDiff = numbers[i] - numbers[i - 1];
            if (curDiff == endsAt[i - 1].diff) {
              endsAt[i] = new Pair(curDiff, endsAt[i - 1].len + 1);
            } else {
              endsAt[i] = new Pair(curDiff, 2);
            }
          }
          longestLen = Math.max(longestLen, endsAt[i].len);
        }
        //
        for (int i = n - 1; i >= 0; i--) {
          if (i == n - 1) {
            startsAt[i] = new Pair(Long.MAX_VALUE, 1);
          } else {
            long curDiff = numbers[i + 1] - numbers[i];
            if (curDiff == startsAt[i + 1].diff) {
              startsAt[i] = new Pair(curDiff, startsAt[i + 1].len + 1);
            } else {
              startsAt[i] = new Pair(curDiff, 2);
            }
          }
          longestLen = Math.max(longestLen, startsAt[i].len);
        }
        // System.out.println("Arrays.toString(endsAt) = " + Arrays.toString(endsAt));
        // System.out.println("Arrays.toString(startsAt) = " + Arrays.toString(startsAt));
        //check for n = 0;
        longestLen = Math.max(longestLen, startsAt[1].len + 1);
        //check for n = n -1
        longestLen = Math.max(longestLen, endsAt[n - 2].len + 1);
        endsAt[0].diff = startsAt[2].diff;
        startsAt[n - 1].diff = endsAt[n - 3].diff;
        for (int i = 1; i < n - 1; i++) {
          long curLongest = 0;
          if (numbers[i + 1] - numbers[i - 1] == 2 * endsAt[i - 1].diff) {
            if ((endsAt[i - 1].diff == startsAt[i + 1].diff))
              curLongest = endsAt[i - 1].len + startsAt[i + 1].len + 1;
            else
              curLongest = endsAt[i - 1].len + 2;
          } else if (numbers[i - 1] == numbers[i + 1] - 2 * startsAt[i + 1].diff) {
            if (endsAt[i - 1].diff == startsAt[i + 1].diff)
              curLongest = endsAt[i - 1].len + startsAt[i + 1].len + 1;
            else
              curLongest = startsAt[i + 1].len + 2;
          }

          longestLen = Math.max(longestLen, endsAt[i - 1].len + 1);
          longestLen = Math.max(longestLen, startsAt[i + 1].len + 1);
          longestLen = Math.max(longestLen, curLongest);
        }
        System.out.println(longestLen);
      }
    }
  }

  static class Pair {
    long diff, len;

    public Pair(long diff, long len) {
      this.diff = diff;
      this.len = len;
    }

    @Override
    public String toString() {
      final StringBuilder sb = new StringBuilder(" ");
      sb.append("d=").append(diff);
      sb.append(", l=").append(len);
      sb.append(' ');
      return sb.toString();
    }
  }
}