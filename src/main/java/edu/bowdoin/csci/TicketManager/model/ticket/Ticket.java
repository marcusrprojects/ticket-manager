package main.java.edu.bowdoin.csci.TicketManager.model.ticket;

import main.java.edu.bowdoin.csci.TicketManager.model.command.Command;

import java.util.ArrayList;

/**
 * Represents a ticket that our system tracks, with various
 * updating fields as the ticket is interacted with.
 * Ticket state is updated from Commands.
 *
 * @author mribeiro
 * @author Darien
 *
 */
public class Ticket {

    /**
     * Human-readable string representing the enum TicketType
     */
    public static final String TT_REQUEST = "Request";
    /**
     * Human-readable string representing the enum TicketType
     */
    public static final String TT_INCIDENT = "Incident";

    /**
     * Human-readable string representing the enum Category
     */
    public static final String C_INQUIRY = "Inquiry";
    /**
     * Human-readable string representing the enum Category
     */
    public static final String C_SOFTWARE = "Software";
    /**
     * Human-readable string representing the enum Category
     */
    public static final String C_HARDWARE = "Hardware";
    /**
     * Human-readable string representing the enum Category
     */
    public static final String C_NETWORK = "Network";
    /**
     * Human-readable string representing the enum Category
     */
    public static final String C_DATABASE = "Database";

    /**
     * Human-readable string representing the enum Priority
     */
    public static final String P_URGENT = "Urgent";

    /**
     * Human-readable string representing the enum Priority
     */
    public static final String P_HIGH = "High";

    /**
     * Human-readable string representing the enum Priority
     */
    public static final String P_MEDIUM = "Medium";

    /**
     * Human-readable string representing the enum Priority
     */
    public static final String P_LOW = "Low";

    /**
     * Human-readable string representing an implementation of TicketState
     */
    public static final String NEW_NAME = "New";

    /**
     * Human-readable string representing an implementation of TicketState
     */
    public static final String WORKING_NAME = "Working";

    /**
     * Human-readable string representing an implementation of TicketState
     */
    public static final String FEEDBACK_NAME = "Feedback";

    /**
     * Human-readable string representing an implementation of TicketState
     */
    public static final String RESOLVED_NAME = "Resolved";

    /**
     * Human-readable string representing an implementation of TicketState
     */
    public static final String CLOSED_NAME = "Closed";

    /**
     * Human-readable string representing an implementation of TicketState
     */
    public static final String CANCELED_NAME = "Canceled";

    /**
     * integer representing counter, used for implementing ticketID
     */
    private static int counter = 1;

    /**
     * integer representing ticket ID of the ticket
     */
    private final int ticketId;

    /**
     * TicketState representing state of the ticket
     */
    private TicketState state;


    /**
     * Ticket's unchangeable instance of TicketState.
     */
    private final TicketState closedState = new ClosedState();

    /**
     * Ticket's unchangeable instance of TicketState.
     */
    private final TicketState feedbackState = new FeedbackState();

    /**
     * Ticket's unchangeable instance of TicketState.
     */
    private final TicketState newState = new NewState();

    /**
     * Ticket's unchangeable instance of TicketState.
     */
    private final TicketState workingState = new WorkingState();

    /**
     * Ticket's unchangeable instance of TicketState.
     */
    private final TicketState canceledState = new CanceledState();

    /**
     * Ticket's unchangeable instance of TicketState.
     */
    private final TicketState resolvedState = new ResolvedState();

    /**
     * Human-readable string representing a subject
     */
    private String subject;

    /**
     * Human-readable string representing a caller
     */
    private String caller;

    /**
     * A category of Ticket
     */
    private Category category;

    /**
     * A priority of Ticket
     */
    private Priority priority;

    /**
     * A ticket type of Ticket
     */
    private TicketType ticketType;

    /**
     * A human-readable string representing an owner
     */
    private String owner;

    /**
     * A feedback code of Ticket
     */
    private Command.FeedbackCode feedbackCode;

