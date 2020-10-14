package nustorage.storage;


import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import nustorage.commons.core.LogsCenter;
import nustorage.commons.exceptions.DataConversionException;
import nustorage.model.FinanceAccount;
import nustorage.model.ReadOnlyAddressBook;
import nustorage.model.ReadOnlyUserPrefs;
import nustorage.model.UserPrefs;


/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private FinanceAccountStorage financeAccountStorage;


    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }


    /**
     * Creates a {@code StorageManager} with the given {@code FinanceAccountStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FinanceAccountStorage financeAccountStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.userPrefsStorage = userPrefsStorage;
        this.financeAccountStorage = financeAccountStorage;
    }

    // ================ UserPrefs methods ==============================


    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }


    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }


    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================


    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }


    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }


    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }


    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }


    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }


    // ================ FinanceAccount methods ==============================


    @Override
    public Path getFinanceAccountFilePath() {
        return financeAccountStorage.getFinanceAccountFilePath();
    }


    @Override
    public Optional<FinanceAccount> readFinanceAccount() throws DataConversionException, IOException {
        return readFinanceAccount(financeAccountStorage.getFinanceAccountFilePath());
    }


    @Override
    public Optional<FinanceAccount> readFinanceAccount(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read finance account from data file: " + filePath);
        return financeAccountStorage.readFinanceAccount(filePath);
    }


    @Override
    public void saveFinanceAccount(FinanceAccount financeAccount) throws IOException {
        saveFinanceAccount(financeAccount, financeAccountStorage.getFinanceAccountFilePath());
    }


    @Override
    public void saveFinanceAccount(FinanceAccount financeAccount, Path filepath) throws IOException {
        logger.fine("Attempting to write finance account to data file: " + filepath);
        financeAccountStorage.saveFinanceAccount(financeAccount, filepath);
    }


}
