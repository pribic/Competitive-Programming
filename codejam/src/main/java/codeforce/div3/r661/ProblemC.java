package codeforce.div3.r661;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author pdoshi 
 */
public class ProblemC {

  public static void main(String[] args) {
    try(Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
          int val = sc.nextInt();
          arr[i] = val;
          frequencyMap.put(val, frequencyMap.getOrDefault(val, 0) + 1);
        }

        Arrays.sort(arr);
        //
   
        //
        if(n == 1) {
          System.out.println("0");
          continue;
        }
          
        int maxSum = arr[n - 1] + arr[n - 2];
        int ans = 1;
        
        for(int i = 2; i <= maxSum; i++) {
          int cnt = 0;
          Map<Integer, Integer> backup = new HashMap<>();
          for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet())
            backup.put(entry.getKey(), entry.getValue());
          for (int j = 0; j < n; j++) {
            int a = arr[j];
            
            int b = i - a;

            if(!frequencyMap.containsKey(a) || !frequencyMap.containsKey(b))
              continue;
            if( a == b) {
              if(frequencyMap.get(a) >=2)
                cnt++;
              else
                continue;
              frequencyMap.put(a, frequencyMap.get(a) - 2);
              if(frequencyMap.get(a) == 0)
                frequencyMap.remove(a);
              continue;
            }
            
            if(frequencyMap.containsKey(b))
              cnt++;
            else
              continue;
            frequencyMap.put(b, frequencyMap.get(b) - 1);
            frequencyMap.put(a, frequencyMap.get(a) - 1);
            if(frequencyMap.get(b) == 0)
              frequencyMap.remove(b);
            if(frequencyMap.get(a) == 0)
              frequencyMap.remove(a);
          }
          ans = Math.max(ans, cnt);
          frequencyMap = backup;
        }
        System.out.println(ans);
      }
    }
  }
}
