package nustorage.logic.commands;

// import static java.util.Objects.requireNonNull;
import static nustorage.testutil.Assert.assertThrows;

/*import nustorage.model.Inventory;
import nustorage.model.ReadOnlyInventory;
import nustorage.model.record.InventoryRecord;
import nustorage.testutil.stub.ModelStub; */
import org.junit.jupiter.api.Test;

// import java.util.ArrayList;

public class AddInventoryCommandTest {

    @Test
    public void constructor_nullInventory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddInventoryRecordCommand(null, null));
    }

}