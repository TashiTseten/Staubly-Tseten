package water;

import solver.Configuration;

import java.util.*;

public class WaterConfig implements Configuration {
    private final int goal;
    private final int[] gallonSizes;
    private int[] current;

    /**
     * constructor for WaterConfig
     * Will make current elements equal to 0
     * @param goal sets value for goal
     * @param gallons sets values for gallons
     */
    public WaterConfig(int goal, int[] gallons)
    {
        this.goal = goal;
        this.gallonSizes = gallons;
        this.current = new int[gallons.length];
        Arrays.fill(current, 0);
    }

    /**
     *
     * @param goal sets value for goal
     * @param gallons sets values for gallons
     * @param current sets values for current
     */
    public WaterConfig(int goal, int[] gallons,int[] current)
    {
        this.goal = goal;
        this.gallonSizes = gallons;
        this.current = new int[gallons.length];
        for(int i=0;i<current.length;i++)
        {
            this.current[i] = current[i];
        }
    }

    /**
     * checks to see if the goal is in the current list
     * @return true or false if the solution was found
     */
    @Override
    public boolean isSolution() {
        for(int i = 0;i<current.length;i++)
        {
            if(current[i]==goal)
                return true;
        }
        return false;
    }

    /**
     * finds all possible values for current
     * @return a collection of configurations
     */
    @Override
    public Collection<Configuration> getNeighbors() {
        HashSet<Configuration> set = new LinkedHashSet<>();

        //current lengths and gallonSizes length are same
        for(int i = 0;i<current.length;i++)
        {
            //This empties each bucket one at a time
            int holder = current[i];
            current[i] = 0;
            set.add(new WaterConfig(goal,gallonSizes,current));
            current[i] = holder;
            //filling it
            holder = current[i];
            current[i] = gallonSizes[i];
            set.add(new WaterConfig(goal,gallonSizes,current));
            current[i] = holder;
            //pouring buckets into each other
            for(int j = 0;j<current.length;j++)
            {
                if(i!=j)
                {
                    if(current[i]+current[j]>=gallonSizes[j])
                    {
                        int holder1 = current[i];
                        int holder2 = current[j];
                        current[i] = current[i]-(gallonSizes[j]-current[j]);
                        current[j] = gallonSizes[j];
                        set.add(new WaterConfig(goal,gallonSizes,current));
                        current[i] = holder1;
                        current[j] = holder2;
                    }
                    else if(current[i]+current[j]<gallonSizes[j])
                    {
                        int holder1 = current[i];
                        int holder2 = current[j];
                        current[j] = current[i] + current[j];
                        current[i] = 0;
                        set.add(new WaterConfig(goal,gallonSizes,current));
                        current[i] = holder1;
                        current[j] = holder2;
                    }
                }
            }
        }
        return set;
    }

    /**
     * checks to see if the two objects are equal
     * @param other a different object, most likely type WaterConfig
     * @return a true or false if the currents are equal
     */
    @Override
    public boolean equals(Object other)
    {
        if(other instanceof WaterConfig)
        {
            WaterConfig otherConfig = (WaterConfig)other;
            for(int i = 0;i<current.length;i++)
            {
                if(current[i]!=otherConfig.current[i])
                    return false;
            }
        }
        return true;
    }

    /**
     * @return an int representing the hash code
     */
    @Override
    public int hashCode()
    {
        int output = 0;
        for(int i = 0;i<current.length;i++)
        {
            output+= Objects.hash(current[i]);
        }
    return output;
    }

    /**
     * @return a string representing the elements in current
     */
    @Override
    public String toString()
    {
        String output = "[";
        for(int i = 0;i<current.length;i++)
        {
            if(i!=0)
                output += "," + current[i];
            else
                output+=current[i];
        }
        return output + "]";
    }
}
