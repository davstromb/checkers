package checkers.engine.board;


import checkers.engine.agent.Agent;
import checkers.engine.agent.Color;
import checkers.util.Json;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.log4j.Logger;

import java.util.Objects;


public class Board implements Json {

    private static final Logger LOGGER = Logger.getLogger(Board.class);

    public final static int SIZE = 8;
    public final static double LEGAL_DISTANCE = distance(new Move(new Position(2, 0), new Position(3, 1)));

    private final Agent player1;

    private final Agent player2;


    private final Color[][] matrix;
    private Agent currentPlayer;

    @JsonProperty(value = "size")
    private int size() {
        return SIZE;
    }

    @JsonProperty(value = "board_matrix")
    private Color[][] matrix() {
        return matrix;
    }

    public String prettyPrint() {
        String s = "";
        for(int i=0; i< SIZE; i++){
            for(int j=0; j< SIZE; j++){
                String tmp = "-";
                if(matrix[i][j] != null) {
                    tmp = "" + matrix[i][j];
                }
                s += String.format("%20s", tmp);

            }
            s += "\n";
        }
        return s;
    }

    public Board(final Agent agent1, final Agent agent2) {

        if(Objects.equals(agent1.color(), agent2.color())) {
            LOGGER.error("Agents colors are matching: " + agent1.color() + " and " + agent2.color());
        }

        if(agent1.name().equals(agent2.name())) {
            LOGGER.error("Agents can not have the same name: \'" + agent1.name() + "\' and \'" + agent2.name() + "\'");
        }

        this.player1 = agent1;
        this.player2 = agent2;
        this.currentPlayer = agent1;
        this.matrix = initBoardMatrix();
    }

    public String currentPlayer() {
        return this.currentPlayer.name();
    }

    public boolean move(final Move move) {
        LOGGER.info("Requested move from " + move.from + " to " + move.to +  " for agent " + currentPlayer.name() + " with color " + currentPlayer.color());
        final Color fromColor = discAtPosition(move.from);

        if(currentPlayer.color() != fromColor) {
            LOGGER.error("Trying to move a disc with color " + fromColor + ", when current player's color is "
                    + currentPlayer.color() + ". Please wait for your turn.");
        }

        if(! moveIsLegal(move)) {
            LOGGER.info("Move of \'"+ fromColor +"\' from " + move.from + " to " + move.to +  " was not performed.");
            return false;
        }
        setPosition(move.from, null);
        setPosition(move.to, fromColor);

        LOGGER.info("Move of "+ fromColor +" from " + move.from + " to " + move.to +  " is done.");
        currentPlayer = oppositeAgent(currentPlayer);
        return true;
    }

    private Agent oppositeAgent(Agent currentPlayer) {
        if(player1.color() == currentPlayer.color()) {
            return player2;
        }
        return player1;
    }


    public boolean moveIsLegal(Move move) {
        return colorIsLegal(move) && distanceIsLegal(move);
    }

    private boolean distanceIsLegal(Move move) {
        if(LEGAL_DISTANCE != distance(move)) {
            LOGGER.error("Distance is not legal, suggested distance: " + distance(move) + ". The distance of a move has to be " + LEGAL_DISTANCE + ".");
            return false;
        }
        return true;
    }

    private static double distance(Move move) {
        final float x1 = move.from.x;
        final float x2 = move.to.x;
        final float y1 = move.from.y;
        final float y2 = move.to.y;
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    private boolean colorIsLegal(Move move) {
        final Color fromColor = matrix[move.from.x][move.from.y];
        final Color toColor = matrix[move.to.x][move.to.y];
        if(! Objects.isNull(toColor)) {
            return false;
        } else if(Objects.isNull(fromColor)) {
            return false;
        }
        return true;
    }

    private void setPosition(Position position, Color disc) {
        matrix[position.y][position.x] = disc;
    }

    private Color discAtPosition(Position from) {
        return matrix[from.y][from.x];
    }


    private Color[][] initBoardMatrix() {
        Color[][] matrix = new Color[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                matrix[i][j] = null;
            }
        }

        matrix[0][0] = Color.WHITE;
        matrix[0][2] = Color.WHITE;
        matrix[0][4] = Color.WHITE;
        matrix[0][6] = Color.WHITE;
        matrix[1][1] = Color.WHITE;
        matrix[1][3] = Color.WHITE;
        matrix[1][5] = Color.WHITE;
        matrix[1][7] = Color.WHITE;
        matrix[2][0] = Color.WHITE;
        matrix[2][2] = Color.WHITE;
        matrix[2][4] = Color.WHITE;
        matrix[2][6] = Color.WHITE;

        matrix[7][1] = Color.BLACK;
        matrix[7][3] = Color.BLACK;
        matrix[7][4] = Color.BLACK;
        matrix[7][7] = Color.BLACK;
        matrix[6][0] = Color.BLACK;
        matrix[6][2] = Color.BLACK;
        matrix[6][4] = Color.BLACK;
        matrix[6][6] = Color.BLACK;
        matrix[5][1] = Color.BLACK;
        matrix[5][3] = Color.BLACK;
        matrix[5][5] = Color.BLACK;
        matrix[5][7] = Color.BLACK;

        return matrix;
    }


}
