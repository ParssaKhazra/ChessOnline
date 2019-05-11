package pieces;

import java.util.ArrayList;

import Game.Board;
import Game.Tile;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Pawn extends Piece
{
	private String url;
	private int select;

	public Pawn(String col)
	{
		super(Piece.PAWN, col);
		url = "/pieces/chess sprites/"+col+"Pawn"+".png";

		super.setName("Pawn");
		super.setImage(new ImageView(new Image(url))) ;
	}

	/*
	 * this method will return a set of moves for the tile that is passed in
	 * input 0 for general moveSet
	 * input 1 for killSet
	 * input 2 for protectedSet
	 */
	public ArrayList<int[]> generateMoveSet(Tile t, int select)	
	{
		ArrayList<int[]> moveSet = new ArrayList<int[]>();
		ArrayList<int[]> killSet = new ArrayList<int[]>();
		ArrayList<int[]> protectedSet = new ArrayList<int[]>();

		int row = t.getCoordinates()[0], col = t.getCoordinates()[1];
		Piece p = t.getPiece();


		/*
		 * if it is white it moves up, else moves down
		 * first turn it can move up 2 spaces
		 * else move up 1 space
		 * check top left and top right for enemy pieces
		 * 
		 * ACCOUNT FOR SPECIAL MOVE : EN PASSANT
		 */

		int limits=1;
		if(super.moveCount ==0)
			limits++;


		//move forward 1 or 2
		boolean blocked =false;

		for(int i=0; i< limits && !blocked; i++)
		{

			if(p.col.equals("white"))
			{
				//check if its blocked
				if(row==0 || Board.grid[row-1-i][col].isOccupied())
					blocked = true;
				else
					moveSet.add(new int[] {row-(i+1), col});

			}
			else
			{
				//check if its blocked
				if(row==7 || Board.grid[row+1+i][col].isOccupied())
					blocked = true;
				else
					moveSet.add(new int[] {row+(i+1), col});
			}

		}

		//killing move set
		int x=0, y=0;
		for(int i = 0; i<4; i++)
		{
			if(i ==0 && col < 7 && row <7 )
			{
				x=1; y = 1;
			}
			else if(i == 1 && row <7 && col >0)
			{
				x=1; y =-1;
			}
			else if(i == 2 && row >0 && col < 7)
			{
				x=-1; y= 1;
			}
			else if(i ==3 && row >0 && col >0 )
			{
				x=-1; y=-1;
			}
			
			int[] index = new int[] {row+x, col+y};
			
			if( (p.col.equals("white") && (i == 3 || i==2)) || (p.col.equals("black") && (i == 0 || i==1)) )	
			{
				if(Board.grid[row+x][col+y].isOccupied())
				{
					if(p.getCol().equals(Board.grid[row+x][col+y].getPiece().getCol()))
					{
						protectedSet.add(index);
					}
					else
					{
						moveSet.add(index);
						killSet.add(index);
					}
				}
				/*else
				{
					killSet.add(index);
				}*/
			}

		}


		if(select == 0)
			return moveSet;
		else if( select == 1)
			return killSet;
		else
			return protectedSet;
	}

	public void pawnPromotion(Tile t)
	{
		// if the white pawn gets to row 0
		//if black pawn gets to row 7
		String col = t.getPiece().getCol();
		Piece p = t.getPiece();
		
		//make a popup
		Stage s = new Stage();
		FlowPane root = new FlowPane();
		HBox pieces = new HBox();
		
		Button knight = new Button();
		knight.setId(""+Piece.KNIGHT);
		knight.setStyle("-fx-background-color: #8FBC8F;");
		knight.setGraphic(new ImageView(new Image("/pieces/chess sprites/"+col+"Knight"+".png")));
		knight.setOnMouseClicked(e->click(knight,t,s));
		
		Button rook = new Button();
		rook.setId(""+Piece.ROOK);
		rook.setStyle("-fx-background-color: #8FBC8F;");
		rook.setGraphic(new ImageView(new Image("/pieces/chess sprites/"+col+"Rook"+".png")));
		rook.setOnMouseClicked(e->click(rook,t,s));
		
		Button queen = new Button();
		queen.setId(""+Piece.QUEEN);
		queen.setGraphic(new ImageView(new Image("/pieces/chess sprites/"+col+"Queen"+".png")));
		queen.setStyle("-fx-background-color: #8FBC8F;");
		queen.setOnMouseClicked(e->click(queen,t,s));
		
		Button bishop = new Button();
		bishop.setId(""+Piece.BISHOP);
		bishop.setStyle("-fx-background-color: #8FBC8F;");
		bishop.setGraphic(new ImageView(new Image("/pieces/chess sprites/"+col+"Bishop"+".png")));
		bishop.setOnMouseClicked(e->click(bishop,t,s));
		
		pieces.setSpacing(15);
		pieces.getChildren().addAll(knight, rook, bishop, queen);
		
		Label l = new Label("Pawn Promotion");
		l.setStyle("-fx-font-size:22");
		l.setPrefSize(200, 50);
		l.setAlignment(Pos.CENTER);
		
		root.setStyle("-fx-background-color: #F5F5DC;");
		
		//root.setVgap(60);
		root.getChildren().add(l);
		root.getChildren().add(pieces);
		root.setPadding(new Insets(10));
		
		root.setAlignment(Pos.TOP_CENTER);
		//root.getChildren().add(1,pieces);
		root.setPrefSize(200, 130);
		
		
		Scene scene = new Scene(root);
		s.setTitle("Pawn Promotion");
		s.centerOnScreen();
		s.getIcons().clear();
		s.getIcons().add(new Image(url));
		s.setScene(scene);
		
		s.setOnCloseRequest(e->e.consume());
		s.setAlwaysOnTop(true);
		
		
		Board.disable();
		s.showAndWait();
		
				
	}
	
	private void click(Button b, Tile t, Stage s)
	{
		int a = Integer.parseInt(b.getId());
		select = a;
		
		Piece b1;
		if (select == Piece.BISHOP)
		{
			 b1 = new Bishop(col);
			 t.setPiece(b1);
		}
		else if(select == Piece.QUEEN)
		{
			 b1 = new Queen(col);
			 t.setPiece(b1);
		}
		else if(select == Piece.KNIGHT)
		{
			 b1 = new Knight(col);
			 t.setPiece(b1);
		}
		else if (select == Piece.ROOK)
		{
			 b1 = new Rook(col);
			 t.setPiece(b1);
		}
		
		s.close();
		select =-1;
		Board.enable();
		
	}




}
