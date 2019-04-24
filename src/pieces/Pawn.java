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
	
	public ArrayList<int[]> generateMoveSet(Tile t)	
	{
		ArrayList<int[]> moveSet = super.getMoveSet();
		moveSet.clear();
		
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
			if(row>0 && p.col.equals("white") && !Board.grid[row-1-i][col].isOccupied())
			{
				moveSet.add(new int[] {row-(i+1), col});
			}
			else if (row>0 && p.col.equals("white") && Board.grid[row-1-i][col].isOccupied())
				blocked = true;
			
			if(row<8 && p.col.equals("black") && !Board.grid[row+1+i][col].isOccupied())
			{
				moveSet.add(new int[] {row+(i+1), col});
			}
			else if(row<8 && p.col.equals("black") && Board.grid[row+1+i][col].isOccupied())
				blocked = true;
			
		}

		//killing move set
		if(p.col.equals("white"))
		{
			
			if(row>0 &&col < 7 && Board.grid[row-1][col+1].isOccupied() && Board.grid[row-1][col+1].getPiece().getCol().equals("black") )
			{
				moveSet.add(new int[]{row-1, col+1});
			}
			if(row>0 && col>0 && Board.grid[row-1][col-1].isOccupied() && Board.grid[row-1][col-1].getPiece().getCol().equals("black"))
			{
				moveSet.add(new int[]{row-1, col-1});
			}

		}
		else
		{
			if(row>0 &&col < 7 && Board.grid[row+1][col+1].isOccupied() && Board.grid[row+1][col+1].getPiece().getCol().equals("white"))
			{
				moveSet.add(new int[]{row+1, col+1});
			}
			if(row>0 && col>0 && Board.grid[row+1][col-1].isOccupied() && Board.grid[row+1][col-1].getPiece().getCol().equals("white"))
			{
				moveSet.add( new int[]{row+1, col-1});
			}
		}
		
		
		return moveSet;
	}
	
	
	
	
}
