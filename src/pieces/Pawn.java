package pieces;

import java.util.ArrayList;

import Game.Board;
import Game.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pawn extends Piece
{
	private String url;

	public Pawn(String col)
	{
		super(Piece.PAWN, col);
		url = "/pieces/chess sprites/"+col+"Pawn"+".png";

		super.setName("Pawn");
		super.setImage(new ImageView(new Image(url))) ;
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
		 * if it is white it moves up, else moves down
		 * first turn it can move up 2 spaces
		 * else move up 1 space
		 * check top left and top right for enemy pieces
		 * 
		 * ACCOUNT FOR SPECIAL MOVE : EN PASSANT
		 */

		int limits=1;
		if(super.moveCount ==0)
			limits++;


		//move forward 1 or 2
		boolean blocked =false;

		for(int i=0; i< limits && !blocked; i++)
		{

			if(p.col.equals("white"))
			{
				//check if its blocked
				if(row==0 || Board.grid[row-1-i][col].isOccupied())
					blocked = true;
				else
					moveSet.add(new int[] {row-(i+1), col});

			}
			else
			{
				//check if its blocked
				if(row==7 || Board.grid[row+1+i][col].isOccupied())
					blocked = true;
				else
					moveSet.add(new int[] {row+(i+1), col});
			}

		}

		//killing move set
		int x=0, y=0;
		for(int i = 0; i<4; i++)
		{
			if(i ==0 && col < 7 && row <7 )
			{
				x=1; y = 1;
			}
			else if(i == 1 && row <7 && col >0)
			{
				x=1; y =-1;
			}
			else if(i == 2 && row >0 && col < 7)
			{
				x=-1; y= 1;
			}
			else if(i ==3 && row >0 && col >0 )
			{
				x=-1; y=-1;
			}
			
			int[] index = new int[] {row+x, col+y};
			
			if( (p.col.equals("white") && (i == 3 || i==2)) || (p.col.equals("black") && (i == 0 || i==1)) )	
			{
				if(Board.grid[row+x][col+y].isOccupied())
				{
					

					if(p.getCol().equals(Board.grid[row+x][col+y].getPiece().getCol()))
					{
						protectedSet.add(index);
					}
					else
					{
						moveSet.add(index);
						killSet.add(index);
					}
				}
				else
				{
					killSet.add(index);
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

	private void pawnPromotion()
	{
		//doing this later
	}




}
