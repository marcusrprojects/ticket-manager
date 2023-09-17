/**
 *
 */
package test.java.edu.bowdoin.csci.TicketManager.model.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.edu.bowdoin.csci.TicketManager.model.command.Command;
import main.java.edu.bowdoin.csci.TicketManager.model.command.Command.CancellationCode;
import main.java.edu.bowdoin.csci.TicketManager.model.command.Command.CommandValue;
import main.java.edu.bowdoin.csci.TicketManager.model.command.Command.FeedbackCode;
import main.java.edu.bowdoin.csci.TicketManager.model.command.Command.ResolutionCode;

/**
 * Tests that Commands are processed and executed correctly.
 * @author mribeiro
 */
public class CommandTest {

    /**
     * create command instance variable to be used throughout the class
     */
    private Command command;

    /**
     * arbitrary owner id
     */
    private static String OWNER_ID = "Marcus";

    /**
     * arbitrary note
     */
    private static String NOTE = "Printer exploded.";

    /**
     * arbitrary command value
     */
    private static CommandValue VALUE = CommandValue.PROCESS;

    /**
     * arbitrary feedback code
     */
    private static FeedbackCode FEEDBACK_CODE = FeedbackCode.AWAITING_CALLER;

    /**
     * arbitrary resolution code
     */
    private static ResolutionCode RESOLUTION_CODE = ResolutionCode.NOT_COMPLETED;

    /**
     * arbitrary cancellation code
     */
    private static CancellationCode CANCELLATION_CODE = CancellationCode.DUPLICATE;


    /**
     * Sets up the test.
     *
     * @throws java.lang.Exception throw for exceptions that should be caught
     */
    @BeforeEach
    public void setUp() throws Exception {
        command = new Command(VALUE, OWNER_ID, FEEDBACK_CODE, RESOLUTION_CODE, CANCELLATION_CODE, NOTE);
    }

    /**
     * Tests to ensure that getCommand does not return null
     */
    @Test
    public void testCommandValue() {

        Assertions.assertEquals(CommandValue.PROCESS, command.getCommand(), "Command.getCommand() should match the instance variable");
    }

    /**
     * If there is a CV Process, OWNER_ID should not be null or an empty string.
     */
    @Test
    public void testOwnerID() {

        Assertions.assertEquals("Marcus", command.getOwnerId(), "Command.getOwnerId() should match the instance variable string");

        Assertions.assertThrows(IllegalArgumentException.class, () -> command = new Command(null, OWNER_ID, FEEDBACK_CODE, RESOLUTION_CODE, CANCELLATION_CODE, NOTE), "Command constructor should not take in a null value");

    }

    /**
     * Tests possible command getters to ensure that they return the proper codes
     */
    @Test
    public void testCommandCodes() {

        //CV FEEDBACK and null FC
        Assertions.assertEquals(FeedbackCode.AWAITING_CALLER, command.getFeedbackCode(), "Command.getFeedbackCode() should return a feedback code string");

        command = new Command(CommandValue.RESOLVE, OWNER_ID, null, RESOLUTION_CODE, null, NOTE);
        // RESOLVE and null RC
        Assertions.assertEquals(ResolutionCode.NOT_COMPLETED, command.getResolutionCode(), "Command.getResolutionCode() should return a resolution code string");

        command = new Command(CommandValue.CANCEL, OWNER_ID, null, null, CANCELLATION_CODE, NOTE);
        // CANCEL and null CC
        Assertions.assertNotNull(command.getCancellationCode(), "Command.getCancellationCode() should return a cancellation code string");

        Assertions.assertThrows(IllegalArgumentException.class, () -> command = new Command(CommandValue.PROCESS, null, FEEDBACK_CODE, RESOLUTION_CODE, CANCELLATION_CODE, NOTE), "Command constructor should not take in a null owner_id when in PROCESS state");

        Assertions.assertThrows(IllegalArgumentException.class, () -> command = new Command(CommandValue.PROCESS, "", FEEDBACK_CODE, RESOLUTION_CODE, CANCELLATION_CODE, NOTE), "Command constructor should not take in an empty owner_id value when in PROCESS state");

        Assertions.assertThrows(IllegalArgumentException.class, () -> command = new Command(CommandValue.FEEDBACK, OWNER_ID, null, RESOLUTION_CODE, CANCELLATION_CODE, NOTE), "Command constructor should not take in a null feedback_code value when in FEEDBACK state");

        Assertions.assertThrows(IllegalArgumentException.class, () -> command = new Command(CommandValue.RESOLVE, OWNER_ID, FEEDBACK_CODE, null, CANCELLATION_CODE, NOTE), "Command constructor should not take in a null resolution_code value when in RESOLVE state");

        Assertions.assertThrows(IllegalArgumentException.class, () -> command = new Command(CommandValue.CANCEL, OWNER_ID, FEEDBACK_CODE, RESOLUTION_CODE, null, NOTE), "Command constructor should not take in a null cancelation_code value when in CANCEL state");
    }

    /**
     * Tests Command Notes by seeing if getNotes returns the inputed note
     */
    @Test
    public void testCommandNotes() {

        // null note
        Assertions.assertThrows(IllegalArgumentException.class, () -> command = new Command(VALUE, OWNER_ID, FEEDBACK_CODE, RESOLUTION_CODE, CANCELLATION_CODE, null));

        // empty note
        Assertions.assertThrows(IllegalArgumentException.class, () -> command = new Command(VALUE, OWNER_ID, FEEDBACK_CODE, RESOLUTION_CODE, CANCELLATION_CODE, ""));

        Assertions.assertNotNull(command.getNote(), "Command.getNote() should not be null");
    }
}
