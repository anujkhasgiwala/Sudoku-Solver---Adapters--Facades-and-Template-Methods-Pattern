package com.usu.sudokuSolver.board;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import com.usu.sudokuSolver.utils.Utilities;

/**
 * @author Anuj Khasgiwala
 *
 */
public class SudokuBoard {
	private JLabel[][] cells;
	private JPanel panel = new JPanel();

	public final String EMPTY = "-";
	public final int size;
	public final int box_size;

	private String[][] board;

	public SudokuBoard(int size) {
		board = new String[size][size];
		this.size = size;
		this.box_size = (int) Math.sqrt(size);
		cells = new JLabel[size][size];
		panel.setLayout(new GridLayout(size, size));
		Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++)  {
				cells[row][col] = new JLabel("", SwingConstants.CENTER);
				cells[row][col].setBorder(border);
				panel.add(cells[row][col]);
			}
		}
	}

	public SudokuBoard(String[][] board) {
		this(board.length);
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				setCell(board[row][col], row, col);
			}
		}
	}

	public void setCell(String boardSymbol, int row, int col) {
		board[row][col] = boardSymbol;
		String text = (boardSymbol == "-") ? "-" : boardSymbol;
		cells[row][col].setText(text);
	}

	public String getCell(int row, int col) {
		return board[row][col];
	}

	public String[] getRow(int row) {
		String[] theRow = new String[board.length];
		for(int i = 0 ; i < board.length; i++) {
			theRow[i] = board[row][i];
		}
		return theRow;
	}

	public String[] getColumn(int column) {
		String[] theColumn = new String[board.length];
		for(int i = 0; i < board.length; i++)
		{
			theColumn[i] = board[i][column];
		}
		return theColumn;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void export(String outputFileName) {
		Utilities.exportSolution(outputFileName, board);
		JOptionPane.showMessageDialog(new JFrame(), "Solution saved at: "+outputFileName);
	}
	
	public void export(String outputFileName, String message) {
		Utilities.exportSolution(outputFileName, board, message);
	}
}