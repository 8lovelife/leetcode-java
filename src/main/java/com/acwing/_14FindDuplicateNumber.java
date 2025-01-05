package com.acwing;

public class _14FindDuplicateNumber {

    public static void main(String[] args) {
        int[] numbers = new int[]{1, 3, 4, 4,2};
        System.out.println(_14FindDuplicateNumber.findDuplicateNumber(numbers));
    }

    public static Integer findDuplicateNumber(int[] numbers) {
        int l = 1,r = numbers.length - 1;
        while (l < r) {
            int count = 0;
            int mid = l + (r-l) / 2;
            for (int number : numbers) {
                if (number >= l && number <= mid) { // inspect [l,mid] section
                    count++;
                }
            }
            if (count > mid - l + 1) { // duplicate number in [l,mid] which have (mid-l+1) numbers.
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}