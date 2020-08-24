package leetcode.weeklycontest.r194;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MakingFileNamesUnique {

  public static void main(String[] args) {
    new MakingFileNamesUnique().findLeastNumOfUniqueInts(new int[]{4,3,1,1,3,3,2}, 3);
  }
  public int findLeastNumOfUniqueInts(int[] arr, int k) {
    Map<Integer, Integer> freq = new HashMap<>();
    for(int ar : arr)
      freq.put(ar, freq.getOrDefault(ar, 0) + 1 );
    List<Holder> list = new ArrayList<Holder>();
    for(Map.Entry<Integer, Integer> entry : freq.entrySet())
      list.add(new Holder(entry.getKey(), entry.getValue()));
    Collections.sort(list, (o1, o2) -> o1.val - o2.val);
    while(k > 0) {
      if(list.get(0).val <= k) {
        k -= list.get(0).val;
        list.remove(0);
      } else {
        break;
      }
    }
    return list.size();

  }
  static class Holder  {
    int key;
    int val;
    public Holder(int key, int val) {
      this.key = key;
      this.val = val;
    }
  }
    public String[] getFolderNames(String[] names) {
    
      Map<String, Integer> map = new HashMap<>();
      String[] out = new String[names.length];
      int idx = 0;
      for(String n : names) {
        if(map.containsKey(n)) {
          //find next valid name;
          int start = 1;
          while(map.containsKey(n + "(" + start++ + ")")){
          }
          out[idx++] = n + "(" + (start  - 1)+ ")";
          map.put(out[idx-1], start);
          System.out.println(out[idx-1]);
        } else {
          out[idx++] = n;
          map.put(out[idx-1], 1); 
        }
      }
      return out;
    }
}
