package os3;

import java.util.Comparator;

/**
 * Provides functionality for simulating a process scheduling algorithm.
 */
abstract class Simulator {

    Time time; // keeps track of current global time in abstract time units
    private Workload workload; // contains and manages all processes
    WaitingQueue waitingQueue; // queue of admitted processes awaiting execution
    String filename; // name of input CSV file

    /**
     * @param filename of input CSV file
     * @param comparator for ordering the waiting queue
     */
    Simulator(String filename, Comparator<Process> comparator) {
        this.time = new Time();
        this.workload = new Workload(filename);
        this.waitingQueue = new WaitingQueue(comparator);
        this.filename = filename;
    }

    /**
     * Runs the simulation and prints results.
     */
    void run() {
        System.out.println(this);
        System.out.println();

        // loop while there are still processes to be admitted
        while (!workload.allProcessesAdmitted() || !this.waitingQueue.isEmpty()) {
            if (!workload.allProcessesAdmitted()) {
                workload.admitProcesses(time, waitingQueue);
                // keep advancing time until we find a process to admit
                while (this.waitingQueue.isEmpty()) {
                    time.advance(1);
                    workload.admitProcesses(time, waitingQueue);
                }
            }
            if (!this.waitingQueue.isEmpty()) {
                Process process = waitingQueue.pop();
                execute(process);
            }
        }

        double AWT = workload.getAWT();
        double ATT = workload.getATT();

        workload.print();
        System.out.println();
        System.out.format("Average waiting time = %.2f\n", AWT);
        System.out.format("Average turnaround time = %.2f\n", ATT);
        System.out.println();
        System.out.println("--------------------------------------");
        System.out.println();
    }


    /**
     * Defines how a process is to be executed when popped from the waiting queue.
     * @param process to be executed
     */
    abstract void execute(Process process);

    @Override
    public String toString() {
        return String.format("%s %s", this.getClass().getSimpleName(), filename);
    }
}
