//Georgios Tsakiridis AEM:9548, Tel.6974936675, email:tsakgeor@ece.auth.gr
//Vasileios Polynopoulos AEM:9584, Tel.6987693944, email:polynopo@ece.auth.gr 


package projectPackage1;

import java.util.*;
public class Game { /*The game itself will generate from this class. That's why it has inside of it the main
	function. The only variable it has is the rounds the players played the game.*/
    int round;
    
    public Game() {
    	this.round = 0;
    }
    public Game(int round) {
    	this.round = round;
    }
    public Game(Game g) {
    	this.round = g.getRound();
    }
    
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public Map<Integer,Integer> setTurns (ArrayList<Object> players) { /*This method assigns random numbers 
	from 1 to 6 as dice for each of the players its argument contains. It then sorts them according to their 
	dice numbers, with the lowest first, and puts them in a map using the dice as the key and their ids as a
	value.*/
	Map<Integer,Integer> toSetTurns = new HashMap<Integer,Integer>();
	for(int i = 0; i < players.size(); i++) {
		Player forMap = new Player((Player)players.get(i));
		int dice = (int)(Math.random() * 6) + 1;
		while(toSetTurns.containsKey(dice))
			dice = (int)(Math.random() * 6) + 1;
		toSetTurns.put(dice,forMap.getPlayerId());
	}
	return toSetTurns;
	}
	public static void main(String[] args) { /* This is the main function. Inside of it the 
	players and the game's board are created.Who will play first is decided by calling the method setTurns and
	 evaluating the resulting map. The check array is the array that takes the result array from the move 
	 function of the player class. It is called check because it is used to check if someone finishes the game
	 and also when the game ends*/
		Game newGame = new Game();
		Board myBoard = new Board(10, 20, 3, 3, 6);
		myBoard.createBoard();
		myBoard.createElementBoard();
		Player Player1 = new Player(1, 0, "Makis", myBoard );
		ArrayList<int[]> myList = new ArrayList<int[]>();
		MinMaxPlayer Player2 = new MinMaxPlayer(2, 0, "PC", myBoard, myList);
		ArrayList<Object> playersList = new ArrayList<Object>();
		playersList.add(Player1);
		playersList.add(Player2);
		Map<Integer,Integer> getTurns = new TreeMap<Integer,Integer>(newGame.setTurns(playersList));
		int[] check = new int[5];
		check[0] = 0;
		int[] moveNext = new int[3];
		int checkValue1 = Player1.getPlayerId(), checkValue2 = Player2.getPlayerId(), checkV = 0,thatRound = 0;
		for(int i = 1; i < 7; i++) {
			if(getTurns.containsKey(i)) {   
			if(getTurns.get(i) == checkValue1) {
				checkV = 1;
				break;
			}
			else if(getTurns.get(i) == checkValue2) { 
				checkV = 2;
				break;
			}
		}
		}
		int player2Tile = 0, finisher = 0;
		if(checkV == 1) {
			for(thatRound = 1; thatRound < 101; thatRound++) { 
				int dice = (int)(Math.random() * 6) + 1;
				check = Player1.move(check[0], dice);
				finisher = 1;
				if(check[0] > (myBoard.getM() * myBoard.getN()) )
				break;
				else {
					moveNext = Player2.getNextMove(player2Tile, check[0]);
					player2Tile += moveNext[0];
					finisher = 2;
				}
				if(player2Tile > (myBoard.getM() * myBoard.getN()) )
					break;
			}
		}
		else {
			for(thatRound = 1; thatRound < 101; thatRound++){
				moveNext = Player2.getNextMove(player2Tile, check[0]);
				player2Tile += moveNext[0];
				finisher = 2;
				if(player2Tile > (myBoard.getM() * myBoard.getN()) )
					break;
				else {
					int dice = (int)(Math.random() * 6) + 1;
					check = Player1.move(check[0], dice);
					finisher = 1;
				}
				if(check[0] > (myBoard.getM() * myBoard.getN()))
					break;
			}
		}
		newGame.setRound(thatRound);
		System.out.println("The players played " + newGame.getRound() + " rounds.");
		System.out.println("The player with the id " + Player1.getPlayerId() + " finished with a score of " + Player1.getScore() + " points.");
		System.out.println("The player with the id " + Player2.getPlayerId() + " finished with a score of " + Player2.getScore() + " points.");
		if((player2Tile * 0.6 + Player2.getScore() * 0.4) > (check[0] * 0.6 + Player1.getScore() * 0.4) ) 
			System.out.println("The player with the id " + Player2.getPlayerId() + "(MinMaxPlayer) is the winner of the game.");
		else if((check[0] * 0.6 + Player1.getScore() * 0.4) > (player2Tile * 0.6 + Player2.getScore() * 0.4))
				System.out.println("The player with the id " + Player1.getPlayerId() + "(Normal Player) is the winner of the game.");
		else {
			if(finisher == 1)
				System.out.println("The player with the id " + Player1.getPlayerId() + "(Normal Player) His the winner of the game.");
			else
				System.out.println("The player with the id " + Player2.getPlayerId() + "(MinMaxPlayer) is the winner of the game.");
		}
}
}
	