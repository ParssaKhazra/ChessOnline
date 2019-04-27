package Game;


import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;
import pieces.Piece;


public class Tile extends Label
{

	private static boolean isSelected;
	private boolean hasPiece, whiteFlag, blackFlag, safe;
	private Piece p;
	private int x, y;
	private static Tile selectedTile;
	public String style;


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
			
			//Board.removeFlags(selectedTile);
						
			setPiece(selectedTile.getPiece());
			//Board.addFlags(this);
			
			selectedTile.setPiece(null);
			isSelected = false;
			
		}

		else
		{
			System.out.println("Cannot move here!");
			isSelected = false;
		}

		Board.setFlags();
		
	}

	// merge check kill to check move
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
		//	Board.removeFlags(this);
		//	Board.removeFlags(selectedTile);
			setPiece(null);
			hasPiece =false;
			selectedTile.getPiece().setMoveCount(selectedTile.getPiece().getMoveCount()+1);
			setPiece(selectedTile.getPiece());
		//	Board.addFlags(this);
			selectedTile.setPiece(null);
			isSelected =  false;

		}
		else
			isSelected = false;
		
		Board.setFlags();
	}


	public void setCol(boolean col)
	{
		if(col == false)
		{
			this.setStyle("-fx-background-color: #8FBC8F;"/*Color.DARKSEAGREEN*/);
			style = "-fx-background-color: #8FBC8F;";
		}
		else
		{
			this.setStyle("-fx-background-color: #F5F5DC;"/*Color.BEIGE*/);
			style = "-fx-background-color: #F5F5DC;";
		}

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
		Board.clearText();
				
		if(p != null && !isSelected)
		{
			p.generateMoveSet(x,y);
			selectedTile = this;
			highlight();
			isSelected = true;
		}
		else if(p !=null && isSelected && selectedTile.getPiece().getCol().equals(p.getCol()))
		{
			p.generateMoveSet(x, y);
			selectedTile = this;
			highlight();
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

	private void highlight()
	{
		ArrayList<int[]> moveSet = selectedTile.getPiece().getMoveSet();
		ArrayList<int[]> protectedSet = selectedTile.getPiece().getProtectedSet();

		//moves and kills
		for(int i=0; i< moveSet.size(); i++)
		{
			Tile t = Board.grid[moveSet.get(i)[0]][moveSet.get(i)[1]];
			if(t.isOccupied())
			{
				t.setStyle(t.getStyle()+"-fx-border-color: red; -fx-border-width: 0.75;");
			}
			else
			{
				t.setText("X");
				t.setStyle(t.getStyle()+"-fx-text-fill: red; -fx-font-size: 20; ");
			}
		}
		
//		for(int i=0; i<protectedSet.size(); i++)
//		{
//			Tile t = Board.grid[protectedSet.get(i)[0]][protectedSet.get(i)[1]];
//			t.setStyle(t.getStyle()+"-fx-border-color: black; -fx-border-width: 0.75;");
//		}
		
		//Color.chartreuse , lawngreen, greenyellow
		selectedTile.setStyle(selectedTile.getStyle()+"-fx-border-color: blue; -fx-border-width: 0.75;");
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

	public boolean getWhiteFlag() {
		return whiteFlag;
	}

	public void setWhiteFlag(boolean whiteFlag) {
		this.whiteFlag = whiteFlag;
	}

	public boolean getBlackFlag() {
		return blackFlag;
	}

	public void setBlackFlag(boolean blackFlag) {
		this.blackFlag = blackFlag;
	}

	public boolean isSafe() {
		return safe;
	}

	public void setSafe(boolean safe) {
		this.safe = safe;
	}

	public boolean getFlag(String s) 
	{
		if( s.equals("white"))
			return whiteFlag;
		else
			return blackFlag;
	}




}
