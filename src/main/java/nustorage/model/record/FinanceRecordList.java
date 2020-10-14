package nustorage.model.record;

import static java.util.Objects.requireNonNull;
import static nustorage.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nustorage.commons.core.index.Index;
import nustorage.model.person.exceptions.PersonNotFoundException;

public class FinanceRecordList implements Iterable<FinanceRecord> {

    private final ObservableList<FinanceRecord> internalList = FXCollections.observableArrayList();
    private final ObservableList<FinanceRecord> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(FinanceRecord toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(FinanceRecord toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setFinanceRecord(FinanceRecord target, FinanceRecord editedRecord) {
        requireAllNonNull(target, editedRecord);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        internalList.set(index, editedRecord);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public Optional<FinanceRecord> remove(Index targetIndex) {
        requireNonNull(targetIndex);
        if (targetIndex.getZeroBased() >= internalList.size()) {
            return Optional.empty();
        }
        return Optional.of(internalList.remove(targetIndex.getZeroBased()));
    }

    public void setFinanceRecords(FinanceRecordList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setFinanceRecords(List<FinanceRecord> financeRecords) {
        requireAllNonNull(financeRecords);
        internalList.setAll(financeRecords);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<FinanceRecord> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<FinanceRecord> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FinanceRecordList // instanceof handles nulls
                && internalList.equals(((FinanceRecordList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
