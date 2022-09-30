package com.interview.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

	public static class Node {
		int value;
		List<Node> adjacent = new ArrayList<>();
	}
	
	Map<Integer, Node> allNodes = new HashMap<>();
	
	public Node getNode(int value) {
		return allNodes.get(value);
	}
	
	public void addEdge(int source, int destination) {
		Node s = getNode(source);
		Node d = getNode(destination);
		s.adjacent.add(d);
	}
	
}
