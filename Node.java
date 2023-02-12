//Georgios Tsakiridis AEM:9548, Tel.6974936675, email:tsakgeor@ece.auth.gr
//Vasileios Polynopoulos AEM:9584, Tel.6987693944, email:polynopo@ece.auth.gr 

package projectPackage1;

import java.util.*;

public class Node {

	Node parent;
	ArrayList<Node> children;
	int nodeDepth;
	int[] nodeMove;
	Board nodeBoard;
	double nodeEvaluation;
	
	

	public Node() {
		this.nodeDepth = 0;
		this.nodeEvaluation = 0;
		this.nodeBoard = new Board();
		this.children = new ArrayList<Node>();
		this.nodeMove = new int[2];
	}
	
	public Node(int nodeDepth, double nodeEvaluation, ArrayList<Node> children, Node parent, int[] nodeMove, Board nodeBoard) {
		this.nodeDepth = nodeDepth;
		this.nodeEvaluation = nodeEvaluation;
		this.nodeBoard = nodeBoard;
		this.parent = parent;
		this.children = children;
	}
	public Node(Node nodeCopy) {
		this.nodeDepth = nodeCopy.getNodeDepth();
		this.nodeEvaluation = nodeCopy.getNodeEvaluation();
		this.nodeBoard = nodeCopy.getNodeBoard();
		this.parent = nodeCopy.getParent();
		this.children = nodeCopy.getChildren();
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public ArrayList<Node> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}
	public int getNodeDepth() {
		return nodeDepth;
	}
	public void setNodeDepth(int nodeDepth) {
		this.nodeDepth = nodeDepth;
	}
	public int[] getNodeMove() {
		return nodeMove;
	}
	public void setNodeMove(int[] nodeMove) {
		this.nodeMove = nodeMove;
	}
	public Board getNodeBoard() {
		return nodeBoard;
	}
	public void setNodeBoard(Board nodeBoard) {
		this.nodeBoard = nodeBoard;
	}
	public double getNodeEvaluation() {
		return nodeEvaluation;
	}
	public void setNodeEvaluation(double nodeEvaluation) {
		this.nodeEvaluation = nodeEvaluation;
	}
	
}
