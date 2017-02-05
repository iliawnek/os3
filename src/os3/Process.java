package os3;

class Process {
    int PID;
    int CBT;
    int RCBT; // remaining CPU burst time
    int AAT;
    int MRAT; // most recent arrival time
    int WT;
    int TA;
    boolean admitted = false;

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

    void admit() {
        this.admitted = true;
    }

    void executeUntilCompletion(Time time) {
        System.out.format("Finish PID = %d from time = %d → %d:\n", PID, time.get(), time.get() + CBT);
        time.advance(CBT);
        TA = time.get() - AAT;
        WT = TA - CBT;
        System.out.format("\tCBT = %d, AAT = %d, TA = %d, WT = %d\n", CBT, AAT, TA, WT);
        System.out.println();
    }

    void executeForQuantum(Time time, int quantum, WaitingQueue waitingQueue) {
        // if process can finish execution during this quantum
        if (RCBT <= quantum) {
            System.out.format("Finish PID = %d from time = %d → %d:\n", PID, time.get(), time.get() + RCBT);
            time.advance(RCBT);
            TA = time.get() - AAT;
            WT = TA - CBT;
            System.out.format("\tTA = %d, WT = %d\n", TA, WT);
        }
        // else process must be reinserted back into waiting queue
        else {
            System.out.format("Partially execute PID = %d from time = %d → %d:\n", PID, time.get(), time.get() + quantum);
            RCBT -= quantum;
            time.advance(quantum);
            MRAT = time.get();
            waitingQueue.add(this);
            System.out.format("\tRCBT = %d - %d = %d\n", RCBT + quantum, quantum, RCBT);
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return String.format("PID = %d, CBT = %d, AAT = %d", PID, CBT, AAT);
    }
}
