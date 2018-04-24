package com.usu.sudokuSolver.solution;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.usu.sudokuSolver.board.SudokuBoard;
import com.usu.sudokuSolver.template.SudokuSolver;

/**
 * @author Anuj Khasgiwala
 *
 */
public class BackTrackingSudokuSolver extends SudokuSolver {

	SudokuBoard sb;

	public BackTrackingSudokuSolver(SudokuBoard sudokuBoard) {
		this.sb = sudokuBoard;
		final JPanel panel = sudokuBoard.getPanel();

		Runnable runner = new Runnable() {
			public void run() {
				final JFrame frame = new JFrame("Sudoku Solver");
				frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

				ActionListener solveBtnListener = new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						if (! solve(0, 0)) {
							sb.export(outputFileName, "This puzzle cannot be solved!");
						} else {
							sb.export(outputFileName);
						}
					}
				};
				
				frame.setLayout(new GridBagLayout());
                addToSwing(frame, panel, 0, 0, 1, 1);
                
                JButton btnSolve = new JButton("Solve!");
                btnSolve.addActionListener(solveBtnListener);
                addToSwing(frame, btnSolve, 0, 1, 1, 1);

                frame.setSize(500, 600);
                frame.setVisible(true);
			}
		};
		EventQueue.invokeLater(runner);
	}

	@Override
	public boolean solve(int row, int col) {
		if (row == sb.size) {
			row = 0;
			if (++col == sb.size)
				return true;
		}
		if (!sb.getCell(row, col).equals("-"))  // skip filled cells
			return solve(row+1,col);

		for (int i = 0; i < sb.size; ++i) {
			if (legal(row, col, symbols[i])) {
				sb.setCell(symbols[i], row, col);
				if (solve(row+1,col))
					return true;
			}
		}
		sb.setCell("-", row, col); // reset on backtrack
		return false;
	}

	boolean legal(int i, int j, String val) {
		for (int k = 0; k < sb.size; ++k)  // row
			if (val.equals(sb.getCell(k, j)))
				return false;

		for (int k = 0; k < sb.size; ++k) // col
			if (val.equals(sb.getCell(i, k)))
				return false;

		int boxRowOffset = (i / sb.box_size) * sb.box_size;
		int boxColOffset = (j / sb.box_size) * sb.box_size;
		for (int k = 0; k < sb.box_size; ++k)
			for (int m = 0; m < sb.box_size; ++m)
				if (val.equals(sb.getCell(boxRowOffset+k, boxColOffset+m)))
					return false;

		return true;
	}
}