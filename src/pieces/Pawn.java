package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pawn extends Piece
{
	private String url;
	
	public Pawn(String col)
	{
		super(Piece.PAWN, col);
		url = "/pieces/chess sprites/"+col+"Pawn"+".png";
		
		super.setImage(new ImageView(new Image(url))) ;
	}
	
	
	
	
}
