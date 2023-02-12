//Georgios Tsakiridis AEM:9548, Tel.6974936675, email:tsakgeor@ece.auth.gr
//Vasileios Polynopoulos AEM:9584, Tel.6987693944, email:polynopo@ece.auth.gr 

package projectPackage1;

import java.util.*;

public class MinMaxPlayer extends Player{

	ArrayList<int[]> path;
	
	public MinMaxPlayer() {
		super();
		this.path = new ArrayList<int[]>();	
	}
	public MinMaxPlayer(int playerId, int score, String name, Board board, ArrayList<int[]> path) {
		super(playerId, score, name, board);
		this.path = path;
	}
	public MinMaxPlayer(MinMaxPlayer copy) {
		this.playerId = copy.getPlayerId();
		this.score = copy.getScore();
		this.name = copy.getName();
		this.board = copy.getBoard();
		this.path = copy.getPath();
	}
	
	public ArrayList<int[]> getPath() {
		return path;
	}
	public void setPath(ArrayList<int[]> path) {
		this.path = path;
	}
	public double evaluate(int currentPos, int dice, Board board) {
		Board boardCopy = new Board(board);
		Player playerCopy = new Player(this.playerId, this.score, this.name, boardCopy);
		int[] fromMove = new int[5];
		fromMove = playerCopy.move(currentPos, dice);
		return (double)(0.6 * (fromMove[0] - currentPos) + (0.4 * (playerCopy.getScore())));
	}
	public void createOpponentSubtree(Node parent, int depth, int currentPos, int opponentCurrentPos, double parentEval) {
		if(parentEval < 1000000) {
		for(int j = 1; j < 7; j++) {
			if(opponentCurrentPos + j < (parent.getNodeBoard().getM() * parent.getNodeBoard().getN())) {
				Board boardCopy = new Board(parent.getNodeBoard());
				MinMaxPlayer playerCopy = new MinMaxPlayer(this);
				playerCopy.setBoard(boardCopy);
				playerCopy.move(opponentCurrentPos, j);
				boardCopy = playerCopy.getBoard();
				Node child = new Node();
				child.setNodeBoard(boardCopy);
				double result = playerCopy.evaluate(opponentCurrentPos, j, boardCopy);
				child.setNodeEvaluation(parentEval - result);
				child.setParent(parent);
				child.setNodeDepth(depth + 1);
				int[] toChild = new int[2];
				toChild[0] = opponentCurrentPos;
				toChild[1] = j;
				child.setNodeMove(toChild);
				ArrayList<Node> toChildren = new ArrayList<Node>(parent.getChildren());
				toChildren.add(child);
				parent.setChildren(toChildren);
			}
			else {
				Node child = new Node();
				child.setParent(parent);
				child.setNodeDepth(0);
				child.setNodeEvaluation(1000000);
				int[] toChild = new int[2];
				toChild[0] = opponentCurrentPos;
				toChild[1] = j;
				child.setNodeMove(toChild);
				ArrayList<Node> toChildren = new ArrayList<Node>(parent.getChildren());
				toChildren.add(child);
				parent.setChildren(toChildren);
				
			}
		}
	}
 }
	public void createMySubtree(Node parent, int depth, int currentPos, int opponentCurrentPos) {
		for(int i = 1; i < 7; i++) {
			if(opponentCurrentPos + i < (parent.getNodeBoard().getM() * parent.getNodeBoard().getN())) {
				Board boardCopy = new Board(parent.getNodeBoard());
				MinMaxPlayer playerCopy = new MinMaxPlayer(this);
				playerCopy.setBoard(boardCopy);
				playerCopy.move(opponentCurrentPos, i);
				boardCopy = playerCopy.getBoard();
				Node child = new Node();
				child.setNodeBoard(boardCopy);
				child.setParent(parent);
				child.setNodeDepth(depth + 1);
				int[] toChild = new int[2];
				toChild[0] = currentPos;
				toChild[1] = i;
				child.setNodeMove(toChild);
				ArrayList<Node> toChildren = new ArrayList<Node>(parent.getChildren());
				toChildren.add(child);
				parent.setChildren(toChildren);
				double result = playerCopy.evaluate(currentPos, i, boardCopy);
				createOpponentSubtree(child, depth + 1, currentPos, opponentCurrentPos, result);
			}
			else {
				Node child = new Node();
				child.setParent(parent);
				child.setNodeDepth(0);
				child.setNodeEvaluation(1000000);
				int[] toChild = new int[2];
				toChild[0] = currentPos;
				toChild[1] = i;
				child.setNodeMove(toChild);
   }
  }
 }
	public int chooseMinMaxMove(Node root) {
	ArrayList<Node>	kids = new ArrayList<Node>(root.getChildren());
	int initSize = kids.size();
	for(int i = 0; i < initSize; i++) { 
		Node temp = new Node(kids.get(i));
		ArrayList<Node> grandKids = new ArrayList<Node>(temp.getChildren());
		Node other = new Node(grandKids.get(0));
		double min = other.getNodeEvaluation();
		for(int k = 0; k < grandKids.size(); k++) {
			Node temp1 = new Node(grandKids.get(k));
			if(temp1.getNodeEvaluation() < min)
				min = temp1.getNodeEvaluation();
		}
		other = kids.get(i);
		other.setNodeEvaluation(min);
		kids.add(i, other);
	}
	root.setChildren(kids);
	int maxI = 5;
	double max  = 0;
	kids = root.getChildren();
	for(int i = 0; i < kids.size(); i++) {
		Node other = new Node(kids.get(i));
		if(other.getNodeEvaluation() > max) {
			max = other.getNodeEvaluation();
			maxI = i;
		}
	}
	root.setNodeEvaluation(max);
	return (maxI + 1);
	}
	public int[] getNextMove(int currentPos, int opponentCurrentPos) {
		Node root = new Node();
		root.setNodeBoard(this.board);
		this.createMySubtree(root, 1, currentPos, opponentCurrentPos);
		int[] bestMove = new int[3];
		bestMove[0] = chooseMinMaxMove(root);
		int[] moveGetter = this.move(currentPos, bestMove[0]);
		int[] toPath = new int[8];
		toPath[0] = bestMove[0];
		toPath[1] = moveGetter[3] - moveGetter[4];
		toPath[2] = moveGetter[0] - currentPos;
		toPath[3] = moveGetter[3] + moveGetter[4];
		toPath[4] = moveGetter[1];
		toPath[5] = moveGetter[2];
		toPath[6] = moveGetter[3];
		toPath[7] = moveGetter[4];
		this.setScore(this.getScore() + toPath[1]);
		this.path.add(toPath);
		return bestMove;
	}
}
