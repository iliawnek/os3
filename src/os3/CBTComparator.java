package os3;

import java.util.Comparator;

/**
 * Orders processes in ascending order based on CPU burst time.
 */
public class CBTComparator implements Comparator<Process> {
    @Override
    public int compare(Process p1, Process p2) {
        return p1.CBT - p2.CBT;
    }
}
