package os3;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Represents a ready queue for a scheduling algorithm.
 */
class ReadyQueue {
    private PriorityQueue<Process> waitingQueue;

    /**
     * @param comparator used for ordering the priority queue
     */
    ReadyQueue(Comparator<Process> comparator) {
        this.waitingQueue = new PriorityQueue<>(comparator);
    }

    /**
     * @param process to be inserted into the ready queue
     */
    void add(Process process) {
        waitingQueue.add(process);
    }

    /**
     * @return process at head of ready queue
     */
    Process pop() {
        return waitingQueue.poll();
    }

    /**
     * @return true if ready queue is empty
     */
    boolean isEmpty() {
        return waitingQueue.isEmpty();
    }
}
