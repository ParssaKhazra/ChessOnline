package Game;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pieces.Piece;


public class Tile extends Label
{

	private boolean isOccupied;
	private Piece p;
	
	
	public Tile()
	{
		
	}
	
	public Tile(boolean colour)
	{
		this.setPrefSize(64, 64);
		
		setCol(colour);
	}
	
	public Tile(boolean colour, Piece p)
	{
		this.setPrefSize(64, 64);		
		setCol(colour);
		setPiece(p);
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
	}
	public Piece getPiece()
	{
		return p;
	}

	public boolean isOccupied() 
	{
		return isOccupied;
	}

	public void setOccupied(boolean isOccupied) 
	{
		this.isOccupied = isOccupied;
	}
	
	//TEMP METHOD
	public void setImage(Image i)
	{
		this.setGraphic(new ImageView(i));
		this.setAlignment(Pos.CENTER);
	}

	
}
