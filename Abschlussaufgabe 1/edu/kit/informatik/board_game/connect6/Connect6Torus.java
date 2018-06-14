package edu.kit.informatik.board_game.connect6;

public class Connect6Torus extends Connect6 {

    /**
     * Constructor.
     * Initialize a new Connect6:torus game with the given parameter. The current player is player 1.
     *
     * @param name        the unique identifier name of the game.
     * @param dimension   the dimension of the square dimensional board.
     * @param totalPlayer the total amount of players joining the game.
     */
    public Connect6Torus(String name, int dimension, int totalPlayer) {
        super(name, dimension, totalPlayer);
    }

    /**
     * Sets the state of the field, after validate the coordinate, to the index of {@link #currentPlayer}.
     * @param row   the row of the field.
     * @param col   the column of the field.
     */
    @Override
    public void setState(int row, int col) {
        super.setState(validate(row), validate(col));
    }

    /**
     * Returns the state of the field, after validate the coordinate.
     * @param row   the row of the field.
     * @param col   the column of the field.
     * @return      the state of the field after validate the coordinate.
     */
    @Override
    public int getState(int row, int col) {
        return super.getState(validate(row), validate(col));
    }

    /**
     * Converts the index proportional to an index, which is always within the board's boundary.
     * @param index     the index you want to convert.
     * @return          the valid index which is within the board's boundary.
     */
    private int validate(int index) {
        return Math.floorMod(index, getDimension());
    }

    /**
     * All coordinates in this game gets validated means they are always valid.
     * @param row   index of the row you want to check.
     * @param col   index of the column you want to check.
     * @return      true
     */
    @Override
    public boolean isValid(int row, int col) {
        return true;
    }
}
