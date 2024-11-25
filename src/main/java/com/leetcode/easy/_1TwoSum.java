package com.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

public class _1TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for (int i = 0; i < nums.length; i++) {
            int search = target - nums[i];
            Integer matchedIndex = map.get(search);
            if (matchedIndex != null){
                return new int[]{matchedIndex,i};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }
    
}
