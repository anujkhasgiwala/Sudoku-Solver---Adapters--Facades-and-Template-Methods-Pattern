package com.usu.sudokuSolver.board.test;

import com.usu.sudokuSolver.board.SudokuBoard;
import com.usu.sudokuSolver.board.SudokuBoardParser;
import com.usu.sudokuSolver.solution.RecursiveBruteSudokuSolver;
import com.usu.sudokuSolver.template.SudokuSolver;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SudokuSolver25x25Test {

    //Test cases for correct array
    String[][] sudoku = SudokuBoardParser.parse("input\\Puzzle-25x25-0101.txt");

    private String[] sortedLine = {"1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G", "H", "I", "J", "K", "L", "M", "N", "O", "P"};

    SudokuBoard sb = new SudokuBoard(sudoku);
    RecursiveBruteSudokuSolver ss = new RecursiveBruteSudokuSolver(sb);
    
    @Test(expected = NullPointerException.class)
    public void testIsSolved_NullValues() {
    	Integer row = null, col = null;
    	ss.solve(row, col);
    }
    
    /*@Test
    public void testIsSolved_Correct() {
    	SudokuSolver.symbols = sortedLine;
    	ss.solve(0, 0);
        for (int i = 0; i < 25; i++) {
            assertTrue(arrayPassed(sb.getRow(i)));
            assertTrue(arrayPassed(sb.getColumn(i)));
        }
    }*/

    private boolean arrayPassed(String[] toVerify) {
        String[] copy = Arrays.copyOf(toVerify, 25);
        Arrays.sort(copy);
        return Arrays.equals(sortedLine, copy);
    }

    //Test cases for invalid symbol
    
    @Test
    public void testContainOutlier_NullValue() {
    	SudokuSolver.symbols = sortedLine;
    	assertFalse(SudokuSolver.isSudokuBoardValid(null));
    }
    
    @Test
    public void testContainOutlier_CorrectValue() {
    	SudokuSolver.symbols = sortedLine;
    	String[][] sudoku1 = SudokuBoardParser.parse("input\\Puzzle-25x25-0201.txt");
    	assertFalse(SudokuSolver.isSudokuBoardValid(sudoku1));
    }

    //Test cases for duplicate value in row
    String[][] sudoku2 = SudokuBoardParser.parse("input\\Puzzle-25x25-0202.txt");
    
    @Test
    public void testContainDuplicateRow_NullValue() {
    	SudokuSolver.symbols = sortedLine;
    	assertFalse(SudokuSolver.isSudokuBoardValid(null));
    }
    
    @Test
    public void testContainDuplicateRow_CorrectValue() {
    	SudokuSolver.symbols = sortedLine;
    	assertFalse(SudokuSolver.isSudokuBoardValid(sudoku2));
    }

    //Test cases for duplicate value in column
    String[][] sudoku3 = SudokuBoardParser.parse("input\\Puzzle-25x25-0203.txt");
    
    @Test
    public void testContainDuplicateColumn_NullValue() {
    	SudokuSolver.symbols = sortedLine;
    	assertFalse(SudokuSolver.isSudokuBoardValid(null));
    }
    
    @Test
    public void testContainDuplicateColumn_CorrectValue() {
    	SudokuSolver.symbols = sortedLine;
    	assertFalse(SudokuSolver.isSudokuBoardValid(sudoku3));
    }

    //Test cases for extra row or invalid row size
    String[][] sudoku4 = SudokuBoardParser.parse("input\\Puzzle-25x25-0204.txt");
    
    @Test
    public void testSizeRow_CorrectValue() {
    	SudokuSolver.symbols = sortedLine;
    	assertFalse(SudokuSolver.isSudokuBoardValid(sudoku4));
    }

    //Test cases for extra column or invalid column size
    String[][] sudoku5 = {
    		{"-", "L", "M", "-", "O", "-", "2", "-", "4", "5", "-", "7", "-", "9", "-", "B", "-", "D", "-", "F", "G", "-", "I", "-", "K"},
    		{"K", "G", "-", "I", "-", "P", "-", "M", "-", "O", "-", "2", "3", "4", "-", "6", "7", "8", "9", "A", "B", "-", "D", "-", "F"},
    		{"F", "B", "C", "-", "-", "K", "-", "-", "I", "-", "P", "-", "M", "-", "O", "1", "-", "3", "4", "-", "6", "-", "8", "-", "A", "2"},
    		{"A", "-", "7", "-", "9", "-", "B", "-", "D", "-", "K", "-", "H", "I", "J", "P", "L", "-", "N", "-", "1", "-", "3", "4", "5"},
    		{"5", "-", "2", "3", "4", "A", "6", "-", "8", "9", "-", "B", "-", "D", "-", "K", "-", "H", "I", "-", "P", "-", "M", "-", "O"},
    		{"O", "-", "L", "-", "N", "5", "-", "2", "3", "-", "A", "6", "7", "-", "9", "F", "-", "C", "-", "E", "-", "G", "H", "I", "J"},
    		{"J", "K", "G", "H", "I", "O", "P", "L", "M", "N", "5", "1", "2", "3", "4", "A", "6", "7", "8", "9", "F", "B", "C", "D", "E"},
    		{"E", "F", "B", "C", "-", "J", "-", "G", "H", "-", "O", "-", "L", "-", "N", "5", "-", "2", "3", "-", "A", "6", "7", "8", "9"},
    		{"9", "A", "-", "7", "-", "E", "-", "B", "-", "D", "-", "K", "G", "-", "I", "-", "P", "-", "M", "-", "5", "-", "2", "-", "4"},
    		{"4", "5", "1", "2", "3", "9", "A", "6", "7", "8", "E", "F", "B", "C", "D", "J", "K", "G", "H", "I", "O", "P", "L", "M", "N"},
    		{"N", "-", "P", "-", "-", "4", "5", "1", "-", "3", "-", "A", "6", "-", "8", "E", "-", "B", "-", "D", "J", "-", "G", "-", "I"},
    		{"I", "J", "-", "G", "H", "-", "O", "P", "L", "M", "4", "-", "1", "-", "3", "-", "A", "6", "-", "8", "-", "F", "-", "C", "D"},
    		{"D", "E", "-", "B", "C", "-", "J", "-", "G", "-", "N", "-", "P", "L", "M", "4", "5", "1", "-", "3", "9", "-", "6", "-", "8"},
    		{"8", "-", "A", "6", "-", "D", "-", "F", "B", "-", "I", "J", "-", "G", "-", "N", "O", "-", "L", "-", "4", "5", "-", "2", "3"},
    		{"3", "4", "-", "1", "-", "8", "9", "A", "-", "7", "D", "-", "F", "-", "C", "I", "-", "K", "-", "H", "N", "-", "P", "L", "M"},
    		{"M", "N", "O", "P", "L", "3", "4", "5", "1", "2", "8", "9", "A", "6", "7", "D", "E", "F", "B", "C", "I", "J", "K", "G", "H"},
    		{"H", "-", "J", "-", "G", "M", "-", "O", "P", "-", "3", "4", "5", "-", "2", "8", "9", "A", "6", "-", "D", "E", "-", "B", "C"},
    		{"C", "D", "-", "F", "-", "H", "-", "J", "-", "G", "-", "N", "-", "P", "L", "-", "4", "-", "1", "2", "-", "9", "A", "-", "7"},
    		{"7", "8", "9", "A", "6", "C", "D", "E", "F", "B", "H", "I", "J", "K", "G", "M", "N", "O", "P", "L", "3", "4", "5", "1", "2"},
    		{"2", "3", "-", "5", "1", "-", "8", "-", "-", "6", "C", "-", "E", "-", "B", "-", "I", "J", "-", "G", "-", "N", "-", "P", "L"},
    		{"L", "M", "N", "O", "P", "2", "3", "4", "5", "1", "7", "8", "9", "A", "6", "C", "D", "E", "F", "B", "H", "I", "J", "K", "G"},
    		{"G", "-", "I", "J", "-", "L", "-", "N", "O", "-", "2", "3", "4", "-", "1", "7", "-", "9", "A", "-", "C", "D", "E", "F", "B"},
    		{"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "2", "3", "4", "5", "1", "7", "8", "9", "A", "6"},
    		{"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "2", "3", "4", "5", "1"},
    		{"1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"}
    };
    
    @Test
    public void testSizeColumn_CorrectValue() {
    	SudokuSolver.symbols = sortedLine;
    	assertFalse(SudokuSolver.isSudokuBoardValid(sudoku5));
    }

    //Test cases for wrong value
    String[][] sudoku6 = SudokuBoardParser.parse("input\\Puzzle-25x25-0901.txt");
    
    SudokuBoard sb1 = new SudokuBoard(sudoku6);
    RecursiveBruteSudokuSolver ss1 = new RecursiveBruteSudokuSolver(sb1);
    
    @Test(expected = NullPointerException.class)
    public void testIsSolved_NullValues_WrongValue() {
    	Integer row = null, col = null;
    	ss1.solve(row, col);
    }
    
    /*@Test
    public void testIsSolved_Correct_WrongValue() {
    	SudokuSolver.symbols = sortedLine;
    	assertTrue(ss1.solve(0, 0));        
    }*/
}