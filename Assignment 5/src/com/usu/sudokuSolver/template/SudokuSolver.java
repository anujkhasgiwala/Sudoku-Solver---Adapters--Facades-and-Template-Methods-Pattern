package com.usu.sudokuSolver.template;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Anuj Khasgiwala
 *
 */
public abstract class SudokuSolver {

	public static String outputFileName = null;

	public static String[] symbols = null;

	protected abstract boolean solve(int row, int column);
	
	static List<Integer> boardSizes = new ArrayList<Integer>(Arrays.asList(2, 3, 4, 5));
	
	protected static void addToSwing(Container container, Component component, int gridx, int gridy, int gridwidth, int gridheight) {
        Insets insets = new Insets(30, 10, 10, 10);
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets, 0, 0);
        container.add(component, gbc);
    }

	public static boolean isSudokuBoardValid(String board[][]) {
		if (board == null || !validBoardSize(board))
			return false;

		if(containsDuplicate(board)) {
			return false;
		} else if(containsOutlier(board)) {
			return false;
		}

		return true;
	}

	private static boolean containsDuplicate(String board[][]) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				Set<String> row = new HashSet<String>();
				if(!board[i][j].equals("-")) {
					//check duplicates in row
					for(int k = 0; k < board.length; k++) {
						if(!board[i][k].equals("-")) {
							if(row.contains(board[i][k]))
								return true;
							else
								row.add(board[i][k]);
						}
					}
				}
			}
		}

		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				Set<String> column = new HashSet<String>();
				if(!board[i][j].equals("-")) {
					//check duplicates in row
					for(int k = 0; k < board.length; k++) {
						if(!board[k][j].equals("-")) {
							if(column.contains(board[k][j]))
								return true;
							else
								column.add(board[k][j]);
						}
					}
				}
			}
		}

		return false;
	}

	private static boolean containsOutlier(String board[][]) {
		List<String> symbolList = new ArrayList<String>(Arrays.asList(symbols));
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(!board[i][j].equals("-")) {
					if(!symbolList.contains(board[i][j])) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private static boolean validBoardSize(String board[][]) {
		if(!boardSizes.contains(Math.sqrt(board.length))) {
			return false;
		}
		
		for(int i = 0; i < board.length; i++) {
			if(board[i].length != board.length) {
				return false;
			}
		}
		return true;
	}
}