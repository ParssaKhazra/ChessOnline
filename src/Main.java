import Game.Board;
import Game.Game;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application 
{
	public void start(Stage primaryStage) 
	{
		try 
		{
			Game g = new Game();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args) 
	{
		launch(args);
	}

	/*
	 * TO DO LIST
	1. re do king movement
	2. check
	3. check mate
	4. dead piece display
	5. special moves
    	-> castling
    	-> en passent
   	6. display turns
   	7. display dead pieces
	8.online support
	 
	*/

}
