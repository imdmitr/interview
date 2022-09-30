package com.interview.tree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.interview.tree.BinaryTree.TraversalMode;

class AllTreeTest {
	BinaryTree bt;
	
	@BeforeEach
	public void setUp() {
		bt = new BinaryTree();
	}
	
	
	@Test
	void testTraversalDFS() {

		Node root1 = createTree1();
		Node root2 = createTree2();

		List<Integer> inorder1 = new ArrayList<>();
		List<Integer> inorder2 = new ArrayList<>();

		System.out.println("Tree 1");
//		bt.inOrder(root1, inorder1);
		System.out.println("Tree 2");
//		inOrder(root2, inorder2);

//		System.out.println("Tree 1 pre");
//		preOrder(root1, inorder1);
//		
//		System.out.println("Tree 1 post");
//		postOrder(root1, inorder1);
//		
//		System.out.println("Tree 1 post");
//		preOrderNoReccursion(root1, inorder1);
		
//		inOrder(root2, inorder2);

//		System.out.println("Tree 1 pre");
//		preOrder(root1, inorder1);
//		
//		System.out.println("Tree 1 post");
//		postOrder(root1, inorder1);
//		
//		System.out.println("Tree 1 post");
//		preOrderNoReccursion(root1, inorder1);

		NodeConsumer<Node> consumer2 = n -> {
			System.out.println(n.value);
			inorder2.add(n.value);
		};
		bt.traversal(root2, consumer2, TraversalMode.InOrder);
		
		NodeConsumer<Node> consumer1 = n -> {
			System.out.println(n.value);
			inorder1.add(n.value);
		};
		bt.traversal(root1, consumer1, TraversalMode.InOrder);

		assertEquals(inorder1, inorder2);
		
	}
	
	@Test
	public void testFindLCA() {
		Node root = createTree3();
		
		assertEquals(bt.findLeastCommonAncestor(root, 4, 5), 2); //2
		assertEquals(bt.findLeastCommonAncestor(root, 4, 6), 1); //1
		assertEquals(bt.findLeastCommonAncestor(root, 3, 4), 1); //1
		assertEquals(bt.findLeastCommonAncestor(root, 2, 4), 2); //2

	}
	
	@Test
	public void testLevelOrderTraversal() {
		Node root = createTree3();

		int height = bt.getHeight(root);
		System.out.println("Height " + height);
		for (int i = 1; i <= height; i++) {
			bt.consumeAtLevel(root, i, n -> {
				System.out.println(n.value);
			});
		}
		
		
		System.out.println("BFS " + height);
		NodeConsumer<Node> consumer2 = n -> {
			System.out.println(n.value);
		};
		
		bt.traversalBreadthFirstSearch(root, consumer2);
		
	}
	
	@Test
	public void testIsBST() {
		
		assertFalse(bt.isBST(createTree3()));
		assertTrue(bt.isBST(createTree1()));
		assertTrue(bt.isBST(createTree2()));
		
		Node root = null;
		
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			int d = random.nextInt(100);
			if (root == null) {
				root = new Node(d);
			} else {
				bt.addBST(root, d);
			}
		}
		
		int[] sorted = new int[20];
		final int i = 0;
		NodeConsumer<Node> consumer =n -> {
			System.out.println(n.value);
		}; 
				
		
		bt.traversal(root, consumer , TraversalMode.InOrder);

	}	
	
	
	@Test
	public void testAutocompleteUsingTrie() {
		Trie<Integer> trie = new Trie<>();
		String[] words = {"doll", "dog","dont", "door", "desk", "dam", "dir", "debth" };
		for (int i = 0; i < words.length; i++) {
			com.interview.tree.TrieNode<Integer> node = trie.insert(words[i]);
			node.data = i; //ranking - end of word;
		}
		
		System.out.println(trie);
		
		List<String> startWithDo = trie.findPrefix("do");
		for (String p : startWithDo) {
			System.out.println(p);
		}
		
		assertEquals(startWithDo.size(), 4);
		
		startWithDo = trie.findPrefix("da");
		assertEquals(startWithDo.size(), 1);
		assertEquals(startWithDo.get(0), "dam");
		
		startWithDo = trie.findPrefix("dir");
		assertEquals(startWithDo.size(), 1);
		assertEquals(startWithDo.get(0), "dir");
		
		startWithDo = trie.findPrefix("de");
		assertEquals(startWithDo.size(), 2);

		
	}
	
	
	private static Node createTree2() {
		Node r = new Node(3);
		r.left = new Node(1);
		r.right = new Node(6);
		r.right.left = new Node(5);
		r.right.right = new Node(7);
		return r;
	}

	private static Node createTree1() {
		Node r = new Node(5);
		r.left = new Node(3);
		r.left.left = new Node(1);
		r.right = new Node(7);
		r.right.left = new Node(6);
		return r;
	}
	private Node createTree3() {
		Node r = new Node(1);
		r.left = new Node(2);
		r.right = new Node(3);
		r.left.left = new Node(4);
		r.left.right = new Node(5);
		r.right.left = new Node(6);
		r.right.right = new Node(7);
		return r;
	}

	
	

}
