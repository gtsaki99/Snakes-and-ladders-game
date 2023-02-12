//Georgios Tsakiridis AEM:9548, Tel.6974936675, email:tsakgeor@ece.auth.gr
//Vasileios Polynopoulos AEM:9584, Tel.6987693944, email:polynopo@ece.auth.gr 


package projectPackage1;

/*The board class will be used for creating the imaginary boards used in each game/round. Further details
about its actions are analyzed in its actions*/
public class Board {
	int N, M;
	int[][] tiles;
	Snake[] snakes;
	Apple[] apples;
	Ladder[] ladders;
	
	public Board() {
		this.N = 0;
		this.M = 0;
		this.tiles = new int[N][M];
		
	}
	public Board(int N, int M, int numberOfSnakes, int numberOfApples, int numberOfLadders) {
		this.N = N;
		this.M = M;
		this.tiles = new int[N][M];
		for(int i = 0; i < N; i++)
			for(int j = 0; j < M; j++)
			tiles[i][j] = 0;
		this.snakes = new Snake[numberOfSnakes];
		for(int i = 0; i < numberOfSnakes; i++)
			this.snakes[i] = new Snake();
			apples = new Apple[numberOfApples];
		for(int i = 0; i < numberOfApples; i++)
			this.apples[i] = new Apple();
			ladders = new Ladder[numberOfLadders];
		for(int i = 0; i < numberOfLadders; i++)
			ladders[i] = new Ladder();
		}
	public Board(Board b) {
		this.N = b.getN();
		this.M = b.getM();
		this.tiles = b.getTiles();
		this.snakes = b.getSnakes();
		this.apples = b.getApples();
		this.ladders = b.getLadders();
	}
	
