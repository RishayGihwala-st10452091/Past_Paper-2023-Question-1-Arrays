public class EstateAgent implements IEstateAgent {

    public void generateEstateAgentSalesReport() {

        String[] agents = {"Joe Bloggs", "Jane Doe"};

        double[][] propertySales = {
                {800000, 1500000, 2000000},
                {700000, 1200000, 1600000}
        };

        String[] months = {"JAN", "FEB", "MAR"};

        System.out.println("Estate Agent Sales Report");
        System.out.println();

        System.out.print("            ");
        for (String month : months) {
            System.out.print(month + "    ");
        }
        System.out.println();

        // Array to store the total sales for each agent (one value per agent)
        double[] totalSalesPerAgent = new double[agents.length];

        // Outer loop: go through each agent (each row of the 2D array)
        for (int i = 0; i < agents.length; i++) {
            System.out.print(agents[i] + "   ");

            // Inner loop: print each month's sales for this agent (each column)
            for (int j = 0; j < propertySales[i].length; j++) {
                System.out.print("R " + (long) propertySales[i][j] + "   ");
            }

            // Use the interface method to calculate the total for this agent (sum of the row)
            totalSalesPerAgent[i] = EstateAgentSales(propertySales[i]);

            // Move to next line for the next agent
            System.out.println();
        }

        System.out.println();

        // Print the totals and the 2% commission for each agent
        for (int i = 0; i < agents.length; i++) {
            System.out.println("Total property sales for " + agents[i] + " = R" + (long) totalSalesPerAgent[i]);
            double commission = EstateAgentCommission(totalSalesPerAgent[i]); // 2%
            System.out.println("Sales Commission for " + agents[i] + " = R" + (long) commission);
            System.out.println();
        }

        // Find the index (position) of the top agent and print their name
        int topIndex = TopEstateAgent(totalSalesPerAgent);
        if (topIndex >= 0) {
            System.out.println("Top performing estate agent: " + agents[topIndex]);
        } else {
            System.out.println("No agents found.");
        }
    }

    // IMPLEMENTATION of interface method:
    // Accepts a 1D array of sales (for one agent) and returns the sum
    @Override
    public double EstateAgentSales(double[] propertySales) {
        double total = 0;
        for (double sale : propertySales) {   // enhanced for-loop: step through each month's sale
            total += sale;                    // add to running total
        }
        return total;                         // return sum of the row
    }

    // IMPLEMENTATION of interface method:
    // Accepts a single double (total sales) and returns commission (2% of the total)
    @Override
    public double EstateAgentCommission(double totalSales) {
        return totalSales * 0.02;
    }

    // IMPLEMENTATION of interface method:
    // Accepts an array of totals (one per agent) and returns index of highest total
    @Override
    public int TopEstateAgent(double[] totalSales) {
        if (totalSales == null || totalSales.length == 0) {
            return -1; // defensive: no agents
        }
        int maxIndex = 0;
        for (int i = 1; i < totalSales.length; i++) {
            if (totalSales[i] > totalSales[maxIndex]) {
                maxIndex = i; // record index of new maximum
            }
        }
        return maxIndex;
    }
}
