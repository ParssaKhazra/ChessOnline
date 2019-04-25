package pieces;

import java.util.ArrayList;

import Game.Board;
import Game.Tile;
import javafx.scene.image.ImageView;

public class Piece 
{
	protected String col;
	private String name;
	private int pieceType, x, y;
	protected int moveCount;
	private ImageView i;
	private ArrayList<int[]> moveSet;
	public static final int KING =1, QUEEN =2, KNIGHT = 3, BISHOP = 4, ROOK = 5, PAWN = 6;

	public Piece()
	{
		moveSet = new ArrayList<int[]>();
		moveCount =0;
	}

	public Piece(int pieceType)
	{
		this.pieceType =pieceType;
		moveSet = new ArrayList<int[]>();
		moveCount =0;
	}

	public Piece(int pieceType, String col)
	{
		this.pieceType =pieceType;
		moveSet = new ArrayList<int[]>();
		moveCount =0;
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	//Description 
	public String getDescription()
	{
		return col+" "+name;
	}


	public void generateMoveSet(int row, int col)
	{

		int type;

		if(Board.grid[row][col].getPiece() != null)
			type = Board.grid[row][col].getPiece().getPieceType();
		else 
			type =0;

		//move set conditions 
		/*
		 * 1. stop at own piece, except for knight
		 * 2. keep going until the end of the board is reached OR it hits an enemy /ally piece
		 * 	-for enemy piece have the tile that it resides on as an option
		 * 	-for ally piece stop before it
		 * 3. account for the 'line of fire' for the specific piece and any other special conditions
		 */

		if (type ==1)
		{
			//KING
			King p = (King) Board.grid[row][col].getPiece();
			moveSet = p.generateMoveSet(Board.grid[row][col]);
		}
		else if(type == 2)
		{
			//QUEEN
			Queen p = (Queen) Board.grid[row][col].getPiece();
			moveSet = p.generateMoveSet(Board.grid[row][col]);
		}
		else if(type == 3)
		{
			//KNIGHT
			Knight p = (Knight) Board.grid[row][col].getPiece();
			moveSet = p.generateMoveSet(Board.grid[row][col]);
		}
		else if(type == 4)
		{
			//BISHOP
			Bishop p = (Bishop) Board.grid[row][col].getPiece();
			moveSet = p.generateMoveSet(Board.grid[row][col]);
		}
		else if (type ==5)
		{
			//ROOK
			Rook p = (Rook) Board.grid[row][col].getPiece();
			moveSet = p.generateMoveSet(Board.grid[row][col]);
		}
		else if (type == 6)
		{
			//PAWN
			Pawn p = (Pawn) Board.grid[row][col].getPiece();
			moveSet = p.generateMoveSet(Board.grid[row][col]);
			/*
			 * { [x,y], [x,y], [x,y], [x,y] }
			 */
		}
		else
		{
			//no moveSet
		}


	}

	public ArrayList<int[]> getMoveSet()
	{
		return moveSet;
	}

	public String printMoveSet()
	{
		String s="";
		if( !moveSet.isEmpty())
			for(int i=0; i<moveSet.size(); i++ )
			{
				s+="("+moveSet.get(i)[0]+","+moveSet.get(i)[1]+")";
				if( i< moveSet.size()-1)
					s+= " ";
			}
		return s;
	}

	public int getMoveCount() {
		return moveCount;
	}

	public void setMoveCount(int moved) {
		moveCount = moved;
	}
}
