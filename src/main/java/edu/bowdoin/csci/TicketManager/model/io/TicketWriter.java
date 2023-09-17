/**
 *
 */
package main.java.edu.bowdoin.csci.TicketManager.model.io;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import main.java.edu.bowdoin.csci.TicketManager.model.ticket.Ticket;

/**
 * Writes tickets to a file, accessing a ticket list.
 *
 * @author mribeiro
 *
 */
public class TicketWriter {

    /**
     * This takes in a List of Strings that it uses to write to your desired file.
     * uses Ticket's toString() to write tickets to a file.
     * throws an illegal argument exception if there are errors.
     * @param fileName file name
     * @param ticketList ticket list
     */
    public static void writeTicketFile(String fileName, List<Ticket> ticketList) {

        FileWriter myWriter;

        if (ticketList.size() == 0) {
            throw new IllegalArgumentException("Unable to save file.");
        }

        try {
            myWriter = new FileWriter(fileName);

            for (Ticket ticket: ticketList) {

                myWriter.write(ticket.toString());
            }

            myWriter.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to save file.");
        }
    }
}
