package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class King extends Piece
{
	private String url;
	public King (String col)
	{
		super(Piece.KING, col);
		url = "/pieces/chess sprites/"+col+"King"+".png";
		
		super.setImage(new ImageView(new Image(url))) ;
		super.setName("King");
	}
}
