package com.leetcode.medium;

public class _79WordSearch {
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int column = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (exist(i, j, board, word, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean exist(int row, int column, char[][] board, String word, int index) {
        if (index == word.length()) {
            return true;
        }

        if (row < 0 || row >= board.length
                || column < 0 || column >= board[0].length
                || board[row][column] != word.charAt(index)) {
            return false;
        }

        boolean result = false;
        char tmp = board[row][column];
        board[row][column] = '#';
        result |= exist(row - 1, column, board, word, index + 1);
        result |= exist(row + 1, column, board, word, index + 1);
        result |= exist(row, column - 1, board, word, index + 1);
        result |= exist(row, column + 1, board, word, index + 1);
        board[row][column] = tmp;

        return result;

    }
}
