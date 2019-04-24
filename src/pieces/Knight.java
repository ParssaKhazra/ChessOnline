package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Knight extends Piece
{
	private String url;
	public Knight (String col)
	{
		super(Piece.KNIGHT, col);
		url = "/pieces/chess sprites/"+col+"Knight"+".png";
		
		super.setImage(new ImageView(new Image(url))) ;
		super.setName("Knight");
	}
}
