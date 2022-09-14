import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FlattenIterator {

  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(1,2,3);
    List<Integer> list1 = Arrays.asList(4);
    List<Integer> empty = Arrays.asList();
    List<Integer> list2 = Arrays.asList(5,6);
    List<List<Integer>> finalList = Arrays.asList(list1, list, empty, empty, empty, list, list1, empty, list2, empty);
    CustomIterator ci = new CustomIterator(finalList);
    while (ci.hasNext())
      System.out.print(ci.next() + " ");
    System.out.println();
  }
  
  static class CustomIterator implements Iterator<Integer> {
    
    private boolean hasMore = false;
    
    private List<List<Integer>> list;
    
    private int x = 0; //represents outer list
    private int y = 0; // represets element within list[x]
    //together x and y represets next element we can serve while calling next()
    
    public CustomIterator(List<List<Integer>> list) {
      this.list = list;
      for(int i = 0; i < list.size(); i++) {
        if(!list.get(i).isEmpty()) {
          x = i;
          y = 0;
          hasMore = true;
          break;
        }
      }
    }
    
    public boolean hasNext() {
      return hasMore;
    }
    
    public Integer next() {
      if(!hasMore) 
        throw new NoSuchElementException("list is empty, please use hasNext() before calling next()");
      int ans = list.get(x).get(y);
      //move x and y to next available number
      if(y + 1 < list.get(x).size()) y++;
      else {
        //ommit empty list
        x++;
        while (x < list.size() && list.get(x).isEmpty()) x++;
        if(x == list.size()) {
          hasMore = false;
        } else {
          y = 0;
        }
      }
      return ans;
    }
  }
}
 