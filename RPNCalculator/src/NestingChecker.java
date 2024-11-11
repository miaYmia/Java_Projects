import java.util.Queue;
import java.util.Stack;

public class NestingChecker {

    public static NestingReport checkNesting(Queue<? extends Nestable> elements) {
        // If the queue is null, return NULL_INPUT status
        if (elements == null) {
            return new NestingReport(NestingReport.Status.NULL_INPUT, null, new Stack<>());
        }

        // Initialize a new stack to keep track of all the elements
        Stack<Nestable> stack = new Stack<>();

        // Now we iterate through the elements and perform actions depending on the element's status
        for (Nestable element : elements) {

            // If the element is null, return NULL_ITEM status
            if (element == null) {
                Stack<Nestable> currentStack = new Stack<>();
                currentStack.addAll(stack);
                return new NestingReport(NestingReport.Status.NULL_ITEM, element, currentStack);
            }

            // If the element is an OPEN element, push it onto the stack
            if (element.getEffect() == Nestable.NestEffect.OPEN) {
                stack.push(element);
            }

            // If we encounter a CLOSE element, we take actions based on the presence of an OPEN element
            else if (element.getEffect() == Nestable.NestEffect.CLOSE) {

                // If the stack is empty, we know there is no opening element
                if (stack.isEmpty()) {
                    Stack<Nestable> currentStack = new Stack<>();
                    currentStack.addAll(stack);
                    return new NestingReport(NestingReport.Status.INVALID_CLOSE, element, currentStack);
                }
                // Make a new copy of the current state of the stack for preservation
                Stack<Nestable> currentStack = new Stack<>();
                currentStack.addAll(stack);
                // Pop the top element for comparison
                Nestable topEle = stack.pop();
                if (!topEle.matches(element)) { // If the element does not match, return INVALID_CLOSE
                    return new NestingReport(NestingReport.Status.INVALID_CLOSE, element, currentStack);
                }
            }
        }

        // After processing all elements, if the stack is not empty, there must be an OPEN without a matching CLOSE. Return NOT_TERMINATED status
        if (!stack.isEmpty()) {
            Stack<Nestable> currentStack = new Stack<>();
            currentStack.addAll(stack);
            return new NestingReport(NestingReport.Status.NOT_TERMINATED, null, currentStack);
        }

        // Otherwise, the stack is good and valid
        return new NestingReport(NestingReport.Status.VALID, null, new Stack<>());
    }
}
