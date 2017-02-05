package os3;

import java.util.Comparator;

abstract class Simulator {

    Time time;
    private Workload workload;
    WaitingQueue waitingQueue;
    String filename;

    Simulator(String filename, Comparator<Process> comparator) {
        this.time = new Time();
        this.workload = new Workload(filename);
        this.waitingQueue = new WaitingQueue(comparator);
        this.filename = filename;
    }

    void run() {
        System.out.println(this);
        System.out.println();

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

        System.out.format("Average waiting time = %d\n", AWT);
        System.out.format("Average turnaround time = %d\n", ATT);
        System.out.println();
        System.out.println("--------------------------------------");
        System.out.println();
    }

    abstract void execute(Process process);

    @Override
    public String toString() {
        return String.format("%s %s", this.getClass().getSimpleName(), filename);
    }
}
