package com.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;

public class HardTest {

    @Test
    public void _2493() {
        int[][] edges = new int[][] { { 1, 2 } };
        int diameter = new _2493DivideNodesIntoMaxGroups().magnificentSets2BFS(6, edges);
        Assert.assertTrue(diameter == 6);
    }

    @Test
    public void _2608() {
        int[][] edges = new int[][] { { 4, 1 }, { 3, 2 }, { 5, 0 }, { 3, 0 }, { 4, 0 }, { 2, 1 }, { 5, 1 } };
        int shortestCycle = new _2608ShortestCycle().findShortestCycleDFS(6, edges);
        Assert.assertTrue(shortestCycle == 4);
    }

    @Test
    public void _2360() {
        int[] edges = new int[] { 1, 0 };
        int shortestCycle = new _2360LongestCycle().longestCycle(edges);
        Assert.assertTrue(shortestCycle == 2);
    }

}