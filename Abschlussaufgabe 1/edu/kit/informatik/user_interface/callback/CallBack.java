package edu.kit.informatik.user_interface.callback;

import edu.kit.informatik.Terminal;

/**
 * A container for information.
 * Used by {@link edu.kit.informatik.user_interface.system.UserInterface}.
 */
public class CallBack {

    /**The full message of this callback.*/
    private String message;
    /**The type of this callback.*/
    private CallBackType type;

    /**
     * Constructor.<br>
     * Sets {@link #type} to {@link CallBackType#IGNORE}.
     */
    public CallBack() {
        this(CallBackType.IGNORE);
    }

    /**
     * Constructor.
     * @param type  {@link #type}.
     */
    public CallBack(CallBackType type) {
        this(null, type);
    }

    /**
     * Constructor.<br>
     * Sets {@link #type} to {@link CallBackType#LEGAL}.
     * @param message   {@link #message}.
     */
    public CallBack(String message) {
        this(message, CallBackType.LEGAL);
    }

    /**
     * Constructor<br>
     * Passing <i>null</i> as {@link #type}, gets converted to {@link CallBackType#IGNORE}.
     * @param message   {@link #message}.
     * @param type      {@link #type}.
     */
    public CallBack(String message, CallBackType type) {
        this.message = message;
        if (type == null) {
            this.type = CallBackType.IGNORE;
        } else {
            this.type = type;
        }
    }

    /**
     * Processes the information.
     */
    public void process() {
        if (!typeOf(CallBackType.IGNORE)) {
            print();
        }
    }

    /**
     * Prints the {@link #message} to the Console.
     */
    public void print() {
        if (message != null) {
            Terminal.printLine(message);
        }
    }

    /**
     * Returns the {@link #message}.
     * @return  the {@link #message}.
     */
    String getMessage() {
        return message;
    }

    /**
     * Compares the CallBackType of this instance against the other.
     * @param other the other CallBackType
     * @return      true if they are the same, false if not.
     */
    public boolean typeOf(CallBackType other) {
        return this.type == other;
    }
}
