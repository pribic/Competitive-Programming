package codeforce.div2.r681;

import java.util.Scanner;

public class B_SavingtheCity {

  public static void main(String[] args) {
    deleteme();
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int a = sc.nextInt();
        int b = sc.nextInt();
        String s = sc.next();
        int cnt = 0;
        boolean flag = false;
        int continuousZero = 0;
        int baseCost = Math.min(2 * a, a + b);
        int zeroCnt = 0;
        StringBuilder sb = new StringBuilder();
        for (char x : s.toCharArray()) {
          if (x == '0') {
            sb.append(x);
            flag = false;
          }
          if (x == '1' & !flag) {
            sb.append(x);
            flag = true;
          }
        }
        s = sb.toString();
       // System.out.println("after removal of 1 " + s);
        //removed all extra 1 so far

        //remove all extra 0 so far

        sb = new StringBuilder();
        for (char x : s.toCharArray()) {
          if (x == '1') {
            sb.append(x);
            zeroCnt = 0;
          }
          if (x == '0' && zeroCnt <= 1) {
            sb.append(x);
          }
          if (x == '0') {
            zeroCnt++;
          }
        }

        s = sb.toString();
      //  System.out.println("after removal of extra 0 " + s);
        int fromIndex = 0;
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
          //10101
          int toIndex = s.indexOf("00", fromIndex);
          cnt = 0;
          if(toIndex == -1) {
            //consider till end
            toIndex = s.length() - 1;
          }
          for (int j = fromIndex; j <= toIndex; j++) {
            if (s.charAt(j) == '1') {
              cnt++;
            }
          }
          ans += cost(cnt, baseCost, a);
          i = toIndex;
          fromIndex = toIndex + 2;
        }
        System.out.println(ans);
      }
    }
  }

  private static void deleteme() {
    double start = 1.62d;
    double total = 0.0d;
    for (int i = 0; i < 9; i++) {
      start *= 1.20;
      total += start;
      System.out.println(i + "=" + start);
    }
    System.out.println("total = " + total);
  }

  private static int cost(int cnt, int baseCost, int a) {
    if(cnt == 1)
      return a;
    if (cnt % 2 == 0)
      return (baseCost * cnt / 2);
    else
      return (baseCost * cnt / 2 + a);
  }
}

//20 18 16 14 12
