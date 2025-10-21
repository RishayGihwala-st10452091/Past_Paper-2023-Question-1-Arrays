/* means this class agrees to follow the interface’s contract,
   so it must write code for all the methods listed in the interface. */
public class EstateAgent implements IEstateAgent {

    // This method prints everything
    public void generateEstateAgentSalesReport() {

        // 1D Array to store the estate agent names.
                             // 0           1
        String[] agents = {"Joe Bloggs", "Jane Doe"};

        // 2D Array ( rows and columns )
        // Each row belongs to one agent
        // Each column is a months sale
        double[][] propertySales = {
                // 3 Columns
                // Jan  ,  Feb   ,  Mar
                {800000, 1500000, 2000000}, // Joe Bloggs
                {700000, 1200000, 1600000}  // Jane Doe          // 2 Rows
        };

        // 1D Array store the months names
        String[] months = {"JAN", "FEB", "MAR"};

        // Outputs the title of the report with a blank line below
        System.out.println("Estate Agent Sales Report");
        System.out.println();

        // Adds space before the first month
        System.out.print("            ");
        // Loops through each month name and prints it
        for (String month : months) {
            System.out.print(month + "    ");
        }
        System.out.println();

        // Made a new array to hold the total sales per agent.
        // the ( agents.length ) ensures that there is one total for each agent.
        double[] totalSalesPerAgent = new double[agents.length];

        // Nested loop to print the sales per agent

        // Outer loop (i) goes through each agent (row)
        for (int i = 0; i < agents.length; i++) {
            System.out.print(agents[i] + "   ");

            // Inner loop (j) goes through each sale for that agent (column)
            for (int j = 0; j < propertySales[i].length; j++) {
                // propertySales[i][j] accesses a single sale and (long) = neatness
                System.out.print("R " + (long) propertySales[i][j] + "   ");
            }

            // Use the interface method to calculate the total sales for that agents row.
            totalSalesPerAgent[i] = EstateAgentSales(propertySales[i]);

            // Move to next line for the next agent
            System.out.println();
        }

        System.out.println();

        // Print the totals and the 2% commission for each agent
        for (int i = 0; i < agents.length; i++) {
            System.out.println("Total property sales for " + agents[i] + " = R" + (long) totalSalesPerAgent[i]);
            // EstateAgentCommission method to calculate the 2%
            double commission = EstateAgentCommission(totalSalesPerAgent[i]);
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
