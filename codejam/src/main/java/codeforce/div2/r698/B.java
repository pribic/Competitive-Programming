package codeforce.div2.r698;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 28/01/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      ArrayList<Integer>[] nums = prebuild();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        for (int i = 0; i < n; i++) {
          int val = sc.nextInt();
          if (q == 1) {
            System.out.println("Yes");
          } else {
            ArrayList<Integer> list = nums[q];
            boolean found = false;
            for (Integer x : list) {
              if ((val - x) >= 0 && ((val - x) % q == 0)) {
                found = true;
                break;
              }
            }
            System.out.println(found ? "Yes" : "No");
          }
        }
      }
    }
  }

  private static ArrayList<Integer>[] prebuild() {
    ArrayList<Integer>[] ans = new ArrayList[10];
    ans[0] = new ArrayList<>();
    ans[1] = new ArrayList<>();
    for (int num = 2; num <= 9; num++) {
      ans[num] = new ArrayList<>();
      ans[num].add(num);
      String s = num + "";
      for (int reminder = 1; reminder < num; reminder++) {
        //find first integer where digit num is present and reminder is reminder
        int findMe = 9;
        while (true) {
          if (findMe % num == reminder) {
            //check for digit
            if (String.valueOf(findMe).contains(s))
              break;
          }
          findMe++;
        }
        ans[num].add(findMe);
      }
    }
    return ans;
  }
}