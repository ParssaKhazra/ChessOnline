package Game;

public class Player {
    private String name;
    private boolean turn;

    public Player(String name){
        this.name = name;
        turn = false;
    }
    public Player(String name, boolean turn){
        this.name = name;
        this.turn = turn;
    }
    public void setTurn(boolean turn){ this.turn = turn;}
    public boolean getTurn(){return turn;}
}
