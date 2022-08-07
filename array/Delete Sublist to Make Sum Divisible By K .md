# LeetCode Link

[Delete Sublist to Make Sum Divisible By K | binarysearch](https://binarysearch.com/problems/Delete-Sublist-to-Make-Sum-Divisible-By-K)



# Idea

Prefix sum


# Code

```java
import java.util.*;

class Solution {
    public int solve(int[] nums, int p) {
        // Find target module
        int target = 0, res = nums.length;
        for (int n : nums)
            target = (target + n) % p;
        // No need to remove any sublist
        if (target == 0) {return 0;}
        
        // Key: prefixSumModP, Value: position index
        Map<Integer, Integer> pos = new HashMap<>();
        pos.put(0, -1);
        
        int curMod = 0;
        for (int i = 0; i < nums.length; ++i) {
            curMod = (curMod + nums[i]) % p;
            // Comp Equation!
            int comp = (p - target + curMod) % p;
            if (pos.containsKey(comp))
                res = Math.min(res, i - pos.get(comp));
            pos.put(curMod, i);
        }    
        return res >= nums.length ? -1 : res;
    }
}
```

# Complexity Analysis

Time Complexity

O(N)

Space Complexity

O(N)
