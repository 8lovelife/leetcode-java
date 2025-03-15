package com.leetcode.medium;

public class _419BattleshipsOnBoard {
    public int countBattleships(char[][] board) {
        int rL = board.length;
        int cL = board[0].length;
        int count = 0;
        for (int r = 0; r < rL; r++) {
            for (int c = 0; c < cL; c++) {
                if (board[r][c] == 'X') {
                    count++;
                    dfs(board, r, rL, c, cL);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] board, int r, int rL, int c, int cL) {

        if (r >= rL || r < 0 || c >= cL || c < 0 || board[r][c] == '.') {
            return;
        }

        board[r][c] = '.';
        // dfs(board, r - 1, rL, c, cL); // vertically
        dfs(board, r + 1, rL, c, cL);

        // dfs(board, r, rL, c - 1, cL); // horizontally
        dfs(board, r, rL, c + 1, cL);

    }
}
