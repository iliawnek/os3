package os3;

/**
 * Simulator for the round robin process scheduling algorithm.
 */
class RR extends Simulator {
    private final int quantum; // duration of quantum in abstract time units

    /**
     * @param filename of input CSV file
     * @param quantum duration in abstract time units
     */
    RR(String filename, int quantum) {
        super(filename, new MRATComparator());
        this.quantum = quantum;
    }

    /**
     * Executes process for no longer than a quantum.
     * @param process to be executed
     */
    @Override
    void execute(Process process) {
        process.executeForQuantum(this.time, this.quantum, this.waitingQueue);
    }

    @Override
    public String toString() {
        return String.format("%s Q = %d", super.toString(), quantum);
    }
}
