package boardtest;

import checkers.engine.agent.Agent;
import checkers.engine.board.Board;
import org.junit.Test;

public class TestBoard {

    @Test
    public void testBoardSize() {
        Board board = new Board(new Agent(), new Agent());
        String s = board.asJson();
    }
}
