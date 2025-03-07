package com.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leetcode.TreeNode;

public class _2096StepByStepDirections {

    public String getDirections(TreeNode root, int startValue, int destValue) {
        Map<Integer, Integer[]> ancestorMap = new HashMap<>();
        recordAncestor(root, null, null, ancestorMap);

        List<Integer> startAncestors = new ArrayList<>();
        Integer[] startCur = { startValue, 1 };
        while (startCur != null) {
            startAncestors.add(startCur[0]);
            startCur = ancestorMap.get(startCur[0]);
        }

        StringBuilder sbDest = new StringBuilder();
        Integer lca = null;
        Integer[] destV = { destValue, 0 };
        while (destV != null) {
            if (startAncestors.contains(destV[0])) {
                lca = destV[0];
                break;
            }
            destV = ancestorMap.get(destV[0]);
            String destPath = destV[1] == 1 ? "L" : "R";
            sbDest.append(destPath);
        }

        StringBuilder sbStart = new StringBuilder();
        startCur = new Integer[] { startValue, 0 };
        while (startCur != null) {
            if (startCur[0].equals(lca)) {
                break;
            }
            sbStart.append("U");
            startCur = ancestorMap.get(startCur[0]);
        }

        sbStart.append(sbDest.reverse());

        return sbStart.toString();

    }

    private void recordAncestor(TreeNode node, TreeNode parent, Integer direction,
            Map<Integer, Integer[]> ancestorMap) {
        if (node == null) {
            return;
        }

        if (parent != null) {
            ancestorMap.put(node.val, new Integer[] { parent.val, direction });
        }

        recordAncestor(node.left, node, 1, ancestorMap);
        recordAncestor(node.right, node, 0, ancestorMap);
    }
}
