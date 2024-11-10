
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.List;

class RpnCalculatorTest {
	
	@Test
	void testRpnCalculator() {


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
