//Georgios Tsakiridis AEM:9548, Tel.6974936675, email:tsakgeor@ece.auth.gr
//Vasileios Polynopoulos AEM:9584, Tel.6987693944, email:polynopo@ece.auth.gr 


package projectPackage1;

public class Player { /* The player class is used to create the players that participate in the game. They
have a score which is equvalent to the sum of the points they get or lose from the apples, they have an id
which is random, obvioysly a name and the board in which they play the game.*/
  int playerId,score, newId;
  String name;
  Board board;
  
   public Player(){
	  this.playerId = 0;
	  this.score = 0;
	  this.name = "";
	  this.board = new Board();	
	   }
   
   public Player(int playerId, int score, String name, Board board) {
	  this.playerId = playerId;
	  this.score = score;
	  this.name = name;
	  this.board = board;
	   }
   
  public Player(Player p) {
	  this.playerId = p.getPlayerId();
	  this.score = p.getScore();
	  this.name = p.getName();
	  this.board = p.getBoard();
	  
	}
public int getPlayerId() {
	return playerId;
}
public void setPlayerId(int playerId) {
	this.playerId = playerId;
}
public int getScore() {
	return score;
}
public void setScore(int score) {
	this.score = score;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Board getBoard() {
	return board;
}
public void setBoard(Board board) {
	this.board = board;
}
public int getNewId() {
	return newId;
}

public void setNewId(int newId) {
	this.newId = newId;
}

public int[] move(int id, int die) { /*This method is used to simulate the movement of each
player through the board. It has three copies of the apples, snakes, and ladders arrays of
each board and the moveArray array which is the array that the function will eventually
return. Also it changes the status of the apples and the ladders array in the assigned board
because some ladders might break and some apples might get eaten.*/
	int[] moveArray = new int[5];
	moveArray[0] = id + die;
	Apple[] myApples = new Apple[board.getApplesLength()];
	for(int i = 0; i < board.getApplesLength(); i++)
		myApples[i] = new Apple();
	myApples = board.getApples();
	Snake[] mySnakes = new Snake[board.getSnakesLength()];
	for(int i = 0; i < board.getSnakesLength(); i++)
		mySnakes[i] = new Snake();
	mySnakes = board.getSnakes();
	Ladder[] myLadders = new Ladder[board.getLaddersLength()];
	for(int i = 0; i < board.getLaddersLength(); i++)
		myLadders[i] = new Ladder();
	myLadders = board.getLadders();
	for(int i = 0; i < board.getApplesLength(); i++) {
		if(moveArray[0] == myApples[i].getAppleTileId()) {
			if(myApples[i].getColor() == "red") {
				this.score += myApples[i].getPoints();
				System.out.println("The Player " + this.playerId + " ate a red apple");
				moveArray[3]++;
			}
			else {
			this.score -= myApples[i].getPoints();
			System.out.println("The Player " + this.playerId + " ate a black apple");
			moveArray[4]++;
			}
			myApples[i].setPoints(0);
		}
	}
	for(int i = 0; i < board.getSnakesLength(); i++) {
		if(moveArray[0] == mySnakes[i].getHeadId()) {
			moveArray[0] = mySnakes[i].getTailId();
			this.move(moveArray[0], 0);
			System.out.println("The Player " + this.playerId + " was bitten by snake");
			moveArray[1]++;
		}
	}
	for(int i = 0; i < board.getLaddersLength(); i++) {
		if(moveArray[0] == myLadders[i].getUpStepId()) {
			moveArray[0] = myLadders[i].getDownStepId();
			myLadders[i].setBroken(true);
			this.move(moveArray[0], 0);
			System.out.println("The Player " + this.playerId + " used a ladder");
			moveArray[2]++;
			}
	}
	board.setApples(myApples);
	board.setLadders(myLadders);
	return moveArray;
}

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
