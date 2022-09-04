# LeetCode Link

[Shortest Cycle Containing Target Node | binarysearch](https://binarysearch.com/problems/Shortest-Cycle-Containing-Target-Node)

# Idea

Start from target and use BFS.

# Code

```java
import java.util.*;

class Solution {
    public int solve(int[][] graph, int target) {
        // BFS. Search from target.
        int n = graph.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(target);
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            step++;
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                visited[cur] = true;
                for (int next : graph[cur]) {
                    if (visited[next] && next == target) return step;
                    if (visited[next]) continue;
                    q.offer(next);
                }
            }
        }
        return -1;
    }
}
```

# Complexity Analysis

**Time Complexity**

O(N). Because potentially we will traverse all the nodes.

**Space Complexity**

O(N). Because potentially we will store all the nodes.
