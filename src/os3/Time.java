package os3;

class Time {
    private int time = 0;

    void advance(int period) {
        this.time += period;
    }

    int get() {
        return this.time;
    }
}
