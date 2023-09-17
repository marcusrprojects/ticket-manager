package main.java.edu.bowdoin.csci.TicketManager.model.io;

import main.java.edu.bowdoin.csci.TicketManager.model.ticket.Ticket;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A class for reading ticket files to create the Ticket objects stored within them
 *
 * @author Darien
 */
public class TicketReader {

    /**
     * Reads a file to parse the list of tickets within it
     * @param filename the name of the file to read
     * @return an ArrayList of Tickets representing the Tickets in the read file
     */
    public static ArrayList<Ticket> readTicketFile(String filename) {
        ArrayList<String> ticketStrings = new ArrayList<>();

        try {
            File ticketFile = new File(filename);
            Scanner ticketScanner = new Scanner(ticketFile);
            ticketScanner.useDelimiter("\\r?\\n?\\*");

            while (ticketScanner.hasNext()) {
                ticketStrings.add(ticketScanner.next());
            }

            ticketScanner.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to load file.");
        }

        ArrayList<Ticket> tickets = new ArrayList<>();

        for (String ticketString : ticketStrings) {
            tickets.add(readTicket(ticketString));
        }

        return tickets;
    }

    /**
     * A helper method that reads out a single ticket from the input String
     * @param ticketString the text to read the Ticket from
     * @return the Ticket with information indicated by the text
     */
    private static Ticket readTicket(String ticketString) {
        Scanner fullScanner = new Scanner(ticketString);
        fullScanner.useDelimiter("\\r?\\n?-");

        String firstLine = fullScanner.next();

        Scanner firstLineScanner = new Scanner(firstLine);
        firstLineScanner.useDelimiter("#");

        int id;
        String state;
        String type;
        String subject;
        String caller;
        String category;
        String priority;
        String owner;
        String code = null;
        ArrayList<String> notes = new ArrayList<>();

        try {
            id = firstLineScanner.nextInt();
            state = firstLineScanner.next();
            type = firstLineScanner.next();
            subject = firstLineScanner.next();
            caller = firstLineScanner.next();
            category = firstLineScanner.next();
            priority = firstLineScanner.next();
            owner = firstLineScanner.next();

            if ("Resolved".equals(state) || "Closed".equals(state) || "Feedback".equals(state) || "Canceled".equals(state)) {
                code = firstLineScanner.next();
            }

            if (firstLineScanner.hasNext()) {
                throw new IllegalArgumentException("Unable to load file.");
            }

        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Unable to load file.");
        }

        if (fullScanner.hasNext()) {
            while (fullScanner.hasNext()) {
                String note = fullScanner.next();
                note = note.replaceAll("\r", "");
                notes.add(note);
            }
        } else {
            throw new IllegalArgumentException("Unable to load file.");
        }

        Ticket ticket;
        try {
            ticket = new Ticket(id, state, type, subject, caller, category, priority, owner, code, notes);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unable to load file.");
        }

        return ticket;
    }
}