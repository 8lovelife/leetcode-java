package com.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

public class _212WordSearch2 {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String endWord;

        void insert(String word) {
            TrieNode current = this;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (current.children[idx] == null) {
                    current.children[idx] = new TrieNode();
                }
                current = current.children[idx];
            }
            current.endWord = word;
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        TrieNode trieNode = new TrieNode();
        for (String word : words) {
            trieNode.insert(word);
        }
        int rowL = board.length;
        int colL = board[0].length;
        for (int i = 0; i < rowL; i++) {
            for (int j = 0; j < colL; j++) {
                exist(i, j, board, trieNode, ans);
            }
        }
        return ans;
    }

    private void exist(int row, int col, char[][] board, TrieNode trieNode, List<String> ans) {
        char c = board[row][col];
        if (c == '#') {
            return;
        }
        TrieNode node = trieNode.children[c - 'a'];
        if (node == null) {
            return;
        }
        if (node.endWord != null) {
            ans.add(node.endWord);
            node.endWord = null;
        }

        trieNode = node;

        board[row][col] = '#';
        if (row > 0)
            exist(row - 1, col, board, node, ans);
        if (row < board.length - 1)
            exist(row + 1, col, board, node, ans);
        if (col > 0)
            exist(row, col - 1, board, node, ans);
        if (col < board[0].length - 1)
            exist(row, col + 1, board, node, ans);

        board[row][col] = c;
    }

}
