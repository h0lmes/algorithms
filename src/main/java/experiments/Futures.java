package experiments;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Futures {

    public static void main(String[] args) {
        CompletableFuture<Integer> future = CompletableFuture.completedFuture(1);
        try {
            System.out.println(future.get(0, TimeUnit.MILLISECONDS));
            System.out.println(future.get(0, TimeUnit.MILLISECONDS));
            System.out.println(future.get(0, TimeUnit.MILLISECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
