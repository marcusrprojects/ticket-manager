package test.java.edu.bowdoin.csci.TicketManager.model.ticket;

import main.java.edu.bowdoin.csci.TicketManager.model.command.Command;
import main.java.edu.bowdoin.csci.TicketManager.model.ticket.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Tests that Tickets have correct validation and correctly validate proposed transition commands.
 * @author Darien
 */
public class TicketTest {
    /**
     * Ticket for use in test cases
     */
    private Ticket ticket;

    /**
     * Sets up a Ticket object for using in each test case
     */
    @BeforeEach
    public void setUpTicket() {
        Ticket.setCounter(1);
        this.ticket = new Ticket(Ticket.TicketType.INCIDENT, "Test", "Darien", Ticket.Category.DATABASE, Ticket.Priority.HIGH, "Marcus");
    }

    /**
     * Tests the creation of Tickets to a wide range of states, categories, priorities, and types
     * {@link Ticket}
     */
    @Test
    public void testTicketCreation() {
        this.ticket = new Ticket(4, "Working", "Request", "Test2", "Marcus", "Software", "Low", "Darien", null, new ArrayList<>());

        Assertions.assertEquals("Marcus", this.ticket.getCaller(), "Caller should have been 'Darien' but was " + this.ticket.getCaller());

        Assertions.assertNull(this.ticket.getCancellationCode(), "CancellationCode should be null when not in Canceled State");

        Assertions.assertEquals("Software", this.ticket.getCategory(), "Category should have been 'Software' but was " + this.ticket.getCategory());

        Assertions.assertNull(this.ticket.getFeedbackCode(), "FeedbackCode should be null when not in Feedback state");

        Assertions.assertEquals("", this.ticket.getNotes(), "The notes getter should return a formatted string of all the notes");

        Assertions.assertEquals("Darien", this.ticket.getOwner(), "Owner should be 'Marcus' but was " + this.ticket.getOwner());

        Assertions.assertEquals("Low", this.ticket.getPriority(), "TicketPriority should be 'Low' but was " + this.ticket.getPriority());

        Assertions.assertNull(this.ticket.getResolutionCode(), "ResolutionCode should be null when not in Resolved state");

        Assertions.assertEquals("Working", this.ticket.getState(), "TicketState should be 'Working' but was " + this.ticket.getState());

        Assertions.assertEquals("Test2", this.ticket.getSubject(), "Subject should be 'Test2' but was " + this.ticket.getSubject());

        Assertions.assertEquals(4, this.ticket.getTicketId(), "Ticket ID should be 4 but was " + this.ticket.getTicketId());

        Assertions.assertEquals(Ticket.TicketType.REQUEST, this.ticket.getTicketType(), "TicketType should be Request but was " + this.ticket.getTicketType());

        Assertions.assertEquals("Request", this.ticket.getTicketTypeString(), "TicketType string should be 'Request' but was " + this.ticket.getTicketTypeString());



        this.ticket = new Ticket(7, "Feedback", "Request", "Test2", "Marcus", "Hardware", "Low", "Darien", "Awaiting Change", new ArrayList<>());

        Assertions.assertEquals("Marcus", this.ticket.getCaller(), "Caller should have been 'Darien' but was " + this.ticket.getCaller());

        Assertions.assertNull(this.ticket.getCancellationCode(), "CancellationCode should be null when not in Canceled State");

        Assertions.assertEquals("Hardware", this.ticket.getCategory(), "Category should have been 'Hardware' but was " + this.ticket.getCategory());

        Assertions.assertEquals("Awaiting Change", this.ticket.getFeedbackCode(), "FeedbackCode should be 'Awaiting Change', but was " + this.ticket.getFeedbackCode());

        Assertions.assertEquals("", this.ticket.getNotes(), "The notes getter should return a formatted string of all the notes");

        Assertions.assertEquals("Darien", this.ticket.getOwner(), "Owner should be 'Marcus' but was " + this.ticket.getOwner());

        Assertions.assertEquals("Low", this.ticket.getPriority(), "TicketPriority should be 'Low' but was " + this.ticket.getPriority());

        Assertions.assertNull(this.ticket.getResolutionCode(), "ResolutionCode should be null when not in Resolved state");

        Assertions.assertEquals("Feedback", this.ticket.getState(), "TicketState should be 'Feedback' but was " + this.ticket.getState());

        Assertions.assertEquals("Test2", this.ticket.getSubject(), "Subject should be 'Test2' but was " + this.ticket.getSubject());

        Assertions.assertEquals(7, this.ticket.getTicketId(), "Ticket ID should be 7 but was " + this.ticket.getTicketId());

        Assertions.assertEquals(Ticket.TicketType.REQUEST, this.ticket.getTicketType(), "TicketType should be Request but was " + this.ticket.getTicketType());

        Assertions.assertEquals("Request", this.ticket.getTicketTypeString(), "TicketType string should be 'Request' but was " + this.ticket.getTicketTypeString());



        this.ticket = new Ticket(10, "Closed", "Request", "Test2", "Marcus", "Network", "Medium", "Darien", "Caller Closed", new ArrayList<>());

        Assertions.assertEquals("Marcus", this.ticket.getCaller(), "Caller should have been 'Darien' but was " + this.ticket.getCaller());

        Assertions.assertNull(this.ticket.getCancellationCode(), "CancellationCode should be null when not in Canceled State");

        Assertions.assertEquals("Network", this.ticket.getCategory(), "Category should have been 'Network' but was " + this.ticket.getCategory());

        Assertions.assertNull(this.ticket.getFeedbackCode(), "FeedbackCode should be null when not in FeedbackState.");

        Assertions.assertEquals("", this.ticket.getNotes(), "The notes getter should return a formatted string of all the notes");

        Assertions.assertEquals("Darien", this.ticket.getOwner(), "Owner should be 'Marcus' but was " + this.ticket.getOwner());

        Assertions.assertEquals("Medium", this.ticket.getPriority(), "TicketPriority should be 'Medium' but was " + this.ticket.getPriority());

        Assertions.assertEquals("Caller Closed", this.ticket.getResolutionCode(), "ResolutionCode should be 'Caller Closed' but was " + this.ticket.getResolutionCode());

        Assertions.assertEquals("Closed", this.ticket.getState(), "TicketState should be 'Closed' but was " + this.ticket.getState());

        Assertions.assertEquals("Test2", this.ticket.getSubject(), "Subject should be 'Test2' but was " + this.ticket.getSubject());

        Assertions.assertEquals(10, this.ticket.getTicketId(), "Ticket ID should be 10 but was " + this.ticket.getTicketId());

        Assertions.assertEquals(Ticket.TicketType.REQUEST, this.ticket.getTicketType(), "TicketType should be Request but was " + this.ticket.getTicketType());

        Assertions.assertEquals("Request", this.ticket.getTicketTypeString(), "TicketType string should be 'Request' but was " + this.ticket.getTicketTypeString());



        this.ticket = new Ticket(15, "Resolved", "Request", "Test2", "Marcus", "Inquiry", "Urgent", "Darien", "Completed", new ArrayList<>());

        Assertions.assertEquals("Marcus", this.ticket.getCaller(), "Caller should have been 'Darien' but was " + this.ticket.getCaller());

        Assertions.assertNull(this.ticket.getCancellationCode(), "CancellationCode should be null when not in Canceled State");

        Assertions.assertEquals("Inquiry", this.ticket.getCategory(), "Category should have been 'Inquiry' but was " + this.ticket.getCategory());

        Assertions.assertNull(this.ticket.getFeedbackCode(), "FeedbackCode should be null when not in FeedbackState.");

        Assertions.assertEquals("", this.ticket.getNotes(), "The notes getter should return a formatted string of all the notes");

        Assertions.assertEquals("Darien", this.ticket.getOwner(), "Owner should be 'Marcus' but was " + this.ticket.getOwner());

        Assertions.assertEquals("Urgent", this.ticket.getPriority(), "TicketPriority should be 'Urgent' but was " + this.ticket.getPriority());

        Assertions.assertEquals("Completed", this.ticket.getResolutionCode(), "ResolutionCode should be 'Completed' but was " + this.ticket.getResolutionCode());

        Assertions.assertEquals("Resolved", this.ticket.getState(), "TicketState should be 'Resolved' but was " + this.ticket.getState());

        Assertions.assertEquals("Test2", this.ticket.getSubject(), "Subject should be 'Test2' but was " + this.ticket.getSubject());

        Assertions.assertEquals(15, this.ticket.getTicketId(), "Ticket ID should be 15 but was " + this.ticket.getTicketId());

        Assertions.assertEquals(Ticket.TicketType.REQUEST, this.ticket.getTicketType(), "TicketType should be Request but was " + this.ticket.getTicketType());

        Assertions.assertEquals("Request", this.ticket.getTicketTypeString(), "TicketType string should be 'Request' but was " + this.ticket.getTicketTypeString());



        this.ticket = new Ticket(152, "Canceled", "Request", "Test2", "Marcus", "Database", "Urgent", "Darien", "Duplicate", new ArrayList<>());

        Assertions.assertEquals("Marcus", this.ticket.getCaller(), "Caller should have been 'Darien' but was " + this.ticket.getCaller());

        Assertions.assertEquals("Duplicate", this.ticket.getCancellationCode(), "CancellationCode should be 'Duplicate' but was " + this.ticket.getCancellationCode());

        Assertions.assertEquals("Database", this.ticket.getCategory(), "Category should have been 'Database' but was " + this.ticket.getCategory());

        Assertions.assertNull(this.ticket.getFeedbackCode(), "FeedbackCode should be null when not in FeedbackState.");

        Assertions.assertEquals("", this.ticket.getNotes(), "The notes getter should return a formatted string of all the notes");

        Assertions.assertEquals("Darien", this.ticket.getOwner(), "Owner should be 'Marcus' but was " + this.ticket.getOwner());

        Assertions.assertEquals("Urgent", this.ticket.getPriority(), "TicketPriority should be 'Urgent' but was " + this.ticket.getPriority());

        Assertions.assertNull(this.ticket.getResolutionCode(), "ResolutionCode should be null when not in ResolvedState");

        Assertions.assertEquals("Canceled", this.ticket.getState(), "TicketState should be 'Canceled' but was " + this.ticket.getState());

        Assertions.assertEquals("Test2", this.ticket.getSubject(), "Subject should be 'Test2' but was " + this.ticket.getSubject());

        Assertions.assertEquals(152, this.ticket.getTicketId(), "Ticket ID should be 152 but was " + this.ticket.getTicketId());

        Assertions.assertEquals(Ticket.TicketType.REQUEST, this.ticket.getTicketType(), "TicketType should be Request but was " + this.ticket.getTicketType());

        Assertions.assertEquals("Request", this.ticket.getTicketTypeString(), "TicketType string should be 'Request' but was " + this.ticket.getTicketTypeString());
    }

