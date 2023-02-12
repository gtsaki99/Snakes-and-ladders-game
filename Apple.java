//Georgios Tsakiridis AEM:9548, Tel.6974936675, email:tsakgeor@ece.auth.gr
//Vasileios Polynopoulos AEM:9584, Tel.6987693944, email:polynopo@ece.auth.gr 


package projectPackage1;

/*The Apple class is used for creating apples in the game. The apples have a color(red or black) and they give
(red) or deduct(black) points from you according to their color. They will be randomly created and position at
the start of each game*/
public class Apple {
	int appleId, appleTileId, points;
	String color;
	
	public Apple() {
		this.appleId = 0;
		this.appleTileId = 0;
		this.points = 0;
		this.color = "";
	}
	public Apple(int appleId, int appleTileId, int points, String color) {
		this.appleId = appleId;
		this.appleTileId = appleTileId;
		this.color = color;
		this.points = points;
	}
	public Apple(Apple a) {
		this.appleId = a.getAppleId();
		this.appleTileId = a.getAppleTileId();
		this.color = a.getColor();
		this.points = a.getPoints();
		
	}
	public int getAppleId() {
		return appleId;
	}
	public void setAppleId(int appleId) {
		this.appleId = appleId;
	}
	public int getAppleTileId() {
		return appleTileId;
	}
	public void setAppleTileId(int appleTileId) {
		this.appleTileId = appleTileId;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
