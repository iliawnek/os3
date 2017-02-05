package os3;

class FCFS extends Simulator {
    FCFS(String filename) {
        super(filename, new AATComparator());
    }

    @Override
    void execute(Process process) {
        process.executeUntilCompletion(this.time);
    }
}
