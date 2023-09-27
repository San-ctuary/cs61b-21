package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int initialSize = 16;
    private double loadFactor = 0.75;

    private HashSet<K> allKeys = new HashSet<>();
    /** Constructors */
    public MyHashMap() {
    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.initialSize = initialSize;
        this.loadFactor = maxLoad;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new ArrayList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!


    @Override
    public void clear() {

        if (buckets != null && size() != 0) {
            for(int i = 0;i < buckets.length;i++) {
                if (buckets[i] != null)
                    buckets[i] = null;
            }
            allKeys.clear();
        }
    }

    @Override
    public boolean containsKey(K key) {
        return allKeys.contains(key);
    }

    @Override
    public V get(K key) {
        if (!allKeys.contains(key))
            return null;
        int position = getPosition(key, buckets.length);
        Collection<Node> bucket = buckets[position];
        for(Node node : bucket) {
            if (node.key.equals(key))
                return node.value;
        }
        return null;
    }

    private int getPosition(K key, int lens) {
        int hash = key.hashCode();
        return Math.floorMod(hash, lens);
    }

    @Override
    public int size() {
        return allKeys.size();
    }

    @Override
    public void put(K key, V value) {
        if (buckets == null)
            this.buckets = createTable(initialSize);
        int position = getPosition(key, buckets.length);
        if (buckets[position] == null)
            buckets[position] = createBucket();
        Collection<Node> bucket = buckets[position];
        for(Node node : bucket) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        bucket.add(createNode(key, value));
        allKeys.add(key);
        if (size() / buckets.length >= initialSize)
            resize();
    }

    private void resize() {
        Collection<Node>[] oldBuckets = buckets;
        Collection<Node>[] newBuckets = createTable(oldBuckets.length * 2);
        for(int i = 0;i < oldBuckets.length;i++) {
            Collection<Node> bucket = oldBuckets[i];
            for(Node node : bucket) {
                int position = getPosition(node.key, newBuckets.length);
                if (newBuckets[position] == null)
                    newBuckets[position] = createBucket();
                Collection<Node> newBucket = newBuckets[position];
                newBucket.add(node);
            }
        }
        buckets = newBuckets;
    }

    @Override
    public Set<K> keySet() {
        return allKeys;
    }

    @Override
    public V remove(K key) {
        if (!allKeys.contains(key))
            return null;
        int position = getPosition(key, buckets.length);
        Collection<Node> bucket = buckets[position];
        V v = get(key);
        allKeys.remove(key);
        bucket.remove(key);
        return v;
    }

    @Override
    public V remove(K key, V value) {
        if (!allKeys.contains(key))
            return null;
        int position = getPosition(key, buckets.length);
        Collection<Node> bucket = buckets[position];
        V v = get(key);
        if (!v.equals(value))
            return null;
        return remove(key);
    }

    @Override
    public Iterator<K> iterator() {
        return allKeys.iterator();
    }
}
