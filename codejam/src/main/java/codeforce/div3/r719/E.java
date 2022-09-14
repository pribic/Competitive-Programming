package codeforce.div3.r719;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 05/05/21
 */
public class E {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        String sheep = sc.next();
        List<Integer> sheepPosition = new ArrayList<>();
        for (int i = 0; i < n; i++) {
          if(sheep.charAt(i) == '*')
            sheepPosition.add(i);
        }
        int[] left = new int[sheepPosition.size()];
        int[] right = new int[sheepPosition.size()];
        left[0] = 0;
        for (int i = 1; i < left.length; i++) {
          //suppose we bring everyone to sheepPosition[i]
          
        }
      }
    }
  }
}