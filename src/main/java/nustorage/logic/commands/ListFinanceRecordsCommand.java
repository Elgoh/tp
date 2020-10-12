package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustorage.model.Model.PREDICATE_REMOVE_ALL_INVENTORY;
import static nustorage.model.Model.PREDICATE_SHOW_ALL_FINANCE;

import javafx.collections.ObservableList;
import nustorage.logic.commands.exceptions.CommandException;
import nustorage.model.Model;
import nustorage.model.record.FinanceRecord;

/**
 * List all finance records in the address book to the user.
 */
public class ListFinanceRecordsCommand extends Command {

    public static final String COMMAND_WORD = "list_finance";

    public static final String MESSAGE_SUCCESS = "Listed all finance records!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredFinanceList(PREDICATE_SHOW_ALL_FINANCE);
        model.updateFilteredInventoryList(PREDICATE_REMOVE_ALL_INVENTORY);
        ObservableList<FinanceRecord> finance = model.getFilteredFinanceList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
