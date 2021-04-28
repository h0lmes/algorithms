package algo;

import java.util.PriorityQueue;

public class MedianFinder {

    public static void main(String[] args) {
        MedianFinder o = new MedianFinder();
        for (int i : new int[]{1, 2, 3, 4, 5}) {
            o.addNum(i);
            System.out.println(o.findMedian());
        }
        MedianFinder u = new MedianFinder();
        for (int i : new int[]{5, 4, 3, 2, 1}) {
            u.addNum(i);
            System.out.println(u.findMedian());
        }
    }

    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    /** initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a,b) -> (a > b) ? -1 : (a == b ? 0 : 1));
    }

    public void addNum(int num) {
        double median = findMedian();
        if (num < median) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }

        balance();
    }

    private void balance() {
        int diff = Math.abs(minHeap.size() - maxHeap.size());
        while (diff > 1) {
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            } else {
                minHeap.offer(maxHeap.poll());
            }
            diff -= 2;
        }
    }

    public double findMedian() {
        if (minHeap.size() == maxHeap.size()) {
            if (minHeap.size() == 0)
                return 0;
            return (double)(minHeap.peek() + maxHeap.peek()) / 2;
        } else if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else {
            return maxHeap.peek();
        }
    }
}
