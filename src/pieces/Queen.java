package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Queen extends Piece
{
	private String url;
	public Queen (String col)
	{
		super(Piece.QUEEN, col);
		url = "/pieces/chess sprites/"+col+"Queen"+".png";
		
		super.setImage(new ImageView(new Image(url)));
		super.setName("Queen");
	}
}
