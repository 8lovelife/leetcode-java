package com.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _39CombinationSum {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs2(candidates, 0, 0, target, new ArrayList<>(), ans);
        return ans;
    }

    private void dfs2(int[] candidates, int curIndex, int curTarget, int target, List<Integer> result,
            List<List<Integer>> ans) {
        if (target == curTarget) {
            ans.add(new ArrayList<>(result));
            return;
        } else if (target < curTarget) {
            return;
        }
        for (int i = curIndex; i < candidates.length; i++) {
            int curCandidate = candidates[i];
            if (curCandidate > target) {
                continue;
            }
            result.add(curCandidate);
            dfs(candidates, i, curTarget + curCandidate, target, result, ans);
            result.remove(result.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, 0, 0, target, result, ans);
        return ans;
    }

    private void dfs(int[] candidates, int curIndex, int curTarget, int target, List<Integer> result,
            List<List<Integer>> ans) {
        if (target == curTarget) {
            ans.add(new ArrayList<>(result));
            return;
        } else if (target < curTarget) {
            return;
        }
        for (int i = curIndex; i < candidates.length; i++) {
            int curCandidate = candidates[i];
            if (curCandidate > target) {
                return;
            }
            result.add(curCandidate);
            dfs(candidates, i, curTarget + curCandidate, target, result, ans);
            result.remove(result.size() - 1);
        }
    }
}
