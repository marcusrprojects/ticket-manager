package main.java.edu.bowdoin.csci.TicketManager.model.command;

/**
 * Creates objects that encapsulates user actions or transitions and updates {@link main.java.edu.bowdoin.csci.TicketManager.model.ticket.Ticket} states
 *
 * @author Darien
 */
public class Command {
    /**
     * Human-readable string representing the FeedbackCode enum AWAITING_CALLER
     */
    public static final String F_CALLER = "Awaiting Caller";

    /**
     * Human-readable string representing the FeedbackCode enum AWAITING_CHANGE
     */
    public static final String F_CHANGE = "Awaiting Change";

    /**
     * Human-readable string representing the FeedbackCode enum AWAITING_PROVIDER
     */
    public static final String F_PROVIDER = "Awaiting Provider";

    /**
     * Human-readable string representing the ResolutionCode enum COMPLETED
     */
    public static final String RC_COMPLETED = "Completed";

    /**
     * Human-readable string representing the ResolutionCode enum NOT_COMPLETED
     */
    public static final String RC_NOT_COMPLETED = "Not Completed";

    /**
     * Human-readable string representing the ResolutionCode enum SOLVED
     */
    public static final String RC_SOLVED = "Solved";

    /**
     * Human-readable string representing the ResolutionCode enum WORKAROUND
     */
    public static final String RC_WORKAROUND = "Workaround";

    /**
     * Human-readable string representing the ResolutionCode enum NOT_SOLVED
     */
    public static final String RC_NOT_SOLVED = "Not Solved";

    /**
     * Human-readable string representing the ResolutionCode enum CALLER_CLOSED
     */
    public static final String RC_CALLER_CLOSED = "Caller Closed";

    /**
     * Human-readable string representing the CancellationCode enum DUPLICATE
     */
    public static final String CC_DUPLICATE = "Duplicate";

    /**
     * Human-readable string representing the CancellationCode enum INAPPROPRIATE
     */
    public static final String CC_INAPPROPRIATE = "Inappropriate";

    /**
     * The CommandValue of this command.
     */
    private final CommandValue c;

    /**
     * A String holding the ID of the owner of this command
     */
    private final String ownerId;

    /**
     * The FeedbackCode for this command.
     */
    private final FeedbackCode feedbackCode;

    /**
     * The ResolutionCode for this command.
     */
    private final ResolutionCode resolutionCode;

    /**
     * The CancellationCode for this command.
     */
    private final CancellationCode cancellationCode;

    /**
     * A String containing the note provided by the owner when making the command
     */
    private final String note;

    /**
     * Creates a new command based on the desired action of the owner
     * @param c the type of the Command to be performed
     * @param ownerId the ID of the person sending the command
     * @param feedbackCode the FeedbackCode if the command has a Feedback value, otherwise null
     * @param resolutionCode the ResolutionCode if the command has a Resolve value, otherwise null
     * @param cancellationCode the CancellationCode if the command has a Cancel value, otherwise null
     * @param note the note for the Command, cannot be empty or null
     */
    public Command(CommandValue c, String ownerId, FeedbackCode feedbackCode, ResolutionCode resolutionCode, CancellationCode cancellationCode, String note) {
        if (c == null) {
            throw new IllegalArgumentException("CommandValue cannot be null.");
        } else if (c == CommandValue.PROCESS && (ownerId == null || ownerId.isEmpty())) {
            throw new IllegalArgumentException("Owner ID must be non-null and non-empty for CommandValue PROCESS.");
        } else if (c == CommandValue.FEEDBACK && feedbackCode == null) {
            throw new IllegalArgumentException("FeedbackCode cannot be null for CommandValue FEEDBACK.");
        } else if (c == CommandValue.RESOLVE && resolutionCode == null) {
            throw new IllegalArgumentException("ResolutionCode cannot be null for CommandValue RESOLVE.");
        } else if (c == CommandValue.CANCEL && cancellationCode == null) {
            throw new IllegalArgumentException("CancellationCode cannot be null for CommandValue CANCEL.");
        } else if (note == null || note.isEmpty()) {
            throw new IllegalArgumentException("Note must not be empty or null.");
        }

        this.c = c;
        this.ownerId = ownerId;
        this.feedbackCode = feedbackCode;
        this.resolutionCode = resolutionCode;
        this.cancellationCode = cancellationCode;
        this.note = note;
    }

    /**
     * Fetches the type of the Command
     * @return the value of the Command
     */
    public CommandValue getCommand() {
        return this.c;
    }

    /**
     * Fetches the ID of the owner of this Command
     * @return the owner's ID
     */
    public String getOwnerId() {
        return this.ownerId;
    }

    /**
     * Fetches the ResolutionCode of this Command
     * @return the ResolutionCode of this Command, should be null if CommandValue is not RESOLVE
     */
    public ResolutionCode getResolutionCode() {
        return this.resolutionCode;
    }

    /**
     * Fetches the Note associated with this Command
     * @return the note of this Command
     */
    public String getNote() {
        return this.note;
    }

    /**
     * Fetches the FeedbackCode of this Command
     * @return the FeedbackCode of this Command, should be null if CommandValue is not FEEDBACK
     */
    public FeedbackCode getFeedbackCode() {
        return this.feedbackCode;
    }

    /**
     * Fetches the CancellationCode of this Command
     * @return the CancellationCode of this Command, should be null if CommandValue is not CANCEL
     */
    public CancellationCode getCancellationCode() {
        return this.cancellationCode;
    }

    /**
     * Represents possible values for the action the Command should perform
     */
    public enum CommandValue { PROCESS, FEEDBACK, RESOLVE, CONFIRM, REOPEN, CANCEL }

    /**
     * Contains possible codes for Commands of the FEEDBACK type
     */
    public enum FeedbackCode { AWAITING_CALLER, AWAITING_CHANGE, AWAITING_PROVIDER }

    /**
     * Contains possible codes for Commands of the RESOLVE type
     */
    public enum ResolutionCode { COMPLETED, NOT_COMPLETED, SOLVED, WORKAROUND, NOT_SOLVED, CALLER_CLOSED }

    /**
     * Contains possible codes for Commands of the CANCEL type
     */
    public enum CancellationCode { DUPLICATE, INAPPROPRIATE }
}