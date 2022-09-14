package codeforce.educational.r108;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Coriginal {

//		            ***                          ++                      
//	             +=-==+                      +++=-                     
//	            +-:---==+                   *+=----=                   
//	           +-:------==+               ++=------==                  
//	           =-----------=++=========================                
//	          +--:::::---:-----============-=======+++====             
//	          +---:..:----::-===============-======+++++++++           
//	          =---:...---:-===================---===++++++++++         
//	          +----:...:-=======================--==+++++++++++        
//	          +-:------====================++===---==++++===+++++      
//	         +=-----======================+++++==---==+==-::=++**+     
//	        +=-----================---=======++=========::.:-+*****    
//	       +==::-====================--:  --:-====++=+===:..-=+*****   
//	       +=---=====================-...  :=..:-=+++++++++===++*****  
//	       +=---=====+=++++++++++++++++=-:::::-====+++++++++++++*****+ 
//	      +=======++++++++++++=+++++++============++++++=======+****** 
//	      +=====+++++++++++++++++++++++++==++++==++++++=:...  . .+**** 
//	     ++====++++++++++++++++++++++++++++++++++++++++-.     ..-+**** 
//	     +======++++++++++++++++++++++++++++++++===+====:.    ..:=++++ 
//	     +===--=====+++++++++++++++++++++++++++=========-::....::-=++* 
//	     ====--==========+++++++==+++===++++===========--:::....:=++*  
//	     ====---===++++=====++++++==+++=======-::--===-:.  ....:-+++   
//	     ==--=--====++++++++==+++++++++++======--::::...::::::-=+++    
//	     ===----===++++++++++++++++++++============--=-==----==+++     
//	     =--------====++++++++++++++++=====================+++++++     
//	     =---------=======++++++++====+++=================++++++++     
//	     -----------========+++++++++++++++=================+++++++    
//	     =----------==========++++++++++=====================++++++++  
//	     =====------==============+++++++===================+++==+++++ 
//	     =======------==========================================++++++

  // created by : Nitesh Gupta

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();
    while (t-- > 0) {
      String[] scn = (br.readLine()).trim().split(" ");
      int n = Integer.parseInt(scn[0]);
      HashMap<Integer, ArrayList<Long>> map = new HashMap<>();

      scn = (br.readLine()).trim().split(" ");
      String[] scn1 = (br.readLine()).trim().split(" ");
      for (int i = 0; i < n; i++) {
        int idx = Integer.parseInt(scn[i]);
        long pow = Long.parseLong(scn1[i]);
        if (!map.containsKey(idx)) {
          map.put(idx, new ArrayList<>());
        }
        map.get(idx).add(pow);
      }
      long[] ans = new long[n + 1];

      for (int key : map.keySet()) {
        ArrayList<Long> list = map.get(key);
        Collections.sort(list, Collections.reverseOrder());
        int size = list.size();
        long[] pre = new long[n + 1];
        for (int j = 1; j <= size; j++) {
          pre[j] = pre[j - 1] + list.get(j-1);
        }
        for (int j = 1; j <= size; j++) {
          int mod = size % j;
          ans[j] += pre[size - mod ];
        }
      }

      for (int i = 1; i <= n; i++) {
        sb.append(ans[i] + " ");
      }
      sb.append("\n");
    }
    System.out.println(sb);

    return;

  }

}