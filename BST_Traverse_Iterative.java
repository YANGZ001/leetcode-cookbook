class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	public TreeNode() {}
	public TreeNode(int v) {
		this.val = v;
	}
	public TreeNode(int v, TreeNode l, TreeNode r) {
		this.val = v;
		this.left = l;
		this.right = r;
	}
	}

public LinkedList<Integer> preorder(TreeNode root) {
	LinkedList<Integer> res = new LinkedList<>();
	traverse(root, res);
	return res;
	}

public void traverse(TreeNode root, LinkedList<Integer> res) {
	if (root == null) return;
	res.addLast(root.val);
	traverse(root.left, res);
	traverse(root.right, res);
	}
	
public LinkedList<Integer> preorderIterative(TreeNode root) {
	LinkedList<Integer> res = new LinkedList<>();
	if (root == null) return res;
	Stack<TreeNode> stack = new Stack<>();
	stack.push(root);
	while (!stack.isEmpty()) {
		TreeNode top = stack.pop();
		res.addLast(top.val);
		if (top.right != null) stack.push(top.right);
		if (top.left != null) stack.push(top.left);
	}
	return res;
	}
	
	public LinkedList<Integer> inorderIterative(TreeNode root) {
		LinkedList<Integer> res = new LinkedList<>();
		if (root == null) return res;
		Stack<TreeNode> stack = new Stack<>();
		TreeNode p = root;
		while (p != null || !stack.isEmpty()) {
			if (p != null) {
				stack.push(p);
				p = p.left;
			}else {
				// p == null
				p = stack.pop();
				res.addLast(p.val);
				p = p.right;
			}
		}
		return res;
	}
	
	public LinkedList<Integer> postorderIterative(TreeNode root) {
	LinkedList<Integer> res = new LinkedList<>();
	if (root == null) return res;
	Stack<TreeNode> stack = new Stack<>();
	stack.push(root);
	while (!stack.isEmpty()) {
		TreeNode top = stack.pop();
		res.addLast(top.val);
		if (top.left != null) stack.push(top.right);
		if (top.right != null) stack.push(top.left);
	}
	return Collections.reverse(res);
	}
