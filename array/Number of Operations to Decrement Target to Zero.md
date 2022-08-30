# LeetCode Link

[Number of Operations to Decrement Target to Zero | binarysearch](https://binarysearch.com/problems/Number-of-Operations-to-Decrement-Target-to-Zero)

# Idea

The question can be converted to `find maximum length of subtrings that has sum equals to totalSum - target`

# Code

```java
import java.util.*;

class Solution {
    public int solve(int[] nums, int target) {
        // query maximum length of subtring that sum equals to sum - target
        if (nums == null || nums.length <= 0) return target == 0 ? 0 : -1;
        int n = nums.length;
        int sum = 0;
        for (int i : nums) sum += i;
        if (sum == target) return n;
        int l = 0, r = 0;
        int cur = 0;
        int res = -1;
        while (r < n) {
            cur += nums[r];
            r++;
            while (l < r && cur >= sum - target) {
                if (cur == sum - target) {
                    res = Math.max(res, r - l);
                }
                cur -= nums[l];
                l++;
            }
        }
        return res == -1 ? -1 : n - res;
    }
}
```

# Complexity Analysis

Time Complexity

O(N)

Space Complexity

O(1)
