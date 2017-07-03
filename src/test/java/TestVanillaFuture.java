import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class TestVanillaFuture {
    @Test
    public void testFuture() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1500);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("End future1");
            return "Result A";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("End future2");
            return "Result B";
        });

        CompletableFuture<String[]> future = future1.thenCombine(future2,
                (resultA, resultB) -> new String[]{resultA, resultB});

        String[] results = future.join();
        Assert.assertEquals("Result A", results[0]);
        Assert.assertEquals("Result B", results[1]);
    }
}
