package seedu.address.storage;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.ClientName;
import seedu.address.model.commons.Address;
import seedu.address.model.commons.Price;
import seedu.address.model.property.Description;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyName;
import seedu.address.model.property.Size;

/**
 * Jackson-friendly version of {@link Property}.
 */
public class JsonAdaptedProperty {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Property's %s field is missing!";

    private final String propertyName;
    private final String address;
    private final Long price;
    private final String size;
    private final String description;
    private final String owner;

    /**
     * Constructs a {@code JsonAdaptedProperty} with the given property details.
     */
    @JsonCreator
    public JsonAdaptedProperty(@JsonProperty("propertyName") String propertyName,
                               @JsonProperty("address") String address,
                               @JsonProperty("price") Long price, @JsonProperty("size") String size,
                               @JsonProperty("description") String description,
                               @JsonProperty("owner") String owner) {
        this.propertyName = propertyName;
        this.address = address;
        this.price = price;
        this.size = size;
        this.description = description;
        this.owner = owner;
    }

    /**
     * Converts a given {@code Property} into this class for Jackson use.
     */
    public JsonAdaptedProperty(Property source) {
        propertyName = source.getFullName().fullName;
        address = source.getAddress().value;
        price = source.getPrice().value;
        size = source.getSize().map(s -> s.value).orElse(null);
        description = source.getDescription().map(d -> d.description).orElse(null);
        owner = source.getOwner().fullName;
    }

    /**
     * Converts this Jackson-friendly adapted property object into the model's {@code Property} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted property.
     */
    public Property toModelType() throws IllegalValueException {
        if (propertyName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PropertyName.class.getSimpleName()));
        }
        if (!PropertyName.isValidPropertyName(propertyName)) {
            throw new IllegalValueException(PropertyName.MESSAGE_CONSTRAINTS);
        }
        final PropertyName modelPropertyName = new PropertyName(propertyName);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (price == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName()));
        }
        if (!Price.isValidPrice(price)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }
        final Price modelPrice = new Price(price);

        final Optional<Size> modelSize = (size == null || size.isEmpty())
                ? Optional.empty() : Optional.of(new Size(size));

        final Optional<Description> modelDescription = (description == null || description.isEmpty())
                ? Optional.empty() : Optional.of(new Description(description));

        if (owner == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ClientName.class.getSimpleName()));
        }
        if (!ClientName.isValidClientName(owner)) {
            throw new IllegalValueException(ClientName.MESSAGE_CONSTRAINTS);
        }
        final ClientName modelClientName = new ClientName(owner);

        return new Property(modelPropertyName, modelAddress, modelPrice, modelSize, modelDescription, modelClientName);
    }
}
