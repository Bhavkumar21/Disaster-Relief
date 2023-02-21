import java.util.*;

public class Client {
    private static Random rand = new Random();

    public static void main(String[] args) throws Exception {
        // List<Location> scenario = createRandomScenario(10, 10, 100, 1000, 100000);
        List<Location> scenario = createSimpleScenario();
        System.out.println(scenario);
        
        double budget = 2000;
        Set<Location> allocation = allocateRelief(budget, scenario);
        printResult(allocation, budget);
    }

    public static Set<Location> allocateRelief(double budget, List<Location> sites) {
        Set<Location> reliefGiven = new TreeSet<Location>();
        return allocateRelief(budget, sites, 0, 0, reliefGiven);
    }

    private static Set<Location> allocateRelief(double budget, List<Location> sites, 
                                double cost, double popSaved, Set<Location> reliefGiven) {

        if (sites.size() == 0 || cost == budget){
            return reliefGiven;
        }
        else {
            Location curr = sites.remove(sites.size() - 1);
            budget -= curr.getCost();
            cost += curr.getCost();
            popSaved += curr.getPopulation();
            reliefGiven.add(curr);
            
            allocateRelief(budget, sites, cost, popSaved, reliefGiven);
            
            budget += curr.getCost();
            cost -= curr.getCost();
            popSaved -= curr.getPopulation();
            reliefGiven.remove(curr);
            return reliefGiven;
        }
    }

    // PROVIDED HELPER METHODS - **DO NOT MODIFY ANYTHING BELOW THIS LINE!**

    public static void printResult(Set<Location> outcome, double budget) {
        System.out.println("Result: ");
        System.out.println("  " + outcome);
        System.out.println("  People saved: " + Location.countPeople(outcome));
        System.out.printf("  Cost: $%.2f\n", Location.totalCost(outcome));
        System.out.printf("  Unused budget: $%.2f\n", (budget - Location.totalCost(outcome)));
    }

    public static List<Location> createRandomScenario(int numLocs, int minPop, int maxPop, double minCostPer, double maxCostPer) {
        List<Location> result = new ArrayList<>();

        for (int i = 0; i < numLocs; i++) {
            int pop = rand.nextInt(minPop, maxPop + 1);
            double cost = rand.nextDouble(minCostPer, maxCostPer) * pop;
            result.add(new Location("Location #" + i, pop, round2(cost)));
        }

        return result;
    }

    public static List<Location> createSimpleScenario() {
        List<Location> result = new ArrayList<>();

        result.add(new Location("Location #1", 50, 500));
        result.add(new Location("Location #2", 100, 700));
        result.add(new Location("Location #3", 60, 1000));
        result.add(new Location("Location #4", 20, 1000));
        result.add(new Location("Location #5", 200, 900));

        return result;
    }    

    private static double round2(double num) {
        return Math.round(num * 100) / 100.0;
    }
}