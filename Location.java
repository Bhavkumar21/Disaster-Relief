/**
* The Location class represents a geographical location with a name, population, and cost.
* It provides methods to retrieve the population and cost of the location,
* as well as a method to generate a string representation of the object.
* Two static methods are provided to calculate the total population and cost
* of a Collection of Locations.
*/
import java.util.*;

public class Location {
    private String name;
    private int population;
    private double cost;

  /**
    * Creates a new Location object with the given name, population, and cost.
    * @param name the name of the location
    * @param pop the population of the location
    * @param cost the cost of the location
    */
    public Location(String name, int pop, double cost) {
        this.name = name;
        this.population = pop;
        this.cost = cost;
    }

    /**
    * Returns the population of the location
    * @return the population of the location
    */
    public int getPopulation() { return this.population; }

    /**
    * Returns the cost of the location
    * @return the cost of the location
    */
    public double getCost() { return this.cost; }


    /**
    * Returns a String representation of a Location object in the format:
    * "<name>: pop. <population>, cost: $<cost>"
    * @return the String representation of a Location object
    */
    public String toString() {
        return name + ": pop. " + population + ", cost: $" + cost;
    }

    // static helper methods for totaling populations or costs

    /**
    * Calculates the combined population in the given Collection
    * of Location objects
    * @param sites the Collection of Location objects
    * @return the combined population of the Location objects
    */
    public static int countPeople(Collection<Location> sites) {
        int total = 0;
        for (Location site : sites) {
            total += site.getPopulation();
        }
        return total;
    }

    /**
    * Calculates the combined cost in the given Collection
    * of Location objects
    * @param sites the Collection of Location objects
    * @return the combined cost of the Location objects
    */
    public static double totalCost(Collection<Location> sites) {
        double total = 0;
        for (Location site : sites) {
            total += site.getCost();
        }
        return total;
    }

    // Overrides of equals and hashCode to make sure Locations work with Sets

    /**
     * Compares the specified object with this location for equality. Returns true if the specified
     *  object is also a location and the two locations have the same name, population, and cost.
     * @param o objec to be compared for equality with this location
     * @return true if the specified object is equal to this location
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Location)) {
            return false;
        }
        Location otherLoc = (Location)other;

        return this.name.equals(otherLoc.name) &&
               this.population == otherLoc.population &&
               this.cost == otherLoc.cost;
    }

    /**
     * Returns the hash code value for this location
     * @return the hash code value for this location
     */
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + Integer.hashCode(population);
        result = 31 * result + Double.hashCode(cost);
        return result;
    }   
}