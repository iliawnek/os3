package os3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Stores a set of processes.
 */
class Workload {
    private ArrayList<Process> workload; // list of all processes in the workload
    private int admissionCount = 0; // number of processes that have been admitted into the waiting queue

    /**
     * Parses and stores processes defined by input CSV file.
     * @param filename of input CSV file
     */
    Workload(String filename) {
        this.workload = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            // for each line in CSV, create a Process and add to workload
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitLine = line.split(",");
                int PID = Integer.parseInt(splitLine[0]);
                int CBT = Integer.parseInt(splitLine[1]);
                int AAT = Integer.parseInt(splitLine[2]);
                Process process = new Process(PID, CBT, AAT);
                this.workload.add(process);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Admits all processes who have arrived (based on current global time) into waiting queue.
     * @param time current global time
     * @param waitingQueue waiting queue for process scheduling algorithm
     */
    void admitProcesses(Time time, WaitingQueue waitingQueue) {
        boolean found = false;
        for (Process process : this.workload) {
            if ((process.AAT <= time.get()) && !process.admitted) {
                // only print if there are processes to admit
//                if (!found) {
//                    System.out.format("Admit processes at time = %d:\n", time.get());
//                    found = true;
//                }
                process.admit(); // setting admission status allows ignoring of already-admitted processes next time
                this.admissionCount++;
//                System.out.format("\t%s\n", process);
                waitingQueue.add(process);
            }
        }
//        if (found) System.out.println(); // only print if there are processes to admit
    }

    /**
     * @return true if all processes in workload have been admitted into the waiting queue
     */
    boolean allProcessesAdmitted() {
        return admissionCount == workload.size();
    }

    /**
     * @return calculated average waiting time of all processes in workload
     */
    double getAWT() {
        double totalWT = 0;
        for (Process process : workload) {
            totalWT += process.WT;
        }
        return totalWT / workload.size();
    }

    /**
     * @return calculated average turnaround time of all processes in workload
     */
    double getATT() {
        double totalTA = 0;
        for (Process process : workload) {
            totalTA += process.TA;
        }
        return totalTA / workload.size();
    }

    /**
     * Prints all processes in the workload to System.out.
     */
    void print() {
        for (Process process : workload) {
            System.out.println(process);
        }
    }
}
