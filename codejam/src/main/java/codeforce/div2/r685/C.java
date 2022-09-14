package codeforce.div2.r685;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class C {

  public static void main(String[] args) {

     
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      while (t-- > 0) {
        int n = sc.nextInt();
        int k = sc.nextInt();
        String a = sc.next();
        String b = sc.next();
        Map<Character, Integer> map1 = populateFrequencyMap(a);
        Map<Character, Integer> map2 = populateFrequencyMap(b);

        boolean flag = false;
        
        for(char c = 'a'; c <= 'z';c++) {
          if(map1.containsKey(c) && map2.containsKey(c)) {
            int min = Math.min(map1.get(c), map2.get(c));
            map1.put(c, map1.get(c) - min);
            map2.put(c, map2.get(c) - min);
            if(map1.get(c) ==0)
              map1.remove(c);
            if(map2.get(c) == 0)
              map2.remove(c);
          }
        }
        boolean flag1 = true;
        while (!map1.isEmpty() && !map2.isEmpty() && flag1) {
          flag1 = false;
          char c1 = 'z';
          for (char c = 'a'; c < 'z'; c++) {
            if (map1.containsKey(c)) {
              c1 = c;
              break;
            }
          }
          char c2 = '0';
          for (char c = 'a'; c <= 'z'; c++) {
            if (map2.containsKey(c)) {
              c2 = c;
              break;
            }
          }
          if (c1 > c2 || c1 == 'z' || c2 == '0') {
            flag = true;
            break;
          } else {
            int min = Math.min(map1.get(c1), map2.get(c2));
            if (min >= k) {
              flag1 = true;
              map1.put(c1, map1.get(c1) - min);
              map2.put(c2, map2.get(c2) - min);
              if(map1.get(c1) == 0)
                map1.remove(c1);
              if(map2.get(c2) == 0)
                map2.remove(c2);
            }
          }
        }

        if (flag) {
          System.out.println("NO");
        } else {
          System.out.println(map1.isEmpty() && map2.isEmpty() ? "YES" : "NO");
        }

      }
    }
  }

  private static Map<Character, Integer> populateFrequencyMap(String a) {
    Map<Character, Integer> map1 = new TreeMap<>();
    for (char x : a.toCharArray()) {
      if (map1.containsKey(x))
        map1.put(x, map1.get(x) + 1);
      else
        map1.put(x, 1);
    }
    return map1;
  }

}











