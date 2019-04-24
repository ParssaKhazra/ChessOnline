package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bishop extends Piece
{
	private String url;
	public Bishop (String col)
	{
		super(Piece.BISHOP, col);
		url = "/pieces/chess sprites/"+col+"Bishop"+".png";
		
		super.setImage(new ImageView(new Image(url)));
		super.setName("Bishop");
	}
}
