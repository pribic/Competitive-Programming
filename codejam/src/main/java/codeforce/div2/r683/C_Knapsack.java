package codeforce.div2.r683;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class C_Knapsack {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      outer:
      while (t-- > 0) {
        int n = sc.nextInt();
        long W = sc.nextLong();
        TreeMap<Long, List<Integer>> map = new TreeMap<>();
        for (int i = 1; i <= n; i++) {
          long curWeight = sc.nextLong();
          if (map.containsKey(curWeight))
            map.get(curWeight).add(i);
          else {
            List<Integer> list = new ArrayList<>();
            list.add(i);
            map.put(curWeight, list);
          }
        }
        long curSum = 0;
        long takenSoFarCnt = 0;
        List<Integer> takenSoFar = new ArrayList<>();
        for (long ww : map.descendingKeySet()) {
          if(ww > W)
            continue;
          if(ww >= (W + 1)/2) {
            System.out.println("1");
            System.out.println(map.get(ww).get(0));
            continue outer;
          }
          long neededRightNow = (W + 1) / 2 - curSum;
          long minNeededByWW = (neededRightNow + ww - 1) / ww;
          int cnt = map.get(ww).size();
          if (cnt >= minNeededByWW) {
            System.out.println(takenSoFarCnt + minNeededByWW);
            //print takenSoFar and then minNeeded from curvalue;
            for (long x : takenSoFar) {
              System.out.print(x + " ");
            }
            for (int xx = 0; xx < minNeededByWW; xx++) {
              System.out.print(map.get(ww).get(xx) + " ");
            }
            System.out.println();
            continue outer;
          } else {
            //take everything with this ww weight
            takenSoFar.addAll(map.get(ww));
            takenSoFarCnt += map.get(ww).size();
            curSum += ww * cnt;
          }
        }
        System.out.println("-1");

      }
    }
  }
}
