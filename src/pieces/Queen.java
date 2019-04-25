package pieces;

import java.util.ArrayList;

import Game.Tile;
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
	
	public ArrayList<int[]> generateMoveSet(Tile t)	
	{
		ArrayList<int[]> moveSet = new ArrayList<int[]>();
		
		Rook r = new Rook(col);
		Bishop b = new Bishop(col);
			
		moveSet.addAll(r.generateMoveSet(t));
		moveSet.addAll(b.generateMoveSet(t));
		
		return moveSet;
	}
}
