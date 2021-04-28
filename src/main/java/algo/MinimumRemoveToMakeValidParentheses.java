package algo;

public class MinimumRemoveToMakeValidParentheses {

    public static void main(String[] args) {
        System.out.println(new MinimumRemoveToMakeValidParentheses().minRemoveToMakeValid("lee(t(c)o)de)"));
        System.out.println(new MinimumRemoveToMakeValidParentheses().minRemoveToMakeValid("a)b(c)d"));
        System.out.println(new MinimumRemoveToMakeValidParentheses().minRemoveToMakeValid("))(("));
        System.out.println(new MinimumRemoveToMakeValidParentheses().minRemoveToMakeValid("(a(b(c)d)"));
    }

    public String minRemoveToMakeValid(String s) {
        char[] result = s.toCharArray();
        int len = s.length();
        int lenFinal = len;

        int balance = 0;
        for (int i = 0; i < len; i++) {
            char c = result[i];
            if (c == '(') balance++;
            else if (c == ')') {
                if (balance > 0) balance--;
                else {
                    result[i] = '-';
                    lenFinal--;
                    continue;
                }
            }
        }

        balance = 0;
        for (int i = len - 1; i >= 0; i--) {
            char c = result[i];
            if (c == ')') balance++;
            else if (c == '(') {
                if (balance > 0) balance--;
                else {
                    result[i] = '-';
                    lenFinal--;
                    continue;
                }
            }
        }

        if (lenFinal == 0) return "";
        if (lenFinal == len) return s;

        char[] result2 = new char[lenFinal];
        int pos = 0;
        for (int i = 0; i < len; i++) {
            if (result[i] != '-') {
                result2[pos++] = result[i];
            }
        }
        return new String(result2);
    }
}
