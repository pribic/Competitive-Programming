package codeforce.div2.r699;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author pribic (Priyank Doshi)
 * @since 05/02/21
 */
public class A {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int T = sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        int px = sc.nextInt();
        int py = sc.nextInt();
        String s = sc.next();
        Map<Character, Integer> map = new HashMap<>();
        for (char x : s.toCharArray()) map.put(x, map.getOrDefault(x, 0) + 1);
        boolean flag1 = false;
        boolean flag2 = false;
        
        if(px == 0) flag1 = true;
        if(py == 0) flag2 = true;
        
        if(px > 0 && map.containsKey('R') && map.get('R') >= px) flag1 = true;
        if(px < 0 && map.containsKey('L') && map.get('L') >= -px) flag1 = true;
        if(py > 0 && map.containsKey('U') && map.get('U') >= py) flag2 = true;
        if(py < 0 && map.containsKey('D') && map.get('D') >= -py) flag2 = true;
        
        System.out.println(flag1 && flag2 ? "YES" : "NO");
      }
    }
  }
}