    /**
     * A resolution code of Ticket
     */
    private Command.ResolutionCode resolutionCode;

    /**
     * A cancellation code of Ticket
     */
    private Command.CancellationCode cancellationCode;

    /**
     * Ticket notes
     */
    private ArrayList<String> notes;

    /**
     * increments the counter so that the id can be uniquely greater for each new ticket.
     */
    public static void incrementCounter() {
        counter++;
    }

    /**
     * sets the counter so that ticket id's will be consistent.
     * @param counter the counter corresponding with ID's
     */
    public static void setCounter(int counter) {
        if (counter <= 0) {
            throw new IllegalArgumentException("Ticket id must be a value greater than 0.");
        } else {
            Ticket.counter = counter;
        }
    }

    /**
     * Creates Ticket when reading file input, using notes.
     * TicketID becomes counter + 1 if the incoming ID is more than the current counter value.
     * Initialize other field arguments.
     *
     * @param id ID
     * @param state state
     * @param ticketType the ticket type
     * @param subject the subject
     * @param caller the caller
     * @param category the category
     * @param priority the priority
     * @param owner the owner
     * @param code the code
     * @param notes the notes
     */
    public Ticket(int id, String state, String ticketType, String subject, String caller, String category, String priority, String owner, String code, ArrayList<String> notes) {
        this.ticketId = id;

        if (id >= Ticket.counter) {
            setCounter(id + 1);
        }

        this.setState(state);
        this.setTicketType(ticketType);
        this.setSubject(subject);
        this.setCaller(caller);
        this.setCategory(category);
        this.setPriority(priority);

        if (!"New".equals(state) && !"Canceled".equals(state) && (owner == null || owner.isEmpty())) {
            throw new IllegalArgumentException("No owner in " + state + " state.");
        }

        this.setOwner(owner);

        if (CANCELED_NAME.equals(state)) {
            this.setCancellationCode(code);
        } else if (FEEDBACK_NAME.equals(state)) {
            this.setFeedbackCode(code);
        } else if (RESOLVED_NAME.equals(state) || CLOSED_NAME.equals(state)) {
            this.setResolutionCode(code);

            if (this.ticketType == TicketType.INCIDENT &&
                    !(this.getResolutionCode().equals(Command.RC_SOLVED) ||
                            this.getResolutionCode().equals(Command.RC_NOT_SOLVED) ||
                            this.getResolutionCode().equals(Command.RC_WORKAROUND) ||
                            this.getResolutionCode().equals(Command.RC_CALLER_CLOSED))) {
                throw new IllegalArgumentException("Invalid code for Resolved Ticket of Incident type.");
            } else if (this.ticketType == TicketType.REQUEST && !(this.getResolutionCode().equals(Command.RC_COMPLETED) ||
                    this.getResolutionCode().equals(Command.RC_NOT_COMPLETED) ||
                    this.getResolutionCode().equals(Command.RC_CALLER_CLOSED))) {
                throw new IllegalArgumentException("Invalid code for Resolved Ticket of Request type.");
            }
        }

        this.notes = notes;
    }

    /**
     * Makes ticket with parameters, producing IllegalArgumentException when necessary.
     * TicketID becomes Ticket.counter as counter increments.
     * Initialize other field arguments.
     *
     * @param ticketType ticket Type
     * @param subject subject
     * @param caller caller
     * @param category category
     * @param priority priority
     * @param note note
     */
    public Ticket(TicketType ticketType, String subject, String caller, Category category, Priority priority, String note) {
        this.ticketId = counter;
        incrementCounter();

        if (ticketType == null) throw new IllegalArgumentException("TicketType cannot be null.");

        if (category == null) throw new IllegalArgumentException("Category cannot be null.");

        if (priority == null) throw new IllegalArgumentException("Priority cannot be null.");

        if (note == null || note.isEmpty()) throw new IllegalArgumentException("Note cannot be null.");

        this.state = this.newState;
        this.ticketType = ticketType;
        this.setSubject(subject);
        this.setCaller(caller);
        this.category = category;
        this.priority = priority;
        this.setOwner("");
        this.notes = new ArrayList<>();
        this.notes.add(note);
    }

