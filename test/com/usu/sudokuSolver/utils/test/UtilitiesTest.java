package com.usu.sudokuSolver.utils.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.usu.sudokuSolver.template.SudokuSolver;
import com.usu.sudokuSolver.utils.Utilities;

public class UtilitiesTest {
	
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

	@Test(expected = NullPointerException.class)
	public void testExportSolution_NullFileName() {
		Utilities.exportSolution(null, sudoku);
	}
	
	@Test(expected = NullPointerException.class)
	public void testExportSolution_NullBoard() {
		String[][] sudoku1 = null;
		Utilities.exportSolution("output\\a.txt", sudoku1);
	}
	
	@Test
    public void testExportSolution_EmptyFileName() {
    	ExpectedException expectedException = ExpectedException.none();
    	expectedException.expect(FileNotFoundException.class);
    	expectedException.expect(IOException.class);
    	Utilities.exportSolution("", sudoku);
    }
	
	@Test
    public void testExportSolution_EmptyBoard() {
    	ExpectedException expectedException = ExpectedException.none();
    	expectedException.expect(FileNotFoundException.class);
    	expectedException.expect(IOException.class);
    	String[][] sudoku1 = new String[9][9];
    	SudokuSolver.symbols = new String[]{"1","2","3","4","5","6","7","8","9"};
    	Utilities.exportSolution("output\\a.txt", sudoku1);
    	assertTrue(new File("output\\a.txt").exists());
    }
	
	@Test
	public void testExportSolution_AllCorrect() {
		SudokuSolver.symbols = new String[]{"1","2","3","4","5","6","7","8","9"};
		Utilities.exportSolution("output\\a.txt", sudoku);
		assertTrue(new File("output\\a.txt").exists());
	}
}
