package ru.job4j.memory.reference;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        SoftReference<V> softValue = new SoftReference<>(value);
        cache.put(key, softValue);
    }

    public final V get(K key) {
        if (cache.get(key).equals(null)) {
            put(key, load(key));
        }
        V strongOut = cache.get(key).get();
        if (strongOut.equals(null)) {
            put(key, load(key));
            V strongIn = cache.get(key).get();
            strongOut = strongIn;
        }
        return strongOut;
    }

    protected abstract V load(K key);

}