    /**
     * gets the caller
     * @return the caller
     */
    public String getCaller() {
        return this.caller;
    }

    /**
     * gets the cancellation code
     * @return the cancellation code
     */
    public String getCancellationCode() {
        if (this.cancellationCode == null) return null;

        return switch (this.cancellationCode) {
            case DUPLICATE -> Command.CC_DUPLICATE;
            case INAPPROPRIATE -> Command.CC_INAPPROPRIATE;
            default -> throw new IllegalArgumentException("Invalid cancellation code.");
        };
    }

    /**
     * gets the category
     * @return the category
     */
    public String getCategory() {
        return switch (this.category) {
            case INQUIRY -> C_INQUIRY;
            case SOFTWARE -> C_SOFTWARE;
            case HARDWARE -> C_HARDWARE;
            case NETWORK -> C_NETWORK;
            case DATABASE -> C_DATABASE;
            default -> throw new IllegalArgumentException("Invalid category.");
        };
    }

    /**
     * gets the feedback code
     * @return the feedback code
     */
    public String getFeedbackCode() {
        if (this.feedbackCode == null) return null;

        return switch (this.feedbackCode) {
            case AWAITING_CALLER -> Command.F_CALLER;
            case AWAITING_CHANGE -> Command.F_CHANGE;
            case AWAITING_PROVIDER -> Command.F_PROVIDER;
            default -> throw new IllegalArgumentException("Invalid feedback code.");
        };
    }

    /**
     * gets the notes
     * @return the notes
     */
    public String getNotes() {
        StringBuilder noteString = new StringBuilder();
        for (String note : this.notes) {
            noteString.append("-").append(note).append("\n");
        }
        return noteString.toString();
    }

    /**
     * gets the owner
     * @return the owner
     */
    public String getOwner() {
        return this.owner;
    }

    /**
     * gets the priority
     * @return the priority
     */
    public String getPriority() {
        return switch (this.priority) {
            case URGENT -> P_URGENT;
            case HIGH -> P_HIGH;
            case MEDIUM -> P_MEDIUM;
            case LOW -> P_LOW;
            default -> throw new IllegalArgumentException("Invalid priority.");
        };
    }

    /**
     * gets the resolution code
     * @return resolution code
     */
    public String getResolutionCode() {
        if (this.resolutionCode == null) return null;

        return switch (this.resolutionCode) {
            case CALLER_CLOSED -> Command.RC_CALLER_CLOSED;
            case WORKAROUND -> Command.RC_WORKAROUND;
            case SOLVED -> Command.RC_SOLVED;
            case NOT_SOLVED -> Command.RC_NOT_SOLVED;
            case COMPLETED -> Command.RC_COMPLETED;
            case NOT_COMPLETED -> Command.RC_NOT_COMPLETED;
            default -> throw new IllegalArgumentException("Invalid resolution code.");
        };
    }

    /**
     * gets the state
     * @return the state
     */
    public String getState() {
        return this.state.getStateName();
    }

    /**
     * gets the subject
     * @return the subject
     */
    public String getSubject() {
        return this.subject;
    }

    /**
     * gets the ticketId
     * @return the ticketId
     */
    public int getTicketId() {
        return this.ticketId;
    }

    /**
     * gets the ticket type
     * @return the ticket type
     */
    public TicketType getTicketType() {
        return this.ticketType;
    }

    /**
     * gets the ticket type string
     * @return the ticket type string
     */
    public String getTicketTypeString() {
        return switch (this.getTicketType()) {
            case INCIDENT -> TT_INCIDENT;
            case REQUEST -> TT_REQUEST;
            default -> throw new IllegalArgumentException("Not a valid ticket type.");
        };
    }

