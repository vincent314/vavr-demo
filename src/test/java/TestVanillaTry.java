import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestVanillaTry {
    @Test
    public void testTry() {
        List<Integer> div = Stream.of("1234", "MONDAY")
                .map(str -> {
                    int result;
                    try {
                        result = Integer.parseInt(str);
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                        result = 0;
                    }
                    return result;
                }).collect(Collectors.toList());

        Assert.assertEquals(Arrays.asList(1234, 0), div);
    }

}
