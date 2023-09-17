package test.java.edu.bowdoin.csci.TicketManager.model.manager;

import main.java.edu.bowdoin.csci.TicketManager.model.command.Command;
import main.java.edu.bowdoin.csci.TicketManager.model.ticket.Ticket;
import main.java.edu.bowdoin.csci.TicketManager.model.manager.TicketManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Tests that the TicketManager correctly deals with Ticket lists and handles saving and loading.
 * @author Darien
 */
public class TicketManagerTest {
    /**
     * Stores the singleton instance of the TicketManager
     */
    private final TicketManager ticketManager = TicketManager.getInstance();

    /**
     * Clears the TicketList of the TicketManager for use in test cases
     * {@link TicketManager}
     */
    @BeforeEach
    public void getManagerInstance() {
        this.ticketManager.createNewTicketList();
    }

    /**
     * Tests the saveTicketsToFile method in TicketManager class
     * {@link TicketManager}
     */
    @Test
    public void testSaveTicketsToFile() {
        this.ticketManager.loadTicketsFromFile("test-files/act_ticket.txt");
        this.ticketManager.executeCommand(1, new Command(Command.CommandValue.PROCESS, "Darien", null, null, null, "note2"));

        this.ticketManager.saveTicketsToFile("test-files/saved_tickets.txt");
        this.ticketManager.createNewTicketList();

        this.ticketManager.loadTicketsFromFile("test-files/saved_tickets.txt");
        Assertions.assertEquals(Ticket.WORKING_NAME, this.ticketManager.getTicketById(1).getState(), "Ticket should be saved with Working state, but wasn't.");
    }

    /**
     * Tests the loadTicketsFromFile method in TicketManager class
     * {@link TicketManager}
     */
    @Test
    public void testLoadTicketsFromFile() {
        this.ticketManager.loadTicketsFromFile("test-files/act_ticket.txt");

        Assertions.assertNotNull(this.ticketManager.getTicketById(1), "Ticket should be not null and loaded.");

        this.ticketManager.loadTicketsFromFile("test-files/ticket2.txt");

        Assertions.assertNotNull(this.ticketManager.getTicketById(3), "Ticket should be not null and loaded.");
        Assertions.assertNotNull(this.ticketManager.getTicketById(6), "Ticket should be not null and loaded.");
        Assertions.assertNotNull(this.ticketManager.getTicketById(7), "Ticket should be not null and loaded.");
    }

    /**
     * Tests the createNewTicketList method in TicketManager class
     * {@link TicketManager}
     */
    @Test
    public void testCreateNewTicketList() {
        this.ticketManager.loadTicketsFromFile("test-files/ticket2.txt");
        this.ticketManager.createNewTicketList();

        Assertions.assertNull(this.ticketManager.getTicketById(3), "New list should not have old, loaded Tickets.");
        Assertions.assertNull(this.ticketManager.getTicketById(6), "New list should not have old, loaded Tickets.");
        Assertions.assertNull(this.ticketManager.getTicketById(7), "New list should not have old, loaded Tickets.");
    }

    /**
     * Tests the getTicketsForDisplay method in TicketManager class
     * {@link TicketManager}
     */
    @Test
    public void testGetTicketsForDisplay() {
        this.ticketManager.loadTicketsFromFile("test-files/ticket2.txt");
        String[][] tickets = this.ticketManager.getTicketsForDisplay();
        String[][] correctList = {
                {"3", Ticket.TT_REQUEST, Ticket.CLOSED_NAME, "Subject line", Ticket.C_INQUIRY, Ticket.P_MEDIUM},
                {"6", Ticket.TT_INCIDENT, Ticket.CANCELED_NAME, "Subject line", Ticket.C_SOFTWARE, Ticket.P_LOW},
                {"7", Ticket.TT_INCIDENT, Ticket.NEW_NAME, "Subject line", Ticket.C_SOFTWARE, Ticket.P_LOW}
        };

        int rows = correctList.length;
        int columns = correctList[0].length;

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < columns; j++) {

                Assertions.assertEquals(correctList[i][j], tickets[i][j], "Tickets for display did not match expected Array at row: " + i + ", column: " + j);
            }
        }

        Assertions.assertEquals(correctList.length, tickets.length, "Tickets for display did not have the same number of rows as the expected Array.");
        Assertions.assertEquals(correctList[0].length, tickets[0].length, "Tickets for display did not have the same number of columns as the expected Array.");


//        String correctListString = Arrays.toString(correctList);
//        String ticketsString = Arrays.toString(tickets);

//        Assertions.assertEquals(correctListString, ticketsString, "Tickets for display did not match expected Array.");

