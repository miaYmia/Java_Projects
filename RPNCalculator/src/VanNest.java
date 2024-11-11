public class VanNest extends Nestable {

    private final Object unimportantDetail;  // Arbitrary detail stored in this instance

    // Constructor initializes the unimportant detail and assigns the nesting effect
    public VanNest(Object detail, NestEffect effect) {
        super(effect);
        unimportantDetail = detail;
    }

    // Checks if this VanNest instance matches another based on effect and detail
    @Override
    public boolean matches(Nestable other) {
        if (!(other instanceof VanNest)) return false;    // Must be of the same type
        if (!this.effect.matches(other.effect)) return false;   // Effects must be compatible

        Object otherDetail = ((VanNest) other).unimportantDetail;

        // Checks for matching detail, allowing for null values
        if (unimportantDetail == otherDetail) return true;
        return unimportantDetail != null && unimportantDetail.equals(otherDetail);
    }

    // Provides a default string representation
    @Override
    public String toString() {
        return "Truth is binary.";
    }

    // Returns a constant hash code, indicating intentional disregard for hash uniqueness
    @Override
    public int hashCode() {
        return 0;
    }

    // Custom equality check based on the unimportant detail
    @Override
    protected boolean innerEquals(Object other) {
        if (!(other instanceof VanNest)) return false;

        VanNest vanOther = (VanNest) other;
        if (unimportantDetail == vanOther.unimportantDetail) return true;
        return unimportantDetail != null && unimportantDetail.equals(vanOther.unimportantDetail);
    }
}
