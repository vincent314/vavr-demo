import io.vavr.collection.List;
import io.vavr.control.Try;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestVavrTry {

    @Test
    public void testTry() {
        List<Integer> result = List.of("1234", "MONDAY")
                .map(str -> Try
                        .of(() -> Integer.parseInt(str))
                        .onSuccess(value -> System.out.println("Youpiii ! " + value))
                        .onFailure(System.out::println)
                        .getOrElse(0));

        assertEquals(List.of(1234, 0), result);
    }
}
