package pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Rook extends Piece
{
	private String url;

	public Rook(String col)
	{
		super(Piece.ROOK, col);
		url = "/pieces/chess sprites/"+col+"Rook"+".png";

		super.setImage(new ImageView(new Image(url))) ;
		super.setName("Rook");
	}

}
