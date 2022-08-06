# Challenge description

![Question Description](../assets/MS-1.png)

# Idea

1.   Use sliding window to get the length of all the potholes, put into a list.
2.   Sort the list in descending order. (Here in the code I used priority queue)
3.   Traverse from the biggest length to smallest. If `budget` can cover, subtract it from `budget`. If not, current cost - 1 till `budget` equals 0.

# Code

```java
// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(String s, int bud) {
        // write your code in Java 8 (Java SE 8)
        //System.out.printf("s=%s,bud=%d",s,bud);
        // Time Complexity: O(N)
        // Space Complexity: O(N)
        int n = s.length();
        int l = 0, r = 0;
        Queue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        // record all the length of potholes, using sliding window
        while (r < n) {
            char c = s.charAt(r);
            if (c == 'x') {
                l = r;
                while (r < n && s.charAt(r) == 'x') r++;
                int len = r - l;
                q.offer(len);
            }
            else {
                r++;
            }
        }
//        System.out.println(q);
        int res = 0;
        while (!q.isEmpty()) {
            int curCost = q.poll() + 1;// cur cost
            while (curCost > 1) {
                // min cost >= 2
                if (bud >= curCost) {
                    // if budget can cover
                    res += curCost - 1;
                    bud -= curCost;
                    if (bud == 0) return res;
                    break;
                }
                else {
                    // if budget cannot, minus 1
                    curCost--;
                }
            }
        }
        return res;
    }
}
```

# Complexity Analysis

Time Complexity

O(N)

Space Complexity

O(N)

```python
# you can write to stdout for debugging purposes, e.g.
# print("this is a debug message")

def solution(s):
    # write your code in Python (Python 3.6)
    # Time Complexity: O(N)
    # Space Complexity: O(N)
    reds = []
    # record all the index of R in list
    for i, c in enumerate(s):
        if c == "R":
            reds.append(i)
    n = len(reds)
    # base case: no red, return 0
    if n == 0:
        return 0
    start_ptr = 0
    end_ptr = n - 1
    count = 0
    while start_ptr < end_ptr:
        # for the outmost pair <start, end>, its move should be length from original position minus length from final position
        # which is reds[end_ptr] - reds[start_ptr] - end_ptr + start_ptr
        count += reds[end_ptr] - reds[start_ptr] - end_ptr + start_ptr
        start_ptr += 1
        end_ptr -= 1
    return -1 if count > 10 ** 9 else count
```

```c++
// you can use includes, for example:
#include <algorithm>
#include <vector>
#include <set>

// you can write to stdout for debugging purposes, e.g.
// cout << "this is a debug message" << endl;

bool solution(vector<int> &A, vector<int> &B, int S) {
    // Time Complexity: O(N)
    // Space Complexity: O(N)
    set<pair<int, int>> st;// Use set to store all the pairs
    int n = A.size();
    if(n > S) return false;// base case: slots not enough
    for(int i=0; i< n; i++){
        pair<int,int> current = make_pair(A[i], B[i]);
        pair<int,int> currRev = make_pair(B[i], A[i]);
        if (st.find(current) == st.end()) st.insert(current);// if current pair no clashes, insert
        else if (st.find(currRev) == st.end()) st.insert(currRev); // else insert reverse-order.
        else return false;// else cannot.
    }
    return true;
}
```

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

```java
// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    private class Pair{
        int key;
        int val;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return key == pair.key && val == pair.val;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, val);
        }

        public Pair(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public boolean solution(int[] A, int[] B, int S) {
        // write your code in Java 8 (Java SE 8)
        // Time Complexity: O(N)
        // Space Complexity: O(N)

        Set<Pair> st = new HashSet<>(); // Use set to store all the pairs
        int n = A.length;
        if (n > S) return false;;// base case: slots not enough
        for (int i = 0; i < n; i++) {
            Pair p = new Pair(A[i], B[i]);
            Pair rp = new Pair(B[i], A[i]);
            if (!st.contains(p)) st.add(p);// if current pair no clashes, insert
            else if (!st.contains(rp)) st.add(rp); // else insert reverse-order.
            else return false; //  else, connot
        }
        return true;
    }
}
```