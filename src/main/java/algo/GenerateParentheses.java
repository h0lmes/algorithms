package algo;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public static void main(String[] args) {
        new GenerateParentheses().generateParenthesis(3).forEach(System.out::println);
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n > 0) work("(", n - 1, n, 1, result);
        return result;
    }

    private void work(String s, int open, int close, int balance, List<String> result) {
        if (open > 0)
            work(s + "(", open - 1, close, balance + 1, result);
        if (close > 0 && balance > 0)
            work(s + ")", open, close - 1, balance - 1, result);
        if (open == 0 && close == 0)
            result.add(s);
    }
}
