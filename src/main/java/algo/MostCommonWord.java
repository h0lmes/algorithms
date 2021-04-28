package algo;

import java.util.*;

public class MostCommonWord {

    public static void main(String[] args) {
        System.out.println(new MostCommonWord().mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"}));
        System.out.println(new MostCommonWord().mostCommonWord("Bob!", new String[]{"hit"}));
    }

    public String mostCommonWord(String paragraph, String[] banned) {
        String[] words = paragraph.toLowerCase().split("\\W+");

        Set<String> set = new HashSet<>();
        for (String b : banned) set.add(b);

        String mostFrequentWord = "";
        int highestFrequency = 0;
        Map<String, Integer> map = new HashMap<>();

        for (String word : words) {
            if (set.contains(word))
                continue;

            int freq = map.getOrDefault(word, 0);
            freq++;
            map.put(word, freq);
            if (freq > highestFrequency) {
                highestFrequency = freq;
                mostFrequentWord = word;
            }
        }

        return mostFrequentWord;
    }
}
