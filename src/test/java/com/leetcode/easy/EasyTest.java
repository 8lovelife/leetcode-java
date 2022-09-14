package com.leetcode.easy;

import org.junit.Assert;
import org.junit.Test;
/**
 * Unit test for simple App.
 */
public class EasyTest 
{

    @Test
    public void twoSum()
    {
        int[] nums = new int[]{1,3,7,5,4};
        int target = 4;
        int[] result = TwoSum.twoSum(nums, target);
        Assert.assertTrue(result!=null 
        && (nums[result[0]] + nums[result[1]]) == target); 
    }
}
