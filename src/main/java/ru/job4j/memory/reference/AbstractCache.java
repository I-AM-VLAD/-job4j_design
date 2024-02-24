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
        if (cache.get(key).get().equals(null)) {
            put(key, load(key));
        }
        return cache.get(key).get();
    }

    protected abstract V load(K key);

}
