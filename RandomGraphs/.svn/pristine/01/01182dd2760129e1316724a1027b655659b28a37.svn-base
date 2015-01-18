package graphs;

import java.util.ArrayList;
import java.util.Stack;

/**
 * This class implements an undirected graph to which edges can be added
 * randomly. It's for experimenting with the theory of random graphs, developed
 * by Paul Erdos and Alfred Renyi in 1959.
 * 
 * @author TODO: put your name here
 */
public class RandomGraph {
	private int size;
	private Vertex[] vertices;
	
	private class Vertex {
		int label;
		ArrayList<Vertex> neighbors;
		boolean isVisited;
		
		public Vertex(int label){
			this.label = label;
			this.neighbors = new ArrayList<Vertex>();
			isVisited = false;
		}
		
		@Override
		public String toString(){
			StringBuilder sb = new StringBuilder();
			sb.append(this.label + ": ");
			for (Vertex nbr : this.neighbors) {
				sb.append(nbr.label + " ");
			}
			return sb.toString().trim();
		}
	}
	
	// TODO: add any necessary fields and classes for your chosen representation

	/**
	 * Constructs a new graph of the given size with no edges.
	 * 
	 * @param size
	 */
	public RandomGraph(int size) {
		this(size, new int[][] {});
	}

	/**
	 * Constructs a new graph of the given size with the given edges
	 * 
	 * @param size
	 * @param edges
	 *            each element is a pair giving the indices of the two nodes to
	 *            be connected
	 */
	public RandomGraph(int size, int[][] edges) {
		this.size = size;
		this.vertices = new Vertex[size];
		for(int i = 0; i < size; i++){
			this.vertices[i] = new Vertex(i);
		}
		
		for (int[] edge : edges) {
			int v1 = edge[0];
			int v2 = edge[1];
			this.vertices[v1].neighbors.add(this.vertices[v2]);
			this.vertices[v2].neighbors.add(this.vertices[v1]);
		}
	}

	/**
	 * @return the size of the largest connected component of this graph
	 */
	public int largestConnectedComponentSize() {
		int max = 0;
		int current = 0;
		
		// Reset all as unvisited
		for(Vertex v : this.vertices){
			v.isVisited = false;
		}
		
		for(Vertex v : this.vertices){
			current = this.componentSize(v);
			if(current>max){
				max = current;
			}
		}
		return max;
	}
	
	// Returns the number of vertices in the component that v is part of.
	private int componentSize(Vertex v){
		int compSize = 0;
		Stack<Vertex> stack = new Stack<Vertex>();
		stack.push(v);
		
		while(!stack.isEmpty()){
			Vertex current = stack.pop();
			if(!current.isVisited){
				compSize++;
				current.isVisited = true;
				for(Vertex neighbor : current.neighbors){
					if(!neighbor.isVisited){
						stack.push(neighbor);
					}
				}
			}
		}
		return compSize;
	}

	/**
	 * Adds a new edge, chosen uniformly from the set of missing edges.
	 * 
	 * @throws IllegalStateException
	 *             if this.isComplete()
	 */
	public void addRandomEdge() throws IllegalStateException {
		// TODO: implement this method
	}

	/**
	 * Returns whether this graph is connected, that is, whether there is a path
	 * from any node in the graph to any other node.
	 * 
	 * @return true iff this graph is connected
	 */
	public boolean isConnected() {
		return size==this.largestConnectedComponentSize();
	}

	/**
	 * Returns whether this graph is complete, that is, any two distinct
	 * vertices are neighbors of each other.
	 * 
	 * @return true iff this graph is complete
	 */
	public boolean isComplete() {
		// TODO: implement this method
		return false;
	}

	@Override
	public String toString() {
		/*
		 * TODO: Implement this method by replacing the super call with code to
		 * create a "reasonable" string representation of the Graph. Any
		 * representation that helps you debug the other methods is fine.
		 */
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<size; i++){
			sb.append(this.vertices[i].toString() + "\n");
		}
		return sb.toString();
	}
}
