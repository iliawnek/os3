package os3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Workload {
    private ArrayList<Process> workload;
    private int N = 0;
    private int admissionCount = 0;

    Workload(String filename) {
        this.workload = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                this.N++;
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

    void admitProcesses(Time time, WaitingQueue waitingQueue) {
        boolean found = false;
        for (Process process : this.workload) {
            if ((process.AAT <= time.get()) && !process.admitted) {
                if (!found) {
                    System.out.format("Admit processes at time = %d:\n", time.get());
                    found = true;
                }
                process.admit();
                this.admissionCount++;
                System.out.format("\t%s\n", process);
                waitingQueue.add(process);
            }
        }
        if (found) System.out.println();
    }

    boolean allProcessesAdmitted() {
        return admissionCount == N;
    }

    int getAWT() {
        int totalWT = 0;
        for (Process process : workload) {
            totalWT += process.WT;
        }
        return totalWT / N;
    }

    int getATT() {
        int totalTA = 0;
        for (Process process : workload) {
            totalTA += process.TA;
        }
        return totalTA / N;
    }
}
