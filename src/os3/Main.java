package os3;

class Main {
    public static void main(String[] args) {
        new FCFS("W1.csv").run();
        new FCFS("W2.csv").run();
        new SJF("W1.csv").run();
        new SJF("W2.csv").run();
        new RR("W1.csv", 15).run();
        new RR("W2.csv", 15).run();
        new RR("W1.csv", 5).run();
        new RR("W2.csv", 5).run();
        new RR("W1.csv", 40).run();
        new RR("W2.csv", 40).run();
    }
}
