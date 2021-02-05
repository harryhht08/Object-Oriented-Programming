package model;

import java.util.Random;

/* This class must extend Game */
public class ClearCellGame extends Game {

	private Random random;
	private int strategy;
	private int score;

	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		this.random = random;
		this.strategy = strategy;
		score = 0;
	}

	@Override
	public boolean isGameOver() {
		for (int i = 0; i < board[0].length; i++) {
			if (board[board.length - 1][i] != BoardCell.EMPTY) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public void nextAnimationStep() {
		BoardCell[][] newBoard = new BoardCell[board.length][board[0].length];
		for (int i = 0; i < newBoard.length; i++) {
			for (int j = 0; j < newBoard[0].length; j++) {
				if (i == 0) {
					newBoard[i][j] = BoardCell.getNonEmptyRandomBoardCell(random);
				} else {
					newBoard[i][j] = board[i - 1][j];
				}
			}
		}
		board = newBoard;

	}

	@Override
	public void processCell(int rowIndex, int colIndex) {
		if (!isGameOver()) {
			helpDiagonal(rowIndex, colIndex, -1, -1);
			helpDiagonal(rowIndex, colIndex, 1, -1);
			helpDiagonal(rowIndex, colIndex, -1, 1);
			helpDiagonal(rowIndex, colIndex, 1, 1);
			helpDiagonal(rowIndex, colIndex, 0, -1);
			helpDiagonal(rowIndex, colIndex, 0, 1);
			helpDiagonal(rowIndex, colIndex, 1, 0);
			helpDiagonal(rowIndex, colIndex, -1, 0);
			board[rowIndex][colIndex] = BoardCell.EMPTY;
			score++;
			mergeRows();
		}
	}

	private void helpDiagonal(int rowIndex, int colIndex, int rowChange, int colChange) {
		int diagonalRow = rowIndex + rowChange;
		int diagonalCol = colIndex + colChange;
		BoardCell current = board[rowIndex][colIndex];
		while (diagonalRow >= 0 && diagonalRow < board.length && diagonalCol >= 0 && diagonalCol < board[0].length) {
			if (board[diagonalRow][diagonalCol] == current) {
				score++;
				board[diagonalRow][diagonalCol] = BoardCell.EMPTY;
			} else {
				break;
			}
			diagonalRow += rowChange;
			diagonalCol += colChange;
		}
	}

	private void mergeRows() {
		int[] realRows = new int[board.length];
		for (int i = 0; i < realRows.length; i++) {
			realRows[i] = 0;
		}
		boolean rowIsEmpty;
		for (int i = 0; i < board.length; i++) {
			rowIsEmpty = true;
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != BoardCell.EMPTY) {
					rowIsEmpty = false;
					break;
				}
			}
			if (!rowIsEmpty) {
				realRows[i] = 1;
			}
		}

		int boardCursor = 0;
		for (int i = 0; i < realRows.length; i++) {
			if (realRows[i] == 1) {
				for (int j = 0; j < board[0].length; j++) {
					board[boardCursor][j] = board[i][j];
				}
				boardCursor++;
			}
		}
		while (boardCursor < board.length) {
			for (int i = 0; i < board[0].length; i++) {
				board[boardCursor][i] = BoardCell.EMPTY;
			}
			boardCursor++;
		}

	}

}