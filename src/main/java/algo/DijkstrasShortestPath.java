package algo;

import java.util.PriorityQueue;

public class DijkstrasShortestPath {

    public static void main(String[] args) {
        int graph[][] = new int[][]{
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };
        new DijkstrasShortestPath().shortestPath(graph, 0, 4);
    }

    public static class Vertex implements Comparable<Vertex> {
        final int index;
        int distance;
        int previous;

        public Vertex(int index, int distance) {
            this.index = index;
            this.previous = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.distance, o.distance);
        }
    }

    public void shortestPath(int[][] graph, int start, int end) {
        Vertex[] vertices = new Vertex[graph.length];
        PriorityQueue<Vertex> q = new PriorityQueue<>(graph.length);

        for (int i = 0; i < vertices.length; i++) {
            q.offer(vertices[i] = new Vertex(i, i == start ? 0 : Integer.MAX_VALUE));
        }

        while (!q.isEmpty()) {
            Vertex v = q.poll();

            for (int i = 0; i < graph[v.index].length; i++) {
                int distanceFromVToI = graph[v.index][i];
                if (distanceFromVToI != 0 && q.contains(vertices[i]) && vertices[i].distance > v.distance + distanceFromVToI) {
                    q.remove(vertices[i]);
                    vertices[i].distance = v.distance + distanceFromVToI;
                    vertices[i].previous = v.index;
                    q.offer(vertices[i]);
                }
            }
        }

        int i = end;
        String path = "";
        while (vertices[i].previous != i) {
            path = " -> " + i + path;
            i = vertices[i].previous;
        }
        path = i + path;
        System.out.println("shortest path from " + start + " to " + end + " is: " + path);

        System.out.println("all vertices");
        for (Vertex v : vertices) {
            System.out.println(v.index + " : " + v.distance + " : " + v.previous);
        }
    }
}
