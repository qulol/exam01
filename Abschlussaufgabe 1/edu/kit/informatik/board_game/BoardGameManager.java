package edu.kit.informatik.board_game;

import edu.kit.informatik.board_game.connect6.*;
import edu.kit.informatik.user_interface.callback.*;
import edu.kit.informatik.user_interface.system.UIRunnable;

/**
 * This Class handles the current game and the game input.
 */
public class BoardGameManager extends UIRunnable {

    /**The reference to the currently managed board game.*/
    private BoardGame game;

    /**Constructor.*/
    public BoardGameManager() {
        super();
    }

    /**
     * Initializes the Manager.<br>
     * If the arguments which passed in are valid, the manager creates a new game of the specified type.
     * If not, the manager quits the entire program.
     * @param args  the arguments to initialize the class
     *              which uses this interface.
     * @return      CallBack of information.
     * @see         UIRunnable#initialize(String...)
     */
    @Override
    public CallBack initialize(String... args) {
        if (args == null || args.length != 3) {
            return new ErrorCallBack("wrong number of arguments. Expected: 3", CallBackType.ILLEGAL);
        }
        String input = args[0] + " " + args[1] + " " + args[2];
        if (input.matches("(standard|torus) (18|20) [2-4]")) {
            initializeGame(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        } else {
            return new ErrorCallBack("unknown input. Expected: standard|torus 18|20 2-4"
                    , CallBackType.ILLEGAL);
        }
        return null; //everything fine
    }

    /**
     * Initializes a new Game and stores it in {@link #game}.
     * @param name              {@link BoardGame#name}
     * @param boardDimension    {@link BoardGame#dimension}
     * @param totalPlayer       {@link BoardGame#totalPlayer}
     */
    private void initializeGame(String name, int boardDimension, int totalPlayer) {
        if (name.equals("standard")) {
            game = new Connect6(name, boardDimension, totalPlayer);
        } else { //name.equals("torus")
            game = new Connect6Torus(name, boardDimension, totalPlayer);
        }
    }

    /**
     * Resets the current {@link #game}.
     * @return  information as a string. Or OK, to keep things simple.
     */
    public CallBack reset() {
        initializeGame(game.getName(), game.getDimension(), game.getTotalPlayer());
        return new CallBack("OK");
    }

    /**
     * Returns the current {@link #game}
     * @return  the current board game
     */
    public BoardGame getGame() {
        return game;
    }
}
