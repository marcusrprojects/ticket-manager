/**
 *
 */
package test.java.edu.bowdoin.csci.TicketManager.model.io;

import main.java.edu.bowdoin.csci.TicketManager.model.ticket.Ticket;
import main.java.edu.bowdoin.csci.TicketManager.model.io.TicketReader;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Ensures that TicketReader reads files properly and if not,
 * it throws appropriate exceptions.
 *
 * @author mribeiro
 *
 */
public class TicketReaderTest {

    /**
     * ticket list
     */
    private ArrayList<Ticket> ticketList;

    /**
     * ticket file 1
     */
    private String filename1 = "test-files/ticket1.txt";

    /**
     * ticket file 2
     */
    private String filename2 = "test-files/ticket2.txt";

    /**
     * ticket file 3
     */
    private String filename3 = "test-files/ticket3.txt";

    /**
     * ticket file 4
     */
    private String filename4 = "test-files/ticket4.txt";

    /**
     * ticket file 5
     */
    private String filename5 = "test-files/ticket5.txt";

    /**
     * ticket file 6
     */
    private String filename6 = "test-files/ticket6.txt";

    /**
     * ticket file 7
     */
    private String filename7 = "test-files/ticket7.txt";

    /**
     * ticket file 8
     */
    private String filename8 = "test-files/ticket8.txt";

    /**
     * ticket file 9
     */
    private String filename9 = "test-files/ticket9.txt";

    /**
     * ticket file 10
     */
    private String filename10 = "test-files/ticket10.txt";

    /**
     * ticket file 11
     */
    private String filename11 = "test-files/ticket11.txt";

    /**
     * ticket file 12
     */
    private String filename12 = "test-files/ticket12.txt";

    /**
     * ticket file 13
     */
    private String filename13 = "test-files/ticket13.txt";

    /**
     * ticket file 14
     */
    private String filename14 = "test-files/ticket14.txt";

    /**
     * ticket file 15
     */
    private String filename15 = "test-files/ticket15.txt";

    /**
     * ticket file 16
     */
    private String filename16 = "test-files/ticket16.txt";

    /**
     * ticket file 17
     */
    private String filename17 = "test-files/ticket17.txt";

    /**
     * ticket file 18
     */
    private String filename18 = "test-files/ticket18.txt";

    /**
     * ticket file 19
     */
    private String filename19 = "test-files/ticket19.txt";

    /**
     * ticket file 20
     */
    private String filename20 = "test-files/ticket20.txt";

    /**
     * Sets up the test.
     *
     * @throws java.lang.Exception java exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        Ticket.setCounter(1);
        ticketList = new ArrayList<>();
    }

    /**
     * Tests that Ticket 1 is read correctly, since it is a valid file.
     */
    @Test
    public void testTicket1() {
        Assertions.assertNotNull(TicketReader.readTicketFile(filename1));
    }

    /**
     * Ticket 2 should work just fine and so this function will test to ensure that
     * it reads the file properly, storing the correct values in the tickets it makes.
     */
    @Test
    public void testTicket2() {

        ticketList = TicketReader.readTicketFile(filename2);

        Assertions.assertEquals(3, ticketList.size(), "The size of ticket list 2 should be 3 since there are 3 tickets in this list.");

        Assertions.assertEquals(3, ticketList.get(0).getTicketId(), "The id should be 3.");

        Assertions.assertEquals("Closed", ticketList.get(0).getState(), "The state should be closed.");

        Assertions.assertEquals("Request", ticketList.get(0).getTicketTypeString(), "The caller should be caller.");

        Assertions.assertEquals("Subject line", ticketList.get(0).getSubject(), "The id should be 3.");

        Assertions.assertEquals("caller", ticketList.get(0).getCaller(), "The caller should be caller.");

        Assertions.assertEquals("Inquiry", ticketList.get(0).getCategory(), "The category should be Inquiry.");

        Assertions.assertEquals("Medium", ticketList.get(0).getPriority(), "The priority should be medium.");

        Assertions.assertEquals("owner", ticketList.get(0).getOwner(), "The owner should be owner.");

        Assertions.assertEquals("Not Completed", ticketList.get(0).getResolutionCode(), "The caller should be caller.");
    }

    /**
     * Reads tickets 3-20 and tries a ticket not in the system. Expects IAE errors.
     */
    @Test
    public void testReadErrorTickets() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> TicketReader.readTicketFile("does-not-exist.txt"), "Missing file - is NOT in the test directory b/c it's missing");

        Assertions.assertThrows(IllegalArgumentException.class, () -> TicketReader.readTicketFile(filename3), "File 3 cannot be loaded since it has no notes");

        Assertions.assertThrows(IllegalArgumentException.class, () -> TicketReader.readTicketFile(filename4), "File 4 cannot be loaded since it has no id");

        Assertions.assertThrows(IllegalArgumentException.class, () -> TicketReader.readTicketFile(filename5), "File 5 cannot be loaded since it has no state");

        Assertions.assertThrows(IllegalArgumentException.class, () -> TicketReader.readTicketFile(filename6), "File 6 cannot be loaded since it has a non-supported state");

        Assertions.assertThrows(IllegalArgumentException.class, () -> TicketReader.readTicketFile(filename7), "File 7 cannot be loaded since it has no ticket type");

        Assertions.assertThrows(IllegalArgumentException.class, () -> TicketReader.readTicketFile(filename8), "File 8 cannot be loaded since it has a non-supported ticket type");

        Assertions.assertThrows(IllegalArgumentException.class, () -> TicketReader.readTicketFile(filename9), "File 9 cannot be loaded since it has an empty subject");

        Assertions.assertThrows(IllegalArgumentException.class, () -> TicketReader.readTicketFile(filename10), "File 10 cannot be loaded since it has no caller");

        Assertions.assertThrows(IllegalArgumentException.class, () -> TicketReader.readTicketFile(filename11), "File 11 cannot be loaded since it has no category");

        Assertions.assertThrows(IllegalArgumentException.class, () -> TicketReader.readTicketFile(filename12), "File 12 cannot be loaded since it has a non-existant category");

        Assertions.assertThrows(IllegalArgumentException.class, () -> TicketReader.readTicketFile(filename13), "File 13 cannot be loaded since it has no priority");

        Assertions.assertThrows(IllegalArgumentException.class, () -> TicketReader.readTicketFile(filename14), "File 14 cannot be loaded since it has a non-existant priority");

        Assertions.assertThrows(IllegalArgumentException.class, () -> TicketReader.readTicketFile(filename15), "File 15 cannot be loaded since it has no owner");

        Assertions.assertThrows(IllegalArgumentException.class, () -> TicketReader.readTicketFile(filename16), "File 16 should be in the feedback state since it is awaiting a caller.");

        Assertions.assertThrows(IllegalArgumentException.class, () -> TicketReader.readTicketFile(filename17), "File 17 cannot be solved while also in the working state");

        Assertions.assertThrows(IllegalArgumentException.class, () -> TicketReader.readTicketFile(filename18), "File 18 should be canceled if it is a duplicate");

        Assertions.assertThrows(IllegalArgumentException.class, () -> TicketReader.readTicketFile(filename19), "File 19 cannot be resolved if it is not completed");

        Assertions.assertThrows(IllegalArgumentException.class, () -> TicketReader.readTicketFile(filename20), "File 20 should only appear for incidents, not requests");
    }
}