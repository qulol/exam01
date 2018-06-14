package edu.kit.informatik.user_interface.callback;

import edu.kit.informatik.Terminal;

public class ErrorCallBack extends CallBack {

    /**
     * Constructor.<br>
     * Sets {@link #type} to {@link CallBackType#LEGAL}.
     * @param message   {@link #message}.
     */
    public ErrorCallBack(String message) {
        this(message, CallBackType.LEGAL);
    }

    /**
     * Constructor.
     * @param message   {@link #message}.
     * @param type      {@link #type}.
     */
    public ErrorCallBack(String message, CallBackType type) {
        super(message, type);
    }

    /**Prints an error message*/
    @Override
    public void print() {
        if (getMessage() != null) {
            Terminal.printError(getMessage());
        }
    }
}
