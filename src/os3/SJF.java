package os3;

class SJF extends Simulator {
    SJF(String filename) {
        super(filename, new CBTComparator());
    }

    @Override
    void execute(Process process) {
        process.executeUntilCompletion(this.time);
    }
}
