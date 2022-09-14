package algoridhm.string;

import java.util.*;

class Solution {
  public static void main(String[] args) {
    System.out.println(new Solution().solve(new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}}, 2, 4));
  }

  public int solve(int[][] requests, int u, int g) {
    Arrays.sort(requests, (int[] a, int[] b) -> {
      if (a[1] == b[1]) {
        return a[0] - b[0]; // sort by user id when timestamp is same
      } else {
        return a[1] - b[1]; // sort by timestamp otherwise
      }
    });
    for (int[] req : requests) System.out.println(Arrays.toString(req));
    Map<Integer, Deque<int[]>> user = new HashMap<>();
    Deque<int[]> global = new ArrayDeque<>();

    int cnt = 0;
    for (int[] req : requests) {
      boolean f1 = false;
      boolean f2 = false;
      int uid = req[0];
      int ts = req[1];
      System.out.println(uid + " " + ts);
      process(uid, ts, global);
      if (global.size() < g) {
        //we can process this request
        f2 = true;
      }
      if (f2) {
        if (user.containsKey(uid)) {
          Deque<int[]> userSpecific = user.get(uid);
          process(uid, ts, userSpecific);
          if (userSpecific.size() < u) {
            f1 = true;
          } else if (userSpecific.size() == 0) {
            user.remove(uid);
          }
        } else {
          user.put(uid, new ArrayDeque<int[]>());
          user.get(uid).offer(req);
          f1 = true;
        }
      }

      if (f1 && f2) {
        cnt++;
        System.out.println("yes");
        global.offer(req);
        user.get(uid).offer(req);
      } else {
        System.out.println("no");
      }
    }

    return cnt;
  }

  void process(int uid, int ts, Deque<int[]> q) {
    while (!q.isEmpty()) {
      int[] left = q.peek();
      if (ts - left[1] >= 60)
        q.poll();
      else
        break;
    }
  }
}

/*
cnt = 1 2
u = 1, g = 2

reject : 606

global : 602
1 - 602
2 -
3 - 503
4 -


 */