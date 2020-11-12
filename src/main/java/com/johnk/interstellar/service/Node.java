package com.johnk.interstellar.service;



import java.util.ArrayList;
import java.util.List;
 
public class Node implements Comparable<Node> {
 
	private boolean visited;
	private String name;
	private List<Edge> List;
	private double dist = Double.MAX_VALUE;
	private Node pr;
	
 
	public Node(String name) {
		this.name = name;
		this.List = new ArrayList<>();
	}
        
        public Node() {
	}
	
	public List<Edge> getList() {
		return List;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}

	public void setList(List<Edge> List) {
		this.List = List;
	}
	
	public void addNeighbour(Edge edge) {
		this.List.add(edge);
	}
	
	public boolean Visited() {
		return visited;
	}
 
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
 
	public Node getPr() {
		return pr;
	}
 
	public void setPr(Node pr) {
		this.pr = pr;
	}
 
	public double getDist() {
		return dist;
	}
 
	public void setDist(double dist) {
		this.dist = dist;
	}
 
	@Override
	public String toString() {
		return this.name;
	}
 
	@Override
	public int compareTo(Node otherV) {
		return Double.compare(this.dist, otherV.getDist());
	}
}