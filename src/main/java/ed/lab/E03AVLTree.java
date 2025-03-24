package ed.lab;

import java.util.Comparator;

public class E03AVLTree<T> {
    private final Comparator<T> comparator;
    private TreeNode<T> root;
    private int size;

    public E03AVLTree(Comparator<T> comparator) {
        this.comparator = comparator;
        this.root = null;
        this.size = 0;
    }

    // Altura de un nodo (devuelve 0 cuando el nodo es null)
    private int height(TreeNode<T> node) {
        return (node == null) ? 0 : 1 + Math.max(height(node.left), height(node.right));
    }

    public int height() {
        return height(root);
    }

    // Factor de balance
    private int balanceFactor(TreeNode<T> node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    // Rotación a la izquierda
    private TreeNode<T> rotateLeft(TreeNode<T> node) {
        TreeNode<T> newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        return newRoot;
    }

    // Rotación a la derecha
    private TreeNode<T> rotateRight(TreeNode<T> node) {
        TreeNode<T> newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        return newRoot;
    }

    // Rebalancear el árbol
    private TreeNode<T> rebalance(TreeNode<T> node) {
        int balance = balanceFactor(node);

        if (balance > 1) { // Árbol desequilibrado hacia la izquierda
            if (balanceFactor(node.left) < 0) { // Rotación doble derecha
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        } else if (balance < -1) { // Árbol desequilibrado hacia la derecha
            if (balanceFactor(node.right) > 0) { // Rotación doble izquierda
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        }

        return node;
    }

    // Insertar un nodo
    private TreeNode<T> insert(TreeNode<T> node, T value) {
        if (node == null) {
            size++;
            return new TreeNode<>(value);
        }

        if (comparator.compare(value, node.value) < 0) {
            node.left = insert(node.left, value);
        } else if (comparator.compare(value, node.value) > 0) {
            node.right = insert(node.right, value);
        }

        return rebalance(node);
    }

    public void insert(T value) {
        root = insert(root, value);
    }

    // Eliminar un nodo
    private TreeNode<T> delete(TreeNode<T> node, T value) {
        if (node == null) {
            return null;
        }

        if (comparator.compare(value, node.value) < 0) {
            node.left = delete(node.left, value);
        } else if (comparator.compare(value, node.value) > 0) {
            node.right = delete(node.right, value);
        } else {
            // Nodo encontrado
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
                size--; // Actualizar tamaño
            } else {
                TreeNode<T> minNode = findMin(node.right);
                node.value = minNode.value;
                node.right = delete(node.right, minNode.value);
            }
        }

        return rebalance(node);
    }

    public void delete(T value) {
        root = delete(root, value);
    }

    // Encontrar el nodo con el valor mínimo en un subárbol
    private TreeNode<T> findMin(TreeNode<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Buscar un nodo
    public T search(T value) {
        TreeNode<T> node = root;
        while (node != null) {
            int cmp = comparator.compare(value, node.value);
            if (cmp == 0) {
                return node.value;
            }
            node = (cmp < 0) ? node.left : node.right;
        }
        return null;
    }

    // Tamaño del árbol
    public int size() {
        return size;
    }
}

