/*
  An interface is like a contract it lists the method names that a class must include,
  but does not say how they work
 */
public interface IEstateAgent {

    /* Rule: any class that implements this interface will have to have a method that
        accepts a 1D array of property sales and returns a total (double value) */
    double EstateAgentSales(double[] propertySales);

    // Must have a method that takes one double value (the total sales) and returns the commission.
    double EstateAgentCommission(double propertySales);

    // Must have a method that finds which agent (index number) had the highest sales.
    int TopEstateAgent(double[] totalSales);

}
