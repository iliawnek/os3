package os3;

import java.util.Comparator;

/**
 * Orders processes in ascending order based on most recent arrival time.
 */
public class MRATComparator implements Comparator<Process> {
    @Override
    public int compare(Process p1, Process p2) {
        return p1.MRAT - p2.MRAT;
    }
}