    /**
     * Tests all the field getters of the Ticket class
     * {@link Ticket}
     */
    @Test
    public void testGetters() {
        Assertions.assertEquals("Darien", this.ticket.getCaller(), "Caller should have been 'Darien' but was " + this.ticket.getCaller());

        Assertions.assertNull(this.ticket.getCancellationCode(), "CancellationCode should be null when not in Canceled State");

        Assertions.assertEquals("Database", this.ticket.getCategory(), "Category should have been 'Database' but was " + this.ticket.getCategory());

        Assertions.assertNull(this.ticket.getFeedbackCode(), "FeedbackCode should be null when not in Feedback state");

        Assertions.assertEquals("-Marcus\n", this.ticket.getNotes(), "The notes getter should return a formatted string of all the notes");

        Assertions.assertEquals("", this.ticket.getOwner(), "Owner should be empty but was " + this.ticket.getOwner());

        Assertions.assertEquals("High", this.ticket.getPriority(), "TicketPriority should be 'High' but was " + this.ticket.getPriority());

        Assertions.assertNull(this.ticket.getResolutionCode(), "ResolutionCode should be null when not in Resolved state");

        Assertions.assertEquals("New", this.ticket.getState(), "TicketState should be 'New' but was " + this.ticket.getState());

        Assertions.assertEquals("Test", this.ticket.getSubject(), "Subject should be 'Test' but was " + this.ticket.getSubject());

        Assertions.assertEquals(1, this.ticket.getTicketId(), "Ticket ID should be 0 but was " + this.ticket.getTicketId());

        Assertions.assertEquals(Ticket.TicketType.INCIDENT, this.ticket.getTicketType(), "TicketType should be Incident but was " + this.ticket.getTicketType());

        Assertions.assertEquals("Incident", this.ticket.getTicketTypeString(), "TicketType string should be 'Incident' but was " + this.ticket.getTicketTypeString());
    }

