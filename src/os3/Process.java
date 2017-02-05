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
        time.advance(CBT);
        this.TA = time.get() - AAT;
        this.WT = TA - CBT;
    }

    void executeForQuantum(Time time, int quantum, WaitingQueue waitingQueue) {
        // if process can finish execution during this quantum
        if (RCBT <= quantum) {
            time.advance(RCBT);
            TA = time.get() - AAT;
            WT = TA - CBT;
        }
        // else process must be reinserted back into waiting queue
        else {
            RCBT -= quantum;
            time.advance(quantum);
            MRAT = time.get();
            waitingQueue.add(this);
        }
    }

    @Override
    public String toString() {
        return String.format("PID: %d\t\tCBT: %d\t\tAAT: %d", PID, CBT, AAT);
    }
}
