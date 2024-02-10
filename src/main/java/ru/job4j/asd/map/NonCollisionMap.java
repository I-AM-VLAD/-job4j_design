package ru.job4j.asd.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (count < table.length && table[count] == null) {
                    count++;
                }
                return count < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[count++].key;
            }
        };
    }

    @Override
    public boolean put(K key, V value) {
        boolean bool = false;
        int hashCode = hashCode(key);
        int i = indexFor(hash(hashCode));
        MapEntry mapEntry = new MapEntry(key, value);
        if (table[i] == null) {
            table[i] = mapEntry;
            bool = true;
            ++modCount;
        }
        return bool;
    }

    private int hashCode(K key) {
        return Objects.hash(key);
    }
    @Override
    public V get(K key) {
        int hashCode = hashCode(key);
        int i = indexFor(hash(hashCode));
        V getElement = null;
        if (table[i] != null) {
            if (hashCode(table[i].key) == hashCode) {
                if (Objects.equals(table[i].key, key)) {
                    getElement = table[i].value;
                }
            }
        }
        return getElement;
    }

    @Override
    public boolean remove(K key) {
        return false;
    }

    private void expand() {

    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    public static void main(String[] args) {
        SimpleMap<Integer, String> map = new NonCollisionMap<>();
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        map.put(4, "4");
        Iterator<Integer> iterator = map.iterator();
        iterator.next();
        iterator.next();
        iterator.next();

        System.out.println(iterator.next());
    }
}

