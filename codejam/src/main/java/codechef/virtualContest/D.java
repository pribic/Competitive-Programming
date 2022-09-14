package codechef.virtualContest;

import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import static java.lang.System.out;
import static java.util.stream.Collectors.joining;

/**
 * @author pribic (Priyank Doshi)
 * @since 10/05/21
 */
public class D {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] cities = new int[n];
        for (int i = 0; i < n; i++) {
          cities[i] = sc.nextInt();
        }
        int[] towers = new int[m];
        for (int i = 0; i < m; i++) {
          towers[i] = sc.nextInt();
        }
        long l = -1;
        long r = Integer.MAX_VALUE;
        while (r > l + 1) {
          long mid = l + (r - l) / 2;
          //out.println(mid);
          if(check(cities, towers, mid))
            r = mid;
          else
            l = mid;
        }
        out.println(r);
      }
    }
  }
 //tower - - - -
 //city  - - -
  private static boolean check(int[] cities, int[] towers, long r) {
    int idxCity = 0;
    int idxTowers = 0;
    while (idxCity < cities.length && idxTowers < towers.length) {
      if(towers[idxTowers] < cities[idxCity] - r || towers[idxTowers] > cities[idxCity] + r)
        idxTowers++;
      else
        idxCity++;
    }
    return idxCity == cities.length;
    //2 ways
    //look from tower
    //mark all city which are tower -r and tower + r range as covered
    //at the end, all city should be coverd
    
    //look from city
    //check if there is a tower in city + r or city - r range, if yes
  }
}