package os3;

class Process {
    int PID;
    int CBT;
    int AAT;
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
        this.AAT = AAT;
    }

    void admit() {
        this.admitted = true;
    }

    void executeUntilCompletion(Time time) {
        time.advance(CBT);
        this.TA = time.get() - AAT;
        this.WT = TA - CBT;
    }

    void executeForQuantum() {

    }

    @Override
    public String toString() {
        return String.format("PID: %d\t\tCBT: %d\t\tAAT: %d", PID, CBT, AAT);
    }
}
