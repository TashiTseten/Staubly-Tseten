package water;

import solver.Solver;

/**
 * Main class for the water buckets puzzle.
 *
 * @author YOUR NAME HERE
 */
public class Water {

    /**
     * Run an instance of the water buckets puzzle.
     *
     * @param args [0]: desired amount of water to be collected;
     *             [1..N]: the capacities of the N available buckets.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Water amount bucket1 bucket2 ...");
        }
        else {
            int goal = Integer.parseInt(args[0]);
            int[] list = new int[args.length-1];
            for(int i=1;i<args.length;i++)
            {
                list[i-1] = Integer.parseInt(args[i]);
            }
            WaterConfig config = new WaterConfig(goal,list);
            Solver solve = new Solver();
            solve.getShortestPath(config);
            System.out.println("Start: " + goal + ", Buckets: " + config.toString());
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
