package com.usu.sudokuSolver.board.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.usu.sudokuSolver.board.SudokuBoard;

public class SudokuBoardTest {

    String[][] sudoku = {
    		{"8","6","-","-","2","-","-","-","-"},
    		{"-","-","-","7","-","-","-","5","9"},
    		{"-","-","-","-","-","-","-","-","-"},
    		{"-","-","-","-","6","-","8","-","-"},
    		{"-","4","-","-","-","-","-","-","-"},
    		{"-","-","5","3","-","-","-","-","7"},
    		{"-","-","-","-","-","-","-","-","-"},
    		{"-","2","-","-","-","-","6","-","-"},
    		{"-","-","7","5","-","9","-","-","-"}
    };

    SudokuBoard sb = new SudokuBoard(sudoku);

    @Test
    public void testRows() {
        String[] row0 = {"8","6","-","-","2","-","-","-","-"};
        assertTrue(Arrays.equals(row0, sb.getRow(0)));
    }

    @Test
    public void testColumns() {
        String[] column0 = {"8","-","-","-","-","-","-","-","-"};
        assertTrue(Arrays.equals(column0, sb.getColumn(0)));
    }

    @Test(expected = NullPointerException.class)
    public void testGetCell_NullValue() {
    	Integer row = null, col = null;
        assertEquals("8", sb.getCell(row.intValue(), col.intValue()));
    }
    
    @Test
    public void testGetCell_Correct() {
        assertEquals("8", sb.getCell(0, 0));
    }

    @Test(expected = NullPointerException.class)
    public void testSetCell_NullValue() {
    	Integer row = null, col = null;
        sb.setCell("3", row.intValue(), col.intValue());
    }
    
    @Test
    public void testSetCell_SymbolNullValue() {
    	String symbol = null;
    	sb.setCell(symbol, 0, 5);
    	assertNull(sb.getCell(0, 5));
    }
    
    @Test
    public void testSetCell() {
        sb.setCell("3", 0, 5);
        assertEquals("3", sb.getCell(0, 5));
    }
}