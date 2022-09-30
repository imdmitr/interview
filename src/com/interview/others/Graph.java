package com.interview.others;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Graph {
    int V;
    List<List<Integer> > adj;
 
    Graph(int V)
    {
        this.V = V;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(i, new ArrayList<>());
        }
    }
 
    // add edge to graph
    void addEdge(int s, int d)
    {
        adj.get(s).add(d);
    }
 
    // BFS function to find path
    // from source to sink
    boolean BFS(int s, int d)
    {
        // Base case
        if (s == d)
            return true;
 
        // Mark all the vertices as not visited
        boolean[] visited = new boolean[V];
 
        // Create a queue for BFS
        Queue<Integer> queue
            = new LinkedList<>();
 
        // Mark the current node as visited and
        // enqueue it
        visited[s] = true;
        queue.offer(s);
 
        // it will be used to get all adjacent
        // vertices of a vertex
        List<Integer> edges;
 
        while (!queue.isEmpty()) {
            // Dequeue a vertex from queue
            s = queue.poll();
 
            // Get all adjacent vertices of the
            // dequeued vertex s. If a adjacent has
            // not been visited, then mark it visited
            // and enqueue it
            edges = adj.get(s);
            for (int curr : edges) {
                // If this adjacent node is the
                // destination node, then return true
                if (curr == d)
                    return true;
 
                // Else, continue to do BFS
                if (!visited[curr]) {
                    visited[curr] = true;
                    queue.offer(curr);
                }
            }
        }
 
        // If BFS is complete without visiting d
        return false;
    }
 
    static boolean isSafe(
        int i, int j, int[][] M)
    {
        int N = M.length;
        if (
            (i < 0 || i >= N)
            || (j < 0 || j >= N)
            || M[i][j] == 0)
            return false;
        return true;
    }
 
    // Returns true if there is a
    // path from a source (a
    // cell with value 1) to a
    // destination (a cell with
    // value 2)
    static boolean findPath(int[][] M)
    {
        // Source and destination
        int s = -1, d = -1;
        int N = M.length;
        int V = N * N + 2;
        Graph g = new Graph(V);
 
        // Create graph with n*n node
        // each cell consider as node
        int k = 1; // Number of current vertex
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (M[i][j] != 0) {
 
                    // connect all 4 adjacent
                    // cell to current cell
                    if (isSafe(i, j + 1, M))
                        g.addEdge(k, k + 1);
                    if (isSafe(i, j - 1, M))
                        g.addEdge(k, k - 1);
                    if (i < N - 1
                        && isSafe(i + 1, j, M))
                        g.addEdge(k, k + N);
                    if (i > 0 && isSafe(i - 1, j, M))
                        g.addEdge(k, k - N);
                }
 
                // source index
                if (M[i][j] == 1)
                    s = k;
 
                // destination index
                if (M[i][j] == 2)
                    d = k;
                k++;
            }
        }
 
        // find path Using BFS
        return g.BFS(s, d);
    }
 
    // Driver program to check above function
    public static void main(
        String[] args) throws Exception
    {
        int[][] M = { { 0, 3, 0, 1 },
                      { 3, 0, 3, 3 },
                      { 2, 3, 3, 3 },
                      { 0, 3, 3, 3 } };
 
        System.out.println(
            ((findPath(M)) ? "Yes" : "No"));
    }
}