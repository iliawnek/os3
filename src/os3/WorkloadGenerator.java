package os3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

class WorkloadGenerator {

    public static void main(String[] args) {
        // constants
        final int N = 1000; // number of processes in workload
        final double M1 = 20; // W1 Gaussian mean
        final double M2 = 60; // W2 Gaussian mean
        final double STD = 3; // Gaussian standard deviation
        final int CBT_MIN = 5; // lower bound for CPU burst time
        double L = 5; // Poisson mean

        Random random = new Random(System.currentTimeMillis());
        generateWorkload("W1.csv", random, N, M1, STD, CBT_MIN, L); // generate workload W1
        generateWorkload("W2.csv", random, N, M2, STD, CBT_MIN, L); // generate workload W2
    }

    private static void generateWorkload(String filename, Random random, int N, double M, double STD, int CBT_MIN, double L) {
        try {
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            int AAT = 0;
            for (int PID = 0; PID < N; PID++) {
                // calculate CPU burst time
                double gaussianValue = random.nextGaussian() * STD + M;
                int CBT = (int) Math.ceil(Math.max(CBT_MIN, gaussianValue));

                // calculate absolute arrival time
                int deltaT = WorkloadGenerator.myPoisson(L, random);
                AAT = AAT + (PID == 0 ? 0 : deltaT);

                writer.format("%d,%d,%d\n", PID, CBT, AAT);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int myPoisson(double mean, Random random) {
        int k = 0;
        double p = 1.0;
        double expLambda = Math.exp(-mean);
        do {
            k++;
            p *= random.nextDouble();
        } while (p >= expLambda);
        return k - 1;
    }
}
