package kickstart.Y2020.round1E.Toys;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 1; tt <= t ; tt++) {
        System.out.print("Case #" + tt + ": ");
        int n = sc.nextInt();
        List<Pair> pairs = new ArrayList<>(n);
        long totalE = 0;
        for (int i = 0; i < n; i++) {
          int e = sc.nextInt();
          int r = sc.nextInt();
          totalE += e;
          pairs.add(new Pair(e, r));
        }
        for (int i = 0; i < n; i++) {
          long remainingE = totalE - pairs.get(i).e;
          if(remainingE < pairs.get(i).r) {
            //bad toy
            pairs.remove(pairs.get(i));
            totalE -= pairs.get(i).e;
          }
        }
        
      }
    }
  }
  
  static class Pair {
    int e, r;

    public Pair(int e, int r) {
      this.e = e;
      this.r = r;
    }
  }
}
