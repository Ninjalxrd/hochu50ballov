package random;

import java.util.Objects;

public class MyHashmap <K extends Comparable<K>,V>  {
    //создание массива нодов, в каждом хранится ключ-значение и связный список в случае коллизии
    private Node<K,V> [] table;
    private int size;
    MyHashmap() {
        this.table = new Node[4];
    }

    public void put (K key, V value) {
        // хешируем объект key
        int hash = hash(key);
        // вставляем в рандомное место по формуле
        int bucketIndex =  hash % table.length;

        //проверка наличия бакета по индексу
        if (table[bucketIndex] == null) {
            table[bucketIndex] = new Node<>(hash, key, value);
            size++;
        } else {
            //обработка коллизии
            Node<K, V> currentNode = table[bucketIndex];
            Node<K, V> prevNode = null;

            while (currentNode != null) {
                if (currentNode.getHash() == hash && currentNode.getKey().equals(key)) {
                    currentNode.setValue(value);
                    return;
                }
                prevNode = currentNode;
                currentNode = currentNode.getNext();
            }
            prevNode.setNext(new Node<>(hash, key, value));
            size++;
        }

        //увеличение массива, в случае, когда кол-во бакетов приближается к вместимости
        if (size >= table.length * 0.75) {
            boostCapacity(table.length * 2);
        }
    }

    public V get (K key) {
        int hash = hash(key);
        int indexBucket = hash % table.length;
        Node <K,V> varNode = table[indexBucket];
        while (varNode != null) {
            if (varNode.getKey().equals(key)) {
                return varNode.getValue();
            }
            varNode = varNode.getNext();
        }
        return null;
    }

    public boolean containsKey (K key) {
        // Высчитывается hash по ключу
        int hash = hash(key);
        // Используется хэш-код для определения индекса bucket
        int bucketIndex = hash % table.length;
        // Поиск ключа
        Node<K,V> curNode = table[bucketIndex];
        while (curNode != null) {
            if (curNode.getKey().equals(key)) {
                return true;
            }
            curNode = curNode.getNext();
        }
        return false;
    }


    public boolean containsValue(V value) {
        for (Node <K,V> buckets : table) {
            while (buckets != null) {
                if (buckets.getValue().equals(value)) {
                    return true;
                }
                buckets = buckets.getNext();
            }
        }
        return false;
    }

    public void remove(K key) {
        int hash = hash(key);
        int bucketIndex = hash % table.length;

        Node<K, V> currentNode = table[bucketIndex];
        Node<K, V> prevNode = null;

        while (currentNode != null) {
            if (currentNode.getHash() == hash && currentNode.getKey().equals(key)) {
                if (prevNode == null) {
                    table[bucketIndex] = currentNode.getNext();
                } else {
                    prevNode.setNext(currentNode.getNext());
                }
                size--;
                return;
            }
            prevNode = currentNode;
            currentNode = currentNode.getNext();
        }

    }

    public int size () {
        return size;
    }
    //специальный метод увеличения вместимости
    private void boostCapacity (int capacity) {
        Node <K,V> [] newTable = new Node[capacity];
        for (Node<K,V> bucket : table) {
            while (bucket != null) {
                int newBucketIndex = bucket.getHash() % newTable.length;
                if (newTable[newBucketIndex] == null) {
                    newTable[newBucketIndex] = new Node<>(bucket.getHash(), bucket.getKey(), bucket.getValue());
                } else {
                    Node<K, V> currentNode = newTable[newBucketIndex];
                    Node<K, V> prevNode = null;

                    while (currentNode != null) {
                        prevNode = currentNode;
                        currentNode = currentNode.getNext();
                    }
                    prevNode.setNext(new Node<>(bucket.getHash(), bucket.getKey(), bucket.getValue()));
                }
                bucket = bucket.getNext();
            }
        }
        table = newTable;
    }

    private int hash(K key) {
        return Objects.hashCode(key);
    }
}

