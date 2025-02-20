package com.leetcode.hard;

import com.leetcode.hard._2493DivideNodesIntoMaxGroups;
import org.junit.Assert;
import org.junit.Test;

public class HardTest {


    @Test
    public void _2493() {
        int[][] edges = new int[][] { { 1, 2 } };
        int diameter = new _2493DivideNodesIntoMaxGroups().magnificentSets(6, edges);
        System.out.println(diameter);
        Assert.assertTrue(diameter == 6);
    }
    
}