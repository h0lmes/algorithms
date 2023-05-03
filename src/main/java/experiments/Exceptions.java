package experiments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Exceptions {

    private static final Logger log = LoggerFactory.getLogger(Exceptions.class);

    public static void main(String[] args) {
        var future = CompletableFuture.runAsync(() -> run(5));
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void run(int depth) {
        depth--;
        if (depth == 0) runAsync();
        else run(depth);
    }

    public static void runAsync() {
        var future = CompletableFuture.runAsync(() -> {
            var e = new RuntimeException();
            e.setStackTrace(filterStackTrace(Thread.currentThread().getStackTrace()));

            var e1 = new RuntimeException();
            e1.setStackTrace(filterStackTrace(Thread.currentThread().getStackTrace()));
            e.initCause(e1);

            var stringWriter = new StringWriter();
            var printWriter = new PrintWriter(stringWriter) {

                @Override
                public void println(Object x) {
                    println(String.valueOf(x));
                }

                @Override
                public void println(String x) {
                    if (x.contains("experiments1.")) {
                        return;
                    }
                    x = filterToken(x, "at ", "//", true, false);
                    x = filterToken(x, "at ", "/", true, false);
                    if (x.startsWith("java.lang.RuntimeException")) {
                        x = "Title 1";
                    }
                    if (x.startsWith("Caused by:")) {
                        x = "Title 2";
                    }
                    super.println(x);
                }

                private String filterToken(String x, String startToken, String endToken, boolean keepStartToken, boolean keepEndToken) {
                    int start = x.indexOf(startToken);
                    int end = x.indexOf(endToken, start + startToken.length());
                    if (start < 0 || end < 0) {
                        return x;
                    }
                    return x.substring(0, start + (keepStartToken ? startToken.length() : 0))
                            + x.substring(end + (keepEndToken ? 0 : endToken.length()));
                }
            };

            e.printStackTrace(printWriter);
            log.info(stringWriter.toString());
        });

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static StackTraceElement[] filterStackTrace(StackTraceElement[] elements) {
        if (elements == null || elements.length == 0) {
            return elements;
        }

        List<StackTraceElement> filtered = new ArrayList<>();
        for (StackTraceElement el : elements) {
            if (el.getClassName().startsWith("experiments.")) {
                continue;
            }
            if (el.getLineNumber() < 0) {
                continue;
            }
            filtered.add(el);
        }

        return filtered.toArray(new StackTraceElement[0]);
    }
}
