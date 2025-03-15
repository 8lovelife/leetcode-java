package com.leetcode.medium;

public class _130SurroundedRegions {

    public void solve(char[][] board) {
        int rL = board.length;
        int cL = board[0].length;
        for (int r = 0; r < rL; r++) {
            for (int c = 0; c < cL; c++) {
                if (board[r][c] == 'O') {
                    boolean[][] visited = new boolean[rL][cL];
                    if (!pathBoundary(board, r, rL, c, cL, visited)) {
                        dfs(board, r, rL, c, cL);
                    }
                }
            }
        }

    }

    private boolean pathBoundary(char[][] board, int r, int rL, int c, int cL, boolean[][] visited) {

        if (board[r][c] != 'O' || visited[r][c]) {
            return false;
        }

        if ((r == 0 || r == rL - 1 || c == 0 || c == cL - 1) && board[r][c] == 'O') {
            return true;
        }

        visited[r][c] = true;

        boolean topBoundary = pathBoundary(board, r - 1, rL, c, cL, visited);
        if (topBoundary) {
            return true;
        }
        boolean downBoundary = pathBoundary(board, r + 1, rL, c, cL, visited);
        if (downBoundary) {
            return true;
        }
        boolean leftBoundary = pathBoundary(board, r, rL, c - 1, cL, visited);
        if (leftBoundary) {
            return true;
        }
        boolean rightBoundary = pathBoundary(board, r, rL, c + 1, cL, visited);
        if (rightBoundary) {
            return true;
        }
        return false;
    }

    private void dfs(char[][] board, int r, int rL, int c, int cL) {
        if (r >= rL || r < 0 || c >= cL || c < 0 || board[r][c] == 'X') {
            return;
        }
        board[r][c] = 'X';
        dfs(board, r - 1, rL, c, cL);
        dfs(board, r + 1, rL, c, cL);
        dfs(board, r, rL, c - 1, cL);
        dfs(board, r, rL, c + 1, cL);
    }

    public void solveEfficient(char[][] board) {
        int rL = board.length;
        int cL = board[0].length;

        for (int i = 0; i < rL; i++) {
            dfs(board, i, rL, 0, cL);
            dfs(board, i, rL, cL - 1, cL);
        }

        for (int j = 1; j < cL - 1; j++) {
            dfs(board, 0, rL, j, cL);
            dfs(board, rL - 1, rL, j, cL);
        }

        for (int r = 0; r < rL; r++) {
            for (int c = 0; c < cL; c++) {
                if (board[r][c] == 'O') {
                    board[r][c] = 'X';
                } else if (board[r][c] == '#') {
                    board[r][c] = 'O';
                }
            }
        }

    }

    private void dfsEfficient(char[][] board, int r, int rL, int c, int cL) {
        if (r < 0 || r >= rL || c < 0 || c >= cL || board[r][c] != 'O') {
            return;
        }

        board[r][c] = '#';
        dfs(board, r - 1, rL, c, cL);
        dfs(board, r + 1, rL, c, cL);
        dfs(board, r, rL, c - 1, cL);
        dfs(board, r, rL, c + 1, cL);
    }

}
