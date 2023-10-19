package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// test all method in ConsumptionList

public class ConsumptionListTest {


    ConsumptionList TestList;

    @BeforeEach
    public void setup() {
        this.TestList = new ConsumptionList("dylan");

    }

    @Test
    public void testNoConsumptionInList() {
        assertEquals(0,TestList.sizeCons());
    }

    @Test
    public void testGetName() {
        assertEquals("dylan", TestList.getName());
    }



    @Test
    public void testManyConsumptionInList() {
        TestList.addCons("02/02", "food", 50);
        TestList.addCons("09/01", "textbook", 99);
        TestList.addCons("09/01", "textbook", 99);
        assertEquals(3,TestList.sizeCons());
    }

    @Test
    public void testSumOfEmptyConsumptionInList() {
        assertEquals(0, TestList.consumptionSum());
    }


    @Test
    public void testSumOfConsumptionInListWithNoNeg() {
        TestList.addCons("02/02", "food", 50);
        TestList.addCons("12/31", "Christmas Concert", 1000);
        TestList.addCons("09/01", "textbook", 99);
        TestList.addCons("09/01", "textbook", 0);

        assertEquals(1149, TestList.consumptionSum());
    }

    @Test
    public void testSumOfConsumptionInListWithNeg() {
        TestList.addCons("02/02", "food", 50);
        TestList.addCons("12/31", "Christmas Concert", 1000);
        TestList.addCons("09/01", "textbook", -50);
        TestList.addCons("09/01", "textbook", 0);

        assertEquals(1000, TestList.consumptionSum());
    }

    @Test
    public void testSumOfConsumptionInListWithAllNeg() {
        TestList.addCons("02/02", "food", -50);
        TestList.addCons("12/31", "Christmas Concert", -1000);
        TestList.addCons("09/01", "textbook", -50);
        TestList.addCons("09/01", "textbook", -1);

        assertEquals(-1101, TestList.consumptionSum());
    }

    @Test
    public void testSumOfConsumptionInListWithDecimals() {
        TestList.addCons("02/02", "food", 50);
        TestList.addCons("12/31", "Christmas Concert", 100);
        TestList.addCons("09/01", "textbook", 0.1);
        TestList.addCons("09/01", "textbook", 50.001);

        assertEquals(200.101, TestList.consumptionSum());
    }

    @Test
    public void testSumOfConsumptionInListWithNegDecimals() {
        TestList.addCons("02/02", "food", 50);
        TestList.addCons("12/31", "Christmas Concert", 100);
        TestList.addCons("09/01", "textbook", -50.6);
        TestList.addCons("09/01", "textbook", 1);

        assertEquals(100.4, TestList.consumptionSum());
    }

    @Test
    public void testAddCons() {

        assertTrue(TestList.addCons("02/02", "food", 50));
    }

    @Test
    public void testSizeCons() {
        TestList.addCons("02/02", "food", 50);
        assertTrue( TestList.addCons("02/02", "food", 50));
    }

    @Test
    public void testGetCons() {
        TestList.addCons("02/02", "food", 50);
        TestList.addCons("12/31", "Christmas Concert", 100);
        TestList.addCons("09/01", "textbook", -50.6);
        TestList.addCons("01/01", "textbook", 1);

        assertEquals("02/02", TestList.getCons(0).getDate());
        assertEquals("01/01", TestList.getCons(3).getDate());
    }
}
