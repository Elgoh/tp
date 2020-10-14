package nustorage.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import nustorage.commons.core.index.Index;
import nustorage.model.record.FinanceRecord;
import nustorage.model.record.FinanceRecordList;

public class FinanceAccount implements ReadOnlyFinanceAccount {

    private final FinanceRecordList financeRecords;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        financeRecords = new FinanceRecordList();
    }

    public FinanceAccount() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public FinanceAccount(ReadOnlyFinanceAccount toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setFinanceRecords(List<FinanceRecord> financeRecords) {
        this.financeRecords.setFinanceRecords(financeRecords);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyFinanceAccount newData) {
        requireNonNull(newData);

        setFinanceRecords(newData.getFinanceList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasFinanceRecord(FinanceRecord financeRecord) {
        requireNonNull(financeRecord);
        return financeRecords.contains(financeRecord);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addFinanceRecord(FinanceRecord financeRecord) {
        financeRecords.add(financeRecord);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setFinanceRecord(FinanceRecord target, FinanceRecord editedRecord) {
        requireNonNull(editedRecord);

        financeRecords.setFinanceRecord(target, editedRecord);
    }

    /**
     * Removes the finance record with the corresponding index
     *
     * @param targetIndex Index of finance record to be removed
     * @return Optional containing removed finance record if index is valid, else an empty optional
     */
    public Optional<FinanceRecord> removeFinanceRecord(Index targetIndex) {
        return financeRecords.remove(targetIndex);
    }

    //// util methods

    @Override
    public String toString() {
        return financeRecords.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<FinanceRecord> getFinanceList() {
        return financeRecords.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FinanceAccount // instanceof handles nulls
                && financeRecords.equals(((FinanceAccount) other).financeRecords));
    }

    @Override
    public int hashCode() {
        return financeRecords.hashCode();
    }
}
