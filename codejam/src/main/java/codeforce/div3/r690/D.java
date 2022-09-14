package codeforce.div3.r690;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class D {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int n = sc.nextInt();
        List<Integer> nums = new ArrayList<>();
        long sum = 0;
        for (int i = 0; i < n; i++) {
          int val = sc.nextInt();
          sum += val;
          nums.add(val);
        }
        int ans = 0;
        int lastCnt = 0;
        long lastSum = 0;
        while (sum > 0) {
          int cnt = 0;
          long curSum = 0;
          boolean flag = false;
          for (int i = 0; i < n; i++) {
            if (curSum + nums.get(i) < sum) {
              curSum += nums.get(i);
            } else if (curSum + nums.get(i) == sum) {
              cnt++;
              curSum = 0;
            } else {
              flag = true;
              break;
            }
          }
          if (!flag && curSum == 0) {
            lastCnt = cnt;
          } else {
            sum = 0;
          }
          lastSum = sum;
          sum /= 2;
        }
        
        long curSum = 0;
        int lastIndex = -1;
        for(int i = 0; i < n; i++) {
              if(curSum + nums.get(i) == lastSum) {
                //found a piece
                ans += (i - lastIndex + 1);
                
              }
        }

        System.out.println();
      }

    }
  }

}

