package seedu.address.logic.parser.deal;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BUYER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SELLER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.stream.Stream;

import seedu.address.logic.commands.deal.AddDealCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.ClientName;
import seedu.address.model.commons.Price;
import seedu.address.model.deal.DealStatus;
import seedu.address.model.property.PropertyName;

/**
 * Parses input arguments and creates a new AddDealCommand object
 */
public class AddDealCommandParser implements Parser<AddDealCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddDealCommand
     * and returns an AddDealCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddDealCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PROPERTY_NAME , PREFIX_BUYER, PREFIX_SELLER,
                        PREFIX_PRICE, PREFIX_STATUS);

        if (!arePrefixesPresent(argMultimap, PREFIX_PROPERTY_NAME, PREFIX_BUYER, PREFIX_SELLER, PREFIX_PRICE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDealCommand.MESSAGE_USAGE));
        }

        // Parse property name
        String propertyNameStr = argMultimap.getValue(PREFIX_PROPERTY_NAME).get();
        if (!PropertyName.isValidPropertyName(propertyNameStr)) {
            throw new ParseException(PropertyName.MESSAGE_CONSTRAINTS);
        }
        PropertyName propertyName = new PropertyName(propertyNameStr);

        // Parse buyer name
        String buyerNameStr = argMultimap.getValue(PREFIX_BUYER).get();
        if (!ClientName.isValidClientName(buyerNameStr)) {
            throw new ParseException(ClientName.MESSAGE_CONSTRAINTS);
        }
        ClientName buyer = new ClientName(buyerNameStr);

        // Parse seller name
        String sellerNameStr = argMultimap.getValue(PREFIX_SELLER).get();
        if (!ClientName.isValidClientName(sellerNameStr)) {
            throw new ParseException(ClientName.MESSAGE_CONSTRAINTS);
        }
        ClientName seller = new ClientName(sellerNameStr);

        // Parse price
        String priceString = argMultimap.getValue(PREFIX_PRICE).get();
        if (!Price.isValidPrice(priceString)) {
            throw new ParseException(Price.MESSAGE_CONSTRAINTS);
        }

        Long priceValue = Long.parseLong(priceString);
        Price price = new Price(priceValue);

        // Parse status (optional)
        DealStatus status = DealStatus.PENDING; // Default status
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            String statusString = argMultimap.getValue(PREFIX_STATUS).get().toUpperCase();
            try {
                // Convert to the format expected by the enum (e.g., "in_negotiation" to "IN_NEGOTIATION")
                if (statusString.equals("IN NEGOTIATION")) {
                    statusString = "IN_NEGOTIATION";
                }
                status = DealStatus.valueOf(statusString);
            } catch (IllegalArgumentException e) {
                throw new ParseException("Invalid status: Must be one of 'PENDING', 'CLOSED', 'IN_NEGOTIATION'.");
            }
        }

        return new AddDealCommand(propertyName, buyer, seller, price, status);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
