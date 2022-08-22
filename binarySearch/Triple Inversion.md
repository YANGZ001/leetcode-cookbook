# LeetCode Link

[Triple Inversion | binarysearch](https://binarysearch.com/problems/Triple-Inversion)

# Idea

`TreeMap` used.

For `i` in `nums`, find all the nums that is greater than `nums[i]`. Luckily, `TreeMap.tailMap(givenKey, isInclusive)` is provided.

# Code

```java
import java.util.*;

class Solution {
    public int solve(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;
        int n = nums.length;
        TreeMap<Integer, Integer> cnt = new TreeMap<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            NavigableMap<Integer, Integer> nm = cnt.tailMap(nums[i] * 3, false); // > given Key. False means exclusive
            for (Map.Entry<Integer, Integer> entry : nm.entrySet()) {
                res += entry.getValue();
            }
            cnt.put(nums[i], cnt.getOrDefault(nums[i], 0) + 1);
        }
        return res;
    }
}
```

# Complexity Analysis

Time Complexity

O(NlogN)

Space Complexity

O(N)
