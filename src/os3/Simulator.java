package os3;

abstract class Simulator {

    Time time;
    private Workload workload;
    WaitingQueue waitingQueue;
    int AWT;
    int ATT;

    Simulator(String filename) {
        this.workload = new Workload(filename);
        this.time = new Time();
    }

    void run() {
        while (!workload.allProcessesAdmitted() || !this.waitingQueue.isEmpty()) {
            if (!workload.allProcessesAdmitted()) {
                workload.admitProcesses(time, waitingQueue);
            }
            if (!this.waitingQueue.isEmpty()) {
                Process process = waitingQueue.pop();
                execute(process);
            }
        }
        int AWT = workload.getAWT();
        int ATT = workload.getATT();
        System.out.format("Average waiting time: %d\n", AWT);
        System.out.format("Average turnaround time: %d\n", ATT);
    }

    abstract void execute(Process process);
}
