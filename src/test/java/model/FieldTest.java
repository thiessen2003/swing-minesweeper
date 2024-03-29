package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class FieldTest {
    @Test
    public void testNotAddNeighborAbove() {
        Field newField = new Field(3,2);
        Field testField = new Field(3,4);
        newField.addNeighbor(testField);
        boolean result = newField.getNeighbors().contains(testField);
        assertFalse(result);
    }

    @Test
    public void testAddNeighborDiagonal() {
        Field newField = new Field(3,3);
        Field testField = new Field(2,2);
        newField.addNeighbor(testField);
        boolean result = newField.getNeighbors().contains(testField);
        assertTrue(result);
    }
    @Test
    public void testAddNeighborHorizontal() {
        Field newField = new Field(3,3);
        Field testField = new Field(3,4);
        newField.addNeighbor(testField);
        boolean result = newField.getNeighbors().contains(testField);
        assertTrue(result);
    }
    @Test
    public void testCountNumberOfNeighbors() {
        Field newField = new Field(3,3);
        Field testField1 = new Field(3,4);
        Field testField2 = new Field(3,2);

        newField.addNeighbor(testField1);
        newField.addNeighbor(testField2);

        int result = newField.getNeighbors().size();
        assertEquals(2, result);
    }
    @Test
    public void testStandardCheckedValue() {
        assertFalse(new Field(3,3).isChecked());
    }
    @Test
    public void testStandardOpenedValue() {
        assertFalse(new Field(3,3).isOpened());
    }

}
