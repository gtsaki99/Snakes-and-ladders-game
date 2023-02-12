//Georgios Tsakiridis AEM:9548, Tel.6974936675, email:tsakgeor@ece.auth.gr
//Vasileios Polynopoulos AEM:9584, Tel.6987693944, email:polynopo@ece.auth.gr 

package projectPackage1;

/*The ladder class is used for creating ladders in the game. A ladder helps the player progress further
through the board by "ascending" with it at a tile further along.They are also randomly generated and
positioned in the game.*/
public class Ladder {
	 int ladderId, upStepId, downStepId;
	 boolean broken;
	 
	 public Ladder() {
		 super();
		 this.ladderId = 0;
		 this.upStepId = 0;
		 this.downStepId = 0;
		 this.broken = false;
	 }
	public Ladder(int ladderId, int upStepId, int downStepId, boolean broken) {
		 this.ladderId = ladderId;
		 this.upStepId = upStepId;
		 this.downStepId = downStepId;
		 this.broken = broken;
	 }
	public Ladder(Ladder l) {
		 this.ladderId = l.getLadderId();
		 this.upStepId = l.getUpStepId();
		 this.downStepId = l.getDownStepId();
		 this.broken = l.isBroken();
	 }
	public int getLadderId() {
		return ladderId;
	}
	public void setLadderId(int ladderId) {
		this.ladderId = ladderId;
	}
	public int getUpStepId() {
		return upStepId;
	}
	public void setUpStepId(int upStepId) {
		this.upStepId = upStepId;
	}
	public int getDownStepId() {
		return downStepId;
	}
	public void setDownStepId(int downStepId) {
		this.downStepId = downStepId;
	}
	public boolean isBroken() {
		return broken;
	}
	public void setBroken(boolean broken) {
		this.broken = broken;
	}
}
