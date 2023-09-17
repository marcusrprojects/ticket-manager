package test.java.edu.bowdoin.csci.TicketManager.model.io;

import main.java.edu.bowdoin.csci.TicketManager.model.manager.TicketList;
import main.java.edu.bowdoin.csci.TicketManager.model.ticket.Ticket;
import main.java.edu.bowdoin.csci.TicketManager.model.io.TicketWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Tests that Tickets are saved correctly and that exceptions are thrown if not.
 * @author Darien
 */
public class TicketWriterTest {
    /**
     * Holds a TicketList for use in test cases
     */
    private TicketList ticketList;

    /**
     * Sets up the TicketList field with some tests to use in running test cases
     */
    @BeforeEach
    public void setUpTicketList() {
        this.ticketList = new TicketList();
        this.ticketList.addTicket(Ticket.TicketType.INCIDENT, "Test", "Darien", Ticket.Category.INQUIRY, Ticket.Priority.URGENT, "Marcus");
        this.ticketList.addTicket(Ticket.TicketType.REQUEST, "Request", "Marcus", Ticket.Category.HARDWARE, Ticket.Priority.LOW, "Darien");
        this.ticketList.addTicket(Ticket.TicketType.INCIDENT, "Incident", "Luke", Ticket.Category.SOFTWARE, Ticket.Priority.MEDIUM, "Abigail");
    }

    /**
     * Tests to make sure that a valid write call does not throw an exception
     * {@link TicketWriter}
     */
    @Test
    public void testValidFileWrite() {
        Assertions.assertDoesNotThrow(() -> TicketWriter.writeTicketFile("newTicketFile.txt", this.ticketList.getTickets()));
    }

    /**
     * Tests that an invalid write call throws an IAE
     * {@link TicketWriter}
     */
    @Test
    public void testInvalidFileWrite() {
        try {
            TicketWriter.writeTicketFile("tickets.png", new ArrayList<>());
            Assertions.fail("Invalid write should throw an IAE, but did not");
        } catch (IllegalArgumentException e) {
            // Expected exception, continue
        }

        try {
            TicketWriter.writeTicketFile("test-files", this.ticketList.getTickets());
            Assertions.fail("Invalid write should throw an IAE, but did not");
        } catch (IllegalArgumentException e) {
            // Expected exception, continue
        }
    }

}