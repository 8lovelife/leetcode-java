package com.leetcode.medium;


import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class MediumTest {


    @Test
    public void _684() {
        int[][] edges = new int[][] { { 1, 2 }, { 1, 3 }, { 2, 3 } };
        int[] result = new _684RedundantConnection().findRedundantConnectionPostDFS(edges);
        System.out.println(Arrays.toString(result));
    }

}