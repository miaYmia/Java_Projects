import java.util.Objects;
import java.util.Stack;

public class NestingReport {

    // Enumeration representing the various possible states of a nesting report
    public enum Status {
        VALID, NULL_INPUT, NULL_ITEM, INVALID_CLOSE, NOT_TERMINATED
    }

    // The final status of the nesting check (non-nullable)
    private final Status status;

    // Item that caused an error in case of an invalid closing operation (nullable)
    private final Nestable badItem;

    // Current state of the stack at the time of report generation (nullable)
    private final Stack<? extends Nestable> stackState;

    // Constructor initializes the nesting report with a specific status, an optional bad item, and stack state
    public NestingReport(Status status, Nestable badItem, Stack<? extends Nestable> stackState) {
        // Ensure the status is not null to avoid null-related issues
        if (status == null)
            throw new IllegalArgumentException("Status cannot be null");
        this.status = status;
        this.badItem = badItem;
        this.stackState = stackState;
    }

    // Returns the status of the nesting report
    public Status getStatus() {
        return status;
    }

    // Returns the problematic item if the status is INVALID_CLOSE; otherwise, returns null
    public Nestable getBadItem() {
        return badItem;
    }

    // Returns the stack state at the time the report was created
    public Stack<? extends Nestable> getStackState() {
        return stackState;
    }

    // Checks equality based on status, bad item, and stack state
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NestingReport that = (NestingReport) o;
        return status == that.status &&
                Objects.equals(badItem, that.badItem) &&
                Objects.equals(stackState, that.stackState);
    }

    // Generates a hash code based on the status, bad item, and stack state
    @Override
    public int hashCode() {
        return Objects.hash(status, badItem, stackState);
    }

    // Provides a string representation of the NestingReport, showing the status, bad item, and stack state
    @Override
    public String toString() {
        return "NestingReport{" +
                "status=" + status +
                ", badItem=" + badItem +
                ", stackState=" + stackState +
                '}';
    }
}
