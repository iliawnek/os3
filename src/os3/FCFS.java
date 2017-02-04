package os3;

class FCFS extends Simulator {
    FCFS(String filename) {
        super(filename);
        this.waitingQueue = new WaitingQueue(new AATComparator());
    }

    @Override
    void execute(Process process) {
        process.executeUntilCompletion(this.time);
    }
}
