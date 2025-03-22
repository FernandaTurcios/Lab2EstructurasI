package ed.lab;

public class Main {

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(8);
        root.right = new TreeNode<>(5);

        System.out.println("Árbol:");
        System.out.println(root);

        // Crear instancia de E01InvertBT y ejecutar invertTree
        E01InvertBT inverter = new E01InvertBT();
        TreeNode<Integer> invertedRoot = inverter.invertTree(root);

        System.out.println("Árbol invertido:");
        System.out.println(invertedRoot);
    }

}
