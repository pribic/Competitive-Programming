import java.util.HashMap;
import java.util.Map;

public class NumberOfFiboCalls {


  public static void main(String[] args) {
    for(int i = 0; i < 10; i++) {
      int[] arr = new NumberOfFiboCalls().fibo(i);
      System.out.println(i + " " + arr[0] + " " + arr[1]);
      arr = new NumberOfFiboCalls().fiboCallsMadeMemo(i, new HashMap<>());
      System.out.println(i + " " + arr[0] + " " + arr[1]);
    }
  }
  public int[] fiboCallsMade(int n) {
    if(n==0)
      return new int[] {1, 0};
    int z = 1;
    int o = 0;
    int ans = -1;
    for(int i = 1; i <= n; i++) {
      int sum = z + o;
      z = o;
      o = sum;
    }
    return new int[] {z, o};
  }
  
  public int[] fiboCallsMadeMemo(int n, Map<Integer, int[]> memo) {
    if(memo.containsKey(n))
      return memo.get(n);
    if(n==0) {
      memo.put(0, new int[]{1, 0});
      return memo.get(0);
    }
    if(n==1) {
      memo.put(1, new int[]{0, 1});
      return memo.get(1);
    }
    int[] arr = new int[] { memo.get(n-1)[1], memo.get(n-1)[0] + memo.get(n-1)[1] };
    memo.put(n , arr);
    return arr;
  }
  
  int[] fibo(int n) {
    if( n == 0) 
      return new int[]{1, 0};
    if( n == 1)
      return new int[]{0, 1};
    int[] arr1 = fibo(n-1);
    int [] arr2 = fibo(n-2);
    arr1[0] = arr1[0] + arr2[0];
    arr1[1] = arr1[1] + arr2[1];
    return arr1;
  }
}
