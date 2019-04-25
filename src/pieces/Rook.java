package pieces;

import java.util.ArrayList;

import Game.Board;
import Game.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Rook extends Piece
{
	private String url;

	public Rook(String col)
	{
		super(Piece.ROOK, col);
		url = "/pieces/chess sprites/"+col+"Rook"+".png";

		super.setImage(new ImageView(new Image(url))) ;
		super.setName("Rook");
	}
	
	public ArrayList<int[]> generateMoveSet(Tile t)	
	{
		ArrayList<int[]> moveSet = super.getMoveSet();
		moveSet.clear();

		int row = t.getCoordinates()[0], col = t.getCoordinates()[1];
		Piece p = t.getPiece();

		/*
		 * Bishop can move diagonal in any direction
		 * stop at friendly(exclusive) and enemy(inclusive)
		 */
		int x=row, y=col;
		/*
		 * 1. x++, y=col
		 * 2. x--, y=col
		 * 3. x=row, y--
		 * 4. x=row, y++
		 */
		boolean blocked = false;
		for(int i=0; i< 4; i++)
		{
			while(!blocked)
			{
				//controlling the increments
				if(i == 0 && x<7 )
				{
					x++; y=col;
				}
				else if(i == 1 && x>0 )
				{
					x--; y=col;
				}
				else if( i == 2 &&  y<7)
				{
					x=row; y++;
				}
				else if(i ==3 && y>0)
				{
					x=row; y--;	
				}
					
				//if the diagonal tile is occupied, check the colour of the piece
				if(Board.grid[x][y].isOccupied())
				{
					if(Board.grid[x][y].getPiece().getCol().equals(p.getCol()) )
						blocked = true;
					else
					{
						moveSet.add(new int[] {x,y});
						blocked = true;
					}
				}
				else
				{
					moveSet.add(new int[] {x,y});
				}
				
				//checking if the piece is at the end of the board
				if( i==0 && x == 7)
					blocked = true;
				else if (i == 1 && x == 0)
					blocked = true;
				else if(i == 2 && y ==7)
					blocked = true;
				else if(i == 3 && y == 0)
					blocked = true;
		
			}
			x=row; y=col; blocked = false;

		}
		return moveSet;
	}

}
