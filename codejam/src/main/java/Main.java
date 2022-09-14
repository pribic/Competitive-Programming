import java.util.Scanner;

/**
 * 1 2 3 4
 * <p>
 * 1 * 2 = 2
 * 2 * 3 = 6
 * 6 * 4 = 24
 * <p>
 * <p>
 * suppose n = 20
 * <p>
 * 1 2 3 4 1num
 * <p>
 * <p>
 * ans = + 8 + 4 + 2 + 1 + 1
 * <p>
 * <p>
 * 0 4 6
 * <p>
 * <p>
 * 1 + 2 = 3
 * 3 - 3 = 0
 * 0 * 5 = 0
 * 0 * 7 = 0
 * 0 * 8 = 0
 * 0 + 4 = 4
 * <p>
 * in 2 operations we get 1 2 3 converted into 0
 * we have remaining n - 5 elements
 * in n - 5 operations we can reduce them to 0
 * total operation so far is n - 5 + 2 = n - 3
 * array state is 0 4 6
 * now we can have to static operations - 0 + 4 = 4 and 4 * 6 = 24
 * resulting in total 2 + n - 5 + 2 = n - 1 operations
 *
 * @author pribic (Priyank Doshi)
 * @since 03/04/21
 */
public class Main {
  static int MOD = 1000000007;

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      int t = 1;//sc.nextInt();
      for (int tt = 0; tt < t; tt++) {
        long a = sc.nextLong();
        long b = sc.nextLong();
        System.out.println(FindRecursive(a, b));
      }
    }
  }

  // 24/5 -> 5/16 5/11 5/6 1/5
  private static long find(long a, long b) {
    long ans = 1;
    while (a != 1 || b != 1) {
      if(a < b) {
        ans += (b - 1) / a;
        b = b - a;
      } else {
        ans += (a - 1) / b;
        a = a - b;
      }
    }
    return ans;
  }
  
  private static long FindRecursive(long a, long b) {
    if(b == 0) return 0;
    return a / b + FindRecursive(b, a % b);
  }

}
// a b b-a -a -b -b+a a b b-a 