	public int getN() {
		return N;
	}
	public void setN(int n) {
		N = n;
	}
	public int getM() {
		return M;
	}
	public void setM(int m) {
		M = m;
	}
	public int[][] getTiles() {
		return tiles;
	}
	public void setTiles(int[][] tiles) {
		this.tiles = tiles;
	}
	public Snake[] getSnakes() {
		return snakes;
	}
	public void setSnakes(Snake[] snakes) {
		this.snakes = snakes;
	}
	public Apple[] getApples() {
		return apples;
	}
	public void setApples(Apple[] apples) {
		this.apples = apples;
	}
	public Ladder[] getLadders() {
		return ladders;
	}
	public void setLadders(Ladder[] ladders) {
		this.ladders = ladders;
	}
	/*The following 4 functions were added just for helping us get the length of each of the arrays of
    elements to be able to copy the arrays afterwards.*/
	public int getTilesLength() {
	 return tiles.length;
	}
	public int getApplesLength() {
		 return apples.length;
		}
	public int getLaddersLength() {
		 return ladders.length;
		}
	public int getSnakesLength() {
		 return snakes.length;
		}
	/*This method below generates the imaginary board in which a round/game will be played. It randomly
	creates and positions each ladder,snake and apple while checking, using simple if statements, that each
	snake's head is above its tail and each ladder's up step is below its down step.*/
	public void createBoard() {
		int i, j;//just two for counters
		for(i = 0; i < N; i++) {
			for(j = 0; j < M ; j++) {
			if(i % 2 == 0)
				tiles[i][j] = N * M - j;
			else
				tiles[i][(M - 1) - j] = N * M - j;
			}
		}
		for(i = 0; i < snakes.length; i++) {
			snakes[i].setSnakeId(i);
			snakes[i].setHeadId((int)(Math.random() * (N * M - 1)) + 1);
			snakes[i].setTailId((int)(Math.random() * (N * M)) + 1);
			while(snakes[i].getHeadId() <= snakes[i].getTailId() ) {
				snakes[i].setHeadId((int)(Math.random() * (N * M  -1)) + 1);
				snakes[i].setTailId((int)(Math.random() * (N * M))+ 1);
			}
		}
		for(i = 0; i < ladders.length; i++) {
			ladders[i].setLadderId(i);
			ladders[i].setUpStepId((int)(Math.random() * (N * M)) + 2);
			ladders[i].setDownStepId((int)(Math.random() * (N * M)) + 1);
			while(ladders[i].getUpStepId() >= ladders[i].getDownStepId()) {
				ladders[i].setUpStepId((int)(Math.random() * (N * M)) + 2);
				ladders[i].setDownStepId((int)(Math.random() * (N * M)) + 1);
			}
		}
		for(i = 0; i < apples.length; i++) {
			apples[i].setAppleId(i + 1);
			apples[i].setAppleTileId((int)(Math.random() * (N * M)) + 1);
			apples[i].setPoints((int)(Math.random() * 10) + 1);
			if(((int)(Math.random() * 2) + 1) == 1)
				apples[i].setColor("red");
			else
				apples[i].setColor("black");
			for(int k = 0; k < snakes.length; k++)
				if(apples[i].getAppleTileId() == snakes[k].getHeadId()) {
					apples[i].setAppleTileId((int)(Math.random() * (N * M)) + 1);
					k = 0;
				}
		}
		int pivot = 0; //pivot is a variable used as a pivot point for checking for all the exceptions below
		for(i = 0; i < apples.length; i++) { //changing tiles of two apples with the same tile
			pivot = apples[i].getAppleTileId();
			for(j =0; j < apples.length; j++) {
				while(apples[j].getAppleTileId() == pivot)
					apples[j].setAppleTileId((int)(Math.random() * (N * M)) + 1);
			}
		}
		for(i = 0; i < snakes.length; i++) { //changing tiles of two snake heads with the same tile
			pivot = snakes[i].getHeadId();
			for(j =0; j < snakes.length; j++) {
				while(snakes[j].getHeadId() == pivot)
					snakes[j].setHeadId((int)(Math.random() * (N * M)) + 1);
			}
		}
		for(i = 0; i < ladders.length; i++) { //changing tiles of ladder bases with the same tile
			pivot = ladders[i].getUpStepId();
			for(j =0; j < ladders.length; j++) {
				while(ladders[j].getUpStepId() == pivot)
					ladders[j].setUpStepId((int)(Math.random() * (N * M)) + 1);
			}
		}
		for(i = 0; i < snakes.length; i++) { // changing tiles of a ladder base and a snake head in the same tile
			pivot = snakes[i].getHeadId();
			for(j = 0; j < ladders.length; j++) {
				while(ladders[j].getUpStepId() == pivot)
					ladders[j].setUpStepId((int)(Math.random() * (N * M)) + 1);
			}
		}		
	}
	/*The method below generates three representations of the board that each show the position of a category
	 of an element. They use this code: SH = snake head, ST = snake tail, LD = ladders up step, LU = ladders
	 down step, AR = red apple, AB = black apple. The number after each code is each unique id/number in its
	 array's category.*/
	public void createElementBoard() {
		String[][] elementBoardSnakes, elementBoardApples, elementBoardLadders;
		elementBoardSnakes = new String[N][M]; /* this a representation of the board showing only the
		position of the snakes in it.*/
		elementBoardApples = new String[N][M];/* this a representation of the board showing only the
		position of the apples in it.*/
		elementBoardLadders = new String[N][M];/* this a representation of the board showing only the
		position of the ladders in it.*/
		for(int i = 0; i < N; i++) {//i is a for counter
			for(int j = 0; j < M; j++) {     //so is j
				for(int k = 0; k < snakes.length; k++) {   //and so is k
				if(snakes[k].getHeadId() == i * j)
					elementBoardSnakes[i][j] = "SH" + snakes[k].getSnakeId();
				else if(snakes[k].getTailId() == i * j)
					elementBoardSnakes[i][j] = "ST" + snakes[k].getSnakeId();
				else
					elementBoardSnakes[i][j] = "_";
				
				}
				for(int k = 0; k < ladders.length; k++) {
					if(ladders[k].getDownStepId() == i * j)
						elementBoardLadders[i][j] = "LU" + ladders[k].getLadderId();
					else if(ladders[k].getUpStepId() == i * j)
						elementBoardLadders[i][j] = "LD" + ladders[k].getLadderId();
					else
						elementBoardLadders[i][j] = "_";
				}
				for(int k = 0; k < apples.length; k++)
					if(apples[k].getAppleTileId() == i * j) {
						if(apples[k].getColor() == "red")
							elementBoardApples[i][j] = "AR" + apples[k].getAppleId();
						else
							elementBoardApples[i][j] = "AB" + apples[k].getAppleId();
					}
					else
						elementBoardApples[i][j] = "_";
			}
		}	
		for(int i = 0; i < N; i++) {
			for(int j =0; j < M; j++) {
				System.out.print(elementBoardSnakes[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		for(int i = 0; i < N; i++) {
			for(int j =0; j < M; j++) {
				System.out.print(elementBoardLadders[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		for(int i = 0; i < N; i++) {
			for(int j =0; j < M; j++) {
				System.out.print(elementBoardApples[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
