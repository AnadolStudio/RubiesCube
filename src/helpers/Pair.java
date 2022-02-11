package helpers;

public class Pair<K, V> {
    // Я не нашел аналога из котлин, поэтому сделал свой
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Pair() {
    }

    public K getK() {
        return key;
    }

    public void setK(K key) {
        this.key = key;
    }

    public V getV() {
        return value;
    }

    public void setV(V value) {
        this.value = value;
    }
}
