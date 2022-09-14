package codeforce.div2.r672;

import java.util.Scanner;

public class C_PokemonArmy {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
          arr[i] = sc.nextInt();
        }
        long ans = 0;
        int index = 0;
        while (true) {
          //find peak
          while (index < n - 1 && arr[index] < arr[index + 1]) index++;
          ans += arr[index];
          index++;
          //find lower peak
          while (index < n - 1 && arr[index] > arr[index + 1]) index++;
          if (index < n)
            ans -= arr[index];
          if (index >= n - 1)
            break;
        }
        //1 2 3 2 1 2 3 2 1 2 3 2
        // add last element back
        if( n >= 2 && arr[n - 1] < arr[n-2]) {
          ans += arr[n-1];
        }
        System.out.println(ans);
      }
    }
  }
} 

