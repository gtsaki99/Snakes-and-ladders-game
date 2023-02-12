package projectPackage1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class Panel {
	Panel(JFrame f,Board b){    
		 
		int id=1;
		for(int i=8-1;i>=0;i--)
		{
			if((8-i)%2==1)
			{
				for(int j=0;j<10;j++)
				{                                                  
					JPanel panel=new JPanel();  
			        panel.setBounds(((j*100)+40),((i*100)+20),100,100); 
			        if(id%4==1)
			        	panel.setBackground(Color.yellow);
			        else if(id%4==2)
			        	panel.setBackground(Color.green);
			        else if(id%4==3)
			        	panel.setBackground(Color.blue);
			        else if(id%4==0)
			        	panel.setBackground(Color.cyan);
			        JLabel l=new JLabel(""+id);
			        l.setBounds(((j*100)+40), ((i*100)+20), 20, 20);
			        panel.add(l);
			        JLabel rApple=new JLabel(new ImageIcon("C:\\Users\\user\\Desktop\\image.png"));
					JLabel bApple=new JLabel(new ImageIcon("C:\\Users\\user\\Desktop\\image1.png"));
			        Apple[] fromBoardA = new Apple[b.getApplesLength()];
					for(int k=0;k<b.getApples().length;k++)
			        {
			        	if(fromBoardA[k].getAppleTileId()==id)
			        	{
			        		if(fromBoardA[k].getColor()=="red")
			                     panel.add(rApple);		        			        		
			        	    else if(fromBoardA[k].getColor()=="black")
			                     panel.add(bApple);
			        	}
			        }
			        id++;
			        f.add(panel);
				}
			}
			else
			{
				for(int j=10-1;j>=0;j--)
				{
					JPanel panel=new JPanel();  
			        panel.setBounds(((j*100)+40),((i*100)+20),100,100); 
			        if(id%4==1)
			        	panel.setBackground(Color.yellow);
			        else if(id%4==2)
			        	panel.setBackground(Color.green);
			        else if(id%4==3)
			        	panel.setBackground(Color.blue);
			        else if(id%4==0)
			        	panel.setBackground(Color.cyan);			        
			        JLabel l=new JLabel(""+id);
			        l.setBounds(((j*100)+40), ((i*100)+20), 20, 20);
			        panel.add(l);
			        JLabel rApple=new JLabel(new ImageIcon("C:\\Users\\user\\Desktop\\image.png"));
					JLabel bApple=new JLabel(new ImageIcon("C:\\Users\\user\\Desktop\\image1.png"));
					Apple[] fromBoardA = new Apple[b.getApplesLength()];
			        for(int k=0;k<b.getApples().length;k++)
			        {
			        	if(fromBoardA[k].getAppleTileId()==id)
			        	{
			        		if(fromBoardA[k].getColor()=="red")
			                     panel.add(rApple);		        			        		
			        	    else if(fromBoardA[k].getColor()=="black")
			                     panel.add(bApple);
			        	}
			        }			                
					id++;	
					f.add(panel);
				}
			}
		}
	}    
	
	    
	public static void main(String[] args) { 
		Game game=new Game(1);
		JFrame f= new JFrame("Panel Example");
		Board board=new Board(8, 10 , 3, 3, 6);
		board.createBoard();
        board.createElementBoard();
        Player player1=new Player(0,0,"Takis",board);
        ArrayList <int[]> useless = new ArrayList<int[]>();
        HeuristicPlayer player2=new HeuristicPlayer(0,0,"Mitsos",board,useless);
        MinMaxPlayer player3=new MinMaxPlayer(0,0,"Giorgos",board,useless);
        ArrayList<Object> players=new ArrayList<Object>();
        String typeOfPlayer[]= {"Random","Heuristic","MinMax"};
        JLabel p1=new JLabel("Player 1");
        p1.setBounds(1050, 405, 100, 30);
        f.add(p1);
        JComboBox<String> cb1=new JComboBox<String>(typeOfPlayer);
        cb1.setBounds(1100, 400, 100, 40);
        JButton createP1=new JButton("Create Player 1");
        createP1.setBounds(1050,350, 200, 30);
        createP1.addActionListener(new ActionListener(){  
	        	public void actionPerformed(ActionEvent e){  
	        		if(cb1.getItemAt(cb1.getSelectedIndex())=="Random")
					{
						player1.setPlayerId(1);
						players.add(player1);
					}	             	
					else if(cb1.getItemAt(cb1.getSelectedIndex())=="Heuristic")
					{
						player2.setPlayerId(1);
						players.add(player2);
					}
					else if(cb1.getItemAt(cb1.getSelectedIndex())=="MinMax")
					{
						player3.setPlayerId(1);
						players.add(player3);
					}
	            }  
	        });    
        f.add(cb1);
        f.add(createP1);
        JLabel p2=new JLabel("Player 2");
        p2.setBounds(1350, 405, 100, 30);
        f.add(p2);
        JComboBox<String> cb2=new JComboBox<String>(typeOfPlayer);
        cb2.setBounds(1400, 400, 100, 40);
        JButton createP2=new JButton("Create Player 2");
        createP2.setBounds(1350, 350, 200, 30);
        createP2.addActionListener(new ActionListener(){  
	        	public void actionPerformed(ActionEvent e){  
	        		if(cb2.getItemAt(cb2.getSelectedIndex())=="Random")
					{
						player1.setPlayerId(2);
						players.add(player1);
					}	             	
					else if(cb2.getItemAt(cb2.getSelectedIndex())=="Heuristic")
					{
						player2.setPlayerId(2);
						players.add(player2);
					}
					else if(cb2.getItemAt(cb2.getSelectedIndex())=="MinMax")
					{
						player3.setPlayerId(2);
						players.add(player3);
					}
	            }  
	        });    
        f.add(cb2);
        f.add(createP2);
	       new Panel(f,board);
	       JButton b=new JButton("Play");
	        b.setBounds(1200, 700, 100, 30);
	        b.addActionListener(new ActionListener(){  
	        	public void actionPerformed(ActionEvent e){  
	                 game.setTurns(players);
	            }  
	        });  
	        f.add(b);	    
	        f.add(new Drawing());
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        f.setSize(400,400);    
	        f.setLayout(null);    
	        f.setVisible(true);    
	}    
}

