/**
 *
 */
package main.java.edu.bowdoin.csci.TicketManager.model.manager;

import java.util.List;

import main.java.edu.bowdoin.csci.TicketManager.model.command.Command;
import main.java.edu.bowdoin.csci.TicketManager.model.io.TicketReader;
import main.java.edu.bowdoin.csci.TicketManager.model.io.TicketWriter;
import main.java.edu.bowdoin.csci.TicketManager.model.ticket.Ticket;
import main.java.edu.bowdoin.csci.TicketManager.model.ticket.Ticket.Category;
import main.java.edu.bowdoin.csci.TicketManager.model.ticket.Ticket.Priority;
import main.java.edu.bowdoin.csci.TicketManager.model.ticket.Ticket.TicketType;

/**
 * Uses a Singleton design pattern.
 * TicketManagerGUI interacts only with one instance of this class.
 * Controls creating and modifying ticket lists. Maintenance of the list is in TicketList.
 * <p>
 * Works closely with TicketReader and TicketWriter.
 *
 * @author mribeiro
 *
 */
public class TicketManager {

    /**
     * Instantiate self
     */
    private static TicketManager singleton = new TicketManager();

    /**
     * array list of ticketLists that are used in this manager
     */
    private TicketList ticketList;

    /**
     * constructor for this ticket manager
     */
    private TicketManager() {

        this.createNewTicketList();
    }

    /**
     * gets the single instance of this manager
     * @return instance of ticket manager
     */
    public static TicketManager getInstance() {
        return singleton;
    }

    /**
     * saves the tickets to a file
     * @param tickets tickets
     */
    public void saveTicketsToFile(String tickets) {
        TicketWriter.writeTicketFile(tickets, this.ticketList.getTickets());
    }

    /**
     * loads tickets from a file
     * @param file file
     */
    public void loadTicketsFromFile(String file) {

        this.ticketList.addTickets(TicketReader.readTicketFile(file));
    }

    /**
     * creates a new list of tickets
     */
    public void createNewTicketList() {

        this.ticketList = new TicketList();
    }

    /**
     * helper function that converts a string to title case.
     * @param word inputted one-word string
     * @return title case string
     */
    private String titleCase(String word) {

        StringBuilder titleCase = new StringBuilder(word.length());
        char[] charArray = word.toCharArray();
        char character;

        for (int i = 0; i < charArray.length; i++) {

            character = charArray[i];
            if (i != 0) {
                character = Character.toLowerCase(character);
            }
            titleCase.append(character);
        }

        return titleCase.toString();
    }

    /**
     * gets the tickets to be displayed
     *
     * @return 2-D String array, [rows][columns], that helps populate the TicketTableModel
     * (TicketManagerGUI.TicketListPanel's inner class) with information.
     * Rows for every ticket, columns for Ticket's id, type, state name, subject, category String, priority String
     */
    public String[][] getTicketsForDisplay() {

        List<Ticket> tickets = this.ticketList.getTickets();
        String id;
        String ticketType;
        String state;
        String subject;
        String category;
        String priority;
        Ticket ticket;

        if (tickets.isEmpty()) {
            return new String[][]{};
        }

        String[][] displayArray = new String[tickets.size()][6];

        for (int i = 0; i < tickets.size(); i++) {

            ticket = tickets.get(i);

            id = Integer.toString(ticket.getTicketId());
            ticketType = this.titleCase(ticket.getTicketType().name());
            state = ticket.getState();
            subject = ticket.getSubject();
            category = ticket.getCategory();
            priority = ticket.getPriority();

            displayArray[i][0] = id;
            displayArray[i][1] = ticketType;
            displayArray[i][2] = state;
            displayArray[i][3] = subject;
            displayArray[i][4] = category;
            displayArray[i][5] = priority;
        }
        return displayArray;
    }

    /**
     * gets the tickets for display, discriminating by type
     * @param ticketType ticket type
     * @return tickets for display by type
     */
    public String[][] getTicketsForDisplayByType(TicketType ticketType) {

        if (ticketType == null) {

            throw new IllegalArgumentException("Unable to load file.");
        }

        List<Ticket> tickets = this.ticketList.getTicketsByType(ticketType);
        String id;
        String state;
        String subject;
        String category;
        String priority;
        String ticketTypeString = this.titleCase(ticketType.name());
        Ticket ticket;

        if (tickets.isEmpty()) {
            return new String[][]{};
        }

        String[][] displayArray = new String[tickets.size()][6];

        for (int i = 0; i < tickets.size(); i++) {

            ticket = tickets.get(i);

            id = Integer.toString(ticket.getTicketId());
            state = ticket.getState();
            subject = ticket.getSubject();
            category = ticket.getCategory();
            priority = ticket.getPriority();

            displayArray[i][0] = id;
            displayArray[i][1] = ticketTypeString;
            displayArray[i][2] = state;
            displayArray[i][3] = subject;
            displayArray[i][4] = category;
            displayArray[i][5] = priority;
        }
        return displayArray;
    }

    /**
     * gets the ticket by the id number
     * @param id id
     * @return ticket
     */
    public Ticket getTicketById(int id) {

        return this.ticketList.getTicketById(id);
    }

    /**
     * executes a command
     * @param id id
     * @param command command
     */
    public void executeCommand(int id, Command command) {

        this.ticketList.executeCommand(id, command);
    }

    /**
     * deletes a ticket based on its id
     * @param id id
     */
    public void deleteTicketById(int id) {

        this.ticketList.deleteTicketById(id);
    }

    /**
     * adds a ticket to a list
     * @param ticketType ticket type
     * @param subject subject
     * @param caller caller
     * @param category category
     * @param priority priority
     * @param owner owner
     */
    public void addTicketToList(TicketType ticketType, String subject, String caller, Category category, Priority priority, String owner) {

        this.ticketList.addTicket(ticketType, subject, caller, category, priority, owner);
    }
}