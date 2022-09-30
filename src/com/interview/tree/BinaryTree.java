package com.interview.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.interview.list.Queue;

public class BinaryTree {

	public enum TraversalMode {
		PreOrder, InOrder, PostOrder
	}


	boolean isBST(Node node) {
		if (node == null) return false;

		return isBSTCheck(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean isBSTCheck(Node node, int minValue, int maxValue) {
		if (node == null) {
			return true; 
		}
		if (node.value > maxValue || node.value < minValue) {
			return false;
		}
		
		return isBSTCheck(node.left, minValue, node.value) && isBSTCheck(node.right, node.value, maxValue);
	}

	void consumeAtLevel(Node node, int level, NodeConsumer<Node> consumer) {
		if (node == null) {
			return;
		}
		
		if (level == 1) {
			consumer.accept(node);
		} else if (level > 1) {
			consumeAtLevel(node.left, level -1, consumer);
			consumeAtLevel(node.right, level -1, consumer);
		}
		
	}

	int getHeight(Node root) {
		if (root == null) {
			return 0;
		}
		
		int l = getHeight(root.left);
		int r = getHeight(root.right);
		if (l > r) {
			return l + 1;
		} else {
			return r + 1;
		}
	}



	public int findLeastCommonAncestor(Node root, int n1, int n2) {
		int rez = -1;
		List<Integer> path1 = new ArrayList<>();
		List<Integer> path2 = new ArrayList<>();
		if (!findPath(root, n1, path1)) {
			return rez;
		}
		
		if (!findPath(root, n2, path2)) {
			return rez;
		}
		
		
		for (Integer p : path1) {
			if (path2.contains(p)) {
				rez = p;
			}
		}
		

		return rez;
	}

	/**
	 * 
	 * DFS using stack
	 * 
	 * @param node
	 * @param inorder
	 */
	private static void preOrderNoReccursion(Node node, List<Integer> inorder) {
		Stack<Node> stack = new Stack<>();

		stack.add(node);
		while (!stack.isEmpty()) {
			Node tmp = stack.pop();

			inorder.add(tmp.value);
			System.out.println(tmp.value);

			if (tmp.right != null) {
				stack.push(tmp.right);
			}

			if (tmp.left != null) {
				stack.push(tmp.left);
			}

		}

	}

	/**
	 * InOrder LNR PostPorder LRN PreOrder NLR
	 * 
	 * @param node
	 * @param inorder
	 */
	private static void inOrder(Node node, List<Integer> inorder) {
		if (node == null) {
			return;
		}

		inOrder(node.left, inorder);

		System.out.println(node.value);
		inorder.add(node.value);

		inOrder(node.right, inorder);
	}

	/**
	 * Depth-First-Search DFS - stack 
	 *  
	 * @param node
	 * @param consumer
	 * @param mode
	 */
	public void traversal(Node node, NodeConsumer<Node> consumer, TraversalMode mode) {
		if (node == null) {
			return;
		}

		if (mode == TraversalMode.PreOrder) {
			consumer.accept(node);
		}

		traversal(node.left, consumer, mode);

		if (mode == TraversalMode.InOrder) {
			consumer.accept(node);
		}

		traversal(node.right, consumer, mode);

		if (mode == TraversalMode.PostOrder) {
			consumer.accept(node);
		}
	}
	
	/**
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 * 
	 * BFS - using queue instead of stack. 
	 * Same level first. then go down. 
	 * 
	 * @param node
	 * @param consumer
	 */
	public void traversalBreadthFirstSearch(Node node, NodeConsumer<Node> consumer) {
		if (node == null) {
			return;
		}
		
		Queue<Node> queue = new Queue<>();
		queue.add(node);
		Node tmp = null;
		while ((tmp = queue.remove()) != null) {
			consumer.accept(tmp);
			
			if (tmp.left != null) {
				queue.add(tmp.left);
			}
			
			if (tmp.right != null) {
				queue.add(tmp.right);
			}
		}
		
		
	}

	private boolean findPath(Node node, int value, List<Integer> path) {
		if (node == null) {
			return false;
		}

		path.add(node.value);
		
		if (node.value == value) {
			return true;
		}

		if (node.left != null && findPath(node.left, value, path)) {
			return true;
		}
		
		if (node.right != null && findPath(node.right, value, path)) {
			return true;
		}
		
		path.remove(path.size() -1);
		return false;
	}

	private static void preOrder(Node node, List<Integer> inorder) {
		if (node == null) {
			return;
		}

		System.out.println(node.value);
		inorder.add(node.value);

		preOrder(node.left, inorder);
		preOrder(node.right, inorder);
	}

	private static void postOrder(Node node, List<Integer> inorder) {
		if (node == null) {
			return;
		}

		postOrder(node.left, inorder);
		postOrder(node.right, inorder);

		System.out.println(node.value);
		inorder.add(node.value);
	}

	public void addBST(Node node, int value) {
		if (node == null) {
			return;
		}
		
		if (value < node.value) {
			if (node.left == null) {
				node.left = new Node(value);
			} else {
				addBST(node.left, value);
			}
		} else {
			if (node.right == null) {
				node.right = new Node(value);
			} else {
				addBST(node.right, value);
			}
		}
	}

}
