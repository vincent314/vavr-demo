import io.vavr.control.Try;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class TestVanillaCollections {

    @Test
    public void testList() {
        List<String> list = Stream.of("toto", "titi")
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        assertEquals(String.join(",", list), "TOTO,TITI");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testImmutable(){
        List<String> unmodifiableList = Collections.unmodifiableList(
                Arrays.asList("first","second"));

        unmodifiableList.add("third"); // BOOM !
    }

    @Test
    public void testMap(){
        Map<String, String> map = Stream.of(
                new AbstractMap.SimpleEntry<>("key1", "value1"),
                new AbstractMap.SimpleEntry<>("key2", "value2")
        ).collect(Collectors.toMap(
                AbstractMap.SimpleEntry::getKey,
                AbstractMap.SimpleEntry::getValue));

        String result = map.get("key3");
        result = (result != null) ? result : "NO VALUE";

        assertEquals("NO VALUE", result);
    }

    @Test
    public void testGroupBy() throws IOException {
        Customer[] customers = new TestUtils().getCustomers();

        Map<String, List<Customer>> byCountry = Stream.of(customers)
                .collect(Collectors.groupingBy(Customer::getCountry));

        Map<String, Integer> countByCountry = byCountry.entrySet().stream()
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue().size()))
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));

        assertEquals(749, countByCountry.get("France").intValue());
    }
}
