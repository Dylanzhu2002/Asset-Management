package persistence;


import model.Consumption;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Use JsonSerializationDemo as the template
public class JsonTest {
    protected void checkConsumption(String date, String type, double balance, Consumption consumption) {
        assertEquals(date, consumption.getDate());
        assertEquals(type, consumption.getType());
        assertEquals(balance, consumption.getConsBalance());
    }
}
