package pieces;

import javafx.scene.image.ImageView;

public class Piece 
{
	private String col;
	private int pieceType;
	private ImageView i;
	public static final int KING =1, QUEEN =2, KNIGHT = 3, BISHOP = 4, ROOK = 5, PAWN = 6;
	
	public Piece()
	{
		
	}
	
	public Piece(int pieceType)
	{
		this.pieceType =pieceType;
	}
	
	public Piece(int pieceType, String col)
	{
		this.pieceType =pieceType;
		this.col =col;
	}
	
	public Piece(boolean col)
	{
		if(col)
			this.setCol("white");
		else
			this.setCol("black");
	}


	public String getCol() {
		return col;
	}


	public void setCol(String col) {
		this.col = col;
	}


	public int getPieceType() {
		return pieceType;
	}


	public void setPieceType(int pieceType) {
		this.pieceType = pieceType;
	}

	public ImageView getImage() {
		return i;
	}

	public void setImage(ImageView i) {
		this.i = i;
	}
}
