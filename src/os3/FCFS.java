package os3;

/**
 * Simulator for the first-come-first-served process scheduling algorithm.
 */
class FCFS extends Simulator {
    /**
     * Uses comparator for ordering ready queue by earliest absolute arrival time first.
     * @param filename of input CSV file
     */
    FCFS(String filename) {
        super(filename, new AATComparator());
    }

    /**
     * Executes all processes until completion (i.e. no pre-emption).
     * @param process to be executed
     */
    @Override
    void execute(Process process) {
        process.executeUntilCompletion(this.time);
    }
}
