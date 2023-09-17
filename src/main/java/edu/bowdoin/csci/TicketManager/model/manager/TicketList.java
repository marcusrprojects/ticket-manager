package main.java.edu.bowdoin.csci.TicketManager.model.manager;

import main.java.edu.bowdoin.csci.TicketManager.model.command.Command;
import main.java.edu.bowdoin.csci.TicketManager.model.ticket.Ticket;

import java.util.ArrayList;
import java.util.List;

/**
 * Maintains a list of Tickets and allows performing several actions on the list
 *
 * @author Darien
 */
public class TicketList {
    /**
     * The list of tickets being maintained by this TicketList
     */
    private List<Ticket> tickets;

    /**
     * Creates a new TicketList and resets the counter to 1
     */
    public TicketList() {
        Ticket.setCounter(1);
        this.tickets = new ArrayList<>();
    }

    /**
     * Adds a new ticket to the maintained list
     * @param type of the Ticket to be added
     * @param subject of the Ticket to be added
     * @param caller of the Ticket to be added
     * @param category of the Ticket to be added
     * @param priority of the Ticket to be added
     * @param note of the Ticket to be added
     * @return the id of the Ticket when added to the list
     */
    public int addTicket(Ticket.TicketType type, String subject, String caller, Ticket.Category category, Ticket.Priority priority, String note) {
        this.tickets.add(new Ticket(type, subject, caller, category, priority, note));
        return this.tickets.get(this.tickets.size() - 1).getTicketId();
    }

    /**
     * Creates a new list of Tickets from the supplied list and sets the counter to the maxId from the supplied list + 1
     * @param tickets the list of Tickets to replace the maintained list with
     */
    public void addTickets(List<Ticket> tickets) {
        if (tickets == null) {
            return;
        }

        this.tickets = tickets;

        int maxId = 0;

        for (Ticket ticket : this.tickets) {
            if (ticket.getTicketId() > maxId) maxId = ticket.getTicketId();
        }

        Ticket.setCounter(maxId + 1);
    }

    /**
     * Gets the currently maintained list of Tickets
     * @return the list of maintained tickets
     */
    public List<Ticket> getTickets() {
        return this.tickets;
    }

    /**
     * Gets all Tickets of a certain type from the maintained list
     * @param type of the Tickets to fetch
     * @return a list of all Tickets of this type in the list
     */
    public List<Ticket> getTicketsByType(Ticket.TicketType type) {
        if (type == null) throw new IllegalArgumentException("Type cannot be null.");

        return this.tickets.stream().filter(ticket -> ticket.getTicketType() == type).toList();
    }

    /**
     * Gets a Ticket from the maintained list by its id
     * @param id of the Ticket to be fetched
     * @return the Ticket with that id, or null if no such Ticket exists
     */
    public Ticket getTicketById(int id) {
        for (Ticket ticket : this.tickets) {
            if (ticket.getTicketId() == id) return ticket;
        }

        return null;
    }

    /**
     * Executes a Command for the Ticket of the supplied id
     * @param id of the Ticket to be updated
     * @param command that the user wants to execute on the Ticket
     */
    public void executeCommand(int id, Command command) {
        for (Ticket ticket : this.tickets) {
            if (ticket.getTicketId() == id) ticket.update(command);
        }
    }

    /**
     * Deletes a Ticket from the list of maintained Tickets by its id
     * @param id of the Ticket to be deleted
     */
    public void deleteTicketById(int id) {
        this.tickets.removeIf(ticket -> ticket.getTicketId() == id);
    }
}