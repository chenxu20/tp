package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLIENT_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLIENT_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.client.Client;

/**
 * A utility class containing a list of {@code Client} objects to be used in tests.
 */
public class TypicalClients {

    public static final Client ALICE = new ClientBuilder().withClientName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .build();
    public static final Client BENSON = new ClientBuilder().withClientName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .build();
    public static final Client CARL = new ClientBuilder().withClientName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Client DANIEL = new ClientBuilder().withClientName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").build();
    public static final Client ELLE = new ClientBuilder().withClientName("Elle Meyer").withPhone("94812224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Client FIONA = new ClientBuilder().withClientName("Fiona Kunz").withPhone("94812427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Client GEORGE = new ClientBuilder().withClientName("George Best").withPhone("94821442")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Client HOON = new ClientBuilder().withClientName("Hoon Meier").withPhone("84821424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Client IDA = new ClientBuilder().withClientName("Ida Mueller").withPhone("84821131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Client's details found in {@code CommandTestUtil}
    public static final Client AMY = new ClientBuilder().withClientName(VALID_CLIENT_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .build();
    public static final Client BOB = new ClientBuilder().withClientName(VALID_CLIENT_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalClients() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical clients.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Client client : getTypicalClients()) {
            ab.addClient(client);
        }
        return ab;
    }

    public static List<Client> getTypicalClients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
