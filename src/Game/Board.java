package Game;


import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import pieces.Piece;

public class Board extends GridPane
{

	public static Tile[][] grid =  new Tile[8][8];

	public Board()
	{	
		for(int i=0; i < 8; i++)
		{
			for(int j=0; j< 8; j++)
			{
				grid[i][j] = new Tile((i+j)%2 == 0);
				this.add(grid[i][j], j, i);
				grid[i][j].setCoordinates(i, j);
			}
		}


	}

	public void addTeams(ArrayList<Piece> whiteTeam, ArrayList<Piece> blackTeam)
	{

		for(int i=0; i< 8; i++)
		{
			for(int j =0; j< 8; j++)
			{
				if(i == 6 )
				{
					grid[i][j].setPiece(whiteTeam.get(j)); 
					whiteTeam.get(j).setX(i); 
					whiteTeam.get(j).setY(j);
				}
				else if( i == 1)
				{
					grid[i][j].setPiece(blackTeam.get(j));
					blackTeam.get(j).setX(i); 
					blackTeam.get(j).setY(j);
				}
				else if( i== 0)
				{
					grid[i][j].setPiece(blackTeam.get(j+8));
					blackTeam.get(j+8).setX(i); 
					blackTeam.get(j+8).setY(j);
				}
				
				else if(i == 7)
				{
					grid[i][j].setPiece(whiteTeam.get(j+8));
					whiteTeam.get(j+8).setX(i); 
					whiteTeam.get(j+8).setY(j);
				}

			}
		}
		
		for(int i=0; i< 8; i++)
		{
			for(int j=0; j< 8; j++)
			{
				grid[i][j].setOnAction();
			}
		}

	}


}
