package os3;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Represents a waiting queue for a scheduling algorithm.
 */
class WaitingQueue {
    private PriorityQueue<Process> waitingQueue;

    /**
     * @param comparator used for ordering the priority queue
     */
    WaitingQueue(Comparator<Process> comparator) {
        this.waitingQueue = new PriorityQueue<>(comparator);
    }

    /**
     * @param process to be inserted into the waiting queue
     */
    void add(Process process) {
        waitingQueue.add(process);
    }

    /**
     * @return process at head of waiting queue
     */
    Process pop() {
        return waitingQueue.poll();
    }

    /**
     * @return true if waiting queue is empty
     */
    boolean isEmpty() {
        return waitingQueue.isEmpty();
    }
}
