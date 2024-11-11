import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class NestableCharacter extends Nestable {

    // Define syntax pairs for nesting characters (e.g., parentheses, brackets)
    private static final char[][] syntaxPairs = {{'(', ')'}, {'[', ']'}, {'{', '}'}};
    private static final Map<Character, Character> mSyntaxPairs = new HashMap<>();
    private static final Map<Character, NestEffect> charType = new HashMap<>();

    // Populate the lookup tables for character pairing and nesting effects
    static {
        for (char[] pair : syntaxPairs) {
            charType.put(pair[0], NestEffect.OPEN);    // Assign OPEN effect to opening characters
            charType.put(pair[1], NestEffect.CLOSE);   // Assign CLOSE effect to closing characters
            mSyntaxPairs.put(pair[0], pair[1]);        // Map each opening character to its closing pair
            mSyntaxPairs.put(pair[1], pair[0]);        // Map each closing character to its opening pair
        }
    }

    // The specific character value represented by this NestableCharacter instance
    final char value;

    // Constructor initializes a NestableCharacter with a character and assigns its nesting effect
    public NestableCharacter(char c) {
        super(charType.getOrDefault(c, NestEffect.NEUTRAL));  // Default to NEUTRAL if no matching effect is found
        value = c;
    }

    // Overloaded constructor to handle input as an integer, commonly used for stream reading
    public NestableCharacter(int i) {
        this((char) i);  // Cast the integer to a character and delegate to the main constructor
    }

    // Defines matching logic specific to NestableCharacter based on character pairs and effects
    @Override
    public boolean matches(Nestable other) {
        // Ensure 'other' is a NestableCharacter, has matching effects, and is a complementary pair
        if (other == null || !(other instanceof NestableCharacter) || !this.effect.matches(other.effect))
            return false;

        // Check if the characters form a complementary pair using the syntax map
        var otherValue = ((NestableCharacter) other).value;
        return value == mSyntaxPairs.getOrDefault(otherValue, otherValue);
    }

    // Custom equality logic for NestableCharacter, comparing only the character values
    @Override
    protected boolean innerEquals(Object other) {
        return other instanceof NestableCharacter && value == ((NestableCharacter) other).value;
    }

    // Provides a unique hash code based on the character value
    @Override
    public int hashCode() {
        return Character.hashCode(value);
    }

    // Returns a string representation of the NestableCharacter, showing its character and effect
    @Override
    public String toString() {
        return "'" + value + "':" + effect;
    }

    // Reads characters from a file and converts them to a queue of NestableCharacter instances
    public static Queue<NestableCharacter> getNestableCharactersFromFile(String filename) throws IOException {
        Queue<NestableCharacter> out = new LinkedList<>();  // Queue to store parsed characters
        FileReader in = new FileReader(filename);           // Reader for the input file

        // Read each character and add as a new NestableCharacter to the queue
        while (in.ready())
            out.add(new NestableCharacter(in.read()));
        in.close();  // Close the file reader

        return out;  // Return the queue of characters
    }
}
