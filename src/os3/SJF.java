package os3;

/**
 * Simulator for the shortest-job-first process scheduling algorithm.
 */
class SJF extends Simulator {
    /**
     * Uses comparator for ordering ready queue by shortest CPU burst time first.
     * @param filename of input CSV file
     */
    SJF(String filename) {
        super(filename, new CBTComparator());
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
