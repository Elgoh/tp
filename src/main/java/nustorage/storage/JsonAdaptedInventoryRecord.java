package nustorage.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import nustorage.commons.exceptions.IllegalValueException;
import nustorage.model.record.InventoryRecord;


class JsonAdaptedInventoryRecord {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Inventory record's %s field is missing!";

    private final int quantity;
    private final String itemName;
    private final LocalDateTime dateTime;


    /**
     * Constructs a {@code JsonAdaptedInventoryRecord} with the given record details.
     */
    public JsonAdaptedInventoryRecord(@JsonProperty("itemName") String itemName,
                                      @JsonProperty("quantity") int quantity,
                                      @JsonProperty("dateTime") LocalDateTime dateTime) {
        this.quantity = quantity;
        this.itemName = itemName;
        this.dateTime = dateTime;
    }


    /**
     * Converts a given {@code InventoryRecord} into this class for Jackson use.
     */
    @JsonCreator
    public JsonAdaptedInventoryRecord(InventoryRecord source) {
        quantity = source.getQuantity();
        itemName = source.getItemName();
        dateTime = source.getDateTime();
    }


    /**
     * Converts this Jackson-friendly adapted inventory record object into the model's {@code InventoryRecord} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public InventoryRecord toModelType() throws IllegalValueException {
        if (this.itemName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "item name"));
        }
        String modelItemName = this.itemName;
        if (this.quantity < 0) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "quantity"));
        }
        final int modelQuantity = this.quantity;

        if (this.dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "dateTime"));
        }
        final LocalDateTime modelDateTime = this.dateTime;

        return new InventoryRecord(modelItemName, modelQuantity, modelDateTime);

    }

}
