package os3;

import java.util.Comparator;

abstract class Simulator {

    Time time;
    private Workload workload;
    WaitingQueue waitingQueue;

    Simulator(String filename, Comparator<Process> comparator) {
        this.time = new Time();
        this.workload = new Workload(filename);
        this.waitingQueue = new WaitingQueue(comparator);
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