    /**
     * sets caller
     * @param caller the caller
     */
    private void setCaller(String caller) {
        if (caller == null || caller.isEmpty()) {
            throw new IllegalArgumentException("Caller must be a String of length 1 or greater.");
        } else {
            this.caller = caller;
        }
    }

    /**
     * sets cancellation code
     * @param cancellationCode cancellation code
     */
    private void setCancellationCode(String cancellationCode) {
        if (Command.CC_DUPLICATE.equals(cancellationCode)) {
            this.cancellationCode = Command.CancellationCode.DUPLICATE;
        } else if (Command.CC_INAPPROPRIATE.equals(cancellationCode)) {
            this.cancellationCode = Command.CancellationCode.INAPPROPRIATE;
        } else {
            throw new IllegalArgumentException("Invalid CancellationCode.");
        }
    }

    /**
     * sets category
     * @param category category
     */
    private void setCategory(String category) {
        if (C_INQUIRY.equals(category)) {
            this.category = Category.INQUIRY;
        } else if (C_SOFTWARE.equals(category)) {
            this.category = Category.SOFTWARE;
        } else if (C_HARDWARE.equals(category)) {
            this.category = Category.HARDWARE;
        } else if (C_NETWORK.equals(category)) {
            this.category = Category.NETWORK;
        } else if (C_DATABASE.equals(category)) {
            this.category = Category.DATABASE;
        } else {
            throw new IllegalArgumentException("Invalid Category.");
        }
    }

    /**
     * sets owner
     * @param owner owner
     */
    private void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * sets feedback code
     * @param feedbackCode feedback code
     */
    private void setFeedbackCode(String feedbackCode) {
        if (Command.F_CALLER.equals(feedbackCode)) {
            this.feedbackCode = Command.FeedbackCode.AWAITING_CALLER;
        } else if (Command.F_CHANGE.equals(feedbackCode)) {
            this.feedbackCode = Command.FeedbackCode.AWAITING_CHANGE;
        } else if (Command.F_PROVIDER.equals(feedbackCode)) {
            this.feedbackCode = Command.FeedbackCode.AWAITING_PROVIDER;
        } else {
            throw new IllegalArgumentException("Invalid FeedbackCode.");
        }
    }

    /**
     * sets priority
     * @param priority priority
     */
    private void setPriority(String priority) {
        if (P_URGENT.equals(priority)) {
            this.priority = Priority.URGENT;
        } else if (P_HIGH.equals(priority)) {
            this.priority = Priority.HIGH;
        } else if (P_MEDIUM.equals(priority)) {
            this.priority = Priority.MEDIUM;
        } else if (P_LOW.equals(priority)) {
            this.priority = Priority.LOW;
        } else {
            throw new IllegalArgumentException("Invalid Priority.");
        }
    }

    /**
     * sets resolution code
     * @param resolutionCode resolution code
     */
    private void setResolutionCode(String resolutionCode) {
        if (Command.RC_CALLER_CLOSED.equals(resolutionCode)) {
            this.resolutionCode = Command.ResolutionCode.CALLER_CLOSED;
        } else if (Command.RC_WORKAROUND.equals(resolutionCode)) {
            this.resolutionCode = Command.ResolutionCode.WORKAROUND;
        } else if (Command.RC_SOLVED.equals(resolutionCode)) {
            this.resolutionCode = Command.ResolutionCode.SOLVED;
        } else if (Command.RC_NOT_SOLVED.equals(resolutionCode)) {
            this.resolutionCode = Command.ResolutionCode.NOT_SOLVED;
        } else if (Command.RC_COMPLETED.equals(resolutionCode)) {
            this.resolutionCode = Command.ResolutionCode.COMPLETED;
        } else if (Command.RC_NOT_COMPLETED.equals(resolutionCode)) {
            this.resolutionCode = Command.ResolutionCode.NOT_COMPLETED;
        } else {
            throw new IllegalArgumentException("Invalid ResolutionCode.");
        }
    }

