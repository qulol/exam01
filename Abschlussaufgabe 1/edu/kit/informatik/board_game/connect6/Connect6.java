package edu.kit.informatik.board_game.connect6;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.board_game.BoardGame;
import edu.kit.informatik.board_game.util.LineStepper;

public class Connect6 extends BoardGame {
    /**The minimum of marks in a row needed to win a game = {@value}*/
    private static final int WIN_LINE_MIN = 6;

    private int rowBuffer;
    private int colBuffer;
    private boolean writeBuffer = true;

    private boolean gameOver;

    /**
     * Constructor.
     * Initialize a new Connect6:standard game with the given parameter. The current player is player 1.
     *
     * @param name        the unique identifier name of the game.
     * @param dimension   the dimension of the square dimensional board.
     * @param totalPlayer the total amount of players joining the game.
     */
    public Connect6(String name, int dimension, int totalPlayer) {
        super(name, dimension, totalPlayer);
    }

    /**
     * Makes a Connect6 place of a player.
     * Means this method gets called twice in a row for each player.
     * First Call stores data in Buffer.
     * Second makes the double {@link BoardGame#place(int, int)} if valid input.
     * @param row   the row of field.
     * @param col   the column of the field.
     */
    @Override
    public void place(int row, int col) {
        if (writeBuffer) {
            rowBuffer = row;
            colBuffer = col;
        } else { //do double place
            if (!(row == rowBuffer && col == colBuffer)
                    && isValid(rowBuffer, colBuffer)
                    && isValid(row, col)
                    && isFree(rowBuffer, colBuffer)
                    && isFree(row, col)
                    && !isGameOver()) {
                super.place(rowBuffer, colBuffer);                                      //if
                super.place(row, col);
                if (hasWon(rowBuffer, colBuffer) || hasWon(row, col)) {
                    Terminal.printLine("P" + getCurrentPlayer() + " wins");
                    setGameOver();
                } else if (isFull()) {
                    Terminal.printLine("draw");
                    setGameOver();
                } else {
                    nextPlayer();
                    Terminal.printLine("OK");
                }                                                                       //endif
            } else {
                Terminal.printError("place denied.");
            }
        }
        writeBuffer = !writeBuffer;
    }

    /**
     * Check if the current Player has won with his last place.
     * @param row   the row of the place.
     * @param col   the column of the place.
     * @return      true if he has won, false if not.
     */
    @Override
    public boolean hasWon(int row, int col) {
        return checkLine(new LineStepper(row, col, 0, 1))           //check horizontal
                || checkLine(new LineStepper(row, col, 1, 0))       //check vertical
                || checkLine(new LineStepper(row, col, 1, 1))       //check diagonal
                || checkLine(new LineStepper(row, col, -1, 1));     //check diagonal
    }

    /**
     * Checks if the placed field is surrounded by a line of equal field states.
     * In other words this method checks if {@link #currentPlayer} has a winning streak
     * of minimum {@link #WIN_LINE_MIN} in a certain line.
     * @return      true if matches, false otherwise.
     */
    private boolean checkLine(LineStepper stepper) {
        while (isValid(stepper.getYPos(), stepper.getXPos())                                    //check one way
                && getState(stepper.getYPos(), stepper.getXPos()) == getCurrentPlayer()) {
            stepper.step();
        }
        stepper.reset();
        stepper.turnAround();
        while (isValid(stepper.getYPos(), stepper.getXPos())                                    //check opposite way
                && getState(stepper.getYPos(), stepper.getXPos()) == getCurrentPlayer()) {
            stepper.step();
        }
        return stepper.getStepCounter() >= WIN_LINE_MIN;
    }

    /**
     * Check if the game is over.
     * @return  true if the game is over, false otherwise.
     */
    private boolean isGameOver() {
        return gameOver;
    }

    /**
     * Sets {@link #gameOver}.
     */
    private void setGameOver() {
        gameOver = true;
    }
}
