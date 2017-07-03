import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.Tuple;
import io.vavr.collection.Array;
import io.vavr.collection.Map;
import io.vavr.collection.Traversable;
import io.vavr.control.Try;
import io.vavr.jackson.datatype.VavrModule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestVavrTuple {

    public void testTuple() {
        Tuple.of("France", 749);
    }

}
