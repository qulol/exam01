package edu.kit.informatik;

import edu.kit.informatik.board_game.BoardGameManager;
import edu.kit.informatik.user_interface.callback.*;
import edu.kit.informatik.user_interface.command.CommandHandler;
import edu.kit.informatik.user_interface.system.UserInterface;

import java.util.regex.Pattern;

public class Main {
    /**
     * <b>Start of Java program.</b><br>
     * Adds {@link CommandHandler} to the {@link BoardGameManager}
     * and runs the manager on the {@link UserInterface}.
     *
     * @param args  The parameter passed in to initialize the manager.
     */
    public static void main(String[] args) {
        BoardGameManager gm = new BoardGameManager();
        //place
        gm.addCommandHandler(new CommandHandler(Pattern.compile("place (-?\\d+);(-?\\d+);(-?\\d+);(-?\\d+)")
                , params -> {
                gm.getGame().place(params[0], params[1]);
                gm.getGame().place(params[2], params[3]);
            return null; //Game handles from here
        }));
        //rowprint
        gm.addCommandHandler(new CommandHandler(Pattern.compile("rowprint (-?\\d+)")
                , params -> new CallBack(gm.getGame().rowToString(params[0]))));
        //colprint
        gm.addCommandHandler(new CommandHandler(Pattern.compile("colprint (-?\\d+)")
                , params -> new CallBack(gm.getGame().colToString(params[0]))));
        //print
        gm.addCommandHandler(new CommandHandler(Pattern.compile("print")
                , params -> new CallBack(gm.getGame().toString())));
        //state
        gm.addCommandHandler(new CommandHandler(Pattern.compile("state (-?\\d+);(-?\\d+)")
                , params -> new CallBack(gm.getGame().stateToString(params[0], params[1]))));
        //reset
        gm.addCommandHandler(new CommandHandler(Pattern.compile("reset")
                , params -> gm.reset()));
        //quit
        gm.addCommandHandler(new CommandHandler(Pattern.compile("quit")
                , params -> new CallBack(CallBackType.ILLEGAL)));

        //run UI
        new UserInterface().run(gm, args);
    }
}
