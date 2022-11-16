package strings;

import solver.Solver;

/**
 * Main class for the strings puzzle.
 *
 * @author Tashi Tseten
 */
public class Strings {
    /**
     * Run an instance of the strings puzzle.
     *
     * @param args [0]: the starting string;
     *             [1]: the finish string.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println(("Usage: java Strings start finish"));
        } else {
            String start = args[0];
            String finish = args[1];
            StringsConfiguration config = new StringsConfiguration(start,finish);
            Solver solve = new Solver();
            solve.getShortestPath(config);
            System.out.println("Start: " + start + ", End: " + finish);
            System.out.println("Total configs: " + solve.getCounter());
            System.out.println("Unique configs: " + solve.getUniqueCounter());
            if(solve.getShortestPath(config).size()!=0)
            {
                for(int i =0;i<solve.getShortestPath(config).size();i++)
                {
                    System.out.println("Step " + i + ": " + solve.getShortestPath(config).get(i));
                }
            }
            else
                System.out.println("No solution");

        }
    }
}
