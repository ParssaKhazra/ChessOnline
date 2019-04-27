package pieces;

import java.util.ArrayList;

import Game.Board;
import Game.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Knight extends Piece
{
	private String url;
	public Knight (String col)
	{
		super(Piece.KNIGHT, col);
		url = "/pieces/chess sprites/"+col+"Knight"+".png";
		
		super.setImage(new ImageView(new Image(url))) ;
		super.setName("Knight");
	}
	
	public ArrayList<int[]> generateMoveSet(Tile t, int select)
	{
		ArrayList<int[]> moveSet = new ArrayList<int[]>();
		ArrayList<int[]> protectedSet = new ArrayList<int[]>();

		int row = t.getCoordinates()[0], col = t.getCoordinates()[1];
		Piece p = t.getPiece();
		
		/*
		 * the knight moves in a L shape
		 * ---------------
		 * |1. x+=2, y+=1|	
		 * |2. x+=2, y-=1|
		 * |3. x-=2, y+=1|
		 * |4. x-=2, y-=1|
		 * |5. x+=1, y+=2|
		 * |6. x+=1, y-=2|
		 * |7. x-=1, y+=2|
		 * |8. x-=1, y-=2|
		 * ---------------
		 */
		
		int x = row; int y= col;
		int[][] moves = new int[][]
		{		
			{x+2, y+1}, {x+2, y-1}, {x-2, y+1}, {x-2, y-1}, {x+1, y+2}, {x+1, y-2}, {x-1, y+2}, {x-1, y-2} 
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
					if(!Board.grid[a][b].getPiece().getCol().equals(p.col))
					{
						moveSet.add(moves[i]);
					}
					else
					{
						protectedSet.add(moves[i]);
					}
				}
				else
				{
					moveSet.add(moves[i]);
				}
			}
		}
				
		if(select == 0)
			return moveSet;
		else if( select == 1)
			return moveSet;
		else
			return protectedSet;	}
}