    /**
     * sets state
     * @param state state
     */
    private void setState(String state) {
        if (NEW_NAME.equals(state)) {
            this.state = this.newState;
        } else if (WORKING_NAME.equals(state)) {
            this.state = this.workingState;
        } else if (FEEDBACK_NAME.equals(state)) {
            this.state = this.feedbackState;
        } else if (RESOLVED_NAME.equals(state)) {
            this.state = this.resolvedState;
        } else if (CLOSED_NAME.equals(state)) {
            this.state = this.closedState;
        } else if (CANCELED_NAME.equals(state)) {
            this.state = this.canceledState;
        } else {
            throw new IllegalArgumentException("Invalid State.");
        }
    }

    /**
     * sets subject
     * @param subject subject
     */
    private void setSubject(String subject) {
        if (subject == null || subject.isEmpty()) {
            throw new IllegalArgumentException("Subject must be a String of length 1 or greater.");
        } else {
            this.subject = subject;
        }
    }

    /**
     * sets ticket type
     * @param ticketType ticket type
     */
    private void setTicketType(String ticketType) {
        if (TT_INCIDENT.equals(ticketType)) {
            this.ticketType = TicketType.INCIDENT;
        } else if (TT_REQUEST.equals(ticketType)) {
            this.ticketType = TicketType.REQUEST;
        } else {
            throw new IllegalArgumentException("Invalid Ticket Type.");
        }
    }

    /**
     * creates a string representing ticket's attributes
     * using getNotes()
     * @return string representing ticket's attributes
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('*').append(this.ticketId);
        stringBuilder.append('#').append(this.getState());
        stringBuilder.append('#').append(this.getTicketTypeString());
        stringBuilder.append('#').append(this.getSubject());
        stringBuilder.append('#').append(this.getCaller());
        stringBuilder.append('#').append(this.getCategory());
        stringBuilder.append('#').append(this.getPriority());
        stringBuilder.append('#');
        if (this.getOwner() != null) stringBuilder.append(this.getOwner());

        stringBuilder.append('#');

        if (this.state instanceof CanceledState) {
            stringBuilder.append(this.getCancellationCode());
        } else if (this.state instanceof FeedbackState) {
            stringBuilder.append(this.getFeedbackCode());
        } else if (this.state instanceof ResolvedState || this.state instanceof ClosedState) {
            stringBuilder.append(this.getResolutionCode());
        }

        stringBuilder.append('\n').append(this.getNotes());

        return stringBuilder.toString();
    }

    /**
     * updates the command
     * @param command command
     */
    public void update(Command command) {
        this.state.updateState(command);

        if ((this.state instanceof FeedbackState || this.state instanceof WorkingState) && command.getCommand() == Command.CommandValue.RESOLVE) {
            if (this.ticketType == TicketType.INCIDENT &&
                    !(command.getResolutionCode() == Command.ResolutionCode.SOLVED ||
                            command.getResolutionCode() == Command.ResolutionCode.NOT_SOLVED ||
                            command.getResolutionCode() == Command.ResolutionCode.WORKAROUND ||
                            command.getResolutionCode() == Command.ResolutionCode.CALLER_CLOSED)) {
                throw new UnsupportedOperationException("Invalid code for Feedback to Resolved transition of Incident type.");
            } else if (this.ticketType == TicketType.REQUEST && command.getResolutionCode() != Command.ResolutionCode.COMPLETED &&
                    command.getResolutionCode() != Command.ResolutionCode.NOT_COMPLETED &&
                    command.getResolutionCode() != Command.ResolutionCode.CALLER_CLOSED) {
                throw new UnsupportedOperationException("Invalid code for Feedback to Resolved transition of Request type.");
            }
        }

        switch (command.getCommand()) {
            case PROCESS:
            case REOPEN:
                this.setState("Working");
                break;
            case FEEDBACK:
                this.setState("Feedback");
                break;
            case RESOLVE:
                this.setState("Resolved");
                break;
            case CONFIRM:
                this.setState("Closed");
                break;
            case CANCEL:
                this.setState("Canceled");
                break;
            default:
                throw new UnsupportedOperationException("Invalid CommandType.");
        }

        this.feedbackCode = command.getFeedbackCode();
        if (command.getCommand() != Command.CommandValue.CONFIRM) this.resolutionCode = command.getResolutionCode();
        this.cancellationCode = command.getCancellationCode();
        this.notes.add(command.getNote());
        if (command.getOwnerId() != null) this.setOwner(command.getOwnerId());
    }


