package Q2020;

import java.util.function.BiFunction;

class abc {
  public static void main(String[] args) {
    //   System.out.println(new abc().longestSubarray( new int[]{8,2,4,7},4));
    System.out.println(new abc().longestSubarray(new int[]{10, 1, 2, 4, 7, 2}, 5));
    // System.out.println(new abc().longestSubarray( new int[]{4,2,2,2,4,4,2,2},0));
  }

  public int longestSubarray(int[] nums, int limit) {
    int ans = 1;
    
    BiFunction<Integer, Integer, Boolean> minHeapProperty = (x, y) -> x < y;
    BiFunction<Integer, Integer, Boolean> maxHeapProperty = (x, y) -> x > y;
    
    Heap minHeap = new Heap(nums.length, minHeapProperty, Integer.MAX_VALUE);
    Heap maxHeap = new Heap(nums.length, maxHeapProperty, Integer.MIN_VALUE); 
    
    int[] bestLen = new int[nums.length];
    minHeap.add(nums[0]);
    maxHeap.add(nums[0]);
    bestLen[0] = 1;
    for (int i = 1; i < nums.length; i++) {
      if (Math.abs(minHeap.getRoot() - nums[i]) <= limit && Math.abs(maxHeap.getRoot() - nums[i]) <= limit) {
        bestLen[i] = bestLen[i - 1] + 1;
        ans = Math.max(bestLen[i], ans);
        minHeap.add(nums[i]);
        maxHeap.add(nums[i]);
      } else {
        while (Math.abs(minHeap.getRoot() - nums[i]) > limit)
          minHeap.deleteRoot();
        while (Math.abs(maxHeap.getRoot() - nums[i]) > limit)
          maxHeap.deleteRoot();
        if(minHeap.size() == 0) {
          minHeap = new Heap(nums.length, minHeapProperty, Integer.MAX_VALUE);
        }
        if(maxHeap.size == 0)
          maxHeap = new Heap(nums.length, maxHeapProperty, Integer.MIN_VALUE);
      }
    }
    return ans;
  }

  static class Heap {
    int[] arr;
    int size;
    BiFunction<Integer, Integer, Boolean> heapProperty;
    int defaultValue;

    Heap(int size, BiFunction<Integer, Integer, Boolean> heapProperty, int defaultValue) {
      arr = new int[size];
      this.size = 0;
      this.heapProperty = heapProperty;
      this.defaultValue = defaultValue;
    }

    int size() {
      return size;
    }
    void add(int x) {
      arr[size] = x;
      size++;
      int child = size;
      int parent = child / 2;
      while (!heapProperty.apply(arr[parent], arr[child])) {
        swap(arr, parent, child);
        child = parent;
        parent /= 2;
      }
    }

    private void swap(int[] arr, int parent, int child) {
      int temp = arr[parent];
      arr[parent] = arr[child];
      arr[child] = temp;
    }

    int getRoot() {
      if (size == 0)
        return defaultValue;
      return arr[0];
    }

    void deleteRoot() {
      if (size == 0)
        return;
      arr[0] = arr[size - 1];
      size--;

      int parent = 0;
      int leftChild = 2 * parent;
      int rightChild = leftChild + 1;
      while (!heapProperty.apply(arr[parent], arr[leftChild]) || !heapProperty.apply(arr[parent], arr[rightChild])) {
        if (heapProperty.apply(arr[leftChild], arr[rightChild])) {
          swap(arr, parent, leftChild);
          parent = leftChild;
          leftChild = 2 * leftChild;
          rightChild = leftChild + 1;
        } else {
          swap(arr, parent, rightChild);
          parent = rightChild;
          rightChild = 2 * rightChild + 1;
          leftChild = rightChild - 1;

        }
      }
    }
  }
}