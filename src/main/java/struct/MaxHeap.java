package struct;

import java.util.Arrays;
import java.util.Comparator;

public class MaxHeap {

    private static final int STARTING_CAPACITY = 10;

    private int[] arr;
    private int capacity = STARTING_CAPACITY;
    private int size = 0;
    private Comparator<Integer> comparator;

    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap();
        int[] values = new int[]{4, 5, 3, 12, 8, 7, 12, 12, 1};

        for (int value : values) {
            heap.add(value);
            System.out.println(pad(value) + " -> " + heap.toString());
        }
        while (!heap.isEmpty()) {
            int el = heap.pop();
            System.out.println(pad(el) + " <- " + heap.toString());
        }
    }

    private static String pad(int value) {
        if (0 < value && value < 10) {
            return " " + value;
        }
        return "" + value;
    }

    public String toString() {
        int iMax = size - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(arr[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

    public MaxHeap() {
        this(STARTING_CAPACITY, null);
    }

    public MaxHeap(int capacity) {
        this(capacity, null);
    }

    public MaxHeap(int capacity, Comparator<Integer> comparator) {
        this.capacity = Math.max(this.capacity, capacity);
        arr = new int[capacity];
        this.comparator = comparator != null ? comparator : Integer::compare;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int i) {
        if (size == capacity)
            grow();
        arr[size++] = i;
        bubble(size - 1);
    }

    public int peek() {
        return arr[0];
    }

    public int pop() {
        if (size == 0) return -1;
        int ret = arr[0];
        arr[0] = arr[--size];
        sift(0);
        if (size <= capacity / 2)
            shrink();
        return ret;
    }

    private void sift(int i) {
        int child = maxChildIndex(i);
        while (child < size && comparator.compare(arr[i], arr[child]) < 0) {
            swap(i, child);
            i = child;
            child = maxChildIndex(i);
        }
    }

    private void bubble(int i) {
        int parent = parentIndex(i);
        while (i > 0 && comparator.compare(arr[i], arr[parent]) > 0) {
            swap(i, parent);
            i = parent;
            parent = parentIndex(i);
        }
    }

    private int parentIndex(int i) {
        return (i - 1) / 2;
    }

    private int childIndex(int i) {
        return 2 * i + 1;
    }

    // index of maximum child (minimum for min heap)
    private int maxChildIndex(int i) {
        int child = childIndex(i);
        if (child >= size - 1)
            return child;

        if (comparator.compare(arr[child], arr[child + 1]) > 0)
            return child;
        else
            return child + 1;
    }

    private void swap(int i, int parent) {
        int t = arr[i];
        arr[i] = arr[parent];
        arr[parent] = t;
    }

    private void grow() {
        capacity = (int)Math.floor(capacity * 1.5) + 1;
        arr = Arrays.copyOf(arr, capacity);
    }

    private void shrink() {
        capacity = Math.max(capacity / 2, STARTING_CAPACITY);
        if (capacity != arr.length)
            arr = Arrays.copyOf(arr, capacity);
    }
}
