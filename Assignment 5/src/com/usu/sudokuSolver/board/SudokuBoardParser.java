package com.usu.sudokuSolver.board;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anuj Khasgiwala
 *
 */
public class SudokuBoardParser {
	static String symbols[] = null;

    public static String[][] parse(String inputFileName) {

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(inputFileName));
            List<String> lines = new ArrayList<String>();
            String line = null;
            while((line = fileReader.readLine()) != null) {
            	if(!line.isEmpty())
            		lines.add(line);
            }
            fileReader.close();
            
            int size = lines.size() - 2;
            symbols = new String[size];
            String[][] board = new String[size][size];
            for(int i = 1; i <= size + 1; i++) {
            	if(i == 1) {
            		for(int j = 0; j < lines.get(1).split(" ").length; j++) {
	                    symbols[j] = lines.get(1).split(" ")[j];
	                }
            	} else {
	                for(int j = 0; j < lines.get(i).split(" ").length; j++) {
	                    board[i - 2][j] = lines.get(i).split(" ")[j];
	                }
            	}
            }
            return board;
        } catch (FileNotFoundException fnfe) {
        	fnfe.printStackTrace();
            return null;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }

	public static String[] getSymbols() {
		return symbols;
	}
}