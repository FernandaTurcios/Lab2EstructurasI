package ed.lab;

public class Main {

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(8);
        root.right = new TreeNode<>(5);

        System.out.println("Ejercicio 1");
        System.out.println("Árbol normal:");
        System.out.println(root);

        E01InvertBT inverter = new E01InvertBT();
        TreeNode<Integer> invertedRoot = inverter.invertTree(root);

        System.out.println("Árbol invertido:");
        System.out.println(invertedRoot);

        System.out.println("Ejercicio 2");

        E02KthSmallest kthSmallestFinder = new E02KthSmallest();
        int k = 3;
        System.out.println("El " + k + "-ésimo elemento más pequeño es: " + kthSmallestFinder.kthSmallest(root, k));

    }

}
