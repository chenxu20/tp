package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Lists all events in the address book to the user.
 */
public class ListEventCommand extends ListCommand<Event> {
    public static final String COMMAND_WORD = "list_event";
    public static final String MESSAGE_SUCCESS = "Listed all events";

    private static final Logger logger = LogsCenter.getLogger(ListEventCommand.class);
    /**
     * Executes the {@code ListEventCommand} and shows all events in the list, removing the find filter (if any).
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        logger.info("Executing ListEventCommand");
        model.updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        logger.info("Listed all events");
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
