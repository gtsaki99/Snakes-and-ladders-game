package projectPackage1;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;  
import javax.swing.*;

public class Drawing extends JPanel {  
	      Board b;
	      Game game;
	      Player p1;
	      Player p2;
	      int move1;
	      int move2;
		public int getMove1() {
			return move1;
		}
		public void setMove1(int move1) {
			this.move1 = move1;
		}
		public int getMove2() {
			return move2;
		}
		public void setMove2(int move2) {
			this.move2 = move2;
		}
		public Board getBoard() {
			return b;
		}
		public void setBoard(Board board) {
			this.b = board;
		}
		public Game getGame() {
			return game;
		}
		public void setGame(Game g) {
			this.game = g;
		}
		public Player getPlayer1() {
			return p1;
		}
		public void setPlayer1(Player p) {
			this.p1 = p;
		}
		public Player getPlayer2() {
			return p2;
		}
		public void setPlayer2(Player p) {
			this.p2 = p;
		}
		public Drawing() {
			
		}
	    public void paintComponent(Graphics g) {  
	        setBackground(Color.LIGHT_GRAY);    
	        int squareSize=100;
	        int id=1;
		    int[][] upstep=new int[2][b.getLadders().length]; //x and y coordinates for making the ladder lines
		    int[][] downstep=new int[2][b.getLadders().length]; //same thing
			int[][] kefali=new int[2][b.getSnakes().length];//x and y coordinates for making the snake lines
		    int[][] oura=new int[2][b.getSnakes().length]; //same thing
		    int[][] milo=new int[2][b.getApples().length]; //same thing 
	       	for(int i=b.getN()-1;i>=0;i--)
	    		{
	    			if((b.getN()-i)%2==1)
	    			{
	    				for(int j=0;j<b.getM();j++)
	    				{                                                     					                               
	    					if(id%4==0)
	    					   g.setColor(Color.YELLOW);
	    					else if(id%4==1)
	    						g.setColor(Color.BLUE);
	    					else if(id%4==2)
	    						g.setColor(Color.GREEN);
	    					else if(id%4==3)
	    						g.setColor(Color.RED);
	    			        g.fillRect(100+j*squareSize, i*squareSize,squareSize, squareSize); 
	    			        g.setColor(Color.DARK_GRAY);
	     			         for(int l=0; l<b.getLadders().length;l++) {
	     			        	 if(b.getLadders()[l].getUpStepId()==id && !b.getLadders()[l].isBroken()) {
	     			        		 g.drawString("P"+l,100+ j*squareSize+squareSize/2, i*squareSize+squareSize/2);
	     			        		upstep[0][l]=j*squareSize+squareSize/2;
	     			        		upstep[1][l]=i*squareSize+squareSize/2;
	     			        	 }
	     			        	 else if(b.getLadders()[l].getDownStepId()==id && !b.getLadders()[l].isBroken()) {
	     			        		g.drawString("K"+l,100+ j*squareSize+squareSize/2, i*squareSize+squareSize/2);
	     			        		downstep[0][l]=j*squareSize+squareSize/2;
	     			        		downstep[1][l]=i*squareSize+squareSize/2;
	     			        	 }
	     			         }
	     			        	for(int s=0; s<b.getSnakes().length;s++) {
	     	  			        	 if(b.getSnakes()[s].getHeadId()==id) {
	     	  			        		 g.drawString("sP"+s,100+ j*squareSize+squareSize/2, i*squareSize+squareSize/2);
	     	  			        		kefali[0][s]=j*squareSize+squareSize/2;
	     	  			        		kefali[1][s]=i*squareSize+squareSize/2;
	     	  			        	 }
	     	  			        	 else if(b.getSnakes()[s].getTailId()==id) {
	     	  			        		g.drawString("sK"+s,100+ j*squareSize+squareSize/2, i*squareSize+squareSize/2);
	     	  			        		oura[0][s]=j*squareSize+squareSize/2;
	     	  			        		oura[1][s]=i*squareSize+squareSize/2;
	     	  			        	 }
	     	  			         }
	     			        	for(int a=0; a<b.getApples().length; a++) {
	     			        		if(b.getApples()[a].getAppleTileId()==id && b.getApples()[a].getPoints()!=0) {
	    	  			        	    g.drawString("A"+a, 100+j*squareSize+squareSize/2, i*squareSize+squareSize/2);
	    	  			        		milo[0][a]=j*squareSize+squareSize/2;
	    	  			        		milo[1][a]=i*squareSize+squareSize/2;
	     			        	} 			
	     			        	}
	     			        
	    			        id++;
	    				}
	    			}
	    			else
	    			{
	    				for(int j=b.getM()-1;j>=0;j--)
	    				{
	    					if(id%4==0)
	     					   g.setColor(Color.YELLOW);
	     					else if(id%4==1)
	     						g.setColor(Color.BLUE);
	     					else if(id%4==2)
	     						g.setColor(Color.GREEN);
	     					else if(id%4==3)
	     						g.setColor(Color.RED);
	     			        g.fillRect(100+j*squareSize, i*squareSize,squareSize, squareSize);
	     			        g.setColor(Color.DARK_GRAY);     			       
	    			         for(int l=0; l<b.getLadders().length;l++) {
	    			        	 if(b.getLadders()[l].getUpStepId()==id && !b.getLadders()[l].isBroken()) {
	    			        		 g.drawString("P"+l,100+ j*squareSize+squareSize/2, i*squareSize+squareSize/2);
	    			        		upstep[0][l]=j*squareSize+squareSize/2;
	    			        		upstep[1][l]=i*squareSize+squareSize/2;
	    			        	 }
	    			        	 else if(b.getLadders()[l].getDownStepId()==id && !b.getLadders()[l].isBroken()) {
	    			        		g.drawString("K"+l,100+ j*squareSize+squareSize/2, i*squareSize+squareSize/2);
	    			        		downstep[0][l]=j*squareSize+squareSize/2;
	    			        		downstep[1][l]=i*squareSize+squareSize/2;
	    			        	 }
	    			         }
	    			        	for(int s=0; s<b.getSnakes().length;s++) {
	    	  			        	 if(b.getSnakes()[s].getHeadId()==id) {
	    	  			        		 g.drawString("sP"+s,100+ j*squareSize+squareSize/2, i*squareSize+squareSize/2);
	    	  			        		kefali[0][s]=j*squareSize+squareSize/2;
	    	  			        		kefali[1][s]=i*squareSize+squareSize/2;
	    	  			        	 }
	    	  			        	 else if(b.getSnakes()[s].getTailId()==id) {
	    	  			        		g.drawString("sK"+s,100+ j*squareSize+squareSize/2, i*squareSize+squareSize/2);
	    	  			        		oura[0][s]=j*squareSize+squareSize/2;
	    	  			        		oura[1][s]=i*squareSize+squareSize/2;
	    	  			        	 }
	    	  			         }
	    			        	for(int a=0; a<b.getApples().length; a++) {
	    			        		if(b.getApples()[a].getAppleTileId()==id && b.getApples()[a].getPoints()!=0) {
	   	  			        	    g.drawString("A"+a, 100+j*squareSize+squareSize/2, i*squareSize+squareSize/2);
	   	  			        		milo[0][a]=j*squareSize+squareSize/2;
	   	  			        		milo[1][a]=i*squareSize+squareSize/2;
	    			        	} 			
	    			        	}
	    			        
	     			        id++;
	    				}
	    			}
	    		}
	       	id=1;
	        g.setColor(Color.DARK_GRAY);
		     Font font = new Font("Calibri", Font.PLAIN, 30);
		     g.setFont(font);
	         for(int i=b.getN()-1;i>=0;i--)
	 		{
	 			if((b.getN()-i)%2==1)
	 			{
	 				for(int j=0;j<b.getM();j++)
	 				{                                                     					                               
	 			         g.drawString(String.valueOf(id),100+ j*squareSize, i*squareSize+squareSize);
	 			        id++;
	 				}
	 			}
	 			else
	 			{
	 				for(int j=b.getM()-1;j>=0;j--)
	 				{
	 			        g.drawString(String.valueOf(id),100+ j*squareSize, i*squareSize+squareSize);
	  			        id++;
	 				}
	 			}
	 		}
	         id=1;
	         g.setColor(Color.GRAY);
		     Font font2 = new Font("Calibri", Font.PLAIN, 28);
		     g.setFont(font2);
	         Color myColor1 = new Color(92, 0, 0);
	         g.setColor(myColor1);
	    for(int l=0; l<b.getLadders().length;l++) {
	    	for(int i=0; i<3;i++) {
	    		g.drawLine(100+downstep[0][l]-squareSize/5+i, downstep[1][l],100+upstep[0][l]-squareSize/5+i, upstep[1][l]);  //aristero podi
	    		g.drawLine(100+downstep[0][l]+squareSize/5+i, downstep[1][l],100+ upstep[0][l]+squareSize/5+i, upstep[1][l]); //deksi podi
	    		g.drawLine(100+downstep[0][l]-squareSize/5, downstep[1][l]+i,100+ downstep[0][l]+squareSize/5, downstep[1][l]+i); //katw orizontia
	    		g.drawLine(100+upstep[0][l]-squareSize/5, upstep[1][l]+i,100+ upstep[0][l]+squareSize/5, upstep[1][l]+i);  //panw orizontia
	    	}	
	    }
	    Color myColor2 = new Color(0, 72, 0);
	    g.setColor(myColor2);
	    for(int s=0; s<b.getSnakes().length;s++) {
	    	g.fillOval(100+kefali[0][s], kefali[1][s]-squareSize/6,squareSize/7, squareSize/4);
	    	g.setColor(Color.BLACK);
	    	g.drawOval(100+kefali[0][s], kefali[1][s]-squareSize/6,squareSize/7, squareSize/4);
	    	g.setColor(myColor2);
	    	for(int i=0; i<5;i++) {
	    	g.drawLine(100+oura[0][s]+i, oura[1][s],100+ kefali[0][s]+i, kefali[1][s]); 	
	    	}
	    }
	    Color myColor3 = new Color(160,0, 0);
	    g.setColor(myColor3);
	    for(int a=0; a<b.getApples().length; a++) {
	    	if(b.getApples()[a].getColor()=="red") 
	    		 g.setColor(myColor3);
	    	else
	    		 g.setColor(Color.BLACK);
	    	g.fillOval(100+milo[0][a],milo[1][a],squareSize/3,squareSize/3);
	    	g.setColor(Color.BLACK);
	    	g.drawOval(100+milo[0][a],milo[1][a],squareSize/3,squareSize/3);
	    }   	
	    g.setColor(Color.BLACK);
	    
	    g.drawString("Round:"+game.getRound(), 0, 45);
	    g.setColor(Color.RED);
	    g.drawString("PLAYER 1", 30, 580);
	    g.drawString("MOVE SCORE:"+move1, 10, 605);
	    g.drawString("TOTAL SCORE:"+p1.getScore(), 10, 630);
	    g.setColor(Color.BLUE);
	    g.drawString("PLAYER 2", 1070, 580);
	    g.drawString("MOVE SCORE:"+move2, 1050, 605);
	    g.drawString("TOTAL SCORE:"+p2.getScore(), 1050, 630); 
	    
	  		}
	  			 	 												    
	    public static void main(String[] args) {  
	    	Game game=new Game(1);	
	        Board board=new Board(5, 10 , 3, 3, 6);
	        Player paiktis1=new Player(1,0,"Giorgos",board);
	        ArrayList<int[]> useless = new ArrayList<int[]>();
	        MinMaxPlayer paiktis2=new MinMaxPlayer(2,0,"Vasilis",board,useless);
	        board.createBoard();
	        board.createElementBoard();
	        Drawing d=new Drawing();  
	        d.setBoard(board);
	        d.setGame(game);
	        d.setPlayer1(paiktis1);
	        d.setPlayer2(paiktis2);
	        d.setMove1(0);
	        d.setMove2(0);
	        JFrame f=new JFrame("Snake Game esketit prr");        
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	  	        
	        //f.add(d); 
	        
	        
	        //f.repaint();
	        //f.revalidate();
	       // f.repaint();
	        f.setSize(1250,800);      
	        f.setVisible(true);
	        
	    }
	}

