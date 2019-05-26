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

	/*
	 * this method will return a set of moves for the tile that is passed in
	 * input 0 for general moveSet
	 * input 1 for killSet
	 * input 2 for protectedSet
	 */

	public ArrayList<int[]> generateMoveSet(Tile t, int select)	
	{
		ArrayList<int[]> moveSet = new ArrayList<int[]>();
		ArrayList<int[]> killSet = new ArrayList<int[]>();
		ArrayList<int[]> protectedSet = new ArrayList<int[]>();

		int row = t.getCoordinates()[0], col = t.getCoordinates()[1];
		Piece p = t.getPiece();

		/*
		 * ROOK can move horizontal/ vertical in any direction
		 * stop at friendly(exclusive) and enemy(inclusive)
		 * extend the killset to the next piece behind the king if empty 
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
				int[] index = new int[] {x,y};

				if(Board.grid[x][y].isOccupied())
				{
					//for killing or protecting 
					if(Board.grid[x][y].getPiece().getCol().equals(p.getCol()) )
					{
						//protecting an ally piece
						blocked = true;
						protectedSet.add(index);
					}
					else
					{
						//killing an enemy piece
						//make this cleaner later - first improve check
						
						if(Board.grid[x][y].getPiece().getPieceType() != Piece.KING)
						{
							blocked = true;
						}
						moveSet.add(index);
						
					}
				}
				else
				{
					//general movement
					moveSet.add(index);
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
		if(select == 0)
			return moveSet;
		else if( select == 1)
			return moveSet;
		else
			return protectedSet;
	}

}
