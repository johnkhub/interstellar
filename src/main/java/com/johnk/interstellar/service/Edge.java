package com.johnk.interstellar.service;


public class Edge {
	private double dist;
	private Node fromVertex;
	private Node toVertex;
	
	public Edge(Node fromV, Node toV,double dist) {
		this.dist = dist;
		this.fromVertex = fromV;
		this.toVertex = toV;
	}
 
	public double getDist() {
		return dist;
	}
 
	public void setDist(double dist) {
		this.dist = dist;
	}
 
	public Node getFromVertex() {
		return fromVertex;
	}
 
	public void setFromVertex(Node fromV) {
		this.fromVertex = fromV;
	}
 
	public Node getToVertex() {
		return toVertex;
	}
 
	public void setToVertex(Node toV) {
		this.toVertex = toV;
	}
}
