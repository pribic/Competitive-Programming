package codeforce.div2.r740;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
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
 * @see <a href="https://codeforces.com/contest/1561/problem/D1" target="_top">https://codeforces.com/contest/1561/problem/D1</a>
 * @since 24/08/21 9:27 PM
 */
public class D1 {
  static FastScanner sc = new FastScanner(System.in);

  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(System.out)) {
      int T = 1;//sc.nextInt();
      for (int tt = 1; tt <= T; tt++) {
        // long time = System.currentTimeMillis();
        int n = sc.nextInt();
        int mod = sc.nextInt();
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int[] prefix = new int[n + 1];
        prefix[1] = 1;
        for (int i = 2; i <= n; i++) {
          for (int j = 1; j < dp.length; j++) {
            int left = i / (j + 1);
            int right = i / j;
            if(left == right && left == 1)
              break;
            int now = (dp[j] * ((right - left + mod) % mod)) % mod;
            dp[i] = (dp[i] + now) % mod;
          }
          dp[i] = (dp[i] + prefix[i - 1]) % mod;
          prefix[i] = (prefix[i - 1] + dp[i]) % mod;
        }
        System.out.println(dp[n]);
        //System.out.println(System.currentTimeMillis() - time);
      }
    }
  }

  private static int solve(int n, int mod, int[] dp) {
    if (n < 0)
      return 0;
    if (n == 1)
      return 1;
    if (dp[n] != -1)
      return dp[n];
    int cur = 0;
    for (int sub = 1; sub <= n - 1; sub++) {
      cur = (cur + solve(n - sub, mod, dp) % mod) % mod;
    }
    for (int div = 2; div <= n; div++) {
      cur = (cur + solve(n / div, mod, dp) % mod) % mod;
    }
    return dp[n] = cur;
  }

  static class FastScanner {
    BufferedReader br;
    StringTokenizer st;

    public FastScanner(File f) {
      try {
        br = new BufferedReader(new FileReader(f));
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }

    public FastScanner(InputStream f) {
      br = new BufferedReader(new InputStreamReader(f), 32768);
    }

    String next() {
      while (st == null || !st.hasMoreTokens()) {
        String s = null;
        try {
          s = br.readLine();
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (s == null)
          return null;
        st = new StringTokenizer(s);
      }
      return st.nextToken();
    }

    boolean hasMoreTokens() {
      while (st == null || !st.hasMoreTokens()) {
        String s = null;
        try {
          s = br.readLine();
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (s == null)
          return false;
        st = new StringTokenizer(s);
      }
      return true;
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    long nextLong() {
      return Long.parseLong(next());
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }
  }
}
/*
/Library/Java/JavaVirtualMachines/jdk8u181-b13/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA 2020.3 CE EAP.app/Contents/lib/idea_rt.jar=59565:/Applications/IntelliJ IDEA 2020.3 CE EAP.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Library/Java/JavaVirtualMachines/jdk8u181-b13/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk8u181-b13/Contents/Home/jre/lib/ext/cldrdata.jar:/Library/Java/JavaVirtualMachines/jdk8u181-b13/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/jdk8u181-b13/Contents/Home/jre/lib/ext/jaccess.jar:/Library/Java/JavaVirtualMachines/jdk8u181-b13/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/jdk8u181-b13/Contents/Home/jre/lib/ext/nashorn.jar:/Library/Java/JavaVirtualMachines/jdk8u181-b13/Contents/Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/jdk8u181-b13/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Library/Java/JavaVirtualMachines/jdk8u181-b13/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Library/Java/JavaVirtualMachines/jdk8u181-b13/Contents/Home/jre/lib/ext/zipfs.jar:/Library/Java/JavaVirtualMachines/jdk8u181-b13/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk8u181-b13/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk8u181-b13/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/jdk8u181-b13/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk8u181-b13/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk8u181-b13/Contents/Home/lib/dt.jar:/Library/Java/JavaVirtualMachines/jdk8u181-b13/Contents/Home/lib/jconsole.jar:/Library/Java/JavaVirtualMachines/jdk8u181-b13/Contents/Home/lib/sa-jdi.jar:/Library/Java/JavaVirtualMachines/jdk8u181-b13/Contents/Home/lib/tools.jar:/Users/pdoshi/personal-tech/codejam/out/production/classes codeforce.div2.r740.D1
5 998244353
i = 2
2 2 1 1 2
1 1 0 0 2
1 0 0 0 2
1 0 0 0 2
1 0 0 0 2
i = 3
2 3 2 1 5
2 1 0 2 5
1 1 0 0 5
1 0 0 0 5
1 0 0 0 5
i = 4
3 4 2 1 10
2 2 2 2 12
2 1 0 5 12
1 1 0 0 12
1 0 0 0 12
i = 5
3 5 3 1 23
2 2 2 2 25
2 1 0 5 25
2 1 0 12 25
1 1 0 0 25
25

Process finished with exit code 0

 */