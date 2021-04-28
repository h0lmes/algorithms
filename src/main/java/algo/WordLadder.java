package algo;

import javafx.util.Pair;

import java.util.*;

public class WordLadder {

    public static void main(String[] args) {
        System.out.println(new WordLadder().ladderLength(
                "hit",
                "cog",
                Arrays.asList(new String[]{"hot", "dot", "dog", "lot", "log", "cog"})
        ));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int len = beginWord.length();

        // key is word mask - d*g, *it, etc
        // value is List of words for this mask
        Map<String, List<String>> dict = new HashMap<>();
        wordList.forEach(
                word -> {
                    for (int i = 0; i < len; i++) {
                        String mask = word.substring(0, i) + '*' + word.substring(i + 1, len);
                        List<String> list = dict.getOrDefault(mask, new ArrayList<>());
                        list.add(word);
                        dict.put(mask, list);
                    }
                });

        Queue<Pair<String, Integer>> q = new LinkedList<>();
        q.add(new Pair(beginWord, 1));

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        while (!q.isEmpty()) {
            Pair<String, Integer> pair = q.poll();
            String word = pair.getKey();
            int level = pair.getValue();

            for (int i = 0; i < len; i++) {
                String mask = word.substring(0, i) + '*' + word.substring(i + 1, len);
                for (String adjacentWord : dict.getOrDefault(mask, new ArrayList<>())) {
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    if (!visited.contains(adjacentWord)) {
                        visited.add(adjacentWord);
                        q.add(new Pair(adjacentWord, level + 1));
                    }
                }
            }
        }

        return 0;
    }
}
