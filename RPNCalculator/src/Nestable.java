public abstract class Nestable {

    public enum NestEffect {
        OPEN, NEUTRAL, CLOSE;

        public boolean matches(NestEffect e) {
            if (e == null) throw new NullPointerException();
            return (this == CLOSE && e == OPEN) || (this == OPEN && e == CLOSE) || (this == NEUTRAL && e == NEUTRAL);
        }
    }

    protected final NestEffect effect;

    public Nestable(NestEffect effect) {
        if (effect == null) throw new IllegalArgumentException("Effect cannot be null");
        this.effect = effect;
    }

    public NestEffect getEffect() {
        return effect;
    }

    public abstract boolean matches(Nestable other);

    @Override
    public final boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || !(other instanceof Nestable)) return false;
        return effect.equals(((Nestable) other).effect) && innerEquals(other);
    }

    protected abstract boolean innerEquals(Object other);
}
