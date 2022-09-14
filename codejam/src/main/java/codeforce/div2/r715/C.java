package codeforce.div2.r715;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 16/04/21
 */
public class C {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        List<Integer> arr = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
          arr.add(sc.nextInt());
        }
        Collections.sort(arr);
        if (n == 1) {
          System.out.println(0);
        } else if (n == 2) {
          System.out.println(Math.abs(arr.get(0) - arr.get(1)));
        } else {
          long minDiscrepancy = Integer.MAX_VALUE;
          Map<Integer, Integer> numberToStart = new HashMap<>();
          Map<Integer, Integer> numberToEnd = new HashMap<>();
          numberToStart.put(arr.get(0), 0);
          numberToEnd.put(arr.get(0), 0);
          for (int i = 1; i < n; i++) {
            if (!arr.get(i).equals(arr.get(i - 1))) {
              numberToStart.put(arr.get(i), i);
              numberToEnd.put(arr.get(i), i);
            } else {
              numberToEnd.put(arr.get(i), numberToEnd.getOrDefault(arr.get(i), 0) + 1);
            }
          }
          System.out.println("numberToStart.toString() = " + numberToStart.toString());
          System.out.println("numberToEnd.toString() = " + numberToEnd.toString());
          Map<Integer, Long> ansPerNum = new HashMap<>();
          long ans = Long.MAX_VALUE;
          for (int i = 0; i < n; i++) {
            int curNum = arr.get(i);
            if (ansPerNum.containsKey(curNum))
              continue;
            long curAns = 0;
            int curMin = curNum;
            int curMax = curNum;
            for (int j = 0; j < n; j++) {
              if (!arr.get(j).equals(curNum)) {
                //find nearest number to curMin
                int curMinStartIdx = numberToStart.get(curMin);
                int diff1 = Integer.MAX_VALUE;
                int diff2 = Integer.MAX_VALUE;
                if (curMinStartIdx > 0) {
                  int nxtMin = arr.get(curMinStartIdx - 1);
                  diff1 = curMax - nxtMin;
                }
                int curMaxEndIdx = numberToEnd.get(curMax);
                if (curMaxEndIdx < n - 1) {
                  int nxtMax = arr.get(curMaxEndIdx + 1);
                  diff2 = nxtMax - curMin;
                }
                if (diff1 == Integer.MAX_VALUE && diff2 == Integer.MAX_VALUE) {
                  continue;
                } else if (diff2 == Integer.MAX_VALUE) {
                  curAns += diff1;
                  curMin = arr.get(curMinStartIdx - 1);
                } else if (diff1 == Integer.MAX_VALUE) {
                  curAns += diff2;
                  curMax = arr.get(curMaxEndIdx + 1);
                } else {
                  if(diff1 <= diff2) {
                    curMin = arr.get(curMinStartIdx - 1);
                  } else {
                    curMax = arr.get(curMaxEndIdx + 1);
                  }
                  curAns += Math.min(diff1, diff2);
                }
              }
            }
            ans = Math.min(ans, curAns);
            ansPerNum.put(curNum, curAns);
          }
          System.out.println(ans);
        }
      }
    }
  }
}