//Georgios Tsakiridis AEM:9548, Tel.6974936675, email:tsakgeor@ece.auth.gr
//Vasileios Polynopoulos AEM:9584, Tel.6987693944, email:polynopo@ece.auth.gr 


package projectPackage1;

/*The snake class is used for creating snakes. A snake will "drop" the player at the tile in which its tail
rests, which is below its head, practically setting the player back by a few tiles. Random generation and
positioning is also used here.*/
public class Snake {
	int snakeId, headId, tailId;
	
	public Snake() {
		super();
		this.snakeId = 0;
		this.headId = 0;
		this.tailId = 0;
	}
	public Snake(int snakeId, int headId, int tailId) {
		this.snakeId = snakeId;
		this.headId = headId;
		this.tailId = tailId;
	}
	public Snake(Snake s) {
		this.headId = s.getHeadId();
		this.snakeId = s.getHeadId();
		this.tailId = s.getTailId();
	}
	public int getSnakeId() {
		return snakeId;
	}
	public void setSnakeId(int snakeId) {
		this.snakeId = snakeId;
	}
	public int getHeadId() {
		return headId;
	}
	public void setHeadId(int headId) {
		this.headId = headId;
	}
	public int getTailId() {
		return tailId;
	}
	public void setTailId(int tailId) {
		this.tailId = tailId;
	}
}
