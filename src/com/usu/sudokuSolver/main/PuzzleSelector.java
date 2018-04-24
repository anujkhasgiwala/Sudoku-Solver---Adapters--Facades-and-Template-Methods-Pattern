package com.usu.sudokuSolver.main;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.usu.sudokuSolver.board.SudokuBoard;
import com.usu.sudokuSolver.board.SudokuBoardParser;
import com.usu.sudokuSolver.solution.BackTrackingSudokuSolver;
import com.usu.sudokuSolver.solution.RecursiveBruteSudokuSolver;
import com.usu.sudokuSolver.template.SudokuSolver;
import com.usu.sudokuSolver.utils.Utilities;

/**
 * @author Anuj Khasgiwala
 *
 */
public class PuzzleSelector {
	
	static SudokuSolver sudokuSolver = null;
	
	public static void main(String[] args) {
		List<String> dialogInputs = getInputOutputFileNameAndAlgorithm();
		if(!dialogInputs.get(0).isEmpty() && !dialogInputs.get(1).isEmpty() && !dialogInputs.get(2).isEmpty()) {
			String[][] board = SudokuBoardParser.parse(dialogInputs.get(0));
			SudokuSolver.symbols = SudokuBoardParser.getSymbols();
			SudokuSolver.outputFileName = dialogInputs.get(1);
			if(SudokuSolver.isSudokuBoardValid(board)) {
				if(dialogInputs.get(2).equals("Back Tracking"))
					sudokuSolver = new BackTrackingSudokuSolver(new SudokuBoard(board));
				else if(dialogInputs.get(2).equals("Brute Force"))
					sudokuSolver = new RecursiveBruteSudokuSolver(new SudokuBoard(board));
			} else {
				Utilities.exportSolution(SudokuSolver.outputFileName, board, "Invalid Puzzle");
				JOptionPane.showMessageDialog(new JFrame(), "Invalid Puzzle!!");
			}
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "Missing inputs!!");
		}
	}

	private static List<String> getInputOutputFileNameAndAlgorithm() {
		List<String> dialogInputs = new ArrayList<String>();
		
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new GridLayout(3,1));
		JLabel inputLabel = new JLabel("Please enter input file name: ");
		myPanel.add(inputLabel);
		JTextField inputFileName = new JTextField(10);
		myPanel.add(inputFileName);
		JLabel outputLabel = new JLabel("Please enter output file name: ");
		myPanel.add(outputLabel);
	    JTextField outputFileName = new JTextField(10);
	    myPanel.add(outputFileName);
	    JLabel algorithmLabel = new JLabel("Please select an Algorithm: ");
		myPanel.add(algorithmLabel);
	    JComboBox<String> algorithmSelection = new JComboBox<String>(new String[] {"", "Back Tracking","Brute Force"});
	    myPanel.add(algorithmSelection);
	    
	    JOptionPane.showMessageDialog(null, myPanel);
		
		dialogInputs.add(inputFileName.getText());
		dialogInputs.add(outputFileName.getText());
		dialogInputs.add((String)algorithmSelection.getSelectedItem());
		return dialogInputs;
	}
}