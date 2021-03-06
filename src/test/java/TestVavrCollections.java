import io.vavr.collection.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestVavrCollections {

    @Test
    public void testListVavr() {
        List<String> list = List.of("toto", "titi")
                .map(String::toUpperCase);
        assertEquals(
                "TOTO,TITI",
                String.join(",", list));
    }

    @Test
    public void testImmutable() {
        List<String> list1 = List.of("first", "second");
        List<String> list2 = list1.append("third");

        assertFalse(list1 == list2);
        assertEquals(
                "first,second,third",
                String.join(",", list2));
    }

    @Test
    public void testMap() {
        HashMap<String, String> map = HashMap.of(
                "key1", "value1",
                "key2", "value2"
        );

        String result = map.getOrElse("key3", "NO VALUE");
        assertEquals("NO VALUE", result);
    }

    @Test
    public void testGroupBy() throws Throwable {
        Customer[] customers = new TestUtils().getCustomers();

        Map<String, Integer> byCountry = Array.of(customers).groupBy(Customer::getCountry)
                .mapValues(Traversable::size);

        assertEquals(749, byCountry.get("France").get().intValue());
    }
}
