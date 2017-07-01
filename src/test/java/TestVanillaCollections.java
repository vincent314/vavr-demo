import org.junit.Test;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
                Stream.of("first","second")
                .collect(Collectors.toList()));

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
}
