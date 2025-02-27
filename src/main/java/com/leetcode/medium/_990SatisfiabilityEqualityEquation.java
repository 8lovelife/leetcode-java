package com.leetcode.medium;

import com.leetcode.DisjointSet;

public class _990SatisfiabilityEqualityEquation {

    public boolean equationsPossible(String[] equations) {
        int n = equations.length;
        DisjointSet disjointSet = new DisjointSet(26);
        for (String equation : equations) {
            char c = equation.charAt(1);
            char lc = equation.charAt(0);
            char rc = equation.charAt(3);
            if (c == '=') {
                disjointSet.union(lc - 'a', rc - 'a');
            }
        }
        for (String equation : equations) {
            char c = equation.charAt(1);
            char lc = equation.charAt(0);
            char rc = equation.charAt(3);
            if (c != '=' && disjointSet.isConnected(lc - 'a', rc - 'a')) {
                return false;
            }
        }
        return true;
    }
}
