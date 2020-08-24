package codeforce.div2.r649;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProblemA {
  public static void main(String[] args) throws Exception {
    long startTime = System.currentTimeMillis();
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        int x = sc.nextInt();
        int[] in = new int[n];
        for (int j = 0; j < n; j++) {
          in[j] = sc.nextInt();
        }
        int ans = -1;
        int[] mod_arr = new int[n];
          int cur_sum = 0;
          int firstNonZero = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
          cur_sum += in[i];
          mod_arr[i] = cur_sum % x;
          if(mod_arr[i] != 0)
            firstNonZero = Math.min(firstNonZero, i);
        }
        for (int i = 0; i < n; i++) {
          if(mod_arr[i] != 0)
            ans = i + 1;
          else if(ans != -1)
            ans = Math.max(ans,  i - firstNonZero);
        }
        System.out.println(ans);
      }
    }
  }
}


class DummScanner implements AutoCloseable{

  int i;
  public DummScanner(InputStream in) {
    
  }

  @Override
  public void close() throws Exception {
    
  }

  public int nextInt() {
    return i++;
  }
}
