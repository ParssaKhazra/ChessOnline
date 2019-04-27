package Game;


import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import pieces.Piece;

public class Board extends GridPane
{

	public static Tile[][] grid =  new Tile[8][8];
	//public static ArrayList<int[]>  flags;

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
		
		//flags =  new ArrayList<int[]>();

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
			
			setFlags();
		}

		for(int i=0; i< 8; i++)
		{
			for(int j=0; j< 8; j++)
			{
				grid[i][j].setOnAction();
			}
		}

	}

	public static void clearText()
	{
		for(int i=0; i< 8; i++)
		{
			for(int j =0; j< 8; j++)
			{
				grid[i][j].setStyle(grid[i][j].style);
				grid[i][j].setText(null);

			}
		}
	}

	public static void setFlags()
	{
		//go thru all of the tiles that contain a piece, then flag the protected set of each one
		//and add the kill set to it too
		//make sure to call this every move
		clearFlags();
		//flags.clear();

		for(int i=0; i< 8; i++)
		{
			for(int j =0; j< 8; j++)
			{
				if (grid[i][j].isOccupied())
				{
					// getting all of the flags
					addFlags(grid[i][j]);
				}
			}
		}
	}
	public static void clearFlags()
	{
		for(int i=0; i< 8; i++)
		{
			for(int j =0; j< 8; j++)
			{
				grid[i][j].setWhiteFlag(false);
				grid[i][j].setBlackFlag(false);
			}
		}

	}


	public static void removeFlags(Tile t)
	{
		ArrayList<int[]> removeSet = new ArrayList<int[]>();
		Piece p = t.getPiece();
		
		p.generateMoveSet(t.getCoordinates()[0], t.getCoordinates()[1]);
		removeSet.addAll(p.getKillSet());
		removeSet.addAll(p.getProtectedSet());

		for(int i=0; i< removeSet.size(); i++)
		{
			if(p.getCol().equals("white"))
				grid[removeSet.get(i)[0]][removeSet.get(i)[1]].setWhiteFlag(false);
			else
				grid[removeSet.get(i)[0]][removeSet.get(i)[1]].setBlackFlag(false);
			
		}
	}

	public static void addFlags(Tile t)
	{
		ArrayList<int[]> addSet = new ArrayList<int[]>();
		Piece p = t.getPiece();
		
		p.generateMoveSet(t.getCoordinates()[0], t.getCoordinates()[1]);
		addSet.addAll(p.getKillSet());
		addSet.addAll(p.getProtectedSet());
		
		for(int i=0; i< addSet.size(); i++)
		{
			if(p.getCol().equals("white"))
				grid[addSet.get(i)[0]][addSet.get(i)[1]].setWhiteFlag(true);
			else
				grid[addSet.get(i)[0]][addSet.get(i)[1]].setBlackFlag(true);
		}
	}
	
	


}