    /**
     * Tests the incrementCounter() method in Ticket class
     * {@link Ticket}
     */
    @Test
    public void testIncrementCounter() {
        int previousCounter = this.ticket.getTicketId();
        Ticket.incrementCounter();
        Ticket ticket2 = new Ticket(Ticket.TicketType.INCIDENT, "Test", "Darien", Ticket.Category.DATABASE, Ticket.Priority.HIGH, "Marcus");

        Assertions.assertEquals(previousCounter + 2, ticket2.getTicketId(), "Ticket ID should have been incremented to " + (previousCounter + 2) + " but was " + ticket2.getTicketId());
    }

    /**
     * Tests valid inputs for the setCounter() method in Ticket class
     * {@link Ticket}
     */
    @Test
    public void testValidSetCounter() {
        Ticket.setCounter(15);
        Ticket ticket2 = new Ticket(Ticket.TicketType.INCIDENT, "Test", "Darien", Ticket.Category.DATABASE, Ticket.Priority.HIGH, "Marcus");

        Assertions.assertEquals(15, ticket2.getTicketId(), "Ticket ID should have been set to 15 but was " + ticket2.getTicketId());
    }

    /**
     * Tests invalid inputs for the setCounter() method in Ticket class
     * {@link Ticket}
     */
    @Test
    public void testInvalidSetCounter() {
        try {
            Ticket.setCounter(-15);
            Assertions.fail("Setting the counter to a negative value should throw an IAE, but did not");
        } catch (IllegalArgumentException e) {
            // Expected exception, continue
        }
    }

