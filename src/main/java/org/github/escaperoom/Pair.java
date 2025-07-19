
//Katelynn Prater - 7/18/25
// small pair class to help with linkedhashmap

package org.github.escaperoom;

public class Pair<K, V> { //mini pair helper class for textmethods class
    public final K key;
    public final V value; //key value for question and runnable output

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }
    public V getValue() {
        return value;
    }
}  //end pair class
