package persistence;

import model.ConsumptionList;
import model.Consumption;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Use JsonSerializationDemo as the template
public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ConsumptionList cl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {

        }
    }

    @Test
    void testReaderEmptyConsumptionList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyConsumptionList.json");
        try {
            ConsumptionList cl = reader.read();
            assertEquals("My list", cl.getName());
            assertEquals(0, cl.sizeCons());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void  testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralConsumptionList.json");
        try {
            ConsumptionList cl = reader.read();
            assertEquals("My list", cl.getName());

            assertEquals(2, cl.sizeCons());
            checkConsumption("22/10/10","book",20, cl.getCons(0));
            checkConsumption("22/12/19","apple",100,cl.getCons(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }




}
