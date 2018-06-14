package edu.kit.informatik.user_interface.system;

import edu.kit.informatik.user_interface.callback.CallBack;
import edu.kit.informatik.user_interface.callback.ErrorCallBack;
import edu.kit.informatik.user_interface.command.CommandHandler;
import java.util.ArrayList;

/**
 * This class is runnable by the {@link UserInterface}.
 * Use the {@link #addCommandHandler(CommandHandler)} method to add new input handler.
 * The {@link #invoke(String)} method is called automatically after each input reading
 * from the {@link UserInterface}.
 */
public abstract class UIRunnable implements Initializable {

    private ArrayList<CommandHandler> commandHandlers;

    /**Constructor.*/
    public UIRunnable() {
        commandHandlers = new ArrayList<>();
    }

    /**
     * Iterate through the {@link #commandHandlers} list
     * and calls {@link CommandHandler#invoke()} when it's pattern matches the command.
     * @param command   the command as a string value.
     * @return          The callback of the called method, or an error callback
     *                  if input was not matching by any command handler.
     */
    public CallBack invoke(String command) {
        for (CommandHandler ch : commandHandlers) {
            if (ch.matches(command))
                return ch.invoke();
        }
        //no matches
        return new ErrorCallBack("unknown input.");
    }

    /**
     * Adds a new {@link CommandHandler} to the list.
     * @param c the {@link CommandHandler} you want to add.
     */
    public void addCommandHandler(CommandHandler c) {
        commandHandlers.add(c);
    }
}
