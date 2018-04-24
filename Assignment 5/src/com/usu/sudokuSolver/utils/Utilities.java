package com.usu.sudokuSolver.utils;

import javax.swing.*;

import com.usu.sudokuSolver.template.SudokuSolver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Anuj Khasgiwala
 *
 */
public class Utilities {

	public static void exportSolution(String filename, String[][] matrix) {
		try {
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filename));
			fileWriter.write(String.valueOf(matrix[0].length));
			fileWriter.newLine();
			for (String symbolMatrix : SudokuSolver.symbols) {
				fileWriter.write(symbolMatrix + " ");
			}
			fileWriter.newLine();
			for (String[] aMatrix : matrix) {
				for (String anAMatrix : aMatrix) {
					fileWriter.write(anAMatrix + " ");
				}
				fileWriter.newLine();
			}
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JFrame(), "Problem saving file!");
		}
	}
	
	public static void exportSolution(String filename, String[][] matrix, String message) {
		try {
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filename));
			fileWriter.write(String.valueOf(matrix[0].length));
			fileWriter.newLine();
			for (String symbolMatrix : SudokuSolver.symbols) {
				fileWriter.write(symbolMatrix + " ");
			}
			fileWriter.newLine();
			for (String[] aMatrix : matrix) {
				for (String anAMatrix : aMatrix) {
					fileWriter.write(anAMatrix + " ");
				}
				fileWriter.newLine();
			}
			fileWriter.write(message);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JFrame(), "Problem saving file!");
		}
	}
}
