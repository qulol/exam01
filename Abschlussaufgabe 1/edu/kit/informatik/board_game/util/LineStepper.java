package edu.kit.informatik.board_game.util;

/**
 * Can traverse in a straight line over the board.
 * Used for {@link edu.kit.informatik.board_game.connect6.Connect6#hasWon(int, int)}
 */
public class LineStepper {
    /**A counter for total steps made*/
    private int stepCounter;

    private int xPos;
    private int yPos;
    private int stepX;
    private int stepY;

    private int posXBuffer;
    private int posYBuffer;

    /**
     * Constructor.
     * @param posY  the y coordinate of this Stepper
     * @param posX  the x coordinate of this Stepper
     * @param stepY the y step value
     * @param stepX the x step value
     */
    public LineStepper(int posY, int posX, int stepY, int stepX) {
        this.xPos = posX;
        this.yPos = posY;
        this.stepX = stepX;
        this.stepY = stepY;
        posXBuffer = posX;
        posYBuffer = posY;
        stepCounter = 0;
    }

    /**Steps one time*/
    public void step() {
        xPos += stepX;
        yPos += stepY;
        stepCounter++;
    }

    /**Turns the Stepper around.*/
    public void turnAround() {
        stepX = -stepX;
        stepY = -stepY;
    }

    /**
     * Sets the Stepper to his starting position
     * and decrements the {@link #stepCounter}.
     */
    public void reset() {
        xPos = posXBuffer;
        yPos = posYBuffer;
        stepCounter--;
    }

    /**
     * Returns the x coordinate of the Stepper.
     * @return the x coordinate of the Stepper.
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * Returns the y coordinate of the Stepper.
     * @return the y coordinate of the Stepper.
     */
    public int getYPos() {
        return yPos;
    }

    /**
     * Returns the {@link #stepCounter}.
     * @return the {@link #stepCounter}.
     */
    public int getStepCounter() {
        return stepCounter;
    }
}
