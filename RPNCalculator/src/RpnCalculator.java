/*
 * I attest that the code in this file is entirely my own except for the starter
 * code provided with the assignment and the following exceptions:
 * <Enter all external resources and collaborations here. Note external code may
 * reduce your score but appropriate citation is required to avoid academic
 * integrity violations. Please see the Course Syllabus as well as the
 * university code of academic integrity:
 *  https://catalog.upenn.edu/pennbook/code-of-academic-integrity/ >
 * Signed,
 * Author: Mia Yuan
 * Penn email: <miayuan@seas.upenn.edu>
 * Date: 2024-09-15
 */

import java.util.List;
import java.util.Stack;


public class RpnCalculator {

    /**
     * Evaluates a reverse polish notation expression
     *
     * @param expression
     * @return an integer of the result of the evaluation
     */
    public Integer evaluateExpression(List<String> expression) {
        // first check if expression is null
        if (expression == null) {
            return null;
        }
        // create stack to hold the RPN evaluation
        Stack<Integer> stack = new Stack<>();

        // iterate through the expression
        for (String token : expression) {
            if (token == null) {
                return null; // if there is null token, terminate and return null
            }

            // now we check if the token is an operator (+-/8) or not and perform appropriate action
            if (token.equals("+") || token.equals("-") || token.equals("/") || token.equals("*")) {
                // we can assume there are two ints in the stack
                int b = stack.pop(); // this is the right operand,
                int a = stack.pop(); // this is the left operand

                if (token.equals("+")) {
                    stack.push(a + b); // add a and b and push the result on the stack
                } else if (token.equals("-")) {
                    stack.push(a - b); // subtract b from a and push the result on the stack
                } else if (token.equals("*")) {
                    stack.push(a * b);
                } else { // only other possibility is operator is /
                    if (b == 0) { // handle divide-by-zero
                        return null;
                    }
                    stack.push(a / b); // divide a by b and push the result on the stack
                }

            } else { // if token is not an operator
                int number = Integer.parseInt(token); // instruction says this can for sure be parsed into in
                stack.push(number); // add this number to stack
            }

            // I know this should never happen per instructions, but let's just make sure

            }
        if (stack.size() != 1) { // after processing all tokens, stack should have exactly one element
            return null; // if not, terminate and return null

        }
        return stack.pop(); // after iterating through expression, the top of the stack should be the final result
    }
}