package checkers.engine.board;

import java.util.Objects;

/**
 * Created by david on 2017-05-17.
 */
public class Move {


    public final Position from;
    public final Position to;

    public Move(Position from, Position to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);

        this.from = from;
        this.to = to;
    }
}
