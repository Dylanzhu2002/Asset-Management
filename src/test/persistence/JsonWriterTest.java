package persistence;

import model.Consumption;
import  model.ConsumptionList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Use JsonSerializationDemo as the template
public class JsonWriterTest extends JsonTest{

    @Test
    public void testWriterInvalidFile() {
        try {
            ConsumptionList cl = new ConsumptionList("My list");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {

        }
    }

    @Test
    void testWriterEmptyConsumptionList() {
        try {
            ConsumptionList cl = new ConsumptionList("My list");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyConsumptionList.json");
            writer.open();
            writer.write(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyConsumptionList.json");
            cl = reader.read();
            assertEquals("My list", cl.getName());
            assertEquals(0, cl.sizeCons());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralConsumptionList() {
        try {
            ConsumptionList cl = new ConsumptionList("My list");
            cl.addCons("22/02/02", "apple", 100);
            cl.addCons("22/05/10", "book", 20);
            cl.addCons("22/12/10", "phone", 1000);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralConsumptionList.json");
            writer.open();
            writer.write(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralConsumptionList.json");
            cl = reader.read();
            assertEquals("My list", cl.getName());
            assertEquals(3, cl.sizeCons());
            checkConsumption("22/02/02", "apple", 100, cl.getCons(0));
            checkConsumption("22/05/10", "book", 20, cl.getCons(1));
            checkConsumption("22/12/10", "phone", 1000, cl.getCons(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
