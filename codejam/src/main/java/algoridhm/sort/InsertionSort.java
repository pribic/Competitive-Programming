package algoridhm.sort;

import java.util.Arrays;

/**
 * @author pribic (Priyank Doshi)
 * @since 28/03/21
 */
public class InsertionSort {

  public static void main(String[] args) {
    int n = 10;
    int[] input = generateArray(n);
    sort(input, 0, n - 1);
    Arrays.stream(input).mapToObj(num -> num + " ").forEach(System.out::print);
    System.out.println();
  }

  private static void sort(int[] input, int l, int r) {
    if(l < r) {
      int index = partition(input, l, r);
      sort(input, l, index - 1);
      sort(input, index + 1, r);
    }
  }

  private static int partition(int[] input, int l, int r) {
    int pivot = input[l];
    while (l < r) {
      while (l < r && input[l] <= pivot) l++;
      while (l < r && input[r] >= pivot) r--;
      swap(input, l, r);
    }
    return l;
  }

  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  private static int[] generateArray(int n) {
    int[] arr = new int[n];
    for(int i = 0; i < n; i++) arr[i] = n - i;
    return arr;
  }
}