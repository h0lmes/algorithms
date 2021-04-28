package algo;

import java.util.Arrays;

public class UniqueEmailAddresses {

    private static String[] data = {"test.email+alex@leetcode.com", "test.e.mail+bob.cathy@leetcode.com", "testemail+david@lee.tcode.com"};

    public static void main(String[] args) {
        new UniqueEmailAddresses();
    }

    public UniqueEmailAddresses() {
        System.out.println(numUniqueEmails(data));
    }

    public int numUniqueEmails(String[] emails) {
        return (int) Arrays.stream(emails).map((s) -> {
            String[] parts = s.split("@");
            parts[0] = parts[0].replaceAll("\\.", "");
            int plusIndex = parts[0].indexOf('+');
            if (plusIndex >= 0)
                parts[0] = parts[0].substring(0, plusIndex);
            return parts[0] + "@" + parts[1];
        }).distinct().count();
    }
}
