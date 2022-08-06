# Challenge description

Efficient Cost

You will be given an integer array and a `threshold` value. The threshold represents the maximum length of subarrays that may be created for the challenge. Each subarray created has a cost equal to the maximum integrr within the subarray. Partition the entire array into subarrays with lengths less than or equal to the threshold, and do it at a minimum cost. The subarrays are to be chosen from contiguous elements, and the given array must remain in its original order.

**Example 1:**

arr=1,3,4,5,2,6

threshold=3

ans=10

**Example 2:**

arr=1,5,2

threshold=2

ans=6

# Idea

DFS with memo.

`memo[i]` means the minimul sum of costs from index `i` onwards.

```java
`i` travers from `n-1` to `0`:
	curMax = 0
	best = Integer.MAX_VALUE;
	for k in `[i, i+threshold]`:
		curMax = max(curMax, arr[k+i])
		if (i+k+1 < n):
			best = min(best, mmax + memo[i+k+1])
		else:
			best = min(best, mmax)
	memo[i] = best
```

# Code

## Iterative
```python
def calculateCost(arr, threshold):
	n = len(arr)
	memo = [0 for _ in range(n)]
	memo[n-1] = arr[n-1]
	for i in range(n-2, -1, -1):
		curMax = 0
		best = float("inf") #
		for k in range(0, threshold):
			if (i + k >= n): break
			curMax = max(curMax, arr[i+k])
			best = min(best, curMax + memo[i + k + 1] if i+k+1 < n else curMax)
		memo[i] = best
	return memo[0]

nums = list(map(int, input().split(",")))
n = int(input())
print("ans = ", calculateCost(nums, n))
        

"""
1,3,4,5,2,6
3

ans=10

1,5,2
2

ans =6

"""
```

## DFS recursive
```python
def calculateCostDFS(arr, threshold):
	n = len(arr)
	memo = [float("inf") for _ in range(n)]
	memo[0] = dfs(arr, threshold, memo, 0)
	return memo[0]

def dfs(arr, threshold, memo, index):
	if (index >= len(arr)): return 0 # Recursion base case: return 0 so that minimalCost = curMax
	if (memo[index] != float("inf")): return memo[index] # Already calculated, return memo[index]
	curMax = 0
	minimalCost = float("inf")
	for i in range(threshold):
		if (i+index >= len(arr)): break
		curMax = max(curMax, arr[i+index])
		minimalCost = min(minimalCost, curMax + dfs(arr, threshold, memo, i+index+1))
	memo[index] = minimalCost
	return memo[index]


nums = list(map(int, input().split(",")))
n = int(input())
print("ans = ", calculateCostDFS(nums, n))
        

"""
1,3,4,5,2,6
3

ans=10

1,5,2
2

ans =6

"""
```

# Complexity Analysis

Time Complexity

O(N\*T), where `T` is `threshold`

Space Complexity

O(N)
