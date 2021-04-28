package algo;

import java.util.Stack;

public class ValidParentheses {

    public static void main(String[] args) {
        System.out.println(new ValidParentheses().isValid("()[]{}"));
        System.out.println(new ValidParentheses().isValid("([)]"));
        System.out.println(new ValidParentheses().isValid("{[]}"));
    }

    public boolean isValid(String s) {
        int len = s.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
                continue;
            }

            if (stack.isEmpty()) {
                if (c == ')' || c == ']' || c == '}') return false;
            } else {
                char last = stack.peek();
                if (c == ')') {
                    if (last == '(') stack.pop();
                    else return false;
                }
                else if (c == ']') {
                    if (last == '[') stack.pop();
                    else return false;
                }
                else {
                    if (last == '{') stack.pop();
                    else return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
