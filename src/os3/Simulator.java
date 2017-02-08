package os3;

import java.util.Comparator;

/**
 * Provides functionality for simulating a process scheduling algorithm.
 */
abstract class Simulator {

    Time time; // keeps track of current global time in abstract time units
    private Workload workload; // contains and manages all processes
    ReadyQueue readyQueue; // queue of admitted processes awaiting execution
    String filename; // name of input CSV file

    /**
     * @param filename of input CSV file
     * @param comparator for ordering the ready queue
     */
    Simulator(String filename, Comparator<Process> comparator) {
        this.time = new Time();
        this.workload = new Workload(filename);
        this.readyQueue = new ReadyQueue(comparator);
        this.filename = filename;
    }

    /**
     * Runs the simulation and prints results.
     */
    void run() {
        System.out.println(this);
        System.out.println();

        // loop while there are still processes to be admitted
        while (!workload.allProcessesAdmitted() || !this.readyQueue.isEmpty()) {
            if (!workload.allProcessesAdmitted()) {
                workload.admitProcesses(time, readyQueue);
                // keep advancing time until we find a process to admit
                while (this.readyQueue.isEmpty()) {
                    time.advance(1);
                    workload.admitProcesses(time, readyQueue);
                }
            }
            if (!this.readyQueue.isEmpty()) {
                Process process = readyQueue.pop();
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
     * Defines how a process is to be executed when popped from the ready queue.
     * @param process to be executed
     */
    abstract void execute(Process process);

    @Override
    public String toString() {
        return String.format("%s %s", this.getClass().getSimpleName(), filename);
    }
}
