package Game;


import javafx.scene.layout.GridPane;

public class Board extends GridPane
{
	
	private Tile[][] grid =  new Tile[8][8];
	
	public Board()
	{	
		for(int i=0; i < 8; i++)
		{
			for(int j=0; j< 8; j++)
			{
				grid[i][j] = new Tile((i+j)%2 == 0);
				this.add(grid[i][j], j, i);
			}
		}
		
		
	}
	

}
