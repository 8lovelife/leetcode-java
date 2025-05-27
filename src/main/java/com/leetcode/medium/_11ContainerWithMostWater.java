package com.leetcode.medium;

public class _11ContainerWithMostWater {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = Math.min(height[left], height[right]) * right;
        while (left < right) {

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }

            int curMaxArea = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(curMaxArea, maxArea);
        }
        return maxArea;
    }

    public int maxAreaTLE(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                maxArea = Math.max(maxArea, (j - i) * Math.min(height[i], height[j]));
                System.out.println(maxArea);
            }
        }
        return maxArea;
    }

    public int maxArea2(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            int minHeight = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, (right - left) * minHeight);
            if (height[left] < height[right]) {
                while (left < right && height[left] <= minHeight)
                    left++;
            } else {
                while (left < right && height[right] <= minHeight)
                    right--;
            }
        }

        return maxArea;
    }
}
