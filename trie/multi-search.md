# LeetCode Link

[面试题 17.17. 多次搜索 - 力扣（LeetCode）](https://leetcode.cn/problems/multi-search-lcci/)

# Idea 1

Based on `big`, build trie tree then for each String `s` in `smalls`, search in `big`.

# Code

```java
class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
    }
    class Trie{
        private TrieNode root;
        private String s;
        public Trie() {
            root = new TrieNode();
        }
        public void insert(String s) {
            this.s = s;
            TrieNode p = root;
            for (char c : s.toCharArray()) {
                if (p.children[c-'a'] == null) {
                    p.children[c-'a'] = new TrieNode();
                }
                p = p.children[c-'a'];
            }
        }

        public List<Integer> search(String prefix) {
            TrieNode p = root;
            int index = 0;
            List<Integer> lst = new ArrayList<>();
            if (searchNode(p, prefix)) lst.add(index);
            for (char c : s.toCharArray()) {
                index++;
                TrieNode next = p.children[c-'a'];
                if (searchNode(next, prefix)) {
                    lst.add(index);
                }
                p = next;
            }
            return lst;
        }
        
        private boolean searchNode(TrieNode root, String prefix) {
            if (prefix == null || prefix.length() == 0) return false;
            TrieNode p = root;
            for (char c : prefix.toCharArray()) {
                TrieNode next = p.children[c-'a'];
                if (next == null) return false;
                p = next;
            }
            return true;
        }
    }

    public int[][] multiSearch(String big, String[] smalls) {
        Trie t = new Trie();
        t.insert(big);
        int[][] res = new int[smalls.length][];
        for (int i = 0; i < smalls.length; i++) {
            List<Integer> lst = t.search(smalls[i]);
            res[i] = new int[lst.size()];
            for (int j = 0; j < lst.size(); j++) {
                res[i][j] = lst.get(j);
            }
        }
        return res;
    }
}

```

# Complexity Analysis

**Time Complexity**

Build tree:`O(N)`, where `N` is the length of `big` string.

Search tree:`O(M*N^2*K)`, where `N` is the length of `big` string, `M` is the length of `smalls` array, `K` is the average length of strings in `smalls`.

**Space Complexity**

`O(26*N)`,  where `N` is the length of `big` string.



# Idea 2

Based on `smalls`, build `trie` tree then for each String `big.substring(i)` , `i` in `range(0,len(big)) `, search in `trie`.

# Code

```java
class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int id = -1;
    }

    class Trie{
        private TrieNode root;
        public Trie() {
            root = new TrieNode();
        }

        public void insert(String s, int id) {
            TrieNode p = root;
            for (char c : s.toCharArray()) {
                if (p.children[c-'a'] == null) {
                    p.children[c-'a'] = new TrieNode();
                }
                p = p.children[c-'a'];
            }
            p.id = id;
            // System.out.printf("s=%s,id=%d\n",s,id);
        }
        
        public List<Integer> search(String prefix) {
            List<Integer> list = new ArrayList<>();
            if (prefix == null || prefix.length() == 0) return list;
            TrieNode p = root;
            for (char c : prefix.toCharArray()) {
                TrieNode next = p.children[c-'a'];
                if (next == null) break;
                if (next.id != -1) list.add(next.id);
                p = next;
            }
            // System.out.printf("prefix=%s,search=%d\n",prefix,p.id);
            return list;
        }
    }

    public int[][] multiSearch(String big, String[] smalls) {
        Trie t = new Trie();
        int n = smalls.length;
        List<Integer>[] lst = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            t.insert(smalls[i], i);
            lst[i] = new ArrayList<>();
        }
        for (int i = 0; i < big.length(); i++) {
            List<Integer> tmp = t.search(big.substring(i, big.length()));
            for (int j : tmp) lst[j].add(i);
        }
        int[][] res = new int[n][];
        for (int i = 0; i < n; i++) {
            res[i] = new int[lst[i].size()];
            for (int j = 0; j < lst[i].size(); j++) {
                res[i][j] = lst[i].get(j);
            }
        }
        return res;
    }
}
```

# Complexity Analysis

**Time Complexity**

Build tree: `O(N * K)`, where `N` is the length of `smalls` array, `K` is the average length of string in `smalls`.

Search tree:

`O(M^2)`, where `M` is length of `big` string. For `i` in `range(0, len(big))`, each time takes `O(len(big) - i)`.

**Space Complexity**

`O(26*N)`,  where `N` is the `sum` of length of string in `smalls` array.

