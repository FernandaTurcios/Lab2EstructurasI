package ed.lab;

public class E02KthSmallest {

    public int kthSmallest(TreeNode<Integer> root, int k) {
        int[] result = {0};
        int[] count = {0};

        inOrder(root, count, k, result);
        return result[0];
    }

    private void inOrder(TreeNode<Integer> node, int[] count, int k, int[] result) {
        if (node == null) {
            return;
        }

        inOrder(node.left, count, k, result);

        count[0]++;
        if (count[0] == k) {
            result[0] = node.value;
            return;
        }

        inOrder(node.right, count, k, result);

    }

}