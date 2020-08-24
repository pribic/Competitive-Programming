package Q2020.round1C.Problem2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    for (int tt = 1; tt <= t; tt++) {
      int U = sc.nextInt();
      String ans = "";
      int[] freq = new int[26];
      for (int i = 1; i <= 10000; i++) {
        String Q = sc.next();
        String R = sc.next();
        freq[R.charAt(0) - 'A']++;
      }
      List<Holder> holders = new ArrayList<>();
      for(int i = 0; i < 26; i++)
        holders.add(new Holder(i, freq[i]));
      Collections.sort(holders);
      
      for(int i = 25; i >= 16; i--) {
        ans += (char)('A' + holders.get(i).index);
      }
      System.out.println(ans);
    }
    
    sc.close();
  }

  static class Holder  implements  Comparable<Holder>{
    int index;
    int frequency;
    Holder(int index, int frequency) {
      this.index = index;
      this.frequency = frequency;
    }

    @Override
    public int compareTo(Holder o) {
      return this.frequency - o.frequency;
    }
  }
 

}
