package edu.kit.informatik.user_interface.system;

import edu.kit.informatik.user_interface.callback.CallBack;

public interface Initializable {

    /**
     * This method represents an initializing process.
     * In most cases this is used like a constructor.
     * Further this {@link CallBack} return is used for
     * information handling during this process.
     * @param args  the arguments to initialize the class
     *              which uses this interface.
     * @return      an informational return to the {@link UserInterface}.
     */
    CallBack initialize(String... args);
}