//        Assertions.assertEquals(correctList, tickets, "Tickets for display did not match expected Array.");
//        Assertions.assertEquals(correctList.length, tickets.length, "Tickets for display did not match expected Array.");
//        Assertions.assertEquals(correctList[0], tickets[0], "Tickets for display did not match expected Array.");
//        Assertions.assertEquals(correctList[1], tickets[1], "Tickets for display did not match expected Array.");
//        Assertions.assertEquals(correctList[2], tickets[2], "Tickets for display did not match expected Array.");

    }

    /**
     * Tests the getTicketsForDisplayByType method in TicketManager class
     * {@link TicketManager}
     */
    @Test
    public void testGetTicketsForDisplayByType() {
        try {
            this.ticketManager.getTicketsForDisplayByType(null);
            Assertions.fail();
        }  catch (IllegalArgumentException e) {
            // Expected exception, continue
        }

        this.ticketManager.loadTicketsFromFile("test-files/act_ticket.txt");
        String[][] tickets = this.ticketManager.getTicketsForDisplayByType(Ticket.TicketType.REQUEST);

        Assertions.assertEquals(0, tickets.length, "Array should be empty as no tickets match Type.");

        tickets = this.ticketManager.getTicketsForDisplayByType(Ticket.TicketType.INCIDENT);
        String[][] correctList = {{"1", Ticket.TT_INCIDENT, Ticket.NEW_NAME, "Subject", Ticket.C_NETWORK, Ticket.P_LOW}};

        for (int i = 0; i < tickets.length; i++) {
            for (int j = 0; j < tickets[i].length; j++) {
                Assertions.assertFalse(i > correctList.length || j > correctList[i].length);

                Assertions.assertEquals(correctList[i][j], tickets[i][j], "Tickets for display did not match expected Array.");
            }
        }
    }

    /**
     * Tests the getTicketById method in TicketManager class
     * {@link TicketManager}
     */
    @Test
    public void testGetTicketById() {
        ArrayList<String> notes = new ArrayList<>();
        notes.add("note");
        Ticket.setCounter(1);

        this.ticketManager.loadTicketsFromFile("test-files/act_ticket.txt");
        Ticket ticket = this.ticketManager.getTicketById(1);

        Assertions.assertEquals(1, ticket.getTicketId(), "Ticket was not fetched correctly.");
        Assertions.assertEquals("New", ticket.getState(), "Ticket was not fetched correctly.");
        Assertions.assertEquals("Incident", ticket.getTicketTypeString(), "Ticket was not fetched correctly.");
        Assertions.assertEquals("Subject", ticket.getSubject(), "Ticket was not fetched correctly.");
        Assertions.assertEquals("Caller", ticket.getCaller(), "Ticket was not fetched correctly.");
        Assertions.assertEquals("Network", ticket.getCategory(), "Ticket was not fetched correctly.");
        Assertions.assertEquals("Low", ticket.getPriority(), "Ticket was not fetched correctly.");

    }

    /**
     * Tests the executeCommand method in TicketManager class
     * {@link TicketManager}
     */
    @Test
    public void testExecuteCommand() {
        this.ticketManager.loadTicketsFromFile("test-files/act_ticket.txt");
        this.ticketManager.executeCommand(1, new Command(Command.CommandValue.PROCESS, "Darien", null, null, null, "note2"));

        Assertions.assertEquals(Ticket.WORKING_NAME, this.ticketManager.getTicketById(1).getState(), "Ticket should now be Working but was " + this.ticketManager.getTicketById(1).getState());
    }

    /**
     * Tests the deleteTicketById method in TicketManager class
     * {@link TicketManager}
     */
    @Test
    public void testDeleteTicketById() {
        this.ticketManager.loadTicketsFromFile("test-files/ticket2.txt");

        this.ticketManager.deleteTicketById(6);

        Assertions.assertNull(this.ticketManager.getTicketById(6), "Ticket should no longer exist, but did.");
        Assertions.assertNotNull(this.ticketManager.getTicketById(3), "Other Tickets should remain, but didn't.");
        Assertions.assertNotNull(this.ticketManager.getTicketById(7), "Other Tickets should remain, but didn't.");
    }

    /**
     * Tests the addTicketToList method in TicketManager class
     * {@link TicketManager}
     */
    @Test
    public void testAddTicketToList() {
        Ticket.setCounter(15);
        this.ticketManager.addTicketToList(Ticket.TicketType.REQUEST, "Subject", "Caller", Ticket.Category.SOFTWARE, Ticket.Priority.MEDIUM, "Owner");

        Assertions.assertNotNull(this.ticketManager.getTicketById(15), "Ticket should have been added to list, but was null.");
    }

}