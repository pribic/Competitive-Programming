package codeforce.practice;
// Java program that print maximum
// number of overlap among given ranges
import java.util.*;
import java.lang.*;
import java.io.*;
public class dummy{

  static class pair
  {
    int first;
    char second;

    pair(int first, char second)
    {
      this.first = first;
      this.second = second;
    }
  }

  // Function that print maximum
// overlap among ranges
  static void overlap(int[][] v)
  {

    // Variable to store the maximum
    // count
    int ans = 0;
    int count = 0;
    ArrayList<pair> data = new ArrayList<>();

    // Storing the x and y
    // coordinates in data vector
    for(int i = 0; i < v.length; i++)
    {

      // Pushing the x coordinate
      data.add(new pair(v[i][0], 'x'));

      // pushing the y coordinate
      data.add(new pair(v[i][1], 'y'));
    }

    // Sorting of ranges
    Collections.sort(data, (a, b) -> a.first - b.first);

    // Traverse the data vector to
    // count number of overlaps
    for(int i = 0; i < data.size(); i++)
    {

      // If x occur it means a new range
      // is added so we increase count
      if (data.get(i).second == 'x')
        count++;

      // If y occur it means a range
      // is ended so we decrease count
      if (data.get(i).second == 'y')
        count--;

      // Updating the value of ans
      // after every traversal
      ans = Math.max(ans, count);
    }

    // Printing the maximum value
    System.out.println(ans);
  }

  // Driver code
  public static void main(String[] args)
  {
    int[][] v = { { 1, 3 },
      { 2, 4 },
      { 3, 6 } };
    overlap(v);
  }
}

// This code is contributed by offbeat
