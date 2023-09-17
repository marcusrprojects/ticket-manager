/**
 *
 */
package test.java.edu.bowdoin.csci.TicketManager.model.manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.edu.bowdoin.csci.TicketManager.model.command.Command;
import main.java.edu.bowdoin.csci.TicketManager.model.command.Command.CancellationCode;
import main.java.edu.bowdoin.csci.TicketManager.model.command.Command.CommandValue;
import main.java.edu.bowdoin.csci.TicketManager.model.ticket.Ticket;
import main.java.edu.bowdoin.csci.TicketManager.model.manager.TicketList;
import main.java.edu.bowdoin.csci.TicketManager.model.ticket.Ticket.Category;
import main.java.edu.bowdoin.csci.TicketManager.model.ticket.Ticket.Priority;
import main.java.edu.bowdoin.csci.TicketManager.model.ticket.Ticket.TicketType;

/**
 * Tests to ensure that a ticket list can be made and interacted with.
 *
 * @author mribeiro
 *
 */
public class TicketListTest {

    /**
     * Ticket list
     */
    TicketList ticketList;

    /**
     * Ticket type
     */
    Ticket.TicketType type = TicketType.INCIDENT;

    /**
     * subject
     */
    String subject = "Subject";

    /**
     * caller
     */
    String caller = "caller";

    /**
     * owner
     */
    String note = "Good talk with client.";

    /**
     * Category
     */
    Ticket.Category category = Category.INQUIRY;

    /**
     * Priority
     */
    Ticket.Priority priority = Priority.LOW;

    /**
     * Ticket list
     */
    ArrayList<Ticket> tickets = new ArrayList<>();

    /**
     * Sets up the test.
     *
     * @throws java.lang.Exception java exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        ticketList = new TicketList();
    }

    /**
     * tests adding tickets
     */
    @Test
    public void testAddAndGetTicket() {

        Assertions.assertEquals(1,ticketList.addTicket(type, subject, caller, category, priority, note), "TicketList.addTicket() should have ticketId of 0");
    }

    /**
     * tests execute command function
     */
    @Test
    public void testExecuteCommand() {

        ticketList.addTicket(TicketType.REQUEST, "subject jr.", "caller jr.", Category.HARDWARE, Priority.MEDIUM, "hardware is not working.");

        Assertions.assertDoesNotThrow(() -> ticketList.executeCommand(1, new Command(CommandValue.CANCEL, "Marcus", null, null, CancellationCode.DUPLICATE, "Duplicate problem.")), "executeCommand does not execute the given proper command");
    }

    /**
     * tests getting tickets
     */
    @Test
    public void testGetTicket() {

        ticketList.addTicket(type, subject, caller, category, priority, note);

        ticketList.addTicket(TicketType.REQUEST, "subject jr.", "caller jr.", Category.HARDWARE, Priority.MEDIUM, "hardware is not working.");

        Assertions.assertEquals(2, ticketList.getTickets().size(), "TicketList.getTickets() should have two 2 tickets.");

        Assertions.assertEquals("subject jr.", ticketList.getTicketById(2).getSubject(), "TicketList.getTicketById() should equal the ticket with an ID of 2. We test to see if the unique subject name matches for this ticket.");

        Assertions.assertEquals("subject jr.", ticketList.getTicketsByType(TicketType.REQUEST).get(0).getSubject(), "TicketList.getTicketsByType() should equal a list with the ticket with an ID of 2. We test to see if the unique subject name matches for this ticket.");

        Assertions.assertEquals(1, ticketList.getTicketsByType(TicketType.REQUEST).size(), "TicketList.getTicketsByType() should equal a list with the ticket with an ID of 2. We test to see if the list has just a length of 1.");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ticketList.getTicketsByType(null);
        }, "TicketList.getTicketsByType() should not take in null.");
    }


    /**
     * Tests adding multiple tickets
     */
    @Test
    public void testAddTickets() {

        List<Ticket> arrayListTickets = new ArrayList<>();

        arrayListTickets.add(new Ticket(Ticket.TicketType.INCIDENT, "Test", "Darien", Ticket.Category.INQUIRY, Ticket.Priority.URGENT, "Marcus"));
        arrayListTickets.add(new Ticket(Ticket.TicketType.REQUEST, "Request", "Marcus", Ticket.Category.HARDWARE, Ticket.Priority.LOW, "Darien"));

        Assertions.assertDoesNotThrow(() -> ticketList.addTickets(arrayListTickets), "Should be able to add tickets to the ticket list from an arraylist");
        int currentSize = ticketList.getTickets().size();
        ticketList.addTickets(null);
        Assertions.assertEquals(currentSize, ticketList.getTickets().size(), "adding null tickets should add no tickets to the list");

    }

    /**
     * tests the deletion of tickets.
     */
    @Test
    public void testDeleteTickets() {

        ticketList.addTicket(type, subject, caller, category, priority, note);

        Assertions.assertDoesNotThrow(() -> ticketList.deleteTicketById(1), "Should be able to delete ticket");

        Assertions.assertNull(ticketList.getTicketById(1), "Tickets.deleteTicket() should have deleted this already.");
    }

}