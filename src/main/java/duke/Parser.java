package duke;

import duke.task.Task;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidTaskNumberException;
import duke.exceptions.MissingTaskNumberException;

import java.util.List;

/**
 * <code>Parser</code> class deals with making sense of the user command.
 */
public class Parser {
    protected static String task;
    protected String userInput;

    /**
     * Constructor for Parser class.
     * @param userInput User input in the command line.
     */
    public Parser(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Parse the String user input into an array of Strings.
     * @return An array of Strings of the parsed user input.
     */
    public String[] parseUserInput() {
        return userInput.split(" ");
    }

    /**
     * Retrieve the user's task action by accessing the array of
     * parsed user input Strings.
     * @return User command task action.
     */
    public String getUserAction() {
        return userInput.split(" ")[0];
    }

    /**
     * Check the user input and ensures that it makes sense.
     * @param list List of existing tasks.
     * @throws MissingTaskNumberException If there is no task number for done or delete actions.
     * @throws InvalidTaskNumberException If the task number is outside the list range.
     * @throws EmptyDescriptionException If there is no description for the task action.
     */
    public void checkUserInput(List<Task> list)
            throws MissingTaskNumberException, InvalidTaskNumberException
            , EmptyDescriptionException {
        String[] userInputArr = userInput.split(" ");
        String task = userInputArr[0];

        switch(task) {
        case "todo":
        case "deadline":
        case "event":
            if (userInputArr.length == 1) {
                throw new EmptyDescriptionException(task);
            }
            break;

        case "done":
        case "delete":
            if (userInputArr.length == 1) {
                throw new MissingTaskNumberException();
            }

            int taskNumber = Integer.parseInt(userInputArr[1]) - 1;
            if (taskNumber < 0 || taskNumber > list.size() - 1) {
                throw new InvalidTaskNumberException();
            }
            break;
        }
    }
}
