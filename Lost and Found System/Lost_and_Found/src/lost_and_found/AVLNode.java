package lost_and_found;

public class AVLNode<K extends Comparable<K>, V extends LostFoundItem> {
    K key;
    V value;
    int height;
    AVLNode<K, V> left, right;

    public AVLNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.height = 1;
    }
}
