package lost_and_found;

import java.util.*;

class AVLTree<K extends Comparable<K>, V extends LostFoundItem> {
    private AVLNode<K, V> root;

    private int height(AVLNode<K, V> node) {
        return (node == null) ? 0 : node.height;
    }

    private int balanceFactor(AVLNode<K, V> node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private AVLNode<K, V> rotateRight(AVLNode<K, V> y) {
        AVLNode<K, V> x = y.left;
        AVLNode<K, V> T = x.right;

        x.right = y;
        y.left = T;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private AVLNode<K, V> rotateLeft(AVLNode<K, V> x) {
        AVLNode<K, V> y = x.right;
        AVLNode<K, V> T = y.left;

        y.left = x;
        x.right = T;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public void insert(K key, V value) {
        root = insert(root, key, value);
    }

    private AVLNode<K, V> insert(AVLNode<K, V> node, K key, V value) {
        if (node == null) {
            return new AVLNode<>(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = insert(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = insert(node.right, key, value);
        } else {
            return node;
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        int balance = balanceFactor(node);

        if (balance > 1 && key.compareTo(node.left.key) < 0) {
            return rotateRight(node);
        }

        if (balance < -1 && key.compareTo(node.right.key) > 0) {
            return rotateLeft(node);
        }

        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }
    
    public List<V> searchInOrder(String query) {
        List<V> results = new ArrayList<>();
        searchInOrder(root, query, results);
        return results;
    }
    
    private void searchInOrder(AVLNode<K, V> node, String query, List<V> results) {
        if (node != null) {
            searchInOrder(node.left, query, results);
                if (node.value.matches(query)) {
                    results.add(node.value);
                } else {
                }
                searchInOrder(node.right, query, results);
        }
    }
}
