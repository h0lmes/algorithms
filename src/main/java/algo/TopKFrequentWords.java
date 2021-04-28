package algo;

import java.util.*;

public class TopKFrequentWords {

    public static void main(String[] args) {
        new TopKFrequentWords().topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4).forEach(System.out::println);
    }

    public List<String> topKFrequent(String[] words, int k) {

        class Item {
            String word;
            int freq;
            public Item(String word, int freq) {
                this.word = word;
                this.freq = freq;
            }
        }

        Map<String, Integer> map = new TreeMap<>();

        for (int i = 0; i < words.length; i++) {
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }

        List<Item> items = new ArrayList<>(map.size());
        map.entrySet().forEach( e -> items.add(new Item(e.getKey(), e.getValue())) );
        items.sort((a, b) -> {
            int compare = Integer.compare(b.freq, a.freq);
            if (compare != 0) return compare;
            return b.word.compareTo(a.word);
        });
        List<String> list = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            list.add(items.get(i).word);
        }
        return list;
    }
}
