package pieces;

import java.util.ArrayList;

import Game.Board;
import Game.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bishop extends Piece
{
	private String url;
	public Bishop (String col)
	{
		super(Piece.BISHOP, col);
		url = "/pieces/chess sprites/"+col+"Bishop"+".png";

		super.setImage(new ImageView(new Image(url)));
		super.setName("Bishop");
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
		 * 1. x++, y++
		 * 2. x--, y++
		 * 3. x++, y--
		 * 4. x--, y--
		 */
		boolean blocked = false;
		for(int i=0; i< 4; i++)
		{
			while(!blocked)
			{
				//controlling the increments
				if(i == 0 && (x<7 && y<7))
				{
					x++; y++;
				}
				else if(i == 1 && (x>0 && y<7))
				{
					x--; y++;
				}
				else if( i == 2 && (x<7 && y>0))
				{
					x++; y--;
				}
				else if( i ==3 && (x>0 && y>0))
				{
					x--; y--;	
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
				
				//at the end of the board
				if( x== 7 || y == 7 || x==0 || y== 0)
					blocked = true;
		
			}
			x=row; y=col; blocked = false;

		}
		return moveSet;
	}
}
