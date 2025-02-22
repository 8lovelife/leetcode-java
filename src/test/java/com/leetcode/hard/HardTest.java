package com.leetcode.hard;

import com.leetcode.hard._2493DivideNodesIntoMaxGroups;
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
        int[][] edges = new int[][] { { 0,1 },{1,2},{2,5},{3,5},{4,5},{3,4},{0,5} };
        int shortestCycle = new _2608ShortestCycle().findShortestCycle(6, edges);
        Assert.assertTrue(shortestCycle == 3);
    }
    
}