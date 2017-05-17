package checkers.engine.agent;

/**
 * Created by david on 2017-05-16.
 */
public class Agent {

    public Color color() {
        return Color.BLACK;
    }

    public String name() {
        return this.getClass().getSimpleName();
    }
}
