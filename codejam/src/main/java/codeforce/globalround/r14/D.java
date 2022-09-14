package codeforce.globalround.r14;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 02/05/21
 */
public class D {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int l = sc.nextInt();
        int r = sc.nextInt();
        Map<Long, Long> left = new HashMap<>();
        Map<Long, Long> right = new HashMap<>();
        long leftSum;
        long rightSum;
        if (r < l) {
          int t = l;
          l = r;
          r = t;
        }
        leftSum = l;
        rightSum = r;
        //left has socks <= right
        for (int i = 0; i < l; i++) {
          long color = sc.nextInt();
          left.put(color, left.getOrDefault(color, 0L) + 1);
        }
        for (int i = 0; i < r; i++) {
          long color = sc.nextInt();
          right.put(color, right.getOrDefault(color, 0L) + 1);
        }
        long ans = 0L;
        //first remove matching socks

        for (Long leftKey : left.keySet()) {
          if (right.containsKey(leftKey)) {
            // we found common color
            // now remove
            long min = Math.min(left.get(leftKey), right.get(leftKey));
            left.put(leftKey, left.get(leftKey) - min);
            right.put(leftKey, right.get(leftKey) - min);
            leftSum -= min;
            rightSum -= min;
          }
        }
        //remove of 0
        if (rightSum < leftSum) {
          right = new HashMap<>(left);
          long t = leftSum;
          leftSum = rightSum;
          rightSum = t;
        }
        //now we want to move some pieces from right to left
        long diff = rightSum - leftSum; // TODO sum(right) - sum(left);
        if (diff > 0) {
          //we need to move diff/2 socks from right to left
          //lets greedily pick socks whose frequency > 1 and move them to left
          for (long rightKey : right.keySet()) {
            if (right.get(rightKey) > 1) {
              //this is a possible candidate
              long available = right.get(rightKey);
              //lets see if we can use all of them
              //current color can provide available/2 shifts , we need diff/2 shifts . we need min of these two as shift
              long minShift = Math.min(available / 2, diff / 2);
              //we can move minShift from right to left and be done with them. so add them to ans
              ans += minShift;
              diff -= 2 * minShift;
              //update remaining , no need to actually move them
              //reduce minShift*2 from right
              right.put(rightKey, right.get(rightKey) - 2 * minShift);
              rightSum -= 2 * minShift;
              if (diff == 0)
                break;
            }
          }
        }
        //now we have diff > 0 and right has unique socks
        if (diff == 0) {
          ans += leftSum; //TODO sum(left);
        } else {
          ans += leftSum + diff; //TODO sum(left) + diff;
        }
        System.out.println(Math.min(ans, n));
      }
    }
  }


}
