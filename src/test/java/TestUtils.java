import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.jackson.datatype.VavrModule;

import java.io.IOException;

public class TestUtils {
    private ObjectMapper mapper = new ObjectMapper().registerModule(new VavrModule());

    public Customer[] getCustomers() throws IOException {
        return mapper.readValue(this.getClass().getClassLoader().getResourceAsStream("customers.json"),
                mapper.getTypeFactory().constructArrayType(Customer.class));
    }
}
