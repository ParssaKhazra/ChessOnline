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
	
	public ArrayList<int[]> generateMoveSet(Tile t,int select)	
	{
		ArrayList<int[]> moveSet = new ArrayList<int[]>();
		ArrayList<int[]> killSet = new ArrayList<int[]>();
		ArrayList<int[]> protectedSet = new ArrayList<int[]>();
		
		Rook r = new Rook(col);
		Bishop b = new Bishop(col);
			
		moveSet.addAll(r.generateMoveSet(t,0));
		moveSet.addAll(b.generateMoveSet(t,0));
		
		killSet = moveSet;
		
		protectedSet.addAll(r.generateMoveSet(t,2));
		protectedSet.addAll(b.generateMoveSet(t,2));
		
		if(select == 0)
			return moveSet;
		else if( select == 1)
			return killSet;
		else
			return protectedSet;
	}
}
