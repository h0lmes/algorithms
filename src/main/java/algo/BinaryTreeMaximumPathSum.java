package algo;

public class BinaryTreeMaximumPathSum {

    public static class TreeNode {
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

    public static void main(String[] args) {
        System.out.println(new BinaryTreeMaximumPathSum().maxPathSum(
                new TreeNode(1, new TreeNode(2), new TreeNode(3))
        ));

        System.out.println(new BinaryTreeMaximumPathSum().maxPathSum(
                new TreeNode(1, new TreeNode(2), new TreeNode(3))
        ));
    }

    public int maxPathSum(TreeNode root) {
        TreeNode q = new TreeNode(Integer.MIN_VALUE);

        int left = 0;
        int right = 0;
        if (root.left != null) left = maxPath(root.left, q);
        if (root.right != null) right = maxPath(root.right, q);

        int max = root.val;
        if (root.val + left > max) max = root.val + left;
        if (root.val + right > max) max = root.val + right;
        if (root.val + left + right > max) max = root.val + left + right;

        return Math.max(q.val, max);
    }

    private int maxPath(TreeNode root, TreeNode q) {
        int left = 0;
        int right = 0;
        if (root.left != null) left = maxPath(root.left, q);
        if (root.right != null) right = maxPath(root.right, q);

        int max = root.val;
        if (root.val + left > max) max = root.val + left;
        if (root.val + right > max) max = root.val + right;
        if (root.val + left + right > max) max = root.val + left + right;

        if (q.val < max) q.val = max;

        return Math.max(root.val, root.val + Math.max(left, right));
    }
}
