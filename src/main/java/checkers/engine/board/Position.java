package checkers.engine.board;


/**
 * Created by david on 2017-05-16.
 */
public class Position {

    public final int x;
    public final int y;

    public Position(int x, int y) {
        if(x > Board.SIZE || x < 0 || y > Board.SIZE || y < 0) {
            System.out.println("Throw Position exception");
            //TODO: Throw exception
        }
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ";" + y + ")";
    }
}
