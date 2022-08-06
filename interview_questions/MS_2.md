# Challenge description

![](../assets/MS-2.jpeg)

# Idea

1.   Store all the indexes of `R` in a list
2.   Use two pointers `l` and `r`, number of moves should be calculated by `lst[r] - lst[l] - (r - l)`. `lst[r] - lst[l]` calculates the distance from original positions. `r - l` calculates distance after moves. The difference is number of moves.
3.   `l++; r++`

     

# Code

```java
// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    int MAX = 1000000000;
    public int solution(String s) {
        // write your code in Java 8 (Java SE 8)
        // Time Complexity: O(N)
        // Space Complexity: O(N)
        List<Integer> lst = new ArrayList<>();
        int n = s.length();
        // record all the index of R in list
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'R') {
                lst.add(i);
            }
        }
        int k = lst.size();
        if (k == 0) return 0;
        // base case: no red, return 0
        int l = 0, r = k - 1;
        long cnt = 0;
        while (l < r) {
            /*
            for the outmost pair <start, end>, its move should be length from original position minus length from final position
            which is reds[end_ptr] - reds[start_ptr] - end_ptr + start_ptr
            */
            cnt += lst.get(r) - lst.get(l) - (r - l);
            r--;
            l++;
        }
        return cnt <= MAX ? (int) cnt : -1;
    }
}
```

# Complexity Analysis

Time Complexity

O(N)

Space Complexity

O(N)