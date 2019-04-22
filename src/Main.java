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


}
