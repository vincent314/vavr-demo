import io.vavr.Tuple2;
import io.vavr.concurrent.Future;
import org.junit.Assert;
import org.junit.Test;

public class TestVavrFuture {

    @Test
    public void testFuture() {
        Future<String> future1 = Future.of(() -> {
            Thread.sleep(1500);
            return "Result A";
        });

        Future<String> future2 = Future.of(() -> {
            Thread.sleep(1000);
            return "Result B";
        });

        Tuple2<String, String> tuple = future1.zip(future2)
                .get();

        Assert.assertEquals("Result A", tuple._1());
        Assert.assertEquals("Result B", tuple._2());
    }

    @Test
    public void testFutureWithError() {
        Future<String> future1 = Future.of(() -> {
            Thread.sleep(1500);
            return "Result A";
        });

        Future<String> future2 = Future.of(() -> {
            Thread.sleep(1000);
            throw new IllegalStateException("ANY ERROR !");
        });

        Future<Tuple2<String, String>> future = future1.zip(future2).await();

        Assert.assertTrue(future.isFailure());
        Assert.assertEquals("ANY ERROR !", future.getCause().get().getMessage());
    }
}