    /**
     * An enum representing the different possible categories of any given Ticket
     */
    public enum Category { INQUIRY, SOFTWARE, HARDWARE, NETWORK, DATABASE }

    /**
     * An enum representing the different possible priorities of any given Ticket
     */
    public enum Priority { URGENT, HIGH, MEDIUM, LOW }

    /**
     * An enum representing the two different types of Tickets
     */
    public enum TicketType { REQUEST, INCIDENT }

    /**
     * A class representing the Closed state of a Ticket
     */
    public static class ClosedState implements TicketState {
        private ClosedState() {
        }

        @Override
        public void updateState(Command command) {
            if (command.getCommand() != Command.CommandValue.REOPEN) {
                throw new UnsupportedOperationException("Invalid CommandValue for ClosedState.");
            }
        }

        @Override
        public String getStateName() {
            return "Closed";
        }
    }

    /**
     * A class representing the Feedback state of a Ticket
     */
    public static class FeedbackState implements TicketState {
        private FeedbackState() {
        }

        @Override
        public void updateState(Command command) {
            Command.CommandValue value = command.getCommand();
            if (value != Command.CommandValue.CANCEL && value != Command.CommandValue.RESOLVE && value != Command.CommandValue.REOPEN) {
                throw new UnsupportedOperationException("Invalid CommandValue for FeedbackState.");
            }
        }

        @Override
        public String getStateName() {
            return "Feedback";
        }
    }

    /**
     * A class representing the Canceled state of a Ticket
     */
    public static class CanceledState implements TicketState {
        private CanceledState() {
        }

        @Override
        public void updateState(Command command) {
            throw new UnsupportedOperationException("Invalid CommandValue for CanceledState.");
        }

        @Override
        public String getStateName() {
            return "Canceled";
        }
    }

    /**
     * A class representing the Resolved state of a Ticket
     */
    public static class ResolvedState implements TicketState {
        private ResolvedState() {
        }

        @Override
        public void updateState(Command command) {
            Command.CommandValue value = command.getCommand();
            if (value != Command.CommandValue.REOPEN && value != Command.CommandValue.FEEDBACK && value != Command.CommandValue.CONFIRM) {
                throw new UnsupportedOperationException("Invalid CommandValue for ResolvedState.");
            }
        }

        @Override
        public String getStateName() {
            return "Resolved";
        }
    }

    /**
     * A class representing the Working state of a Ticket
     */
    public static class WorkingState implements TicketState {
        private WorkingState() {
        }

        @Override
        public void updateState(Command command) {
            Command.CommandValue value = command.getCommand();
            if (value != Command.CommandValue.CANCEL && value != Command.CommandValue.RESOLVE && value != Command.CommandValue.FEEDBACK) {
                throw new UnsupportedOperationException("Invalid CommandValue for WorkingState.");
            }
        }

        @Override
        public String getStateName() {
            return "Working";
        }
    }

    /**
     * A class representing the New state of a Ticket
     */
    public static class NewState implements TicketState {
        private NewState() {
        }

        @Override
        public void updateState(Command command) {
            Command.CommandValue value = command.getCommand();
            if (value != Command.CommandValue.PROCESS && value != Command.CommandValue.CANCEL) {
                throw new UnsupportedOperationException("Invalid CommandValue for ClosedState.");
            }
        }

        @Override
        public String getStateName() {
            return "New";
        }
    }
}