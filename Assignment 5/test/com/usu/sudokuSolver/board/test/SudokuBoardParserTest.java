package com.usu.sudokuSolver.board.test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.usu.sudokuSolver.board.SudokuBoardParser;

public class SudokuBoardParserTest {

    @Test(expected = NullPointerException.class)
    public void testParsingLogic_NullInput() {
    	String fileName = null;
        SudokuBoardParser.parse(fileName);
    }
    
    @Test
    public void testParsingLogic_EmptyInput() {
    	ExpectedException expectedException = ExpectedException.none();
    	expectedException.expect(FileNotFoundException.class);
    	expectedException.expect(IOException.class);
    	SudokuBoardParser.parse("");
    }
    
    @Test
    public void testParsingLogic_Correct() {
        String[][] puzzle = SudokuBoardParser.parse("input\\Puzzle-4x4-0101.txt");
        assertEquals(Integer.parseInt(puzzle[0][0]), 2);
    }
}
