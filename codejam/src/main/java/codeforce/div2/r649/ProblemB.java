package codeforce.div2.r649;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProblemB {
  public static void main(String[] args) throws Exception {
    long startTime = System.currentTimeMillis();
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        int[] in = new int[n];
        for (int j = 0; j < n; j++) {
          in[j] = sc.nextInt();
        }
        List<Integer> output = new ArrayList<>();
        output.add(in[0]);
        for(int i = 1; i < n - 1; i++) {
          if(!between(in, i))
            output.add(in[i]);
        }
        output.add(in[n - 1]);
        System.out.println(output.size());
        for(Integer i : output)
          System.out.print(i + " ");
        System.out.println();
      }
    }
  }

  private static boolean between(int[] in, int i) {
    int prev = in[i-1];
    int cur = in[i];
    int next = in[i+1];
    if(prev < cur && cur < next)
      return true;
    if(prev > cur && cur > next)
      return true;
    return false;
  }
}
