package os3;

/**
 * Represents a process.
 */
class Process {
    int PID; // unique process ID
    int CBT; // duration of CPU burst
    int RCBT; // remaining CPU burst time (if process is only partially executed)
    int AAT; // absolute arrival time
    int MRAT; // most recent arrival time (if process is reinserted back into the ready queue)
    int WT; // waiting time
    int TA; // turnaround time
    boolean admitted = false; // true if process has been admitted into ready queue

    /**
     * @param PID process ID
     * @param CBT CPU burst time
     * @param AAT absolute arrival time
     */
    Process(int PID, int CBT, int AAT) {
        this.PID = PID;
        this.CBT = CBT;
        this.RCBT = CBT;
        this.AAT = AAT;
        this.MRAT = AAT;
    }

    /**
     * Sets a process as admitted into the ready queue.
     */
    void admit() {
        this.admitted = true;
    }

    /**
     * Fully executes a process until completion and advances global time.
     * Calculates waiting and turnaround times.
     * @param time current global time
     */
    void executeUntilCompletion(Time time) {
//        System.out.format("Finish PID = %d from time = %d → %d:\n", PID, time.get(), time.get() + CBT);
        time.advance(CBT);
        TA = time.get() - AAT;
        WT = TA - CBT;
//        System.out.format("\tTA = %d, WT = %d\n", TA, WT);
//        System.out.println();
    }

    /**
     * @param time current global time
     * @param quantum duration of a quantum
     * @param readyQueue ready queue for process scheduling algorithm
     */
    void executeForQuantum(Time time, int quantum, ReadyQueue readyQueue) {
        // if process can finish execution during this quantum
        if (RCBT <= quantum) {
//            System.out.format("Finish PID = %d from time = %d → %d:\n", PID, time.get(), time.get() + RCBT);
            time.advance(RCBT);
            TA = time.get() - AAT;
            WT = TA - CBT;
//            System.out.format("\tTA = %d, WT = %d\n", TA, WT);
        }
        // else process must be reinserted back into ready queue
        else {
//            System.out.format("Partially execute PID = %d from time = %d → %d:\n", PID, time.get(), time.get() + quantum);
            RCBT -= quantum;
            time.advance(quantum);
            MRAT = time.get();
            readyQueue.add(this);
//            System.out.format("\tRCBT = %d - %d = %d\n", RCBT + quantum, quantum, RCBT);
        }
//        System.out.println();
    }

    @Override
    public String toString() {
        return String.format("PID = %d, WT = %d, TA = %d", PID, WT, TA);
    }
}
