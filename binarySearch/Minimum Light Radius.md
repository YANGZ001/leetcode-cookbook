# LeetCode Link

[Minimum Light Radius | binarysearch](https://binarysearch.com/problems/Minimum-Light-Radius)

# Idea

Use binary search.

`Search Range` is `0` to `max distance`

If `mid` can cover, then we reduce the `upper bound`.

Else increase the `lower bound`.

# Code

```java
import java.util.*;

class Solution {
    public double solve(int[] nums) {
        if (nums == null || nums.length <= 3) return 0;
        int n = nums.length;
        Arrays.sort(nums);
        int l = 0, r = nums[n-1];
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (can(nums, mid)) {
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }
        return l / 2.0;
    }
    
    public boolean can(int[] nums, int l) {
        int n = nums.length;
        int cnt = 1;
        int end = nums[0] + l;
        for (int i = 0; i < n; i++) {
            if (nums[i] > end) {
                cnt++;
                end = nums[i] + l;
            }
        }
        return cnt <= 3;
    }
}
```

# Complexity Analysis

Time Complexity

O(NlogN)

Space Complexity

O(1)
