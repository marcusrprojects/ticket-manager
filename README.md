# Ticket Manager

The Ticket Manager is a Java application that allows users to manage tickets. It provides functionality to add, remove, edit, sort, and hide tickets. This project includes several classes to support these features, as well as unit tests and system tests to ensure their functionality.

## Project Structure

The project is organized into several classes:

- `TicketReader`: Responsible for reading ticket data from a source (e.g., a file).
- `TicketWriter`: Handles writing ticket data to a destination (e.g., a file).
- `Ticket`: Represents a single ticket with various attributes.
- `TicketState`: interface that ensures ticket states conform to the Ticket Manager State Pattern.
- `TicketManager`: Manages a collection of tickets and provides operations to manipulate them.
- `Command`: Composes commands for interacting with the Ticket Manager.
- `TicketManagerGUI`: A graphical user interface (GUI) for interacting with the Ticket Manager.

## Features

- **Add Ticket**: Add a new ticket with relevant details.
- **Remove Ticket**: Remove a ticket from the system.
- **Edit Ticket**: Modify the details of an existing ticket.
- **Sort Tickets**: Sort the tickets by ticket type.
- **Hide Tickets**: Hide or filter tickets based on specific criteria (e.g., Request or Incident).

## Running Tests

To ensure the correctness of the application, unit tests have been implemented using JUnit. You can run these tests to verify the functionality of the classes.
Additionally, [System Tests](./project-docs/Project 1 System Test.docx) are provided and executed on as well.
