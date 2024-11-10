

import org.junit.jupiter.api.*;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions. *;


class NestingCheckerTest {

    @Test
    void testNestingChecker() {

		/*
		 test a valid case
		 */
        Queue<Nestable> elements = new LinkedList<>();
        elements.add(new NestableCharacter('(')); //OPEN
        elements.add(new NestableCharacter(')')); //CLOSE
        elements.add(new NestableCharacter('h')); //random char in the middle
        elements.add(new NestableCharacter('{')); //OPEN
        elements.add(new NestableCharacter('}')); //CLOSE

        NestingReport result = NestingChecker.checkNesting(elements);
        // result should be valid
        assertEquals(NestingReport.Status.VALID, result.getStatus(), "This is a valid nesting");
        // and there is no bad item
        assertNull(result.getBadItem(), "There should be no bad item");
        // and the stack should be empty in the end
        assertTrue(result.getStackState().isEmpty(), "the stack should be empty");

		/*
		 test the queue and stack are empty
		 */
        Queue<Nestable> elements2 = new LinkedList<>();
        NestingReport result2 = NestingChecker.checkNesting(elements2);
        // result should be valid
        assertEquals(NestingReport.Status.VALID, result2.getStatus(), "This is a valid nesting");
        // and there is no bad item
        assertNull(result2.getBadItem(), "There should be no bad item");
        // and the stack should be empty in the end
        assertTrue(result2.getStackState().isEmpty(), "the stack should be empty");


		/*
		test elements in the queue is null (null item)
		 */
        Queue<Nestable> elements3 = new LinkedList<>();
        elements3.add(new NestableCharacter('(')); //OPEN
        elements3.add(new NestableCharacter(')')); //CLOSE
        elements3.add(null); //random char in the middle
        elements3.add(new NestableCharacter('{')); //OPEN
        elements3.add(new NestableCharacter('}')); //CLOSE

        NestingReport result3 = NestingChecker.checkNesting(elements3);
        // the status should be NULL_ITEM
        assertEquals(NestingReport.Status.NULL_ITEM, result3.getStatus(), "The status should be NULL_ITEM");
        // the bad item should be null
        assertNull(result3.getBadItem(), "The bad item should be null");


        /*
        test queue has invalid close element (invalid close)
         */
        Queue<Nestable> elements4 = new LinkedList<>();
        elements4.add(new NestableCharacter('(')); //OPEN
        elements4.add(new NestableCharacter(')')); //CLOSE
        elements4.add(null); //random char in the middle
        elements4.add(new NestableCharacter('}')); //CLOSE

        NestingReport result4 = NestingChecker.checkNesting(elements4);
        // the status should be INULL_ITEM
        assertEquals(NestingReport.Status.NULL_ITEM, result4.getStatus(), "Status should be NULL_ITEM");
        // check the stack is empty
        assertTrue(result4.getStackState().isEmpty(), "There was an OPEN and a CLOSE before the bad item, stack should be empty");


		/*
		test the queue is empty but stack is not (not terminated)
		 */
        Queue<Nestable> elements5 = new LinkedList<>();
        elements5.add(new NestableCharacter('(')); //OPEN
        elements5.add(new NestableCharacter('{')); //CLOSE

        NestingReport result5 = NestingChecker.checkNesting(elements5);
        // the queue has a { without a closer, we should get the NOT_TERMINATED status
        assertEquals(NestingReport.Status.NOT_TERMINATED, result5.getStatus(), "Status should be NOT_TERMINATED");
        //the bad item should be null
        assertNull(result5.getBadItem(), "The bad item should be null");


    }

}
