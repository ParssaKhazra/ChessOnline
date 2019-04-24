package Game;


import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pieces.Piece;


public class Tile extends Label
{

	private static boolean isSelected;
	private boolean  hasPiece;
	private Piece p;
	private int x, y;
	private static Tile selectedTile;


	public Tile()
	{

	}

	public Tile(boolean colour)
	{
		this.setPrefSize(64, 64);
		setCol(colour);
		setPiece(null);
	}

	public Tile(boolean colour, Piece p)
	{
		this.setPrefSize(64, 64);		
		setCol(colour);
		setPiece(p);

	}

	public void setOnAction() 
	{
		this.setOnMouseClicked(e-> setSelected());
	}

	private void checkMove() 
	{

		ArrayList<int[]> moveSet = selectedTile.getPiece().getMoveSet();

		boolean canMove = false;
		for(int i=0; i< moveSet.size() && !canMove; i++)
		{
			if(moveSet.get(i)[0] == x && moveSet.get(i)[1] == y)
				canMove =true;

		}

		if(canMove)
		{
			selectedTile.getPiece().setMoveCount(selectedTile.getPiece().getMoveCount()+1);
			setPiece(selectedTile.getPiece());

			int row = selectedTile.x, col = selectedTile.y;
			isSelected = false;
			Board.grid[row][col].setPiece(null);
		}

		else
			System.out.println("Cannot move here!");

		setOnAction();
	}

	private void checkKill()
	{
		ArrayList<int[]> moveSet = selectedTile.getPiece().getMoveSet();

		boolean canMove = false;
		for(int i=0; i< moveSet.size() && !canMove; i++)
		{
			if(moveSet.get(i)[0] == x && moveSet.get(i)[1] == y)
				canMove =true;

		}
		
		if(canMove)
		{
			//set enemy piece to null -> add to dead pile later
			
			setPiece(null);
			selectedTile.getPiece().setMoveCount(selectedTile.getPiece().getMoveCount()+1);
			setPiece(selectedTile.getPiece());
			selectedTile.setPiece(null);
			isSelected =  false;
			
			
		}

	}




	public void setCol(boolean col)
	{
		if(col == false)
			this.setStyle("-fx-background-color: #8FBC8F;"/*Color.DARKSEAGREEN*/);
		else
			this.setStyle("-fx-background-color: #F5F5DC;"/*Color.BEIGE*/);

	}

	public void setPiece(Piece p)
	{
		this.p = p;

		if (p != null)
		{
			setImage(p.getImage());
			hasPiece = true;
		}
		else
		{
			setImage(null);
			hasPiece = false;	
		}

	}
	public Piece getPiece()
	{
		return p;
	}

	public boolean isOccupied() 
	{
		return hasPiece;
	}

	public void setHasPiece(boolean isOccupied) 
	{
		this.hasPiece = isOccupied;
	}

	//TEMP METHOD
	public void setImage(ImageView i)
	{
		this.setGraphic(i);
		this.setAlignment(Pos.CENTER);
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected() 
	{

		if(p != null && !isSelected)
		{
			p.generateMoveSet(x,y);
			selectedTile = this;
			isSelected = true;
		}
		else if(p == null && isSelected)
		{
			checkMove();
		}
		else if(p != null && isSelected)
		{
			checkKill();
		}
		System.out.println(getDescription());
	}

	public void setCoordinates(int x, int y)
	{
		this.x =x;
		this.y = y;
	}

	public int[] getCoordinates()
	{
		int[] i = {x,y};
		return i;
	}

	public String getDescription()
	{
		if(hasPiece)
			return "Tile at ("+x+","+y+") contains "+p.getDescription()+"\nPossible moves: {"+p.printMoveSet()+"}";
		else
			return "Tile at ("+x+","+y+") does not contain a piece";
	}




}
