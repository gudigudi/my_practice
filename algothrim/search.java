// example of red-white tree
//
//
//
public V search(K k) {
    BalanceBinaryNode<K, V> p = root;
    while (p != null) {
        int cmp = compare(k, p.key);
        if (cmp == 0) {
            return p.value;
        } else if (cmp < 0) {
            p = p.left;
        } else {
            p = p.right;
        }
    }
    // not found
    return null;
}
