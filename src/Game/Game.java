package Game;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Game 
{

	public Game()
	{
		
			Stage p = new Stage();
			GridPane root = new GridPane();
			Board board = new Board();

			root.getChildren().add(board);
			Scene scene = new Scene(root);
			
			p.setScene(scene);
			p.show();
		
		
	}
	
}
