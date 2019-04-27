package pieces;

import java.util.ArrayList;

import Game.Board;
import Game.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class King extends Piece
{
	private String url;
	public King (String col)
	{
		super(Piece.KING, col);
		url = "/pieces/chess sprites/"+col+"King"+".png";
		
		super.setImage(new ImageView(new Image(url))) ;
		super.setName("King");
	}
	
	public ArrayList<int[]> generateMoveSet(Tile t,int select)
	{
		ArrayList<int[]> moveSet = new ArrayList<int[]>();
		ArrayList<int[]> killSet = new ArrayList<int[]>();
		ArrayList<int[]> protectedSet = new ArrayList<int[]>();

		int row = t.getCoordinates()[0], col = t.getCoordinates()[1];
		Piece p = t.getPiece();
		
		/*
		 * the king moves in every direction, 1 space	
		 */
		//ACCOUNTING FOR CHECK LATER
				
		int x = row; int y= col;
		int[][] moves = new int[][]
		{		
			{x+1, y+1}, {x+1, y-1}, {x-1, y+1}, {x-1, y-1}, {x+1, y}, {x-1, y}, {x, y+1}, {x, y-1} 
		};
		
		
		//iterate through the moves and detect valid ones
		for(int i=0; i< 8; i++)
		{
			//if x>7, x<0, y>7, y<0 auto invalid
			int a = moves[i][0], b = moves[i][1];
			if(a <=7 &&  a >=0 && b <=7 && b >= 0)
			{
				//check for occupied
				if(Board.grid[a][b].isOccupied())
				{
					String s =Board.grid[a][b].getPiece().getCol();
					if(!s.equals(p.col) && !Board.grid[a][b].getFlag(s))
					{
						moveSet.add(moves[i]);
						killSet.add(moves[i]);
					}
					else
					{
						protectedSet.add(moves[i]);
					}
				}
				else 
				{
					//moveSet.add(moves[i]);
					if( Board.grid[a][b].getWhiteFlag() && p.col.equals("white"))
					{
						moveSet.add(moves[i]);
					}
					else if(Board.grid[a][b].getWhiteFlag() && p.col.equals("black"))
					{
						System.err.println("flaged ("+a+","+b+")");
					}
					else if( Board.grid[a][b].getBlackFlag() && p.col.equals("black"))
					{
						moveSet.add(moves[i]);
					}
					else if(Board.grid[a][b].getBlackFlag() && p.col.equals("white"))
					{
						System.err.println("flaged ("+a+","+b+")");
					}
					else
					{
						moveSet.add(moves[i]);
					}
					
				}
			}
		}
		
		
		if(select == 0)
			return moveSet;
		else if( select == 1)
			return killSet;
		else
			return protectedSet;
	}
}
