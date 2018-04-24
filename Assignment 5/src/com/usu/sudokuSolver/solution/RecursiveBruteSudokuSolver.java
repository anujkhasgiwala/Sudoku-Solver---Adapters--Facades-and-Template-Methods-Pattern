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
public class RecursiveBruteSudokuSolver extends SudokuSolver {

    SudokuBoard sb;

    public RecursiveBruteSudokuSolver(SudokuBoard sudokuBoard) {
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
        int nextCol = (col + 1) % sb.size;
        int nextRow = (nextCol == 0) ? row + 1 : row;

        try {
            if (!sb.getCell(row, col).equals("-"))
                return solve(nextRow, nextCol);
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }

        for (int i = 0; i < sb.size; i++) {
            if (check(symbols[i], row, col)) {
            	sb.setCell(symbols[i], row, col);
                if (solve(nextRow, nextCol)) {
                    return true;
                }
            }
        }
        sb.setCell(sb.EMPTY, row, col);
        return false;
    }

    private boolean check(String num, int row, int col) {
        int r = (row / sb.box_size) * sb.box_size;
        int c = (col / sb.box_size) * sb.box_size;

        for (int i = 0; i < sb.size; i++) {
            if (sb.getCell(row, i).equals(num) || sb.getCell(i, col).equals(num) || sb.getCell(r + (i % sb.box_size), c + (i / sb.box_size)).equals(num)) {
                return false;
            }
        }
        return true;
    }
}