package benchmark;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ObjectMemoryFootprint {

    private static final String QUERTY = "ABCDEF GHIJKLMN OPQRST UVWXYZ abcdef ghijklmn opqrst uvwxyz ,.!-";
    private static final char[] CHARS = QUERTY.toCharArray();

    private static final String UNICODE = "\uD83D\uDE00\uD83D\uDE42";
    private static final char[] UCHARS = UNICODE.toCharArray();

    private static String randomChars(char[] chars, int len) {
        return ThreadLocalRandom.current()
                .ints(0, chars.length)
                .limit(len)
                .map(i -> chars[i])
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private static class Obj implements Serializable {

        private static final long serialVersionUID = 1L;

        private String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f;
        private String g;
        private String h;
        private String i;
        private String j;
        private String k;
        private String l;
        private String m;
        private String n;
        private String o;
        private String p;
        private String q;
        private String r;
        private String s;
        private String t;
        private String u;
        private String v;
        private String w;
        private String x;
        private String y;

        public Obj(char[] chars, int len) {
            a = randomChars(chars, len);
            b = randomChars(chars, len);
            c = randomChars(chars, len);
            d = randomChars(chars, len);
            e = randomChars(chars, len);
            f = randomChars(chars, len);
            g = randomChars(chars, len);
            h = randomChars(chars, len);
            i = randomChars(chars, len);
            j = randomChars(chars, len);
            k = randomChars(chars, len);
            l = randomChars(chars, len);
            m = randomChars(chars, len);
            n = randomChars(chars, len);
            o = randomChars(chars, len);
            p = randomChars(chars, len);
            q = randomChars(chars, len);
            r = randomChars(chars, len);
            s = randomChars(chars, len);
            t = randomChars(chars, len);
            u = randomChars(chars, len);
            v = randomChars(chars, len);
            w = randomChars(chars, len);
            x = randomChars(chars, len);
            y = randomChars(chars, len);
        }

        public String toString() {
            return a + ";" +
                    b + ";" +
                    c + ";" +
                    d + ";" +
                    e + ";" +
                    f + ";" +
                    g + ";" +
                    h + ";" +
                    i + ";" +
                    j + ";" +
                    k + ";" +
                    l + ";" +
                    m + ";" +
                    n + ";" +
                    o + ";" +
                    p + ";" +
                    q + ";" +
                    r + ";" +
                    s + ";" +
                    t + ";" +
                    u + ";" +
                    v + ";" +
                    w + ";" +
                    x + ";" +
                    y;
        }
    }

    public static void printObjectSize(Object object) {
        System.out.println("Object type: " + object.getClass() +
                ", size: " + InstrumentationAgent.getObjectSize(object) + " bytes");
    }

    public static void printObjectSizeDeep(Object object) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            out.flush();
            byte[] bytes = bos.toByteArray();
            System.out.println("Object type: " + object.getClass() + ", size: " + bytes.length + " bytes");
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }
    }

    public static void writeFile(File file, String data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(osw)) {
            writer.append(data);
        }
    }

    public static void main(String[] args) throws IOException {
        int numberOfObjects = 100;
        int stringLength = 100;

        // ascii

        List<Obj> list = new ArrayList<>();
        for (int i = 0; i < numberOfObjects; i++) {
            list.add(new Obj(CHARS, stringLength));
        }
        printObjectSize(list);
        printObjectSizeDeep(list);

        StringBuilder builder = new StringBuilder();
        list.forEach(o -> builder.append(o.toString()).append(System.lineSeparator()));
        writeFile(new File(".\\obj-ascii.csv"), builder.toString());

        // unicode

        List<Obj> ulist = new ArrayList<>();
        for (int i = 0; i < numberOfObjects; i++) {
            ulist.add(new Obj(UCHARS, stringLength));
        }
        printObjectSize(ulist);
        printObjectSizeDeep(ulist);

        StringBuilder ubuilder = new StringBuilder();
        ulist.forEach(o -> ubuilder.append(o.toString()).append(System.lineSeparator()));
        writeFile(new File(".\\obj-unicode.csv"), ubuilder.toString());
    }
}
