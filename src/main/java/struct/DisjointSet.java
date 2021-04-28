package struct;

import java.util.Arrays;

public class DisjointSet {

    public static void main(String[] args) {
        new DisjointSet().test();
    }

    public void test() {
        int[] set = create(6);
        print(set);

        union(set, 1, 0);
        union(set, 2, 1);
        union(set, 4, 3);
        union(set, 5, 4);
        print(set);

        System.out.println(find(set, 2));
        print(set);

        System.out.println(find(set, 5));
        print(set);
    }

    public void print(int[] set) {
        System.out.println(Arrays.toString(set));
    }

    public int[] create(int size) {
        int[] set = new int[size];
        for (int i = 0; i < size; i++) {
            set[i] = i;
        }
        return set;
    }

    public void union(int[] set, int element, int parent) {
        set[element] = parent;
    }

    public int find(int[] set, int element) {
        int p = parent(set, element);
        compress(set, element, p);
        return p;
    }

    public int parent(int[] set, int element) {
        int parent = element;
        while (parent != set[parent]) {
            parent = set[parent];
        }
        return parent;
    }

    public void compress(int[] set, int element, int parent) {
        int next;
        while (set[element] != parent) {
            next = set[element];
            set[element] = parent;
            element = next;
        }
    }
}
