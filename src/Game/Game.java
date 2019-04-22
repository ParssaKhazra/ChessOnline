package Game;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pieces.Pawn;
import pieces.Piece;

public class Game 
{

	private ArrayList<Piece> whiteTeam; 
	private ArrayList<Piece> blackTeam;
	private Board board;

	public Game()
	{

		//start
		startStage();

	}

	//this will open the gui 
	private void startStage()
	{
		Stage p = new Stage();
		GridPane root = new GridPane();
		board = new Board();

		root.getChildren().add(board);
		Scene scene = new Scene(root);

		initTeams();
		
		p.setScene(scene);
		p.show();
	}

	//this method will initialize the array list pieces
	private void initTeams()
	{
		//FOR NOW ONLY PAWNS

		whiteTeam = new ArrayList<Piece>(8);
		blackTeam = new ArrayList<Piece>(8);

		//TEMP ADDING BLACK AND WHITE PAWNS TO THE list
		for(int i=0; i< 16; i++)
		{
			if(i < 8)
			{
				whiteTeam.add(new Pawn("white"));
			}
			else
			{
				blackTeam.add(i-8,new Pawn("black"));
			}
		}
		
		board.addTeams(whiteTeam, blackTeam);




	}




}
