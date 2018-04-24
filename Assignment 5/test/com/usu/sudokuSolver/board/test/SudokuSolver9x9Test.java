package com.usu.sudokuSolver.board.test;

import com.usu.sudokuSolver.board.SudokuBoard;
import com.usu.sudokuSolver.solution.RecursiveBruteSudokuSolver;
import com.usu.sudokuSolver.template.SudokuSolver;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SudokuSolver9x9Test {

    //Test cases for correct array
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

    private String[] sortedLine = {"1","2","3","4","5","6","7","8","9"};

    SudokuBoard sb = new SudokuBoard(sudoku);
    RecursiveBruteSudokuSolver ss = new RecursiveBruteSudokuSolver(sb);
    
    @Test(expected = NullPointerException.class)
    public void testIsSolved_NullValues() {
    	Integer row = null, col = null;
    	ss.solve(row, col);
    }
    
    @Test
    public void testIsSolved_Correct() {
    	SudokuSolver.symbols = sortedLine;
    	ss.solve(0, 0);
        for (int i = 0; i < 9; i++) {
            assertTrue(arrayPassed(sb.getRow(i)));
            assertTrue(arrayPassed(sb.getColumn(i)));
        }

    }

    private boolean arrayPassed(String[] toVerify) {
        String[] copy = Arrays.copyOf(toVerify, 9);
        Arrays.sort(copy);
        return Arrays.equals(sortedLine, copy);
    }

    //Test cases for invalid symbol
    String[][] sudoku1 = {
    		{"8","A","-","-","2","-","-","-","-"},
    		{"-","-","-","7","-","-","-","5","9"},
    		{"-","-","-","-","-","-","-","-","-"},
    		{"-","-","-","-","6","-","8","-","-"},
    		{"-","4","-","-","-","-","-","-","-"},
    		{"-","-","5","3","-","-","-","-","7"},
    		{"-","-","-","-","-","-","-","-","-"},
    		{"-","2","-","-","-","-","6","-","-"},
    		{"-","-","7","5","-","9","-","-","-"}
    };
    
    @Test
    public void testContainOutlier_NullValue() {
    	SudokuSolver.symbols = sortedLine;
    	assertFalse(SudokuSolver.isSudokuBoardValid(null));
    }
    
    @Test
    public void testContainOutlier_CorrectValue() {
    	SudokuSolver.symbols = sortedLine;
    	assertFalse(SudokuSolver.isSudokuBoardValid(sudoku1));
    }

    //Test cases for duplicate value in row
    String[][] sudoku2 = {
    		{"8","6","-","6","2","-","-","-","-"},
    		{"-","-","-","7","-","-","-","5","9"},
    		{"-","-","-","-","-","-","-","-","-"},
    		{"-","-","-","-","6","-","8","-","-"},
    		{"-","4","-","-","-","-","-","-","-"},
    		{"-","-","5","3","-","-","-","-","7"},
    		{"-","-","-","-","-","-","-","-","-"},
    		{"-","2","-","-","-","-","6","-","-"},
    		{"-","-","7","5","-","9","-","-","-"}
    };
    
    @Test
    public void testContainDuplicateRow_NullValue() {
    	SudokuSolver.symbols = sortedLine;
    	assertFalse(SudokuSolver.isSudokuBoardValid(null));
    }
    
    @Test
    public void testContainDuplicateRow_CorrectValue() {
    	SudokuSolver.symbols = sortedLine;
    	assertFalse(SudokuSolver.isSudokuBoardValid(sudoku4));
    }

    //Test cases for duplicate value in column
    String[][] sudoku3 = {
    		{"8","4","-","-","2","-","-","-","-"},
    		{"-","-","-","7","-","-","-","5","9"},
    		{"-","-","-","-","-","-","-","-","-"},
    		{"-","-","-","-","6","-","8","-","-"},
    		{"-","4","-","-","-","-","-","-","-"},
    		{"-","-","5","3","-","-","-","-","7"},
    		{"-","-","-","-","-","-","-","-","-"},
    		{"-","2","-","-","-","-","6","-","-"},
    		{"-","-","7","5","-","9","-","-","-"}
    };
    
    @Test
    public void testContainDuplicateColumn_NullValue() {
    	SudokuSolver.symbols = sortedLine;
    	assertFalse(SudokuSolver.isSudokuBoardValid(null));
    }
    
    @Test
    public void testContainDuplicateColumn_CorrectValue() {
    	SudokuSolver.symbols = sortedLine;
    	assertFalse(SudokuSolver.isSudokuBoardValid(sudoku4));
    }

    //Test cases for extra row or invalid row size
    String[][] sudoku4 = {
    		{"8","6","-","-","2","-","-","-","-"},
    		{"-","-","-","7","-","-","-","5","9"},
    		{"-","-","-","-","-","-","-","-","-"},
    		{"-","-","-","-","6","-","8","-","-"},
    		{"-","4","-","-","-","-","-","-","-"},
    		{"-","-","5","3","-","-","-","-","7"},
    		{"-","-","-","-","-","-","-","-","-"},
    		{"-","2","-","-","-","-","6","-","-"},
    		{"-","-","7","5","-","9","-","-","-"},
    		{"2"}
    };
    
    @Test
    public void testSizeRow_CorrectValue() {
    	SudokuSolver.symbols = sortedLine;
    	assertFalse(SudokuSolver.isSudokuBoardValid(sudoku4));
    }

    //Test cases for extra column or invalid column size
    String[][] sudoku5 = {
    		{"8","6","-","-","2","-","-","-","-"},
    		{"-","-","-","7","-","-","-","5","9", "1"},
    		{"-","-","-","-","-","-","-","-","-"},
    		{"-","-","-","-","6","-","8","-","-"},
    		{"-","4","-","-","-","-","-","-","-"},
    		{"-","-","5","3","-","-","-","-","7"},
    		{"-","-","-","-","-","-","-","-","-"},
    		{"-","2","-","-","-","-","6","-","-"},
    		{"-","-","7","5","-","9","-","-","-"}
    };
    
    @Test
    public void testSizeColumn_CorrectValue() {
    	SudokuSolver.symbols = sortedLine;
    	assertFalse(SudokuSolver.isSudokuBoardValid(sudoku5));
    }

    //Test cases for wrong value
    String[][] sudoku6 = {
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
    
    SudokuBoard sb1 = new SudokuBoard(sudoku6);
    RecursiveBruteSudokuSolver ss1 = new RecursiveBruteSudokuSolver(sb1);
    
    @Test(expected = NullPointerException.class)
    public void testIsSolved_NullValues_WrongValue() {
    	Integer row = null, col = null;
    	ss1.solve(row, col);
    }
    
    @Test
    public void testIsSolved_Correct_WrongValue() {
    	SudokuSolver.symbols = sortedLine;
    	assertTrue(ss1.solve(0, 0));        
    }
}