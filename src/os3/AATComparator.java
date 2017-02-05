package os3;

import java.util.Comparator;

/**
 * Orders processes in ascending order based on absolute arrival time.
 */
public class AATComparator implements Comparator<Process> {
    @Override
    public int compare(Process p1, Process p2) {
        return p1.AAT - p2.AAT;
    }
}
