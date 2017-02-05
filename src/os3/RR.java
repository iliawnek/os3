package os3;

class RR extends Simulator {
    private final int quantum;

    RR(String filename, int quantum) {
        super(filename, new MRATComparator());
        this.quantum = quantum;
    }

    @Override
    void execute(Process process) {
        process.executeForQuantum(this.time, this.quantum, this.waitingQueue);
    }

    @Override
    public String toString() {
        return String.format("%s Q = %d", super.toString(), quantum);
    }
}
