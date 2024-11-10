/*
 ***** Important!  Please Read! *****
 *
 *  - Do NOT remove any of the existing import statements
 *  - Do NOT import additional junit packages 
 *  - You MAY add in other non-junit packages as needed
 * 
 *  - Do NOT remove any of the existing test methods or change their name
 *  - You MAY add additional test methods.  If you do, they should all pass
 * 
 *  - ALL of your assert test cases within each test method MUST pass, otherwise the 
 *        autograder will fail that test method
 *  - You MUST write the require number of assert test cases in each test method, 
 *        otherwise the autograder will fail that test method
 *  - You MAY write more than the required number of assert test cases as long as they all pass
 * 
 *  - All of your assert test cases within a method must be related to the method they are meant to test
 *  - All of your assert test cases within a method must be distinct and non-trivial
 *  - Your test cases should reflect the method requirements in the homework instruction specification
 * 
 *  - Your assert test cases will be reviewed by the course instructors and they may take off
 *        points if your assert test cases to do not meet the requirements
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.List;

class RpnCalculatorTest {
	
	@Test
	void testRpnCalculator() {
		// TODO
		/* 
		 * Write at least 5 assert test cases that test your "evaluateExpression" method in the "RpnCalculator" class 
		 * Review the homework instructions and write assert test realated the this methods specification
		 * All 5 assert statements MUST pass.
		 */

		// create new calculator object
		RpnCalculator myCalculator = new RpnCalculator();

		// test integer division
		List<String> expression1 = List.of("3", "2", "/"); // output should be 1
		assertEquals(1, myCalculator.evaluateExpression(expression1), "3 /2 is 1");

		// test divide by 0
		List<String> expression2 = List.of("3", "0", "/"); // output should be null
        assertNull(myCalculator.evaluateExpression(expression2), "divide by 0 should return null");

		// test simple regular expression
		List<String> expression3 = List.of("3", "2", "+"); // output should be 5
		assertEquals(5, myCalculator.evaluateExpression(expression3), "3 + 2 is 5");

		// test a more complex expression
		List<String> expression4 = List.of("5", "1", "2", "+", "4", "*", "+", "3", "-"); // output should be 14
		assertEquals(14, myCalculator.evaluateExpression(expression4), "Output should be 14");

		// test null expression
		List<String> expression5 = null;
        assertNull(myCalculator.evaluateExpression(expression5), "Output should be null");
	}

}
