package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// test all method in Consumption

class ConsumptionTest {
    private Consumption testconsumption;

    @BeforeEach
    void setup() {
        testconsumption = new Consumption("02/02", "food", 50);
    }

    @Test
    void testConsumption() {
        assertEquals("02/02", testconsumption.getDate());
        assertEquals("food", testconsumption.getType());
        assertEquals(50, testconsumption.getConsBalance());
    }


    @Test
    void testGetDate () {
        Consumption testconsumption5 = new Consumption("08/12", "apple", 100);
        assertEquals("08/12", testconsumption5.getDate());
    }

    @Test
    void testGetType () {
        Consumption testconsumption5 = new Consumption("08/12", "apple", 100);
        assertEquals("apple", testconsumption5.getType());
    }

    @Test
    void testGetConsBalance () {
        Consumption testconsumption5 = new Consumption("08/12", "apple", 100);
        assertEquals(100, testconsumption5.getConsBalance());
    }

}