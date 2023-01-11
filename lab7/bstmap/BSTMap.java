package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable,V> implements Map61B<K, V> {
    public BSTNode root;

    private class BSTNode {
        public K key;
        public V value;
        public BSTNode left;
        public BSTNode right;

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "BSTNode{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if(key == null)
            return false;
        return containsKey(key, root);
    }

    private boolean containsKey(K key, BSTNode node) {
        if(node == null)
            return false;
        if(node.key.equals(key))
            return true;
        else if(key.compareTo(node.key) < 0) {
            return containsKey(key, node.left);
        }
        else
            return containsKey(key, node.right);
    }

    @Override
    public V get(K key) {
        if (!containsKey(key))
            return null;
        return get(key, root);
    }

    private V get(K key, BSTNode node) {
        if(node.key.equals(key))
            return node.value;
        else if(key.compareTo(node.key) < 0)
            return get(key, node.left);
        else
            return get(key, node.right);
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(BSTNode node) {
        if(node == null)
            return 0;
        return 1 + size(node.left) + size(node.right);
    }

    @Override
    public void put(K key, V value) {
        root = put(key, value, root);
    }

    private BSTNode put(K key, V value, BSTNode node) {
        if(node == null)
            return new BSTNode(key, value);
        else if(key.compareTo(node.key) < 0) {
            node.left = put(key, value, node.left);
        } else {
            node.right = put(key, value, node.right);
        }
        return node;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        keySet(set, root);
        return set;
    }

    public void keySet(Set<K> set, BSTNode node) {
        if(node == null)
            return;
        set.add(node.key);
        keySet(set, node.left);
        keySet(set, node.right);
    }

    private BSTNode getNodeByKey(K key) {
        return getNodeByKey(key,root);
    }

    private BSTNode getNodeByKey(K key, BSTNode node) {
        if(node == null) {
            return null;
        }
        if(key.compareTo(node.key) == 0)
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNodeByKey(key, node.left);
        else
            return getNodeByKey(key, node.right);

    }

    private int calChildByNode(BSTNode node) {
        int cnt = 0;
        if(node.left != null)
            cnt += 1;
        if(node.right != null)
            cnt += 1;
        return cnt;
    }

    private BSTNode fineRight(BSTNode node) {
        if(node.right != null)
            return fineRight(node.right);
        return node;
    }

    private BSTNode findFather(K key, BSTNode root) {
        if(root == null)
            return null;
        if(root.left != null && root.left.key == key || root.right != null && root.right.key == key)
            return root;
        if(key.compareTo(root.key) < 0)
            return findFather(key, root.left);
        else
            return findFather(key, root.right);
    }

    @Override
    public V remove(K key) {
        if(!containsKey(key)) {
            return null;
        }
        BSTNode node = getNodeByKey(key);
        int cntChild = calChildByNode(node);
        if(node == root) {
            if(cntChild == 0)
                clear();
            else if(cntChild == 1) {
                if(node.left != null)
                    root = node.left;
                else
                    root = node.right;
            } else {
                BSTNode newRoot = fineRight(node.left);
                BSTNode newRootFather = findFather(newRoot.key, root);
                BSTNode fatherNext = newRoot.left;
                if(newRootFather.left != null && newRootFather.left == newRoot) {
                    newRootFather.left = fatherNext;
                } else {
                    newRootFather.right = fatherNext;
                }
                newRoot.left = root.left;
                newRoot.right = root.right;
                root = newRoot;
            }
        } else {
            BSTNode father = findFather(node.key, root);
            if(cntChild == 0) {
                if(father.left != null && father.left == node)
                    father.left = null;
                else
                    father.right = null;
            } else if(cntChild == 1) {
                BSTNode next;
                if(node.left != null) {
                    next = node.left;
                } else {
                    next = node.right;
                }
                if(father.left != null && father.left == node)
                    father.left = next;
                else
                    father.right = next;
            } else {
                BSTNode newRoot = fineRight(node.left);
                BSTNode newRootFather = findFather(newRoot.key, root);
                BSTNode fatherNext = newRoot.left;
                if(newRootFather.left != null && newRootFather.left == newRoot) {
                    newRootFather.left = fatherNext;
                } else {
                    newRootFather.right = fatherNext;
                }
//                newRootFather.right = fatherNext;
                newRoot.left = root.left;
                newRoot.right = root.right;
                if(father.left == node)
                    father.left = newRoot;
                else
                    father.right = newRoot;
            }
        }
        return node.value;
    }

    @Override
    public V remove(K key, V value) {
        if(!containsKey(key) || !get(key).equals(value)) {
            return null;
        }
        return remove(key);
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    public String printInOrder() {
        return printInOrder(root);
    }

    private String printInOrder(BSTNode node) {
        if (node == null)
            return "";
        return printInOrder(node.left) + " " + printInOrder(node.right);
    }
}
