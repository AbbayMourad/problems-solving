import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
}

public class VerticalOrderTraversalOfABinaryTree {
    private Map<Integer, Map<Integer, List<Integer>>> byColsMap = new TreeMap<>();

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        registerNode(root, 0, 0);
        return getResult();
    }

    // can be done using a loop along with a queue
    private void registerNode(TreeNode node, int row, int col) {
        if (node == null) {
            return;
        }
        Map<Integer, List<Integer>> byRowsMap = byColsMap.get(col);
        if (byRowsMap == null) {
            byRowsMap = new TreeMap<>();
            byColsMap.put(col, byRowsMap);
        }
        List<Integer> list = byRowsMap.get(row);
        if (list == null) {
            list = new ArrayList<>();
            byRowsMap.put(row, list);
        }
        list.add(node.val);
        registerNode(node.left, row + 1, col - 1);
        registerNode(node.right, row + 1, col + 1);
    }

    private List<List<Integer>> getResult() {
        List<List<Integer>> result = new ArrayList<>();
        for (Map<Integer, List<Integer>> byColRowsMap: byColsMap.values()) {
            List<Integer> colNodes = new ArrayList<>();
            for (List<Integer> colRowNodes: byColRowsMap.values()) {
                Collections.sort(colRowNodes);
                colNodes.addAll(colRowNodes);
            }
            result.add(colNodes);
        }
        return result;
    }

    public static void main(String[] args) {
    }
}
