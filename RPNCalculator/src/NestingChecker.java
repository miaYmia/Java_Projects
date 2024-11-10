

import java.util.Queue;
import java.util.Stack;

public class NestingChecker {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 123674589918L;

   
    public static NestingReport checkNesting(Queue<? extends Nestable> elements) {
        // if the queue is null, return null_input status
        if (elements == null) {
            return new NestingReport(NestingReport.Status.NULL_INPUT, null, new Stack<>());
        }

        // intialize a new stack to keep track of all the elements
        Stack<Nestable> stack = new Stack<>();

        // now we iterate through the elements, and do things to it depending on the status of the element
        for (Nestable element : elements) {

            // if the element is null, return NULL_ITEM status
            if (element == null) {
                Stack<Nestable> currentStack = new Stack<>();
                currentStack.addAll(stack);
                return new NestingReport(NestingReport.Status.NULL_ITEM, element, currentStack);
            }

            // if the element is an OPEN element, push it onto the stack
            if (element.getEffect() == Nestable.NestEffect.OPEN) {
                stack.push(element);
            }

            // if we encounter a CLOSE element, we do things depends on if there is an OPEN element
            else if (element.getEffect() == Nestable.NestEffect.CLOSE) {

                // if the stack is empty, we know there is no opening element
                if (stack.isEmpty()) {
                    Stack<Nestable> currentStack = new Stack<>();
                    currentStack.addAll(stack);
                    return new NestingReport(NestingReport.Status.INVALID_CLOSE, element, currentStack);
                }
                // make a new copy of the current state of the stack for preservation
                Stack<Nestable> currentStack = new Stack<>();
                currentStack.addAll(stack);
                // pop the top element for comparison
                Nestable topEle = stack.pop();
                if (!topEle.matches(element)) { // if the element does not match, return INVALID_CLOSE
                    return new NestingReport(NestingReport.Status.INVALID_CLOSE, element, currentStack);
                }
            }
        }

        // at this point we have processed all elements, if the stack is not empty, then there must
        // be an OPEN without a matching CLOSE. Return NOT_TERMINATED status
        if (!stack.isEmpty()) {
            Stack<Nestable> currentStack = new Stack<>();
            currentStack.addAll(stack);
            return new NestingReport(NestingReport.Status.NOT_TERMINATED, null, currentStack);
        }

        // otherwise the stack is good and valid
        return new NestingReport(NestingReport.Status.VALID, null, new Stack<>());
    }
}
