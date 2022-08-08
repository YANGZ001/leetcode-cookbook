# LeetCode Link

[Sublist Sum | binarysearch](https://binarysearch.com/problems/Sublist-Sum)

# Idea

Similar to maximum subarray question. Don't think of it as a presum question.


# Code

```java
import java.util.*;

class Solution {
    public boolean solve(int[] nums) {
        int n = nums.length;
        int cur = 0;
        int sum = 0;
        for (int i : nums) sum += i;
        if (sum < 0) return true;
        for (int i = 0; i < n; i++) {
            cur = Math.max(cur + nums[i], nums[i]);
            if (cur > sum) return true;
        }
        return false;
    }
}
```

# Complexity Analysis

Time Complexity

O(N)

Space Complexity

O(1)
