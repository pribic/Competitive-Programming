package codeforce.div2.r718;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 23/04/21
 */
public class B {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<List<Integer>> grid = new ArrayList<>();
        for (int i = 0; i < n; i++) {
          grid.add(new ArrayList<>());
          for (int j = 0; j < m; j++) {
            grid.get(i).add(sc.nextInt());
          }
        }
        //n=2, m = 3
        // 2 3 4
        // 1 3 5
        for (int i = 0; i < n; i++) {
          Collections.sort(grid.get(i));
        }
        List<List<Integer>> ans = new ArrayList<>();
        //find mean from current left indexes;
        for (int ii = 0; ii < m; ii++) {
          ans.add(new ArrayList<>());
          int curMin = Integer.MAX_VALUE;
          for (int i = 0; i < n; i++)
            curMin = Math.min(curMin, grid.get(i).get(0));
          boolean hasCurrentMinCome = false;
          for (int i = 0; i < n; i++) {
            int first = grid.get(i).get(0);
            int last = grid.get(i).get(grid.get(i).size() - 1);
            if (first == curMin && !hasCurrentMinCome) {
              hasCurrentMinCome = true;
              ans.get(ii).add(first);
              grid.get(i).remove(0);
            } else {
              ans.get(ii).add(last);
              grid.get(i).remove(grid.get(i).size() - 1);
            }
          }
        }
        //ans has m * n grid
        for(int i = 0; i < n; i++) {
          for(int j = 0; j < m; j++){
            System.out.print(ans.get(j).get(i) + " ");
          }
          System.out.println();
        }

      }
    }
  }
}