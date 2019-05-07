package Game;

import java.util.ArrayList;
import java.util.Optional;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pieces.*;


public class Game 
{

	private ArrayList<Piece> whiteTeam; 
	private ArrayList<Piece> blackTeam;
	private Board board;
	private String username;
	public Game()
	{
		/*TextInputDialog dialog = new TextInputDialog("walter");
		dialog.setTitle("Text Input Dialog");
		dialog.setHeaderText(null);
		dialog.setContentText("Please enter a username: ");
		username = dialog.showAndWait().get();

		Alert runServerAlert = new Alert(Alert.AlertType.CONFIRMATION);
		runServerAlert.setTitle("Run server?");
		runServerAlert.setHeaderText(null);
		runServerAlert.setContentText("Do you want to run the server on PORT 3125?");
		Optional<ButtonType> result = runServerAlert.showAndWait();
		if (result.get() == ButtonType.OK){

		} else {

		}*/
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
		
		whiteTeam = new ArrayList<Piece>(16);
		blackTeam = new ArrayList<Piece>(16);

		for(int i=0; i< 16; i++)
		{
			if(i < 8)
				whiteTeam.add(new Pawn("white"));
			else
				blackTeam.add(i-8,new Pawn("black"));
		}

		whiteTeam.add(new Rook("white"));
		whiteTeam.add(new Knight("white"));
		whiteTeam.add(new Bishop("white"));
		whiteTeam.add(new Queen("white"));
		whiteTeam.add(new King("white"));
		whiteTeam.add(new Bishop("white"));
		whiteTeam.add(new Knight("white"));
		whiteTeam.add(new Rook("white"));

		blackTeam.add(new Rook("black"));
		blackTeam.add(new Knight("black"));
		blackTeam.add(new Bishop("black"));
		blackTeam.add(new Queen("black"));
		blackTeam.add(new King("black"));
		blackTeam.add(new Bishop("black"));
		blackTeam.add(new Knight("black"));
		blackTeam.add(new Rook("black"));

		board.addTeams(whiteTeam, blackTeam);
		
	}




}
