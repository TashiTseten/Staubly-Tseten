package strings;

import solver.Configuration;
import solver.Solver;

import java.util.*;

public class StringsConfiguration implements Configuration {
    private String start;
    private String finish;
    private String current;

    /**
     * constructor for StringsConfiguration
     * @param start first String in the puzzle
     * @param finish last String in the puzzle
     */
    public StringsConfiguration(String start, String finish)
    {
        this.start = start;
        this.finish = finish;
        this.current = start;
    }
    /**
     * constructor for StringsConfiguration
     * @param start first String in the puzzle
     * @param finish last String in the puzzle
     * @param current the current String it is at
     */
    public StringsConfiguration(String start, String finish, String current)
    {
        this.start = start;
        this.finish = finish;
        this.current = current;
    }

    /**
     * @return a true or false whether the current String is the goal
     */
    @Override
    public boolean isSolution() {
        return current.equals(finish);
    }

    /**
     * @return a collection of neighboring configurations
     */
    public Collection<Configuration> getNeighbors()
    {
        HashSet<Configuration> set = new LinkedHashSet<>();
        char charBefore;
        char charAfter;
        for(int i = 0;i<current.length();i++)
        {
            char charCurr = current.charAt(i);
            if(charCurr=='A')
            {
                charBefore= 'Z';
                charAfter = 'B';
            }
            else if(charCurr=='Z')
            {
                charBefore = 'Y';
                charAfter = 'A';
            }
            else {
                charBefore = (char) (charCurr - 1);
                charAfter = (char) (charCurr + 1);
            }
            set.add(new StringsConfiguration(start, finish, current.substring(0,i) + charBefore + current.substring(i+1)));
            set.add(new StringsConfiguration(start, finish, current.substring(0,i) + charAfter + current.substring(i+1)));
        }
        return set;
    }

    /**
     * @param o type objects, probably type StringsConfiguration
     * @return returns whether or not this.current is equal
     * to the other object's current
     */
    public boolean equals(Object o)
    {
        if(o instanceof StringsConfiguration)
        {
            StringsConfiguration other = (StringsConfiguration)o;
            return this.current.equals(other.current);
        }
        return false;
    }

    /**
     * @return an int representing the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(start, finish, current);
    }

    /**
     * @return a String that represents current
     */
    @Override
    public String toString()
    {
        return current;
    }

}
