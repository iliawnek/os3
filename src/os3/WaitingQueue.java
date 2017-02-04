package os3;

import java.util.Comparator;
import java.util.PriorityQueue;

class WaitingQueue {
    private PriorityQueue<Process> waitingQueue;

    WaitingQueue(Comparator<Process> comparator) {
        this.waitingQueue = new PriorityQueue<>(comparator);
    }

    void add(Process process) {
        waitingQueue.add(process);
    }

    Process pop() {
        return waitingQueue.poll();
    }

    boolean isEmpty() {
        return waitingQueue.isEmpty();
    }
}
