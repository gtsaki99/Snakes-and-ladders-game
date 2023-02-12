//Georgios Tsakiridis AEM:9548, Tel.6974936675, email:tsakgeor@ece.auth.gr
//Vasileios Polynopoulos AEM:9584, Tel.6987693944, email:polynopo@ece.auth.gr

package projectPackage1;

import java.util.*; 

public class HeuristicPlayer extends Player{ /*This class will be used to create objects/players that play with
strategic.Specifically they pick what number they'll roll on the dice based on what we have set as the most
beneficial for them(advancing on the board or collecting points).*/
ArrayList<int[]> path;

public HeuristicPlayer() {
	super();
	path = new ArrayList<int[]>() ;
}

public HeuristicPlayer(int playerId, int score, String name, Board board, ArrayList <int[]> path) {
	super(playerId, score, name, board);
	this.path = path;
}
public HeuristicPlayer(HeuristicPlayer h) {
	this.playerId = h.getPlayerId();
	this.score = h.getScore();
	this.name = h.getName();
	this.board = h.getBoard();
	this.path = h.path;
}
public double evaluate(int currentPos, int dice) { /*This method returns a number which serves as the evaluation
 for the dice given as its parameter. Specifically it uses a copy of the player for whom it's called so as not to
 change the status of the board and counts how much he has moved and how much points he has gained. It then 
 returns a number based on this formula: 0.6 * movement + 0.4 * points*/
	HeuristicPlayer test= new HeuristicPlayer(this);
	int[] results = new int[5];
	results = test.move(currentPos, dice);
	return (double)(0.6 * (results[0] - currentPos) + 0.4 * (test.getScore() - this.getScore()));
}

public int getNextMove(int currentPos) { /*This method calls the evaluate method for each of the six available
rolls of dice and takes their evaluations. It puts them in a map, finds the best evaluation and then moves the 
player according to that roll of dice.*/
	int[] toPath = new int[8];
	toPath[1] = this.score;
	toPath[2] = currentPos;
	int[] moveCopy = new int[5];
	HashMap<Integer,Double> results = new HashMap<Integer,Double>();
	for(int i = 0; i < 6; i++) 
	results.put(i + 1, evaluate(currentPos, i + 1));
	int maxKey = 0;
	double maxValue = 0;
	for(int i = 0; i < 6; i++) {
		if(maxValue < results.get(i + 1)) {
			maxValue =  results.get(i + 1);
			maxKey = i + 1;
		}
	}
	moveCopy = this.move(currentPos, maxKey);
	toPath[0] = maxKey;
	toPath[1] = this.score - toPath[1];
	toPath[2] = this.playerId - toPath[2];
	toPath[3] = moveCopy[3] + moveCopy[4];
	for(int i = 4; i < 8; i++)
		toPath[i] = moveCopy[i - 3];
	path.add(toPath);
    return moveCopy[0];
}

public void statistics(int rounds) { /*this method prints useful information about the HeuristicPlayer such as
the number of apples(red and black separately) he ate or the snakes that bit him etc.*/
	for(int i = 0; i < rounds; i++) {
		System.out.println("On round " + i + " the player chose to roll the number " + path.get(i)[0]);
		System.out.println(" ,landed on " + path.get(i)[4] + " snake heads,");
		System.out.println( "ascended " + path.get(i)[5] + " ladders,");
		System.out.println(" ate " + path.get(i)[6] + " red apples");
		System.out.println( "and " + path.get(i)[7] + " black apples.");
	}
}
}