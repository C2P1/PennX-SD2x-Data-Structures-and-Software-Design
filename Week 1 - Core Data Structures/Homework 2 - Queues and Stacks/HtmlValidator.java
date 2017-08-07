import java.io.IOException;
import java.util.Queue;
import java.util.Stack;

/*
 * SD2x Homework #2
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

public class HtmlValidator {

    public static void main(String[] args) {
        try {
            Stack<HtmlTag> newStack =isValidHtml(HtmlReader.getTagsFromHtmlFile("demo.html"));
            if (newStack != null && newStack.isEmpty()) {
                System.out.println("Is Valid!");
            } else {
                System.out.println("Invalid!");
                System.out.println(newStack);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
            System.out.println(tags.toString());
            Stack<HtmlTag> stack = new Stack<>();
            for (HtmlTag tag : tags) {
                System.out.println(tag);
                if (stack.isEmpty()) {
                    if (tag.isSelfClosing()) {
                        continue;
                    } else if (!tag.isOpenTag()) {
                        System.out.println("null");
                        return null;
                    } else {
                        stack.push(tag);
                    }
                    continue;
                }
                if (tag.isOpenTag()) {
                    stack.push(tag);
                }
                if (!tag.isOpenTag()) {
                    if (!stack.isEmpty()) {
                        if (tag.isSelfClosing()) {
                            continue;
                        }
                        if (stack.peek().matches(tag)) {
                            stack.pop();
                        } else {
                            System.out.println("to string: " + stack.toString());
                            return stack;
                        }
                    }
                }
            }
            System.out.println("to string 2: " + stack.toString());
            return stack;
        }
    }

