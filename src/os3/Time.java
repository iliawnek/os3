package os3;

/**
 * Represents an instance of global time.
 */
class Time {
    private int time = 0;

    /**
     * Moves time forward.
     * @param period of time to advance.
     */
    void advance(int period) {
        this.time += period;
    }

    int get() {
        return this.time;
    }
}
