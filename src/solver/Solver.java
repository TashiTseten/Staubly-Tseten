package solver;

import strings.Strings;
import strings.StringsConfiguration;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solver {
    private int counter;
    private int uniqueCounter;
    public Solver()
    {
        counter=1;
        uniqueCounter=1;
    }

    /**
     * @param start the first configuration
     * @return a list of neighboring configurations that end at the
     * goal configuration
     */
    public List<Configuration> getShortestPath(Configuration start) {
        Configuration finish = null;
        List<Configuration> queue = new LinkedList<>();
        queue.add(start);
        Map<Configuration, Configuration> predecessors = new HashMap<>();
        predecessors.put(start,start);
        while (!queue.isEmpty()) {
            Configuration current = queue.remove(0);
            if (current.isSolution()) {
                finish=current;
                break;
            }
            else {
                for (Configuration nbr : current.getNeighbors()) {
                    counter++;
                    if (!predecessors.containsKey(nbr)) {
                        predecessors.put(nbr, current);
                        queue.add(nbr);
                        uniqueCounter++;
                    }
                }
            }
        }
        return constructPath(predecessors, start, finish);
    }

    /**
     * Method to return a path from the starting to finishing node.
     *
     * @param predecessors Map used to reconstruct the path
     * @param start starting configuration
     * @param finish finishing configuration
     * @return a list containing the sequence of configurations  comprising the path.
     * An empty list if no path exists.
     */
    private List<Configuration> constructPath(Map<Configuration,Configuration> predecessors,
                                              Configuration start, Configuration finish) {
        List<Configuration> path = new LinkedList<>();
        if(predecessors.containsKey(finish)) {
            Configuration curr = finish;
            while (curr != start) {
                path.add(0, curr);
                curr = predecessors.get(curr);
            }
            path.add(0, start);
        }
        return path;
    }

    /**
     * @return the number of configurations
     */
    public int getCounter()
    {
        return counter;
    }

    /**
     * @return the number of unique Configurations
     */
    public int getUniqueCounter()
    {
        return uniqueCounter;
    }
}
