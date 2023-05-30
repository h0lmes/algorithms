package experiments;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Cron {

    public static final int EVERY_SECOND = -1;
    public static final int EVERY_MINUTE = -1;
    public static final int EVERY_HOUR = -1;

    private static final String QUERTY = "ABCDEF GHIJKLMN OPQRST UVWXYZ abcdef ghijklmn opqrst uvwxyz ,.!-";
    private static final char[] CHARS = QUERTY.toCharArray();
    private static final String ARTICLE = "Eight decades have passed since physicists realized that the theories " +
            "of quantum mechanics and gravity don’t fit together, and the puzzle of how to combine the two remains unsolved. " +
            "In the last few decades, researchers have pursued the problem in two separate programs - " +
            "string theory and loop quantum gravity - that are widely considered incompatible by their practitioners. " +
            "But now some scientists argue that joining forces is the way forward.\n" +
            "Among the attempts to unify quantum theory and gravity, string theory has attracted the most attention. " +
            "Its premise is simple: Everything is made of tiny strings. " +
            "The strings may be closed unto themselves or have loose ends; " +
            "they can vibrate, stretch, join or split. " +
            "And in these manifold appearances lie the explanations for all phenomena we observe, both matter and space-time included.\n" +
            "Loop quantum gravity, by contrast, is concerned less with the matter that inhabits space-time than with the quantum properties of space-time itself. " +
            "In loop quantum gravity or LQG space-time is a network. " +
            "The smooth background of Einstein’s theory of gravity is replaced by nodes and links to which quantum properties are assigned. " +
            "In this way, space is built up of discrete chunks. LQG is in large part a study of these chunks.\n" +
            "This approach has long been thought incompatible with string theory. " +
            "Indeed, the conceptual differences are obvious and profound. " +
            "For starters, LQG studies bits of space-time, whereas string theory investigates the behavior of objects within space-time. " +
            "Specific technical problems separate the fields. " +
            "String theory requires that space-time have 10 dimensions; LQG doesn’t work in higher dimensions. " +
            "String theory also implies the existence of supersymmetry, in which all known particles have yet-undiscovered partners. " +
            "Supersymmetry isn’t a feature of LQG.";
    private final String[] vocabulary;

    public static void main(String[] args) {
        new Cron();
        int seconds = 10000 % 3600;
        int minutes = seconds / 60;
        seconds %= 60;
        System.out.println(expression(seconds, minutes, EVERY_HOUR));
    }

    public Cron() {
        vocabulary = ARTICLE
                .replaceAll("[^A-Za-z\\-\\s]", "")
                .toLowerCase()
                .split("\\s+");

        int[] groups = new int[60];
        for (int i = 0; i < 1000; i++) {
            String name = randomWord(5) + "." + randomWord(100000);
            int seconds = Math.abs(name.hashCode() % 3600);
            int group = seconds / 60;
            groups[group]++;
        }
        for (int group : groups) {
            System.out.println(repeat('#', group));
        }
    }

    private static String repeat(char ch, int count) {
        char[] chars = new char[count];
        Arrays.fill(chars, ch);
        return new String(chars);
    }

    public String randomWord(int maxWord) {
        return ThreadLocalRandom.current()
                .ints(0, Math.min(maxWord, vocabulary.length))
                .boxed()
                .map(i -> vocabulary[i])
                .findFirst()
                .orElseGet(this::randomChars);
    }

    private String randomChars() {
        int len = ThreadLocalRandom.current().nextInt(10, 30);
        return ThreadLocalRandom.current()
                .ints(0, CHARS.length)
                .limit(len)
                .map(i -> CHARS[i])
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String expression(int seconds, int minutes, int hours) {
        return (seconds == EVERY_SECOND ? "*" : "" + seconds)
                + " " + (minutes == EVERY_MINUTE ? "*" : "" + minutes)
                + " " + (hours == EVERY_HOUR ? "*" : "" + hours)
                + " ? * * *";
    }
}