    /**
     * Tests the formatting of the toString method in Ticket class
     * {@link Ticket}
     */
    @Test
    public void testToString() {
        Assertions.assertEquals("*1#New#Incident#Test#Darien#Database#High##\n-Marcus\n", this.ticket.toString(), "Ticket does not follow correct formatting in toString()");
    }

    /**
     * Tests the update method in Ticket class
     * {@link Ticket}
     */
    @Test
    public void testUpdate() {
        this.ticket.update(new Command(Command.CommandValue.PROCESS, "Darien", null, null, null, "Assigned to Darien"));

        Assertions.assertEquals("Working", this.ticket.getState(), "State should be updated to 'Working' but was " + this.ticket.getState());
        Assertions.assertEquals("Darien", this.ticket.getOwner(), "Owner should be updated to 'Darien' but was " + this.ticket.getOwner());
        Assertions.assertEquals("-Marcus\n-Assigned to Darien\n", this.ticket.getNotes(), "Notes should now have an assignment note but did not");
    }

    /**
     * Tests invalid FSM flow scenarios for Ticket states.
     * {@link Ticket}
     */
    @Test
    public void testInvalidFlow() {
        Ticket ticket1 = new Ticket(4, "Canceled", "Incident", "Subject", "Caller", "Hardware", "Low", "Owner", "Inappropriate", new ArrayList<>());

        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            ticket1.update(new Command(Command.CommandValue.PROCESS, "Darien", null, null, null, "Bad command."));
        });

        Ticket ticket2 = new Ticket(6, "Closed", "Incident", "Subject", "Caller", "Hardware", "Low", "Owner", "Caller Closed", new ArrayList<>());

        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            ticket2.update(new Command(Command.CommandValue.PROCESS, "Darien", null, null, null, "Bad command."));
        });
    }

    /**
     * Tests FSM flow from New state to Canceled state
     * {@link Ticket}
     */
    @Test
    public void testNewtoCanceled() {
        this.ticket.update(new Command(Command.CommandValue.CANCEL, "Darien", null, null, Command.CancellationCode.INAPPROPRIATE, "Canceled issue"));

        Assertions.assertEquals("Canceled", this.ticket.getState(), "State should be updated to 'Canceled' but was " + this.ticket.getState());
        Assertions.assertEquals("Inappropriate", this.ticket.getCancellationCode(), "CancellationCode should be 'Inappropriate' but was " + this.ticket.getCancellationCode());
        Assertions.assertEquals("Darien", this.ticket.getOwner(), "Owner should be updated to 'Darien' but was " + this.ticket.getOwner());
        Assertions.assertEquals("-Marcus\n-Canceled issue\n", this.ticket.getNotes(), "Notes should now have a cancellation note but did not");
    }

    /**
     * Tests FSM flow from Working state to Canceled state
     * {@link Ticket}
     */
    @Test
    public void testWorkingToCanceled() {
        this.ticket.update(new Command(Command.CommandValue.PROCESS, "Darien", null, null, null, "Assigned to Darien"));

        Assertions.assertEquals("Working", this.ticket.getState(), "State should be updated to 'Working' but was " + this.ticket.getState());
        Assertions.assertEquals("Darien", this.ticket.getOwner(), "Owner should be updated to 'Darien' but was " + this.ticket.getOwner());
        Assertions.assertEquals("-Marcus\n-Assigned to Darien\n", this.ticket.getNotes(), "Notes should now have an assignment note but did not");

        this.ticket.update(new Command(Command.CommandValue.CANCEL, "Darien", null, null, Command.CancellationCode.INAPPROPRIATE, "Canceled issue"));

        Assertions.assertEquals("Canceled", this.ticket.getState(), "State should be updated to 'Canceled' but was " + this.ticket.getState());
        Assertions.assertEquals("Inappropriate", this.ticket.getCancellationCode(), "CancellationCode should be 'Inappropriate' but was " + this.ticket.getCancellationCode());
        Assertions.assertEquals("Darien", this.ticket.getOwner(), "Owner should be updated to 'Darien' but was " + this.ticket.getOwner());
        Assertions.assertEquals("-Marcus\n-Assigned to Darien\n-Canceled issue\n", this.ticket.getNotes(), "Notes should now have an assignment note and cancellation note but did not");
    }

    /**
     * Tests FSM flow from Feedback state to Canceled state
     * {@link edu.bowdoin.csci.TicketManager.model.ticket.Ticket.CanceledState}
     */
    @Test
    public void testFeedbacktoCanceled() {
        this.ticket.update(new Command(Command.CommandValue.PROCESS, "Darien", null, null, null, "Assigned to Darien"));

        Assertions.assertEquals("Working", this.ticket.getState(), "State should be updated to 'Working' but was " + this.ticket.getState());
        Assertions.assertEquals("Darien", this.ticket.getOwner(), "Owner should be updated to 'Darien' but was " + this.ticket.getOwner());
        Assertions.assertEquals("-Marcus\n-Assigned to Darien\n", this.ticket.getNotes(), "Notes should now have an assignment note but did not");

        this.ticket.update(new Command(Command.CommandValue.FEEDBACK, "Darien", Command.FeedbackCode.AWAITING_CALLER, null, null, "Need feedback"));

        Assertions.assertEquals("Feedback", this.ticket.getState(), "State should be updated to 'Feedback' but was " + this.ticket.getState());
        Assertions.assertEquals("Awaiting Caller", this.ticket.getFeedbackCode(), "FeedbackCode should be 'Awaiting Caller' but was " + this.ticket.getFeedbackCode());
        Assertions.assertEquals("Darien", this.ticket.getOwner(), "Owner should be updated to 'Darien' but was " + this.ticket.getOwner());
        Assertions.assertEquals("-Marcus\n-Assigned to Darien\n-Need feedback\n", this.ticket.getNotes(), "Notes should now have an assignment note and feedback note but did not");

        this.ticket.update(new Command(Command.CommandValue.CANCEL, "Darien", null, null, Command.CancellationCode.INAPPROPRIATE, "Canceled issue"));

        Assertions.assertEquals("Canceled", this.ticket.getState(), "State should be updated to 'Canceled' but was " + this.ticket.getState());
        Assertions.assertEquals("Inappropriate", this.ticket.getCancellationCode(), "CancelationCode should be 'Inappropriate' but was " + this.ticket.getCancellationCode());
        Assertions.assertEquals("Darien", this.ticket.getOwner(), "Owner should be updated to 'Darien' but was " + this.ticket.getOwner());
        Assertions.assertEquals("-Marcus\n-Assigned to Darien\n-Need feedback\n-Canceled issue\n", this.ticket.getNotes(), "Notes should now have an assignment note, feedback note, and cancellation note but did not");
    }

    /**
     * Tests all remaining paths in the FSM flow
     * {@link Ticket}
     */
    @Test
    public void testRemainingFSMFlow() {
        this.ticket.update(new Command(Command.CommandValue.PROCESS, "Darien", null, null, null, "Assigned to Darien"));

        this.ticket.update(new Command(Command.CommandValue.RESOLVE, "Darien", null, Command.ResolutionCode.CALLER_CLOSED, null, "Resolved"));

        Assertions.assertEquals("Resolved", this.ticket.getState(), "State should be updated to 'Resolved' but was " + this.ticket.getState());
        Assertions.assertEquals("Caller Closed", this.ticket.getResolutionCode(), "ResolutionCode should be 'Caller Closed' but was " + this.ticket.getResolutionCode());
        Assertions.assertEquals("Darien", this.ticket.getOwner(), "Owner should be updated to 'Darien' but was " + this.ticket.getOwner());
        Assertions.assertEquals("-Marcus\n-Assigned to Darien\n-Resolved\n", this.ticket.getNotes(), "Notes do not match new notes");

        this.ticket.update(new Command(Command.CommandValue.FEEDBACK, "Darien", Command.FeedbackCode.AWAITING_CALLER, null, null, "Feedback"));

        Assertions.assertEquals("Feedback", this.ticket.getState(), "State should be updated to 'Feedback' but was " + this.ticket.getState());
        Assertions.assertEquals("Awaiting Caller", this.ticket.getFeedbackCode(), "FeedbackCode should be 'Awaiting Caller' but was " + this.ticket.getFeedbackCode());
        Assertions.assertEquals("Darien", this.ticket.getOwner(), "Owner should be updated to 'Darien' but was " + this.ticket.getOwner());
        Assertions.assertEquals("-Marcus\n-Assigned to Darien\n-Resolved\n-Feedback\n", this.ticket.getNotes(), "Notes do not match new notes");

        this.ticket.update(new Command(Command.CommandValue.REOPEN, "Darien", null, null, null, "Working"));

        Assertions.assertEquals("Working", this.ticket.getState(), "State should be updated to 'Working' but was " + this.ticket.getState());
        Assertions.assertEquals("Darien", this.ticket.getOwner(), "Owner should be updated to 'Darien' but was " + this.ticket.getOwner());
        Assertions.assertEquals("-Marcus\n-Assigned to Darien\n-Resolved\n-Feedback\n-Working\n", this.ticket.getNotes(), "Notes do not match new notes");

        this.ticket.update(new Command(Command.CommandValue.FEEDBACK, "Darien", Command.FeedbackCode.AWAITING_PROVIDER, null, null, "Feedback"));

        this.ticket.update(new Command(Command.CommandValue.RESOLVE, "Darien", null, Command.ResolutionCode.SOLVED, null, "Resolved"));

        Assertions.assertEquals("Resolved", this.ticket.getState(), "State should be updated to 'Resolved' but was " + this.ticket.getState());
        Assertions.assertEquals("Solved", this.ticket.getResolutionCode(), "ResolutionCode should be 'Solved' but was " + this.ticket.getResolutionCode());
        Assertions.assertEquals("Darien", this.ticket.getOwner(), "Owner should be updated to 'Darien' but was " + this.ticket.getOwner());
        Assertions.assertEquals("-Marcus\n-Assigned to Darien\n-Resolved\n-Feedback\n-Working\n-Feedback\n-Resolved\n", this.ticket.getNotes(), "Notes do not match new notes");

        this.ticket.update(new Command(Command.CommandValue.CONFIRM, "Darien", null, null, null, "Closed"));

        Assertions.assertEquals("Closed", this.ticket.getState(), "State should be updated to 'Closed' but was " + this.ticket.getState());
        Assertions.assertEquals("Darien", this.ticket.getOwner(), "Owner should be updated to 'Darien' but was " + this.ticket.getOwner());
        Assertions.assertEquals("-Marcus\n-Assigned to Darien\n-Resolved\n-Feedback\n-Working\n-Feedback\n-Resolved\n-Closed\n", this.ticket.getNotes(), "Notes do not match new notes");

        this.ticket.update(new Command(Command.CommandValue.REOPEN, "Darien", null, null, null, "Reopen"));

        Assertions.assertEquals("Working", this.ticket.getState(), "State should be updated to 'Working' but was " + this.ticket.getState());
        Assertions.assertEquals("Darien", this.ticket.getOwner(), "Owner should be updated to 'Darien' but was " + this.ticket.getOwner());
        Assertions.assertEquals("-Marcus\n-Assigned to Darien\n-Resolved\n-Feedback\n-Working\n-Feedback\n-Resolved\n-Closed\n-Reopen\n", this.ticket.getNotes(), "Notes do not match new notes");

        this.ticket.update(new Command(Command.CommandValue.RESOLVE, "Darien", null, Command.ResolutionCode.WORKAROUND, null, "Resolved"));

        this.ticket.update(new Command(Command.CommandValue.REOPEN, "Darien", null, null, null, "Working"));

        Assertions.assertEquals("Working", this.ticket.getState(), "State should be updated to 'Working' but was " + this.ticket.getState());
        Assertions.assertEquals("Darien", this.ticket.getOwner(), "Owner should be updated to 'Darien' but was " + this.ticket.getOwner());
        Assertions.assertEquals("-Marcus\n-Assigned to Darien\n-Resolved\n-Feedback\n-Working\n-Feedback\n-Resolved\n-Closed\n-Reopen\n-Resolved\n-Working\n", this.ticket.getNotes(), "Notes do not match new notes");
    }
}