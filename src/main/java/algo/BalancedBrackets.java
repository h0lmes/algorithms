package algo;

import java.util.Stack;

public class BalancedBrackets {

    public static void main(String[] args) {
        run("{[()]}");
        run("{[(])}");
        run("{{[[(())]]}}");
        run("{{)[](}}");
        run("{(([])[])[]}");
        run("{(([])[])[]]}");
        run("{(([])[])[]}[]");
    }

    public static void run(String s) {
        System.out.println(s + " : " + isBalanced(s));
    }

    static String isBalanced(String s) {
        if (s.length() < 1) return "YES";

        char[] arr = s.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return "NO";
                Character last = stack.pop();
                if (c == '}' && last != '{') return "NO";
                if (c == ']' && last != '[') return "NO";
                if (c == ')' && last != '(') return "NO";
            }
        }
        return stack.isEmpty() ? "YES" : "NO";
    }
}
