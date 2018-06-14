package edu.kit.informatik.board_game;

/**
 * This class represents a simple board game with a <i>square dimensional</i> board.<br>
 * It just implements the really basic functionality of a board game like placing and
 * basic visual output.<br>
 *<br>
 * The board is represented by a matrix of int values. These values representing the state of each field.<br>
 * Changing the state of a field meaning a player placed a mark on it.
 * <br>
 * The game starts with player 1 and iterate upwards through all players.<br>
 * Visual output:<br>
 * All following toString methods return a string representation like this:<br>
 *     "**" for a free field.<br>
 *     "Px" for a field that is marked by a player P with its player number x.<br>
 *     For example if player 3 has a mark on the field it returns "P3" for this field.
 */
public abstract class BoardGame {
    private static final String INDEX_ERROR_MESSAGE = "index out of bounds.";

    /**the unique identifier name of the game*/
    private final String name;

    /**The dimension of the square dimensional board.*/
    private final int dimension;

    /**The total amount of players in this game.*/
    private final int totalPlayer;

    /**The matrix representing the board.*/
    private int[][] board;

    /**The current player who is allowed to make his next place.*/
    private int currentPlayer = 1;

    /**The total amount of placed fields.*/
    private int placeCounter;

    /**
     * Constructor.
     * Initialize a new board game with the given parameter.
     * @param name          the unique identifier name of the game.
     * @param totalPlayer   the total amount of players joining the game.
     * @param dimension     the dimension of the square dimensional board.
     */
    public BoardGame(String name, int dimension, int totalPlayer) {
        this.name = name;
        this.dimension = dimension;
        this.totalPlayer = totalPlayer;
        board = new int[dimension][dimension];
    }

    /**
     * Checks if the coordinate exists on the square dimensional board.
     * @param row   index of the row you want to check.
     * @param col   index of the column you want to check.
     * @return      true if the coordinate exists on the board. Otherwise false.
     */
    public boolean isValid(int row, int col) {
        return isValid(row) && isValid(col);
    }

    /**
     * Checks if the index exists on the square dimensional board.
     * @param index the index you want to check.
     * @return      true if the index exists on the board. Otherwise false.
     */
    private boolean isValid(int index) {
        return index >= 0 && index < dimension;
    }

    /**
     * Places a players mark of the current player on the field.
     * So changing the value of the field to the players index.
     * Increasing the {@link #placeCounter} by one if field was empty.
     * This method will <b>not</b> call {@link #nextPlayer()} at all.
     * @param row   the row of field.
     * @param col   the column of the field.
     * @throws      ArrayIndexOutOfBoundsException if row or col is out of the bord's boundary.
     */
    public void place(int row, int col) throws ArrayIndexOutOfBoundsException {
        if (isFree(row, col))
            placeCounter++;
        setState(row, col);
    }

    /**
     * Checks if the field is free, meaning no player currently marks it.
     * @param row   the row of the field.
     * @param col   the col of the field.
     * @return      true if the field is free, false otherwise.
     * @throws      ArrayIndexOutOfBoundsException if row or col is out of the bord's boundary.
     */
    public boolean isFree(int row, int col) throws ArrayIndexOutOfBoundsException {
        return getState(row, col) == 0;
    }

    /**
     * Set the next player. Going in upwards order and repeating.<br>
     * example: if 3 players are in the game the next player order is: 1-2-3-1-2-3-1-2... .
     */
    public void nextPlayer() {
        if (currentPlayer == totalPlayer) {
            currentPlayer = 1;
        } else {
            currentPlayer++;
        }
    }

    /**
     * Check if the current Player has won with his last place.
     * @param row   the row of the place.
     * @param col   the column of the place.
     * @return      true if he has won, false if not.
     */
    public abstract boolean hasWon(int row, int col);

    /**
     * Check if the board is full. Meaning there are no more free fields with the value of 0.
     * In other words, all fields are taken by any players.
     * @return  true if the board is full, false otherwise.
     */
    public boolean isFull() {
        return placeCounter == dimension * dimension;
    }

    /**
     * Returns the current State of this field from the board.
     * @param row   the row of the field.
     * @param col   the column of the field.
     * @return      the current State of this field from the board.
     * @throws      ArrayIndexOutOfBoundsException if row or col is out of the bord's boundary.
     */
    public int getState(int row, int col) throws ArrayIndexOutOfBoundsException {
        return board[row][col];
    }

    /**
     * Sets the state of this field from the board to the current player's index.
     * @param row   the row of the field.
     * @param col   the column of the field.
     * @throws      ArrayIndexOutOfBoundsException if row or col is out of the bord's boundary.
     */
    public void setState(int row, int col) throws ArrayIndexOutOfBoundsException {
        board[row][col] = currentPlayer;
    }

    /**
     * Returns the {@link #name} of the game.
     * @return  {@link #name}.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current player {@link #currentPlayer}.
     * @return  {@link #currentPlayer}.
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns the total amount of player {@link #totalPlayer}.
     * @return  {@link #totalPlayer}.
     */
    public int getTotalPlayer() {
        return totalPlayer;
    }

    /**
     * Returns the dimension of the square dimensional board {@link #dimension}.
     * @return  {@link #dimension}.
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * Returns a string representation of a single state of a field on the board.
     * @param row   the index of the row.
     * @param col   the index of the column.
     * @return      a string representation of a single state of a field on the board.
     */
    public String stateToString(int row, int col) {
        if (!isValid(row, col)) {
            return INDEX_ERROR_MESSAGE;
        } else if (!isFree(row, col)) {
            return "P" + getState(row, col);
        }
        return "**";
    }

    /**
     * Returns a string representation of a single row of the board.
     * @param row   the index of the row.
     * @return      a string representation of a single row of the board.
     */
    public String rowToString(int row) {
        if (!isValid(row)) {
            return INDEX_ERROR_MESSAGE;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dimension; i++) {
            sb.append(stateToString(row, i)).append(" ");
        }
        //remove the last unnecessary white space from the string.
        return sb.toString().substring(0, sb.length() - 1);
    }

    /**
     * Returns a string (horizontal) representation of a single column of the board.
     * @param col   the index of the column.
     * @return      a string representation of a single column of the board.
     */
    public String colToString(int col) {
        if (!isValid(col)) {
            return INDEX_ERROR_MESSAGE;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dimension; i++) {
            sb.append(stateToString(i, col)).append(" ");
        }
        //remove the last unnecessary white space from the string.
        return sb.toString().substring(0, sb.length() - 1);
    }

    /**
     * Returns a string representation from the board.
     * @return  a string representation from the board.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dimension; i++) {
            sb.append(rowToString(i)).append("\n");
        }
        //remove the last unnecessary carriage return from the string.
        return sb.toString().substring(0, sb.length() - 1);
    }
}
