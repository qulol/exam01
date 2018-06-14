package edu.kit.informatik.user_interface.system;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.user_interface.callback.CallBack;
import edu.kit.informatik.user_interface.callback.CallBackType;

/**
 * A UserInterface to interact with an instance of a {@link UIRunnable} class by the {@link Terminal}.
 * Meaning you can type in commands in the console to execute proper methods.
 */
public final class UserInterface {

    /**default Constructor.*/
    public UserInterface() { }

    /**
     * Initializes the {@link UIRunnable} instance. If successful it runs the input reading loop.<br>
     * Uses {@link CallBack}, if <i>notnull</i>, for information handling of each iteration.
     * @param instance  the instance you want to run.
     * @param args      the arguments passed to the {{@link UIRunnable#initialize(String...)}} method.
     */
    public void run(UIRunnable instance, String... args) {
        CallBack callBack = instance.initialize(args);
        while (true) {
            if (callBack != null) {
                callBack.process();
                if (callBack.typeOf(CallBackType.ILLEGAL)) {
                    return; // quit loop
                }
            }
            callBack = instance.invoke(Terminal.readLine());
        }
    }
}
