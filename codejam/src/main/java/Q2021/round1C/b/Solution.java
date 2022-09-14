package Q2021.round1C.b;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 01/05/21
 */
public class Solution {

  public static void main(String[] args) {
    List<Long> roadingNum = new ArrayList<>(new HashSet<>(generate()));
    Collections.sort(roadingNum);
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        int l = -1;
        int r = roadingNum.size();
        while (r > l + 1) {
          int mid = l + ( r - l) / 2;
          if(roadingNum.get(mid) > n) {
            r = mid;
          } else {
            l = mid;
          }
        }
        System.out.println(roadingNum.get(r));
      }
    }
  }

  private static List<Long> generate() {
    List<Long> ans = new ArrayList<>();
    for (int i = 1; i <= 999; i++) {
      generateAllFrom(i, ans);
    }
    return ans;
  }

  private static void generateAllFrom(long num, List<Long> ans1) {
    StringBuilder ans = new StringBuilder(num + "");
    while (ans.length() <= 7) {
      ans.append(++num);
      ans1.add(Long.valueOf(ans.toString()));
    }
  }
}