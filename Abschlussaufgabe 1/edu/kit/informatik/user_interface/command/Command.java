package edu.kit.informatik.user_interface.command;

import edu.kit.informatik.user_interface.callback.CallBack;

@FunctionalInterface
public interface Command {

    /**
     * This method is fired by a {@link CommandHandler}
     * whenever an input matches his match pattern.
     * @param params    the parameters passed as an int array to the called method.
     * @return          a callback of information.
     */
    CallBack invoke(int... params);
}
