package com.leetcode.medium;

import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class _133CloneGraph {
    public Node cloneGraph(Node node) {
        Map<Node, Node> map = new HashMap<>();
        Node copyNode = copy(node, map);
        return copyNode;
    }

    public Node copy(Node node, Map<Node, Node> map) {
        if (node == null)
            return null;
        if (map.containsKey(node)) {
            return map.get(node);
        }
        Node copy = new Node(node.val);
        map.put(node, copy);
        for (Node neighbor : node.neighbors) {
            copy.neighbors.add(copy(neighbor, map));
        }
        return copy;
    }
}
