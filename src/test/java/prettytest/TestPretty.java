package prettytest;

import checkers.engine.agent.Agent;
import checkers.engine.board.Board;
import checkers.engine.board.Move;
import checkers.engine.board.Position;
import org.junit.Test;


public class TestPretty {

    @Test
    public void testBoardPretty() {
        Board board = new Board(new Agent(), new Agent());
        String s = board.asJson();
        String p = board.prettyPrint();
        System.out.println(s);
        System.out.println(p);
        board.move(new Move(new Position(2, 0), new Position(3, 1)));
        board.move(new Move(new Position(2, 0), new Position(3, 2)));

    }
}
