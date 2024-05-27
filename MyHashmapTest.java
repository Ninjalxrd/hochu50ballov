package random;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHashmapTest {

    @Test
    void put() {
        MyHashmap<String, Integer> hashMap = new MyHashmap<>();
        hashMap.put("One", 1);
        hashMap.put("Two", 2);
        hashMap.put("Three", 3);
        assertEquals(3, hashMap.size());
        assertEquals(1, hashMap.get("One"));
        assertEquals(2, hashMap.get("Two"));
        assertEquals(3, hashMap.get("Three"));
    }

    @Test
    void get() {
        MyHashmap<String, Integer> hashMap = new MyHashmap<>();
        hashMap.put("One", 1);
        hashMap.put("Two", 2);
        hashMap.put("Three", 3);
        assertEquals(1, hashMap.get("One"));
        assertEquals(2, hashMap.get("Two"));
        assertEquals(3, hashMap.get("Three"));
        assertNull(hashMap.get("Four"));
    }

    @Test
    void containsKey() {
        MyHashmap<String, Integer> hashMap = new MyHashmap<>();
        hashMap.put("One", 1);
        hashMap.put("Two", 2);
        hashMap.put("Three", 3);
        assertTrue(hashMap.containsKey("One"));
        assertTrue(hashMap.containsKey("Two"));
        assertTrue(hashMap.containsKey("Three"));
        assertFalse(hashMap.containsKey("Four"));
    }

    @Test
    void containsValue() {
        MyHashmap<String, Integer> hashMap = new MyHashmap<>();
        hashMap.put("One", 1);
        hashMap.put("Two", 2);
        hashMap.put("Three", 3);
        assertTrue(hashMap.containsValue(1));
        assertTrue(hashMap.containsValue(2));
        assertTrue(hashMap.containsValue(3));
        assertFalse(hashMap.containsValue(4));
    }

    @Test
    void remove() {
        MyHashmap<String, Integer> hashMap = new MyHashmap<>();
        hashMap.put("One", 1);
        hashMap.put("Two", 2);
        hashMap.put("Three", 3);
        hashMap.remove("Two");
        assertEquals(2, hashMap.size());
        assertNull(hashMap.get("Two"));
    }

    @Test
    void size() {
        MyHashmap<String, Integer> hashMap = new MyHashmap<>();
        assertEquals(0, hashMap.size());
        hashMap.put("One", 1);
        assertEquals(1, hashMap.size());
        hashMap.put("Two", 2);
        assertEquals(2, hashMap.size());
        hashMap.put("Three", 3);
        assertEquals(3, hashMap.size());
    }
}