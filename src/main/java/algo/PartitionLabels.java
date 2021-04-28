package algo;

import java.util.*;

public class PartitionLabels {

    public static void main(String[] args) {

    }

    public List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();
        int len = S.length();
        if (len < 1) {
            result.add(0);
            return result;
        }
        if (len < 2) {
            result.add(1);
            return result;
        }

        int[] last = new int[26];
        for (int i = 0; i < len; i++) {
            int charIndex = S.charAt(i) - 'a';
            last[charIndex] = i;
        }

        int partStart = 0;
        int partEnd = 0;
        for (int i = 0; i < len; i++) {
            int charIndex = S.charAt(i) - 'a';
            if (last[charIndex] > partEnd)
                partEnd = last[charIndex];
            if (partEnd == i) {
                result.add(partEnd - partStart + 1);
                partStart = i + 1;
            }
        }

        return result;
    }
}
