package struct;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    public static void main(String[] args) {
        LRUCache c = new LRUCache(2);
        System.out.println(c.get(2));
        c.put(2, 6);
        System.out.println(c.get(1));
        c.put(1, 5);
        c.put(1, 2);
        System.out.println(c.get(1));
        System.out.println(c.get(2));
        c.put(3, 8);
        System.out.println(c.get(1));
        System.out.println(c.get(2));
        System.out.println(c.get(3));
    }

    private final int capacity;
    private int size = 0;

    class Node {
        int key;
        int value;
        Node prev;
        Node next;
    }

    private Node head = new Node();
    private Node tail = new Node();
    private Map<Integer, Node> map = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null)
            return -1;

        if (head.next != node) {
            deleteNode(node);
            insertNode(node);
        }

        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            if (size >= capacity) {
                deleteNode(tail.prev);
                size--;
            }
            node = new Node();
            node.key = key;
            node.value = value;
        } else {
            node.value = value;
            deleteNode(node);
            size--;
        }
        insertNode(node);
        size++;
    }

    private void insertNode(Node node) {
        Node first = head.next;
        head.next = node;
        node.prev = head;
        node.next = first;
        first.prev = node;
        map.put(node.key, node);
    }

    private void deleteNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
        map.remove(node.key);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node n = head.next;
        while (n != tail) {
            builder.append(n.key).append(":").append(n.value).append(". ");
            n = n.next;
        }
        builder.append("  |  ");
        map.forEach((k, v) -> builder.append(k).append(":").append(v.value).append(". "));
        builder.append('\n');
        return builder.toString();
    }
}

/**
 * Your struct.LRUCache object will be instantiated and called as such:
 * struct.LRUCache obj = new struct.LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */