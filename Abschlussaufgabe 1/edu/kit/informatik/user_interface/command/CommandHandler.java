package edu.kit.informatik.user_interface.command;

import edu.kit.informatik.user_interface.callback.ErrorCallBack;
import edu.kit.informatik.user_interface.callback.CallBack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This Class is used for specify different input commands by the User Interface.
 * If the input matches the given pattern, this class fires the {@link Command#invoke(int...)}
 * method of the referenced {@link #command}.
 * <b>Pattern Syntax:</b><br>
 * Each pattern group will get parsed as a parameter to the int array
 * this gets passed through to the command.<br> So all parameters needs to be parsable to an Integer.
 * You may not use a multiplier for multiply whole parameter because they will get overridden.
 * For using this intuitive, separator tokens, if any, and the method's name itself should not be grouped.<br>
 *
 * <b>Example:</b><br>
 * without parameter:
 *     <pre>{@code
 *         Pattern.compile("methodName")
 *     }</pre>
 * and with parameter:
 *     <pre>{@code
 *         Pattern.compile("methodName (arg0pattern);(arg1pattern)")
 *     }</pre>
 * <br>
 */
public class CommandHandler {
    private static final String PARSE_ERROR_MSG
            = ". Expected number from: " + Integer.MIN_VALUE + " to " + Integer.MAX_VALUE;

    private Matcher matcher;

    /**The pattern for a valid command.*/
    private Pattern pattern;

    /**The command which gets fired by {@link #invoke()}.*/
    private Command command;

    /**
     * Constructor.
     * @param pattern   {@link #pattern}
     * @param command   {@link #command}
     */
    public CommandHandler(Pattern pattern, Command command) {
        this.pattern = pattern;
        this.command = command;
    }

    /**
     * Checks if the input string matches the pattern.
     * @param input the input command as a String.
     * @return      true if the input string matches the {@link #pattern}, false otherwise.
     */
    public boolean matches(String input) {
        matcher = pattern.matcher(input);
        return matcher.matches();
    }

    /**
     * Fires the invoke method from the referenced {@link #command} with parsed parameter.<br>
     * Each pattern group gets interpreted as a parameter.
     * @return  the callback returned by the {@link #command} invoke method
     * @throws  NullPointerException if {@link #matches(String)} was not returning <i>true</i> once before.
     */
    public CallBack invoke() throws NullPointerException {
        int argCounter = matcher.groupCount();
        int[] params = new int[argCounter];
        try {
            for (int i = 0; i < argCounter; i++) {
                //cuts out the first group which is the whole string by default.
                params[i] = Integer.parseInt(matcher.group(i + 1));
            }
        } catch (NumberFormatException e) {
            return new ErrorCallBack(e.getMessage().toLowerCase() + PARSE_ERROR_MSG);
        }
        return command.invoke(params);
    }
}
