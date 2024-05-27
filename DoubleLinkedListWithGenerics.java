package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedListWithGenerics<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoubleLinkedListWithGenerics(T... data) {
        for (T element : data) {
            add(element);
        }
    }

    public DoubleLinkedListWithGenerics() {
        head = null;
        tail = null;
        size = 0;
    }

    public void listToString() {
        if (isEmpty()) {
            System.out.println("Лист пуст");
            return;
        }
        Node<T> currentNode = head;
        System.out.print("Лист: ");
        while (currentNode != null) {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    public void add(T data) {
        Node<T> newNode = new Node<T>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void addId(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            add(data);
            return;
        }
        if (index == 0) {
            addFirst(data);
            return;
        }
        Node<T> newNode = new Node<T>(data);
        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        newNode.next = current.next;
        newNode.prev = current;
        current.next.prev = newNode;
        current.next = newNode;
        size++;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<T>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public void removeId(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            removeFirst();
            return;
        }
        if (index == size - 1) {
            removeLast();
            return;
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
    }

    public void removeFirst() {
        if (isEmpty()) {
            return;
        }
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
    }

    public void removeLast() {
        if (isEmpty()) {
            return;
        }
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
    }

    public T findId(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public int findElement(T data) {
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(data)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private Node<T> current = head;
        private Node<T> lastReturned = null;


        public boolean hasNext() {
            return current != null;
        }


        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = current;
            current = current.next;
            return lastReturned.data;
        }

        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            if (lastReturned == head) {
                removeFirst();
            } else if (lastReturned == tail) {
                removeLast();
            } else {
                lastReturned.prev.next = lastReturned.next;
                lastReturned.next.prev = lastReturned.prev;
                size--;
            }
            lastReturned = null;
        }
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> prev;

        public Node(T data) {
            this.data = data;
        }
    }
}